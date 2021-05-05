package com.example.mymentoapp.util;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.mymentoapp.data.SpecificCourseDao;
import com.example.mymentoapp.data.StudentDao;
import com.example.mymentoapp.data.TutorDao;
import com.example.mymentoapp.model.SpecificCourse;
import com.example.mymentoapp.model.Student;
import com.example.mymentoapp.model.StudentWithCourse;
import com.example.mymentoapp.model.Tutor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Student.class, Tutor.class, SpecificCourse.class}, version = 1, exportSchema = false)
public abstract class MyRoomDatabase extends RoomDatabase {
    public abstract StudentDao studentDao();
    public abstract TutorDao tutorDao();
    public abstract SpecificCourseDao specificCourseDao();

    public static final int NUMBER_OF_THREADS = 4;

    private static volatile MyRoomDatabase INSTANCE;
    public static final ExecutorService databaseWriteExecutor
            = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static MyRoomDatabase getDatabase(final Context context){

        if(INSTANCE == null){
            synchronized (MyRoomDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MyRoomDatabase.class, "my_database").addCallback(sRoomDatabaseCallback).build();
                }
            }
        }
        return INSTANCE;
    }


    private static final RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){
                @Override
                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                    super.onCreate(db);
                    databaseWriteExecutor.execute(() ->{
                        StudentDao studentDao = INSTANCE.studentDao();
                        studentDao.deleteAll();

//                        SpecificCourse specificCourse =  new SpecificCourse("OOP", "Cel mai curs");
//                        SpecificCourse specificCourse2 =  new SpecificCourse("BD", "Curs sustinut la baze de date");


//                        Student student = new Student("Maria", "Florea", "II",
//                                "Informatics", "0748848099", "florea@gmail.com",
//                                "maria22", "ooaoa");
//                        studentDao.insertStudent(student);

                        TutorDao tutorDao = INSTANCE.tutorDao();
                        tutorDao.deleteAll();

                        SpecificCourseDao specificCourseDao = INSTANCE.specificCourseDao();
                        specificCourseDao.deleteAll();


                    });
                }
            };



}

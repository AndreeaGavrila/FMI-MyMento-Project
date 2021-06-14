package com.example.mymentoapp.util;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.mymentoapp.data.CourseToTeachDao;
import com.example.mymentoapp.data.RatingStudentDao;
import com.example.mymentoapp.data.NotificationDao;
import com.example.mymentoapp.data.SpecificCourseDao;
import com.example.mymentoapp.data.StudentDao;
import com.example.mymentoapp.data.StudentNotificationDao;
import com.example.mymentoapp.data.TaughtCourseDao;
import com.example.mymentoapp.data.TutorDao;
import com.example.mymentoapp.model.CourseToTeach;
import com.example.mymentoapp.model.RatingStudent;
import com.example.mymentoapp.model.Notification;
import com.example.mymentoapp.model.SpecificCourse;
import com.example.mymentoapp.model.Student;
import com.example.mymentoapp.model.StudentNotification;
import com.example.mymentoapp.model.TaughtCourse;
import com.example.mymentoapp.model.Tutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Student.class, Tutor.class, SpecificCourse.class, CourseToTeach.class, TaughtCourse.class, RatingStudent.class,
        Notification.class, StudentNotification.class}, version = 1, exportSchema = false)
public abstract class MyRoomDatabase extends RoomDatabase {

    public abstract StudentDao studentDao();
    public abstract TutorDao tutorDao();
    public abstract SpecificCourseDao specificCourseDao();
    public abstract CourseToTeachDao courseToTeachDao();
    public abstract TaughtCourseDao taughtCourseDao();
    public abstract RatingStudentDao ratingStudentDao();
    public abstract NotificationDao notificationDao();
    public abstract StudentNotificationDao studentNotificationDao();

    public static final int NUMBER_OF_THREADS = 10;

    private static volatile MyRoomDatabase INSTANCE;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

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

                        TutorDao tutorDao = INSTANCE.tutorDao();
                        tutorDao.deleteAll();

                        SpecificCourseDao specificCourseDao = INSTANCE.specificCourseDao();
                        specificCourseDao.deleteAll();

                        CourseToTeachDao courseToTeachDao = INSTANCE.courseToTeachDao();
                        courseToTeachDao.deleteAll();

                        TaughtCourseDao taughtCourseDao = INSTANCE.taughtCourseDao();
                        taughtCourseDao.deleteAll();

                        RatingStudentDao ratingStudentDao = INSTANCE.ratingStudentDao();
                        ratingStudentDao.deleteAll();

                        NotificationDao notificationDao = INSTANCE.notificationDao();
                        notificationDao.deleteAll();

                        StudentNotificationDao studentNotificationDao = INSTANCE.studentNotificationDao();
                        studentNotificationDao.deleteAll();

                    });
                }
            };



}

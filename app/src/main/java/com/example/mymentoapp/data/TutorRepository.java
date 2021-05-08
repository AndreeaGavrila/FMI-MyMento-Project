package com.example.mymentoapp.data;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.mymentoapp.model.CourseToTeach;
import com.example.mymentoapp.model.SpecificCourse;
import com.example.mymentoapp.model.Student;
import com.example.mymentoapp.model.StudentWithCourse;
import com.example.mymentoapp.model.Tutor;
import com.example.mymentoapp.model.TutorWithCourse;
import com.example.mymentoapp.util.MyRoomDatabase;

import java.util.List;

public class TutorRepository {
    private TutorDao tutorDao;
    private LiveData<List<Tutor>> allTutors;
    private StudentDao studentDao;
    private LiveData<List<Student>> allStudents;

    public TutorRepository(Application application){
        MyRoomDatabase db = MyRoomDatabase.getDatabase(application);
        tutorDao = db.tutorDao();
        allTutors = tutorDao.getAllTutors();
        studentDao = db.studentDao();
        allStudents = studentDao.getAllStudents();
    }
    public LiveData<List<Tutor>> getAllData(){
        return allTutors;
    }

    public void insertTutor(Tutor tutor){
//        MyRoomDatabase.databaseWriteExecutor.execute(()->{
//            studentDao.insertStudent(tutor);
//        });

        MyRoomDatabase.databaseWriteExecutor.execute(()->{
            tutorDao.insertTutor(tutor);
        });

    }

//    public void insertTutorWithCourses(TutorWithCourse tutorWithCourse) {
//        new TutorRepository.insertAsync(tutorDao).execute(tutorWithCourse);
//    }


//    private static class insertAsync extends AsyncTask<TutorWithCourse, Void, Void> {
//        private TutorDao tutorDaoAsync;
//
//        insertAsync(TutorDao tutorDao){ tutorDaoAsync = tutorDao;}
//        @Override
//        protected Void doInBackground(TutorWithCourse... tutorWithCourses) {
//
//            long identifier = tutorDaoAsync.insertTutor(tutorWithCourses[0].tutor);
//
//            for (CourseToTeach courseToTeach : tutorWithCourses[0].courseToTeach) {
//                courseToTeach.setId_FkTutor(identifier);
//            }
//            tutorDaoAsync.insertToTeachCourses(tutorWithCourses[0].courseToTeach);
//            return null;
//        }
//    }

    public void deleteAll(){
        MyRoomDatabase.databaseWriteExecutor.execute(()->{
            tutorDao.deleteAll();
        });
    }





}

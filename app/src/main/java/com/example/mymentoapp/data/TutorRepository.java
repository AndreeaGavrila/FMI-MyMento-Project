package com.example.mymentoapp.data;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.mymentoapp.model.CourseToTeach;
import com.example.mymentoapp.model.Notification;
import com.example.mymentoapp.model.SpecificCourse;
import com.example.mymentoapp.model.Student;
import com.example.mymentoapp.model.StudentWithCourse;
import com.example.mymentoapp.model.Tutor;
import com.example.mymentoapp.model.TutorWithCourse;
import com.example.mymentoapp.model.TutorWithNotifications;
import com.example.mymentoapp.util.MyRoomDatabase;

import java.util.List;

public class TutorRepository {
    private final TutorDao tutorDao;
    private final List<Tutor> allTutors;
    private StudentDao studentDao;
    private List<Student> allStudents;

    public TutorRepository(Application application){
        MyRoomDatabase db = MyRoomDatabase.getDatabase(application);
        tutorDao = db.tutorDao();
        allTutors = tutorDao.getAllTutors();
        studentDao = db.studentDao();
        allStudents = studentDao.getAllStudents();
    }
    public List<Tutor> getAllData(){
        return allTutors;
    }


    public void insertTutor(Tutor tutor){
        MyRoomDatabase.databaseWriteExecutor.execute(()->{
            tutorDao.insertTutor(tutor);
        });

    }

    public void insertTutorWithCourses(TutorWithCourse tutorWithCourse) {
        new TutorRepository.insertAsync(tutorDao).execute(tutorWithCourse);
    }


    private static class insertAsync extends AsyncTask<TutorWithCourse, Void, Void> {
        private final TutorDao tutorDaoAsync;

        insertAsync(TutorDao tutorDao){ tutorDaoAsync = tutorDao;}
        @Override
        protected Void doInBackground(TutorWithCourse... tutorWithCourses) {

            long identifier = tutorDaoAsync.insertTutor(tutorWithCourses[0].getTutor());
            System.out.println("In insert cursuri de predat");
            for (CourseToTeach c : tutorWithCourses[0].getCourseToTeach())
                System.out.println(c.getCourseName());

            for (CourseToTeach courseToTeach : tutorWithCourses[0].getCourseToTeach()) {
                courseToTeach.setId_FkTutor(identifier);
            }
            tutorDaoAsync.insertToTeachCourses(tutorWithCourses[0].getCourseToTeach());
            return null;
        }
    }


    public void insertTutorWithNotifications(TutorWithNotifications tutorWithNotification) {
        new TutorRepository.insertAsync2(tutorDao).execute(tutorWithNotification);
    }


    private static class insertAsync2 extends AsyncTask<TutorWithNotifications, Void, Void> {
        private final TutorDao tutorDaoAsync;

        insertAsync2(TutorDao tutorDao){ tutorDaoAsync = tutorDao;}
        @Override
        protected Void doInBackground(TutorWithNotifications... tutorWithNotifications) {

            long identifier = tutorDaoAsync.insertTutor(tutorWithNotifications[0].getTutor());
            System.out.println("In insert notifications");

            for (Notification notification : tutorWithNotifications[0].getNotifications()) {
                notification.setId_FkTutor(identifier);
            }
            tutorDaoAsync.insertNotifications(tutorWithNotifications[0].getNotifications());
            return null;
        }
    }


    public void deleteAll(){
        MyRoomDatabase.databaseWriteExecutor.execute(()->{
            tutorDao.deleteAll();
        });
    }

    public Tutor getTutorByUsername(String inputUsername){
        return tutorDao.getTutorByUserName(inputUsername);
    }
    public Tutor getTutor(int id){
        return tutorDao.getTutor(id);
    }

    public void updateTutor(Tutor tutor){
        MyRoomDatabase.databaseWriteExecutor.execute(() -> {
            tutorDao.updateTutor(tutor);
        });
    }

    public Tutor getTutorByName(String lastName, String firstName){
        return tutorDao.getTutorByName(lastName, firstName);
    }


}

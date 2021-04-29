package com.example.mymentoapp.data;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.mymentoapp.model.SpecificCourse;
import com.example.mymentoapp.model.Student;
import com.example.mymentoapp.model.StudentWithCourse;
import com.example.mymentoapp.util.MyRoomDatabase;

import java.util.List;

public class StudentRepository {

    private StudentDao studentDao;
    private LiveData<List<Student>> allStudents;

    public StudentRepository(Application application){
        MyRoomDatabase db = MyRoomDatabase.getDatabase(application);
        studentDao = db.studentDao();
        allStudents = studentDao.getAllStudents();
    }
    public LiveData<List<Student>> getAllData(){
        return allStudents;
    }

    public void insertStudent(Student student){
        MyRoomDatabase.databaseWriteExecutor.execute(()->{
            studentDao.insertStudent(student);
        });
    }

    public void insertStudentWithCourses(StudentWithCourse studentWithCourse) {
        new insertAsync(studentDao).execute(studentWithCourse);
    }

    private static class insertAsync extends AsyncTask<StudentWithCourse, Void, Void> {
        private StudentDao studentDaoAsync;

        insertAsync(StudentDao studentDao) {
            studentDaoAsync = studentDao;
        }

        @Override
        protected Void doInBackground(StudentWithCourse... studentWithCourses) {

            long identifier = studentDaoAsync.insertStudent(studentWithCourses[0].student);

            for (SpecificCourse specificCourse : studentWithCourses[0].specificCourses) {
                specificCourse.setId_FkStudent(identifier);
            }
            studentDaoAsync.insertSpecificCourses(studentWithCourses[0].specificCourses);
            return null;
        }
    }


}

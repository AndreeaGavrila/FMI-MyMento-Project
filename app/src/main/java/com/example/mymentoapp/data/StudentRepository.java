package com.example.mymentoapp.data;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.loader.content.AsyncTaskLoader;
import androidx.room.OnConflictStrategy;

import com.example.mymentoapp.model.CourseToTeach;
import com.example.mymentoapp.model.SpecificCourse;
import com.example.mymentoapp.model.Student;
import com.example.mymentoapp.model.StudentWithCourse;
import com.example.mymentoapp.model.StudentWithTaughtCourses;
import com.example.mymentoapp.model.Tutor;
import com.example.mymentoapp.util.MyRoomDatabase;

import java.util.List;

public class StudentRepository {

    private StudentDao studentDao;
    private TutorDao tutorDao;
    private LiveData<List<Student>> allStudents;
    private LiveData<List<StudentWithTaughtCourses>> studentWithTaughtCourses;

    public StudentRepository(Application application){
        MyRoomDatabase db = MyRoomDatabase.getDatabase(application);
        studentDao = db.studentDao();
        tutorDao = db.tutorDao();
        allStudents = studentDao.getAllStudents();
        studentWithTaughtCourses = studentDao.getAllStudentsWithTaughtCourses();
    }
    public LiveData<List<Student>> getAllData(){
        return allStudents;
    }

    public LiveData<List<StudentWithTaughtCourses>> getStudentWithTaughtCourses(){
        return studentWithTaughtCourses;
    }

    public void insertStudent(Student student){
        MyRoomDatabase.databaseWriteExecutor.execute(()->{
            studentDao.insertStudent(student);
        });
    }

    public void insertStudentWithCourses(StudentWithCourse studentWithCourse) {
        new insertAsync(studentDao).execute(studentWithCourse);
    }

    public void deleteAll(){
        MyRoomDatabase.databaseWriteExecutor.execute(()->{
            studentDao.deleteAll();
        });
    }

    private static class insertAsync extends AsyncTask<StudentWithCourse, Void, Void> {
        private StudentDao studentDaoAsync;

        insertAsync(StudentDao studentDao) {
            studentDaoAsync = studentDao;
        }
        @Override
        protected Void doInBackground(StudentWithCourse... studentWithCourses) {

            long identifier = studentDaoAsync.insertStudent(studentWithCourses[0].getStudent());
            System.out.println("identifier" +identifier);

            // TODO: 06.05.2021  e ok pt ca un student o sa fie adaugat direct cu cursurile lui
            for (SpecificCourse specificCourse : studentWithCourses[0].getSpecificCourses()) {
                specificCourse.setId_FkStudent(identifier);
            }
            studentDaoAsync.insertSpecificCourses(studentWithCourses[0].getSpecificCourses());
            return null;
        }
    }



}

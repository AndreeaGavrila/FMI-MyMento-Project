package com.example.mymentoapp.data;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.mymentoapp.model.SpecificCourse;
import com.example.mymentoapp.model.Student;
import com.example.mymentoapp.model.StudentWithCourse;
import com.example.mymentoapp.model.StudentWithTaughtCourses;
import com.example.mymentoapp.model.TaughtCourse;
import com.example.mymentoapp.model.Tutor;
import com.example.mymentoapp.util.MyRoomDatabase;

import java.util.List;

public class StudentRepository {

    private StudentDao studentDao;
    private TutorDao tutorDao;
    private List<Student> allStudents;

    public StudentRepository(Application application){
        MyRoomDatabase db = MyRoomDatabase.getDatabase(application);
        studentDao = db.studentDao();
        tutorDao = db.tutorDao();
        allStudents = studentDao.getAllStudents();
    }
    public List<Student> getAllData(){
        return allStudents;
    }

    public void insertStudent(Student student){
        MyRoomDatabase.databaseWriteExecutor.execute(()->{
            studentDao.insertStudent(student);
        });
    }

    public Student getStudentByUsernameAndPassword(String usernameInput, String passwordInput){
        return studentDao.getStudentByUsernameAndPassword(usernameInput, passwordInput);
    }
    public Student getStudentByUsername(String usernameInput){
        return studentDao.getStudentByUsername(usernameInput);
    }

    public void updateStudent(Student student){
        MyRoomDatabase.databaseWriteExecutor.execute(()->{
            studentDao.updateStudent(student);
        });
    }


    public Student getStudent(int id){
        return studentDao.getStudent(id);
    }

//    public void deleteSpecificCourse(int idFkinput){
//        MyRoomDatabase.databaseWriteExecutor.execute(()->{
//            studentDao.deleteSpecificCourses(idFkinput);
//        });
//    }

    public void deleteAll(){
        MyRoomDatabase.databaseWriteExecutor.execute(()->{
            studentDao.deleteAll();
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

            long identifier = studentDaoAsync.insertStudent(studentWithCourses[0].getStudent());

            // TODO: 06.05.2021  e ok pt ca un student o sa fie adaugat direct cu cursurile lui
            for (SpecificCourse specificCourse : studentWithCourses[0].getSpecificCourses()) {
                specificCourse.setId_FkStudent(identifier);
            }
            studentDaoAsync.insertSpecificCourses(studentWithCourses[0].getSpecificCourses());
            return null;
        }

    }

    public void insertStudentWithTaughtCourses(StudentWithTaughtCourses studentWithTaughtCourse) {
        new insertAsync2(studentDao).execute(studentWithTaughtCourse);
    }


    private static class insertAsync2 extends AsyncTask<StudentWithTaughtCourses, Void, Void> {
        private StudentDao studentDaoAsync2;

        insertAsync2(StudentDao studentDao) {
            studentDaoAsync2 = studentDao;
        }

        @Override
        protected Void doInBackground(StudentWithTaughtCourses... studentWithTaughtCourses) {

            long identifier = studentDaoAsync2.getStudentData(studentWithTaughtCourses[0].getStudent().getIdStudent());

            // TODO: 06.05.2021  e ok pt ca un student o sa fie adaugat direct cu cursurile lui
            for (TaughtCourse taughtCourse : studentWithTaughtCourses[0].getTaughtCourses()) {
               taughtCourse.setId_FkStudent(identifier);
            }
            studentDaoAsync2.insertTaughtCourses(studentWithTaughtCourses[0].getTaughtCourses());
            return null;
        }
    }


}

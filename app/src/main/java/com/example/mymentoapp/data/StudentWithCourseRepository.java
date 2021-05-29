//package com.example.mymentoapp.data;
//
//import android.app.Application;
//
//import com.example.mymentoapp.model.StudentWithCourse;
//import com.example.mymentoapp.util.MyRoomDatabase;
//
//import java.util.ArrayList;
//
//public class StudentWithCourseRepository {
//    private StudentWithCourseDao studentWithCourseDao;
//    private ArrayList<StudentWithCourse> studentWithCourses;
//
//    public StudentWithCourseRepository(Application application){
//        MyRoomDatabase db = MyRoomDatabase.getDatabase(application);
//        studentWithCourseDao = db.studentWithCourseDao();
//    }
//
//    public void updateStudentWithCourse(StudentWithCourse studentWithCourse){
//        MyRoomDatabase.databaseWriteExecutor.execute(()->{
//            studentWithCourseDao.updateStudentWithCourse(studentWithCourse);
//        });
//    }
//}

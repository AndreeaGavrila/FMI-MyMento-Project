package com.example.mymentoapp.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.mymentoapp.model.CourseToTeach;
import com.example.mymentoapp.model.SpecificCourse;
import com.example.mymentoapp.model.Student;
import com.example.mymentoapp.util.MyRoomDatabase;

import java.util.List;

public class CourseToTeachRepository {

    private CourseToTeachDao courseToTeachDao;

    private LiveData<List<CourseToTeach>> allCoursesToTeach;

    public CourseToTeachRepository(Application application){
        MyRoomDatabase db = MyRoomDatabase.getDatabase(application);
        courseToTeachDao = db.courseToTeachDao();
        allCoursesToTeach = courseToTeachDao.getAllToTeachCourses();


    }
    public LiveData<List<CourseToTeach>> getAllData(){
        return allCoursesToTeach;
    }

    public void insert(CourseToTeach courseToTeach){
        MyRoomDatabase.databaseWriteExecutor.execute(()->{
            courseToTeachDao.insertCourseToTeach(courseToTeach);
        });
    }
//    public void update(){
//        MyRoomDatabase.databaseWriteExecutor.execute(()->{
//            courseToTeachDao.updateSpecificCourses();
//        });
//    }
    public void deleteAll(){
        MyRoomDatabase.databaseWriteExecutor.execute(()->{
            courseToTeachDao.deleteAll();
        });
    }

    public List<CourseToTeach> getAllSpecificCourses(int id){
        return courseToTeachDao.getAllSpecificCoursesForTutor(id);
    }

}

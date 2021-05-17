package com.example.mymentoapp.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.mymentoapp.model.CourseToTeach;
import com.example.mymentoapp.model.SpecificCourse;

import java.util.List;

@Dao
public interface CourseToTeachDao {
    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCourseToTeach(CourseToTeach courseToTeach);

    @Query("DELETE FROM course_to_teach")
    void deleteAll();

    @Query("SELECT * FROM course_to_teach")
    LiveData<List<CourseToTeach>> getAllToTeachCourses();
    @Query("SELECT * FROM course_to_teach WHERE id_FkTutor=:idInput")
    List<CourseToTeach> getAllSpecificCoursesForTutor(int idInput);



//    @Query("DELETE FROM course_to_teach WHERE id_FkTutor = :id")
//    void deleteCourseToTeach(int id);


//    @Update
//    void updateSpecificCourses(SpecificCourse... specificCourses);
}

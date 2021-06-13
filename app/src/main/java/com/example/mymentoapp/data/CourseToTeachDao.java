package com.example.mymentoapp.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.mymentoapp.model.CourseToTeach;

import java.util.List;

@Dao
public interface CourseToTeachDao {
    @Transaction
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertCourseToTeach(CourseToTeach courseToTeach);

    @Query("DELETE FROM course_to_teach")
    void deleteAll();

    @Query("SELECT * FROM course_to_teach WHERE idCourseToTeach=:idCourse")
    CourseToTeach getCourseById(int idCourse);

    @Query("SELECT * FROM course_to_teach")
    List<CourseToTeach> getAllToTeachCourses();

    @Query("SELECT * FROM course_to_teach WHERE id_FkTutor=:idInput")
    List<CourseToTeach> getAllSpecificCoursesForTutor(int idInput);

    @Query("SELECT * FROM course_to_teach WHERE courseName=:courseName")
    List<CourseToTeach> getAllCoursesForSpecificCourse(String courseName);

    @Query("SELECT * FROM course_to_teach WHERE id_FkTutor !=:idInput")
    List<CourseToTeach> getAllCoursesWithout(int idInput);

    @Query("DELETE FROM course_to_teach WHERE id_FkTutor = :id")
    void deleteCourseToTeach(int id);

//    @Update
//    void updateSpecificCourses(SpecificCourse... specificCourses);
}

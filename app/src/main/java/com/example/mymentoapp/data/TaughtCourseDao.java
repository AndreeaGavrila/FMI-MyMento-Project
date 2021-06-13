package com.example.mymentoapp.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.mymentoapp.model.TaughtCourse;

import java.util.List;

@Dao
public interface TaughtCourseDao {
    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTaughtCourse(TaughtCourse taughtCourse);

    @Query("DELETE FROM taught_course")
    void deleteAll();

    @Query("SELECT * FROM taught_course")
    List<TaughtCourse> getAllTaughtCourses();

    @Query("SELECT * FROM taught_course WHERE id_FkStudent=:idInput")
    List<TaughtCourse> getAllTaughtCoursesForStudent(int idInput);

    @Query("SELECT * FROM taught_course WHERE id_FkTutor=:idInput")
    List<TaughtCourse> getAllTaughtCoursesForTutor(int idInput);

    @Query("DELETE FROM taught_course WHERE id_FkStudent = :id")
    void deleteTaughtCourses(int id);

    @Update
    void updateTaughtCourses(TaughtCourse... taughtCourses);

}

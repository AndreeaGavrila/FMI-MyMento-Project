package com.example.mymentoapp.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.mymentoapp.model.SpecificCourse;
import com.example.mymentoapp.model.Student;

import java.util.List;

@Dao
public interface SpecificCourseDao {

    @Transaction
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertSpecificCourse(SpecificCourse specificCourse);

    @Query("DELETE FROM specific_course")
    void deleteAll();

    @Query("SELECT * FROM specific_course")
    LiveData<List<SpecificCourse>> getAllSpecificCourses();
}

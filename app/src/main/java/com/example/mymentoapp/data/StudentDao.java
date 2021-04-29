package com.example.mymentoapp.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.mymentoapp.model.SpecificCourse;
import com.example.mymentoapp.model.Student;

import java.util.List;

@Dao
public interface StudentDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insertStudent(Student student);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertSpecificCourses(List<SpecificCourse> specificCourses);

    @Query("DELETE FROM student_table")
    void deleteAll();

    @Query("SELECT * FROM student_table")
    LiveData<List<Student>> getAllStudents();

}

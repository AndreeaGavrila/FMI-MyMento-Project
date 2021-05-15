package com.example.mymentoapp.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mymentoapp.model.SpecificCourse;
import com.example.mymentoapp.model.Student;
import com.example.mymentoapp.model.StudentWithTaughtCourses;
import com.example.mymentoapp.model.TaughtCourse;

import java.util.List;

@Dao
public interface StudentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertStudent(Student student);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSpecificCourses(List<SpecificCourse> specificCourses);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTaughtCourses(List<TaughtCourse> taughtCourses);

    @Query("DELETE FROM student_table")
    void deleteAll();

    @Query("SELECT * FROM student_table")
    LiveData<List<Student>> getAllStudents();

    @Query("SELECT * FROM student_table")
    LiveData<List<StudentWithTaughtCourses>> getAllStudentsWithTaughtCourses();

    @Update
    void updateStudents(Student... students);



}

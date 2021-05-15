package com.example.mymentoapp.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.mymentoapp.model.Student;
import com.example.mymentoapp.model.StudentWithTaughtCourses;
import com.example.mymentoapp.model.TaughtCourseStudentCross;

import java.util.List;

@Dao
public interface StudentTaughtCoursesDao {

    @Query("SELECT * FROM student_taught_courses")
    LiveData<List<StudentWithTaughtCourses>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertStudent(Student student);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertStudentTaughtCourses(TaughtCourseStudentCross studentCross);

    @Query("DELETE FROM student_taught_courses")
    void deleteAll();


}

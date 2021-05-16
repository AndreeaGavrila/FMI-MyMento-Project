package com.example.mymentoapp.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mymentoapp.model.SpecificCourse;
import com.example.mymentoapp.model.Student;

import java.util.List;

@Dao
public interface StudentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertStudent(Student student);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSpecificCourses(List<SpecificCourse> specificCourses);

    @Query("DELETE FROM student_table")
    void deleteAll();

    @Query("SELECT * FROM student_table")
    LiveData<List<Student>> getAllStudents();

    @Query("SELECT * FROM student_table where username=:usernameInput and password=:passwordInput")
    Student getStudentByUsernameAndPassword(String usernameInput, String passwordInput);
//
//    @Query("SELECT * FROM student_table where username=:usernameInput and password=:passwordInput")
//    Student getStudentByUsernameAndPassword(String usernameInput, String passwordInput);


    @Query("SELECT * FROM student_table where username=:usernameInput and password=:passwordInput")
    Student getStudentByUsernameAndPassword2(String usernameInput, String passwordInput);

    @Query("SELECT * FROM student_table WHERE idStudent=:studentIdInput")
    Student getStudent(int studentIdInput);

    @Update
    void updateStudents(Student... students);

    @Update
    void updateStudent(Student student);

    @Insert
    void registerStudent(Student student);

    @Query("SELECT * FROM student_table where username=:usernameInput")
    Student getStudentByUsername(String usernameInput);
}

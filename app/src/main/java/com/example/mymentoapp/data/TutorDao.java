package com.example.mymentoapp.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mymentoapp.model.CourseToTeach;
import com.example.mymentoapp.model.SpecificCourse;
import com.example.mymentoapp.model.Student;
import com.example.mymentoapp.model.Tutor;
import java.util.List;



@Dao
public interface TutorDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertTutor(Tutor tutor);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertToTeachCourses(List<CourseToTeach> courseToTeach);

    @Query("DELETE FROM tutor_table")
    void deleteAll();

    @Query("SELECT * FROM tutor_table WHERE username =:userName")
    public Tutor getTutorByUserName(String userName);

    @Query("SELECT * FROM tutor_table")
    public LiveData<List<Tutor>> getAllTutors();

    @Query("SELECT * FROM tutor_table WHERE idStudent=:studentIdInput")
    public Tutor getTutor(int studentIdInput);

}
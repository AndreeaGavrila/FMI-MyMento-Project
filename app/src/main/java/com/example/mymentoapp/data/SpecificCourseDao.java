package com.example.mymentoapp.data;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;
import com.example.mymentoapp.model.SpecificCourse;
import com.example.mymentoapp.model.Student;
import java.util.List;
@Dao
public interface SpecificCourseDao {

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSpecificCourse(SpecificCourse specificCourse);

    @Query("DELETE FROM specific_course")
    void deleteAll();

    @Query("SELECT * FROM specific_course")
    LiveData<List<SpecificCourse>> getAllSpecificCourses();

    @Query("SELECT * FROM specific_course WHERE id_FkStudent=:idInput")
    List<SpecificCourse> getAllSpecificCoursesForStudent(int idInput);

    @Query("DELETE FROM specific_course WHERE id_FkStudent = :id")
    void deleteSpecificCourse(int id);

    @Update
    void updateSpecificCourses(SpecificCourse... specificCourses);
}
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

    @Query("SELECT lastName || ' ' || firstName || ',' || c.courseName from " +
            "student_table s join taught_course t on s.idStudent=t.id_FkStudent " +
            "join course_to_teach c on t.idCourseToTeach=c.idCourseToTeach " +
            "where c.id_FkTutor=:idTutore")
    List<String> getStudentAndCourseByTutorId(Integer idTutore);

    @Query("SELECT lastName || ' ' || firstName || ',' || " +
            "(select count(*) from taught_course where id_FkStudent=s.idStudent) as no_attendance " +
            "from student_table s join taught_course t on s.idStudent=t.id_FkStudent " +
            "where t.id_FkTutor=:idTutore " +
            "order by no_attendance asc")
    List<String> getStudentAndAttendace(Integer idTutore);

    @Update
    void updateTaughtCourses(TaughtCourse... taughtCourses);

}

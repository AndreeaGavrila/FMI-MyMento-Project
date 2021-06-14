package com.example.mymentoapp.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.mymentoapp.model.StudentNotification;

import java.util.List;

@Dao
public interface StudentNotificationDao {

    @Transaction
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertStudentNotification(StudentNotification notification);

    @Query("DELETE FROM student_notifications")
    void deleteAll();

    @Query("DELETE FROM student_notifications WHERE idNotification=:id ")
    void deleteStudentNotification(int id);

    @Query("SELECT * FROM student_notifications")
    List<StudentNotification> getAllStudentNotifications();

    @Query("SELECT * FROM student_notifications WHERE usernameStudent=:input")
    List<StudentNotification> getAllNotificationsForStudent(String input);

    @Query("DELETE FROM student_notifications WHERE usernameStudent=:input")
    void deleteStudentNotificationForStudent(String input);

    @Update
    void updateNotification(StudentNotification studentNotification);

}

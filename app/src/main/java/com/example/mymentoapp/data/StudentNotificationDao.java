package com.example.mymentoapp.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.mymentoapp.model.Notification;
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

    @Query("SELECT * FROM student_notifications WHERE id_FkStudent=:idInput")
    List<StudentNotification> getAllNotificationsForStudent(int idInput);

    @Query("DELETE FROM student_notifications WHERE id_FkStudent = :id")
    void deleteStudentNotificationForStudent(int id);
}

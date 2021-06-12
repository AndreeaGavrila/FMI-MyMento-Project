package com.example.mymentoapp.data;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.mymentoapp.model.CourseToTeach;
import com.example.mymentoapp.model.Notification;

import java.util.List;

@Dao
public interface NotificationDao {
    @Transaction
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertNotification(Notification notification);

    @Query("DELETE FROM notifications")
    void deleteAll();

    @Query("SELECT * FROM notifications")
    List<Notification> getAllNotifications();

    @Query("SELECT * FROM notifications WHERE id_FkTutor=:idInput")
    List<Notification> getAllNotificationsForTutor(int idInput);

    @Query("DELETE FROM notifications WHERE id_FkTutor = :id")
    void deleteNotificationForTutor(int id);
}

package com.example.mymentoapp.model;

import android.app.Application;

import androidx.annotation.NonNull;

import com.example.mymentoapp.data.NotificationRepository;
import com.example.mymentoapp.data.StudentNotificationRepository;

import java.util.List;

public class StudentNotificationViewModel {

    public static StudentNotificationRepository repository;
    public List<StudentNotification> allNotifications;

    public StudentNotificationViewModel(@NonNull Application application) {
        super();
        repository = new StudentNotificationRepository(application);
        allNotifications = repository.getAllStudentNotifications();

    }

    public void deleteNotificationsForStudent(int id) {
        repository.deleteNotificationForStudent(id);
    }

    public void deleteNotification(int id){repository.deleteNotification(id);}

    public List<StudentNotification> getAllNotifications() {
        return allNotifications;
    }

    public void insert(StudentNotification notification) {
        repository.insert(notification);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public List<StudentNotification> getAllNotificationsForStudent(int id) {
        return repository.getAllNotificationsForStudent(id);
    }

}

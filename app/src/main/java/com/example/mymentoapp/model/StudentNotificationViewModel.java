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

    public void deleteNotificationsForStudent(String name) {
        repository.deleteNotificationForStudent(name);
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

    public List<StudentNotification> getAllNotificationsForStudent(String username) {
        return repository.getAllNotificationsForStudent(username);
    }

    public void updateNotification(StudentNotification studentNotification){
        repository.updateNotification(studentNotification);
    }

}

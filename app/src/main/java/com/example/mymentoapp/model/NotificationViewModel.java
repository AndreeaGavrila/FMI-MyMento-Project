package com.example.mymentoapp.model;

import android.app.Application;

import androidx.annotation.NonNull;

import com.example.mymentoapp.data.CourseToTeachRepository;
import com.example.mymentoapp.data.NotificationRepository;

import java.util.List;

public class NotificationViewModel {

    public static NotificationRepository repository;
    public List<Notification> allNotifications;


    public NotificationViewModel(@NonNull Application application) {
        super();
        repository = new NotificationRepository(application);
        allNotifications = repository.getAllNotifications();

    }

    public void deleteNotificationsForTutor(int id) {
        repository.deleteNotificationForTutor(id);
    }

    public List<Notification> getAllNotifications() {
        return allNotifications;
    }

    public void insert(Notification notification) {
        repository.insert(notification);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public List<Notification> getAllNotificationsForTutor(int id) {
        return repository.getAllNotificationsForTutor(id);
    }

}

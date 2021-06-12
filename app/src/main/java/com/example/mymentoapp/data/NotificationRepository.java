package com.example.mymentoapp.data;

import android.app.Application;


import com.example.mymentoapp.model.CourseToTeach;
import com.example.mymentoapp.model.Notification;
import com.example.mymentoapp.util.MyRoomDatabase;

import java.util.List;

public class NotificationRepository {

    private final NotificationDao notificationDao;
    private final List<Notification> allNotifications;

    public NotificationRepository(Application application){
        MyRoomDatabase db = MyRoomDatabase.getDatabase(application);
        notificationDao = db.notificationDao();
        allNotifications = notificationDao.getAllNotifications();

    }
    public List<Notification> getAllNotifications(){
        return allNotifications;
    }

    public void insert(Notification notification){
        MyRoomDatabase.databaseWriteExecutor.execute(()->{
            notificationDao.insertNotification(notification);
        });
    }


    public void deleteAll(){
        MyRoomDatabase.databaseWriteExecutor.execute(notificationDao::deleteAll);
    }
    public void deleteNotification(int id){
        MyRoomDatabase.databaseWriteExecutor.execute(()->{
            notificationDao.deleteNotification(id);
        });
    }

    public List<Notification> getAllNotificationsForTutor(int id){
        return notificationDao.getAllNotificationsForTutor(id);
    }

    public List<Notification> getAllNotificationsSentByStudent(int id){
        return notificationDao.getAllNotificationsSentByStudent(id);
    }
    public void deleteNotificationForTutor(int id){
        MyRoomDatabase.databaseWriteExecutor.execute(()->{
            notificationDao.deleteNotificationForTutor(id);
        });
    }

}

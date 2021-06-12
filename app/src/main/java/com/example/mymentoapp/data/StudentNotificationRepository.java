package com.example.mymentoapp.data;

import android.app.Application;

import com.example.mymentoapp.model.Notification;
import com.example.mymentoapp.model.StudentNotification;
import com.example.mymentoapp.util.MyRoomDatabase;

import java.util.List;

public class StudentNotificationRepository {

    private final StudentNotificationDao studentNotificationDao;
    private final List<StudentNotification> allStudentNotifications;

    public StudentNotificationRepository(Application application){
        MyRoomDatabase db = MyRoomDatabase.getDatabase(application);
        studentNotificationDao = db.studentNotificationDao();
        allStudentNotifications = studentNotificationDao.getAllStudentNotifications();

    }
    public List<StudentNotification> getAllStudentNotifications(){
        return allStudentNotifications;
    }

    public void insert(StudentNotification notification){
        MyRoomDatabase.databaseWriteExecutor.execute(()->{
           studentNotificationDao.insertStudentNotification(notification);
        });
    }


    public void deleteAll(){
        MyRoomDatabase.databaseWriteExecutor.execute(studentNotificationDao::deleteAll);
    }
    public void deleteNotification(int id){
        MyRoomDatabase.databaseWriteExecutor.execute(()->{
            studentNotificationDao.deleteStudentNotification(id);
        });
    }

    public List<StudentNotification> getAllNotificationsForStudent(int id){
        return studentNotificationDao.getAllNotificationsForStudent(id);
    }

    public void deleteNotificationForStudent(int id){
        MyRoomDatabase.databaseWriteExecutor.execute(()->{
            studentNotificationDao.deleteStudentNotificationForStudent(id);
        });
    }
}

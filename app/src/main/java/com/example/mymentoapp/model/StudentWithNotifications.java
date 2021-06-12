package com.example.mymentoapp.model;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class StudentWithNotifications {
    @Embedded
    private Student student;
    @Relation(
            parentColumn = "idStudent",
            entityColumn = "id_FkStudent"
    )
    private List<StudentNotification> notifications;


    public StudentWithNotifications(Student student, List<StudentNotification> notifications) {
        this.student = student;
        this.notifications = notifications;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<StudentNotification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<StudentNotification> notifications) {
        this.notifications = notifications;
    }
}

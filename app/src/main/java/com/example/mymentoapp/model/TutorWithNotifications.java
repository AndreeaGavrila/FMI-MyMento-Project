package com.example.mymentoapp.model;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class TutorWithNotifications {

    @Embedded
    private Tutor tutor;
    @Relation(
            parentColumn = "idStudent",
            entityColumn = "id_FkTutor"
    )
    private List<Notification> notifications;

    public TutorWithNotifications(Tutor tutor, List<Notification> notifications) {
        this.tutor = tutor;
        this.notifications = notifications;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }
}

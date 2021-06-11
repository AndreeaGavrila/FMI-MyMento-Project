package com.example.mymentoapp.model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "notifications")

public class Notification {

    @PrimaryKey(autoGenerate = true)
    private int idNotification;

    @ForeignKey
            (entity = Tutor.class,
                    parentColumns = "idStudent",
                    childColumns = "id_FkTutor",
                    onDelete = CASCADE,
                    onUpdate = CASCADE

            )
    private long id_FkTutor;

    @ColumnInfo(name = "description")
    private String description;

    @ForeignKey
            (entity = Student.class,
                    parentColumns = "idStudent",
                    childColumns = "id_FkStudent",
                    onDelete = CASCADE,
                    onUpdate = CASCADE

            )
    private long id_FkStudent;

    @ForeignKey
            (entity = CourseToTeach.class,
                    parentColumns = "idCourseToTeach",
                    childColumns = "id_FkCourseToTeach",
                    onDelete = CASCADE,
                    onUpdate = CASCADE

            )

    private long id_FkCourseToTeach;


    public Notification() {
        this.description = "You have a new notification from ";
    }

    public Notification(long id_FkTutor, String description, long id_FkStudent, long id_FkCourseToTeach) {
        this.id_FkTutor = id_FkTutor;
        this.description = description;
        this.id_FkStudent = id_FkStudent;
        this.id_FkCourseToTeach = id_FkCourseToTeach;
    }

    public int getIdNotification() {
        return idNotification;
    }

    public void setIdNotification(int idNotification) {
        this.idNotification = idNotification;
    }

    public long getId_FkTutor() {
        return id_FkTutor;
    }

    public void setId_FkTutor(long id_FkTutor) {
        this.id_FkTutor = id_FkTutor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getId_FkStudent() {
        return id_FkStudent;
    }

    public void setId_FkStudent(long id_FkStudent) {
        this.id_FkStudent = id_FkStudent;
    }

    public long getId_FkCourseToTeach() {
        return id_FkCourseToTeach;
    }

    public void setId_FkCourseToTeach(long id_FkCourseToTeach) {
        this.id_FkCourseToTeach = id_FkCourseToTeach;
    }
}

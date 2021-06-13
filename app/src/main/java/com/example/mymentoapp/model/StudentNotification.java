package com.example.mymentoapp.model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Comparator;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "student_notifications")
public class StudentNotification {

    @PrimaryKey(autoGenerate = true)
    private int idNotification;


    @ForeignKey
            (entity = Student.class,
                    parentColumns = "username",
                    childColumns = "usernameStudent",
                    onDelete = CASCADE,
                    onUpdate = CASCADE

            )
    private String usernameStudent;

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
    @ColumnInfo(name = "status")
    private String status;


    @ForeignKey
            (entity = CourseToTeach.class,
                    parentColumns = "idCourseToTeach",
                    childColumns = "id_FkCourseToTeach",
                    onDelete = CASCADE,
                    onUpdate = CASCADE

            )

    private long id_FkCourseToTeach;


    public StudentNotification() {
        this.description = "Your request from ";
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

    public String getUsernameStudent() {
        return usernameStudent;
    }

    public void setUsernameStudent(String usernameStudent) {
        this.usernameStudent = usernameStudent;
    }

    public long getId_FkCourseToTeach() {
        return id_FkCourseToTeach;
    }

    public void setId_FkCourseToTeach(long id_FkCourseToTeach) {
        this.id_FkCourseToTeach = id_FkCourseToTeach;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

//    @Override
//    public int compare(StudentNotification a, StudentNotification b) {
//        return a.getStatus().equals("New") && b.getStatus().equals("Old") ? -1
//                : a.getStatus().equals("Old") && b.getStatus().equals("New") ? 1
//                :0;
//    }


}

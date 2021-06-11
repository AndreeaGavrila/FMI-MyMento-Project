package com.example.mymentoapp.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "students_rating_tutors")
public class RatingStudent {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "studentUsername")
    private String studentUsername;

    @ColumnInfo(name = "tutorUsername")
    private String tutorUsername;

    public String getStudentUsername() {
        return studentUsername;
    }

    public RatingStudent(String studentUsername, String tutorUsername){
        this.studentUsername = studentUsername;
        this.tutorUsername = tutorUsername;
    }

    public void setStudentUsername(String studentUsername) {
        this.studentUsername = studentUsername;
    }

    public String getTutorUsername() {
        return tutorUsername;
    }

    public void setTutorUsername(String tutorUsername) {
        this.tutorUsername = tutorUsername;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

package com.example.mymentoapp.model;

import androidx.room.Entity;

@Entity(tableName = "student_taught_courses", primaryKeys = {"idStudent", "idTaughtCourse"})
public class TaughtCourseStudentCross {

    private int idStudent;
    private int idTaughtCourse;

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    public int getIdTaughtCourse() {
        return idTaughtCourse;
    }

    public void setIdTaughtCourse(int idTaughtCourse) {
        this.idTaughtCourse = idTaughtCourse;
    }
}

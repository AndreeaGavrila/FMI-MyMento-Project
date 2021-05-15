package com.example.mymentoapp.model;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class StudentWithTaughtCourses {

    @Embedded private Student student;
    @Relation(
            parentColumn = "idStudent",
            entityColumn = "id_FkStudent"
    )
    private List<TaughtCourse> taughtCourses;

    public StudentWithTaughtCourses(Student student, List<TaughtCourse> taughtCourses) {
        this.student = student;
        this.taughtCourses = taughtCourses;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<TaughtCourse> getTaughtCourses() {
        return taughtCourses;
    }

    public void setTaughtCourses(List<TaughtCourse> taughtCourses) {
        this.taughtCourses = taughtCourses;
    }
}

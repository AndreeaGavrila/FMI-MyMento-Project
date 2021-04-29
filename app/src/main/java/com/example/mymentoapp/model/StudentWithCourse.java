package com.example.mymentoapp.model;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class StudentWithCourse {

    @Embedded
    public Student student;
    @Relation(
            parentColumn = "idStudent",
            entityColumn = "idFkStudent"
    )
    public List<SpecificCourse> specificCourses;

    public StudentWithCourse(Student student, List<SpecificCourse> specificCourses) {
        this.student = student;
        this.specificCourses = specificCourses;
    }
}

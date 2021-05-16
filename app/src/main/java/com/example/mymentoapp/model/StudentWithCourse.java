package com.example.mymentoapp.model;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class StudentWithCourse {

    @Embedded
    public Student student;
    @Relation(
            parentColumn = "idStudent",
            entityColumn = "id_FkStudent"
    )
    public List<SpecificCourse> specificCourses;

    public StudentWithCourse(Student student, List<SpecificCourse> specificCourses) {
        this.student = student;
        this.specificCourses = specificCourses;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<SpecificCourse> getSpecificCourses() {
        return specificCourses;
    }

    public void setSpecificCourses(List<SpecificCourse> specificCourses) {
        this.specificCourses = specificCourses;
    }
}

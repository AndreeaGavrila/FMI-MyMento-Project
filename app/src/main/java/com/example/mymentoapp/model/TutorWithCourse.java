package com.example.mymentoapp.model;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class TutorWithCourse {

    @Embedded
    public Tutor tutor;
    @Relation(
            parentColumn = "idStudent",
            entityColumn = "id_FkTutor"
    )
    public List<CourseToTeach> courseToTeach;
    public TutorWithCourse(Tutor tutor, List<CourseToTeach> courseToTeach) {
        this.tutor = tutor;
        this.courseToTeach = courseToTeach;
    }
}

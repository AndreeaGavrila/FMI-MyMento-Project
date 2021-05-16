package com.example.mymentoapp.model;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class TutorWithCourse {

    @Embedded
    private Tutor tutor;
    @Relation(
            parentColumn = "idStudent",
            entityColumn = "id_FkTutor"
    )
    private List<CourseToTeach> courseToTeach;
    public TutorWithCourse(Tutor tutor, List<CourseToTeach> courseToTeach) {
        this.tutor = tutor;
        this.courseToTeach = courseToTeach;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    public List<CourseToTeach> getCourseToTeach() {
        return courseToTeach;
    }

    public void setCourseToTeach(List<CourseToTeach> courseToTeach) {
        this.courseToTeach = courseToTeach;
    }
}

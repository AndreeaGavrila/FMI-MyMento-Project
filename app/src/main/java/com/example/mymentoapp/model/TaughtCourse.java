package com.example.mymentoapp.model;


import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "taught_course", indices = {@Index(value = {"idCourseToTeach", "id_FkStudent", "id_FkTutor"},
        unique = true)})
public class TaughtCourse extends CourseToTeach {


    @ForeignKey
            (entity = Student.class,
                    parentColumns = "idStudent",
                    childColumns = "id_FkStudent",
                    onDelete = CASCADE,
                    onUpdate = CASCADE

            )
    private long id_FkStudent;


    public TaughtCourse(String courseName, String description) {
        super(courseName, description);
    }

    public long getId_FkStudent() {
        return id_FkStudent;
    }

    public void setId_FkStudent(long id_FkStudent) {
        this.id_FkStudent = id_FkStudent;
    }


}

package com.example.mymentoapp.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "course_to_teach", indices = {@Index(value = {"courseName"},
        unique = true)})

public class CourseToTeach implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int idCourseToTeach;

    @ForeignKey
            (entity = Tutor.class,
                    parentColumns = "idStudent",
                    childColumns = "id_FkTutor",
                    onDelete = CASCADE,
                    onUpdate = CASCADE

            )
    private long id_FkTutor;

    @ColumnInfo(name = "courseName")
    private String courseName;

    @ColumnInfo(name = "description")
    private String description;

    public CourseToTeach(@NonNull String courseName, @NonNull String description) {
        this.courseName = courseName;
        this.description = description;
    }

    public int getIdCourseToTeach() {
        return idCourseToTeach;
    }

    public void setIdCourseToTeach(int idCourseToTeach) {
        this.idCourseToTeach = idCourseToTeach;
    }

    public long getId_FkTutor() {
        return id_FkTutor;
    }

    public void setId_FkTutor(long id_FkTutor) {
        this.id_FkTutor = id_FkTutor;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public String toString() {
        return "CourseToTeach{" +
                "idCourseToTeach=" + idCourseToTeach +
                ", id_FkTutor=" + id_FkTutor +
                ", courseName='" + courseName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
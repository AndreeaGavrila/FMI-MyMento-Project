package com.example.mymentoapp.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "specific_course", indices = {@Index(value = {"courseName", "id_FkStudent"},
        unique = true)})
public class SpecificCourse {

    @PrimaryKey(autoGenerate = true)
    private int idSpecificCourse;

    @ForeignKey
            (entity = Student.class,
                    parentColumns = "idStudent",
                    childColumns = "id_FkStudent",
                    onDelete = CASCADE,
                    onUpdate = CASCADE

            )
    private long id_FkStudent;


    @ColumnInfo(name = "courseName")
    private String courseName;

    @ColumnInfo(name = "description")
    private String description;

    public SpecificCourse(@NonNull String courseName, @NonNull String description) {
        this.courseName = courseName;
        this.description = description;

    }


    public int getIdSpecificCourse() {
        return idSpecificCourse;
    }

    public void setIdSpecificCourse(int idSpecificCourse) {
        this.idSpecificCourse = idSpecificCourse;
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

    public long getId_FkStudent() {
        return id_FkStudent;
    }

    public void setId_FkStudent(long id_FkStudent) {
        this.id_FkStudent = id_FkStudent;
    }
}

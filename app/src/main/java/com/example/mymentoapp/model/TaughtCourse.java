package com.example.mymentoapp.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "taught_course")
public class TaughtCourse extends CourseToTeach {

    @PrimaryKey(autoGenerate = true)
    private int idTaughtCourse;

    @ForeignKey
            (entity = Student.class,
                    parentColumns = "idStudent",
                    childColumns = "id_FkStudent",
                    onDelete = CASCADE,
                    onUpdate = CASCADE

            )
    private long id_FkStudent;

    public int getIdTaughtCourse() {
        return idTaughtCourse;
    }

    public void setIdTaughtCourse(int idTaughtCourse) {
        this.idTaughtCourse = idTaughtCourse;
    }

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

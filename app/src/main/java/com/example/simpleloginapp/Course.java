package com.example.simpleloginapp;

public class Course {

    protected int idCourse, idTutore;
    protected String courseName, studyYear;

    public Course(){
    }
    public Course(int idCourse, String courseName, int idTutore, String studyYear) {
        this.idCourse = idCourse;
        this.courseName = courseName;
        this.idTutore = idTutore;
        this.studyYear = studyYear;
    }

    public int getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(int idCourse) {
        this.idCourse = idCourse;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getIdTutore() {
        return idTutore;
    }

    public void setIdTutore(int idTutore) {
        this.idTutore = idTutore;
    }

    public String getStudyYear() {
        return studyYear;
    }

    public void setStudyYear(String studyYear) {
        this.studyYear = studyYear;
    }
}

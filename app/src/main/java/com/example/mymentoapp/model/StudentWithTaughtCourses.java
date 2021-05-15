//package com.example.mymentoapp.model;
//
//import androidx.room.Embedded;
//import androidx.room.Entity;
//import androidx.room.Junction;
//import androidx.room.Relation;
//
//import java.util.List;
//@Entity
//public class StudentWithTaughtCourses {
//
//    @Embedded private Student student;
//    @Relation(
//            parentColumn = "idStudent",
//            entityColumn = "idCourseToTeach",
//            associateBy = @Junction(TaughtCourseStudentCross.class)
//    )
//    private List<CourseToTeach> courseToTeach;
//
//    public StudentWithTaughtCourses(Student student, List<CourseToTeach> courseToTeach) {
//        this.student = student;
//        this.courseToTeach = courseToTeach;
//    }
//
//    public Student getStudent() {
//        return student;
//    }
//
//    public void setStudent(Student student) {
//        this.student = student;
//    }
//
//    public List<CourseToTeach> getCourseToTeach() {
//        return courseToTeach;
//    }
//
//    public void setCourseToTeach(List<CourseToTeach> courseToTeach) {
//        this.courseToTeach = courseToTeach;
//    }
//}

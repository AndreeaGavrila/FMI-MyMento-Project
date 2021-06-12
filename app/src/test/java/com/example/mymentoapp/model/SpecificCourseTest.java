package com.example.mymentoapp.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpecificCourseTest {

    @Test
    void getCourseName() {
        SpecificCourse sc = new SpecificCourse("OOP", "Course on the principles of object-oriented programming");
        assertEquals("OOP", sc.getCourseName());
    }

    @Test
    void setCourseName() {
        SpecificCourse sc = new SpecificCourse("OOP", "Course on the principles of object-oriented programming");
        sc.setCourseName("POO");
        assertEquals("POO", sc.getCourseName());
    }

    @Test
    void getDescription() {
        SpecificCourse sc = new SpecificCourse("OOP", "Course on the principles of object-oriented programming");
        assertEquals("Course on the principles of object-oriented programming", sc.getDescription());
    }

    @Test
    void setDescription() {
        SpecificCourse sc = new SpecificCourse("OOP", "Course on the principles of object-oriented programming");
        sc.setDescription("Course on the principles of OOP");
        assertEquals("Course on the principles of OOP", sc.getDescription());
    }
}
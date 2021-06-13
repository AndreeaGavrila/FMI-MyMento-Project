package com.example.mymentoapp.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegisterTest {

    @Test
    void getUsername() {
        Student student = new Student("Test", "Testtt", "I", "Mathematics", "+40712345678", "test@gmail.com", "test", "1234");
        assertEquals("test", student.getUsername());
    }

    @Test
    void setUsername() {
        Student student = new Student("Test", "Testtt", "I", "Mathematics", "+40712345678", "test@gmail.com", "test", "1234");
        student.setUsername("tttest");
        assertEquals("tttest", student.getUsername());
    }

    @Test
    void getPassword() {
        Student student = new Student("Test", "Testtt", "I", "Mathematics", "+40712345678", "test@gmail.com", "test", "1234");
        assertEquals("1234", student.getPassword());
    }

    @Test
    void setPassword() {
        Student student = new Student("Test", "Testtt", "I", "Mathematics", "+40712345678", "test@gmail.com", "test", "1234");
        student.setPassword("1111");
        assertEquals("1111", student.getPassword());
    }
}
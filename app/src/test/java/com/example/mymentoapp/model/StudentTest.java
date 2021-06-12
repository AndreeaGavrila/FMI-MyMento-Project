package com.example.mymentoapp.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @Test
    void getFirstName() {
        Student student = new Student("Test", "Testtt", "I", "Mathematics", "+40712345678", "test@gmail.com", "test", "1234");
        assertEquals("Test", student.getFirstName());
//        fail();
    }

    @Test
    void setFirstName() {
        Student student = new Student("Test", "Testtt", "I", "Mathematics", "+40712345678", "test@gmail.com", "test", "1234");
        student.setFirstName("Test2");
        assertEquals("Test2", student.getFirstName());
    }

    @Test
    void getLastName() {
        Student student = new Student("Test", "Testtt", "I", "Mathematics", "+40712345678", "test@gmail.com", "test", "1234");
        assertEquals("Testtt", student.getLastName());
    }

    @Test
    void setLastName() {
        Student student = new Student("Test", "Testtt", "I", "Mathematics", "+40712345678", "test@gmail.com", "test", "1234");
        student.setLastName("Testtt2");
        assertEquals("Testtt2", student.getLastName());
    }

    @Test
    void getStudyYear() {
        Student student = new Student("Test", "Testtt", "I", "Mathematics", "+40712345678", "test@gmail.com", "test", "1234");
        assertEquals("I", student.getStudyYear());
    }

    @Test
    void setStudyYear() {
        Student student = new Student("Test", "Testtt", "I", "Mathematics", "+40712345678", "test@gmail.com", "test", "1234");
        student.setStudyYear("III");
        assertEquals("III", student.getStudyYear());
    }

    @Test
    void getStudyDomain() {
        Student student = new Student("Test", "Testtt", "I", "Mathematics", "+40712345678", "test@gmail.com", "test", "1234");
        assertEquals("Mathematics", student.getStudyDomain());
    }

    @Test
    void setStudyDomain() {
        Student student = new Student("Test", "Testtt", "I", "Mathematics", "+40712345678", "test@gmail.com", "test", "1234");
        student.setStudyDomain("Informatics");
        assertEquals("Informatics", student.getStudyDomain());
    }

    @Test
    void getPhoneNumber() {
        Student student = new Student("Test", "Testtt", "I", "Mathematics", "+40712345678", "test@gmail.com", "test", "1234");
        assertEquals("+40712345678", student.getPhoneNumber());
    }

    @Test
    void setPhoneNumber() {
        Student student = new Student("Test", "Testtt", "I", "Mathematics", "+40712345678", "test@gmail.com", "test", "1234");
        student.setPhoneNumber("+40778965412");
        assertEquals("+40778965412", student.getPhoneNumber());
    }

    @Test
    void getEmail() {
        Student student = new Student("Test", "Testtt", "I", "Mathematics", "+40712345678", "test@gmail.com", "test", "1234");
        assertEquals("test@gmail.com", student.getEmail());
    }

    @Test
    void setEmail() {
        Student student = new Student("Test", "Testtt", "I", "Mathematics", "+40712345678", "test@gmail.com", "test", "1234");
        student.setEmail("test@yahoo.ro");
        assertEquals("test@yahoo.ro", student.getEmail());
    }

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
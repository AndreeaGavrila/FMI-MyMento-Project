package com.example.mymentoapp.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TutorTest {

    @Test
    void getRating() {
        Tutor tutor = new Tutor("Test", "Testtt", "I", "Mathematics", "+40712345678", "test@gmail.com", "test", "1234", 4, "142424242242422");
        assertEquals(4, tutor.getRating());
    }

    @Test
    void setRating() {
        Tutor tutor = new Tutor("Test", "Testtt", "I", "Mathematics", "+40712345678", "test@gmail.com", "test", "1234", 4, "142424242242422");
        tutor.setRating(5);
        assertEquals(5, tutor.getRating());
    }

    @Test
    void getIban() {
        Tutor tutor = new Tutor("Test", "Testtt", "I", "Mathematics", "+40712345678", "test@gmail.com", "test", "1234", 4, "142424242242422");
        assertEquals("142424242242422", tutor.getIban());
    }

    @Test
    void setIban() {
        Tutor tutor = new Tutor("Test", "Testtt", "I", "Mathematics", "+40712345678", "test@gmail.com", "test", "1234", 4, "142424242242422");
        tutor.setIban("142424242247896");
        assertEquals("142424242247896", tutor.getIban());
    }
}
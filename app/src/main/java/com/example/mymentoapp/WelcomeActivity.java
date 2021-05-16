package com.example.mymentoapp;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mymentoapp.model.SpecificCourse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class WelcomeActivity extends AppCompatActivity {

    Button viewProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        viewProfile = findViewById(R.id.btn_viewProfile);
        Bundle bundle = getIntent().getExtras();
        int idStudent = bundle.getInt("idStudent");
        System.out.println("id student welcome: " + idStudent);;
        ArrayList<SpecificCourse> extras_courses = (ArrayList<SpecificCourse>) bundle.getSerializable("lista_cursuri");

        //ArrayList<String> finalExtras_courses = extras_courses;
        viewProfile.setOnClickListener(v -> {
            Bundle b = new Bundle();
            b.putSerializable("lista_cursuri", extras_courses);
            Intent intent = new Intent(WelcomeActivity.this, ViewProfileActivity.class);
            intent.putExtra("idStudent", idStudent);
            //intent.putExtra("lista_cursuri", extras_courses);
            intent.putExtras(b);
            startActivity(intent);
        });
    }
}
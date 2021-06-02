package com.example.mymentoapp;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class WelcomeActivity extends AppCompatActivity {

    Button viewProfile;
    Button showRecommend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        viewProfile = findViewById(R.id.btn_viewProfile);
        showRecommend = findViewById(R.id.btn_recommend);
        Bundle bundle = getIntent().getExtras();
        String studentName = bundle.getString("studentName");

//        System.out.println("id student welcome: " + idStudent);;
//        ArrayList<String> extras_courses = new ArrayList<>();
//        extras_courses = (ArrayList<String>) bundle.get("lista_cursuri");

       // ArrayList<String> finalExtras_courses = extras_courses;
        viewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, ViewProfileActivity.class);
                intent.putExtra("studentName", studentName);
               // intent.putExtra("lista_cursuri", finalExtras_courses);
                startActivity(intent);
            }
        });

        showRecommend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, RecommendActivity.class);
                intent.putExtra("studentName", studentName);
                // intent.putExtra("lista_cursuri", finalExtras_courses);
                startActivity(intent);
            }
        });
    }
}

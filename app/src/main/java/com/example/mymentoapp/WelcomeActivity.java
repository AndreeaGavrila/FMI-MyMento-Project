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

public class WelcomeActivity extends AppCompatActivity {

    Button viewProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        viewProfile = (Button)findViewById(R.id.btn_viewProfile);
        Bundle bundle = getIntent().getExtras();
        int idStudent = bundle.getInt("idStudent");


        viewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, ViewProfileActivity.class);
                intent.putExtra("idStudent", idStudent);
                startActivity(intent);
            }
        });
    }
}

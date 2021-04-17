package com.example.simpleloginapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SelectProfile extends AppCompatActivity {

    Button option_tutor, option_student;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_status); //prof sau elev
        option_tutor = (Button) findViewById(R.id.tutor_button);
        option_student = (Button) findViewById(R.id.student_button);

        option_tutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent auxIntent = getIntent();
                String username = auxIntent.getStringExtra("registeredUsername");
                String password = auxIntent.getStringExtra("registeredPassword");
                Intent intent = new Intent(SelectProfile.this, CreateTutorProfile.class);
                intent.putExtra("registeredUsername", username);
                intent.putExtra("registeredPassword", password);
                startActivity(intent);
            }
        });

        option_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent auxIntent = getIntent();
                String username = auxIntent.getStringExtra("registeredUsername");
                String password = auxIntent.getStringExtra("registeredPassword");
                Intent intent = new Intent(SelectProfile.this, CreateStudentProfile.class);
                intent.putExtra("registeredUsername", username);
                intent.putExtra("registeredPassword", password);
                startActivity(intent);
            }
        });
    }
}

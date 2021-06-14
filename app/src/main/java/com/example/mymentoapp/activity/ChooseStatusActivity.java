package com.example.mymentoapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mymentoapp.R;


public class ChooseStatusActivity extends AppCompatActivity {

    Button chooseStudent, chooseTutor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_status);

        chooseStudent = findViewById(R.id.student_button);
        chooseTutor = findViewById(R.id.tutor_button);

        Bundle bundle = getIntent().getExtras();
        String username  = bundle.getString("username");

        chooseStudent.setOnClickListener(v -> {
            Intent intent = new Intent(ChooseStatusActivity.this, ProfileStudentActivity.class);
            intent.putExtra("username", username);
            startActivity(intent);
        });

        chooseTutor.setOnClickListener(v -> {
            Intent intent = new Intent(ChooseStatusActivity.this, ProfileTutorActivity.class);
            intent.putExtra("username", username);
            startActivity(intent);
        });
    }

}



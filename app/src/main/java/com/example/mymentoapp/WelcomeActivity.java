package com.example.mymentoapp;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.mymentoapp.RecommendActivity;
import com.example.mymentoapp.ViewProfileActivity;
import com.example.mymentoapp.data.TutorDao;
import com.example.mymentoapp.model.Login;
import com.example.mymentoapp.model.Student;
import com.example.mymentoapp.model.Tutor;
import com.example.mymentoapp.util.MyRoomDatabase;

public class WelcomeActivity extends AppCompatActivity {

    Button viewProfile, logOut, showRecommend, notification;
    Toolbar toolbar;
    Button manageButton;

    MyRoomDatabase roomDatabase;
    Tutor tutor;
    TutorDao tutorDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        logOut = findViewById(R.id.logout);
        viewProfile = findViewById(R.id.btn_viewProfile);
        showRecommend = findViewById(R.id.btn_recommend);
        notification = findViewById(R.id.notification_btn);
        manageButton = findViewById(R.id.btn_manage_students);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        roomDatabase = MyRoomDatabase.getDatabase(getApplicationContext());

        Bundle bundle = getIntent().getExtras();
        String studentName = bundle.getString("studentName");

        tutorDao = roomDatabase.tutorDao();

        manageButton.setVisibility(View.INVISIBLE);

        viewProfile.setOnClickListener(v -> {
            Intent intent = new Intent(WelcomeActivity.this, ViewProfileActivity.class);
            intent.putExtra("studentName", studentName);
            startActivity(intent);
        });

        showRecommend.setOnClickListener(v -> {
            Intent intent = new Intent(WelcomeActivity.this, RecommendActivity.class);
            intent.putExtra("studentName", studentName);
            startActivity(intent);
        });

        logOut.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(WelcomeActivity.this);
            builder.setTitle("Log Out")
                    .setMessage("Are you sure you want to logout?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
                        startActivity(intent);
                    })
                    .setNegativeButton("No", null )
                    .create()
                    .show();
        });

        notification.setOnClickListener(v ->{
            Intent intent = new Intent(WelcomeActivity.this, ViewNotificationActivity.class);
            intent.putExtra("studentName", studentName);
            startActivity(intent);
        });
        new Thread(() -> {
             tutor = tutorDao.getTutorByUserName(studentName);
             if(tutor!=null){
                 manageButton.setVisibility(View.VISIBLE);
                 manageButton.setOnClickListener(v->{
                     Intent intent = new Intent(WelcomeActivity.this, ManageStudentsActivity.class);
                     intent.putExtra("studentName", studentName);
                     startActivity(intent);
                 });
             }
        }).start();
    }
}



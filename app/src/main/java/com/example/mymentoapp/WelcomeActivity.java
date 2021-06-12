package com.example.mymentoapp;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.mymentoapp.RecommendActivity;
import com.example.mymentoapp.ViewProfileActivity;
import com.example.mymentoapp.model.Login;
public class WelcomeActivity extends AppCompatActivity {

    Button viewProfile, logOut, showRecommend, notification;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        logOut = findViewById(R.id.logout);
        viewProfile = findViewById(R.id.btn_viewProfile);
        showRecommend = findViewById(R.id.btn_recommend);
        notification = findViewById(R.id.notification_btn);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        Bundle bundle = getIntent().getExtras();
        String studentName = bundle.getString("studentName");

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
    }
}



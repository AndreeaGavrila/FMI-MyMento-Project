package com.example.mymentoapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mymentoapp.data.StudentDao;
import com.example.mymentoapp.model.Student;
import com.example.mymentoapp.util.MyRoomDatabase;

import java.util.concurrent.atomic.AtomicInteger;

public class ViewProfileActivity extends AppCompatActivity {

    TextView firstName, lastName, phoneNumber, email, studyYear, domain;
    Button editProfile;

    MyRoomDatabase roomDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);

        Bundle bundle = getIntent().getExtras();
        int idStudent = bundle.getInt("idStudent");

        //System.out.println("id student view profile = "+ idStudent);

        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        phoneNumber = findViewById(R.id.phoneNumber);
        email = findViewById(R.id.email_view);
        studyYear = findViewById(R.id.studyYear);
        domain = findViewById(R.id.domain);
        editProfile = findViewById(R.id.edit_btn);

        AtomicInteger studentId = new AtomicInteger();
        roomDatabase = MyRoomDatabase.getDatabase(getApplicationContext());
        StudentDao studentDao = roomDatabase.studentDao();
        new Thread(() -> {
            Student student = studentDao.getStudent(idStudent);
            studentId.set(student.getIdStudent());
            this.runOnUiThread(() -> {
                firstName.setText(student.getFirstName());
                lastName.setText(student.getLastName());
                phoneNumber.setText(student.getPhoneNumber());
                email.setText(student.getEmail());
                studyYear.setText(student.getStudyYear());
                domain.setText(student.getStudyDomain());
            });
        }).start();


        editProfile.setOnClickListener(v -> {
            Intent newIntent = new Intent (ViewProfileActivity.this, EditProfileActivity.class);
            newIntent.putExtra("studentId", idStudent);
            startActivity(newIntent);
        });
    }
}

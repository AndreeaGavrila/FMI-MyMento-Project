package com.example.mymentoapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mymentoapp.data.StudentDao;
import com.example.mymentoapp.data.TutorDao;
import com.example.mymentoapp.util.MyRoomDatabase;
public class ManageStudentsActivity extends AppCompatActivity {
    Button btn_alphabetic, btn_course, btn_attendance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_manager);
        btn_alphabetic=findViewById(R.id.btn_alphabetical);
        btn_course=findViewById(R.id.btn_course_order);
        btn_attendance=findViewById(R.id.btn_most_attendance);

        Bundle bundle = getIntent().getExtras();
        String tutorName = bundle.getString("studentName");


        btn_alphabetic.setOnClickListener(v->{
            Intent intent = new Intent(ManageStudentsActivity.this, StudentListingActivity.class);
            intent.putExtra("tutorName", tutorName);
            intent.putExtra("orderCriteria", "alphabetic");
            startActivity(intent);
        });

        btn_course.setOnClickListener(v->{
            Intent intent = new Intent(ManageStudentsActivity.this, StudentListingActivity.class);
            intent.putExtra("tutorName", tutorName);
            intent.putExtra("orderCriteria", "course_order");
            startActivity(intent);
        });
        btn_attendance.setOnClickListener(v->{
            Intent intent = new Intent(ManageStudentsActivity.this, StudentListingActivity.class);
            intent.putExtra("tutorName", tutorName);
            intent.putExtra("orderCriteria", "attendance_order");
            startActivity(intent);
        });


    }
}
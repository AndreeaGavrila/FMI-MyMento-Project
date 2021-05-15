package com.example.mymentoapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mymentoapp.data.StudentDao;
import com.example.mymentoapp.model.Student;
import com.example.mymentoapp.util.MyRoomDatabase;

public class ViewProfileActivity extends AppCompatActivity {

    TextView firstName ;
    TextView lastName;
    Button editProfile;

    MyRoomDatabase roomDatabase = MyRoomDatabase.getDatabase(getApplicationContext());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);

        firstName = (TextView)findViewById(R.id.firstName);
        lastName = (TextView)findViewById(R.id.lastName);
        editProfile = (Button)findViewById(R.id.edit_profile);

        Bundle bundle = getIntent().getExtras();
        int idStudent = bundle.getInt("idStudent");

        System.out.println("id student view profile = "+ idStudent);

        //MyRoomDatabase roomDatabase = MyRoomDatabase.getDatabase(getApplicationContext());
        StudentDao studentDao = roomDatabase.studentDao();
        Student student = studentDao.getStudent(idStudent);


        System.out.println("first name = " + student.getFirstName());
        System.out.println("last name = " + student.getLastName());
        firstName.setText(student.getFirstName());
        lastName.setText(student.getLastName());

        editProfile.setOnClickListener(v -> {
//            Intent newIntent = new Intent (ViewProfileActivity.this, EditProfileActivity.class);
//            newIntent.putExtra("studentId", student.getIdStudent());
//            startActivity(newIntent);
        });
    }
}

package com.example.mymentoapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mymentoapp.data.StudentRepository;
import com.example.mymentoapp.model.CourseToTeach;
import com.example.mymentoapp.model.CourseToTeachViewModel;
import com.example.mymentoapp.model.SpecificCourse;
import com.example.mymentoapp.model.SpecificCourseViewModel;
import com.example.mymentoapp.model.Student;
import com.example.mymentoapp.model.StudentViewModel;
import com.example.mymentoapp.model.Tutor;
import com.example.mymentoapp.model.TutorViewModel;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class ViewProfileActivity extends AppCompatActivity {

    TextView firstName, lastName, phoneNumber, email, studyYear, domain;
    Button editProfile;
//    ListView list;

    StudentRepository studentRepository = new StudentRepository(this.getApplication());
    TextView textViewSpecificCourse;
    TextView textViewToTeachCourse;
    TextView textView;
//    private ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        StudentViewModel studentViewModel = new StudentViewModel(this.getApplication());
        TutorViewModel tutorViewModel = new TutorViewModel(this.getApplication());
        SpecificCourseViewModel specificCourseViewModel = new SpecificCourseViewModel(this.getApplication());
        CourseToTeachViewModel courseToTeachViewModel = new CourseToTeachViewModel(this.getApplication());

        Bundle bundle = getIntent().getExtras();
        int idStudent = bundle.getInt("idStudent");

        System.out.println("PANA AICI A AJUNS ACUM");
        //System.out.println("id student view profile = "+ idStudent);
        //list = (ListView) findViewById(R.id.list_view_courses);
        textViewSpecificCourse = (TextView) findViewById(R.id.text_view_course);
        textViewToTeachCourse = (TextView) findViewById(R.id.teach_courses);
        textView = (TextView) findViewById(R.id.text_view_course2);
        //recycler = findViewById(R.id.recycle_course_view);
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        phoneNumber = findViewById(R.id.phoneNumber);
        email = findViewById(R.id.email_view);
        studyYear = findViewById(R.id.studyYear);
        domain = findViewById(R.id.domain);
        editProfile = findViewById(R.id.edit_btn);

        int studentId = 0;
        //textViewToTeachCourse.setVisibility(View.VISIBLE);
       // textView.setVisibility(View.VISIBLE);

        new Thread(() -> {
            Student student = new Student();
            System.out.println("UP2");
            System.out.println("UP3" + studentRepository.getAllData().toString());
            for (Student student1: Objects.requireNonNull(studentRepository.getAllData().getValue())){
                if(student1.getIdStudent() == idStudent){
                    student = student1;
                    System.out.println("CEVA DE AICI");
                }
            }

            System.out.println("emaio" + student.getEmail());
            System.out.println("UP1");
            ArrayList<SpecificCourse> courses =(ArrayList<SpecificCourse>) (specificCourseViewModel
                    .getAllSpecificCoursesForStudent(student.getIdStudent()));
            System.out.println("LISTA DE CURSURI ADUSE PE VIEW PROFILE");
            Tutor tutor = tutorViewModel.getTutorByUserName(student.getUsername());
            ArrayList<CourseToTeach> courseToTeachArrayList = new ArrayList<CourseToTeach>();
            if(tutor != null){
                courseToTeachArrayList = (ArrayList<CourseToTeach>) courseToTeachViewModel.getAllSpecificCoursesForTutor(tutor.getIdStudent());
            }

//            ArrayList<String> specificCourseNames = new ArrayList<>();
//            for(SpecificCourse specificCourse : courses){
//                specificCourseNames.add(specificCourse.getCourseName());
//            }
//
//            ArrayList<String> toTeachCourseNames = new ArrayList<>();
//            for(CourseToTeach courseToTeach: courseToTeachArrayList){
//                toTeachCourseNames.add(courseToTeach.getCourseName());
//            }


//            System.out.println("id student este " + student.getIdStudent());

//            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
//                    this,
//                    android.R.layout.simple_list_item_1, courseNames);
//
//            list.setAdapter(arrayAdapter);


            System.out.println("APOI AICI");
            ArrayList<CourseToTeach> finalCourseToTeachArrayList = courseToTeachArrayList;
            if(tutor != null){
                System.out.println("diferit de null");

                textView.setVisibility(View.VISIBLE);
                for(CourseToTeach courseToTeach : finalCourseToTeachArrayList){
                    textViewToTeachCourse.append(courseToTeach.getCourseName());
                    textViewToTeachCourse.append("\n");
                }
            }
            for(SpecificCourse course : courses){
                textViewSpecificCourse.append(course.getCourseName());
                textViewSpecificCourse.append("\n");
            }

            firstName.setText(student.getFirstName());
            lastName.setText(student.getLastName());
            phoneNumber.setText(student.getPhoneNumber());
            email.setText(student.getEmail());
            studyYear.setText(student.getStudyYear());
            domain.setText(student.getStudyDomain());

        }).start();

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent (ViewProfileActivity.this, EditProfileActivity.class);
                newIntent.putExtra("studentId", idStudent);
                startActivity(newIntent);
            }
        });

    }
}

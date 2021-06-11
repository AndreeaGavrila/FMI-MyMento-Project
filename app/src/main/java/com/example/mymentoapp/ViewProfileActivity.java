package com.example.mymentoapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mymentoapp.model.CourseToTeach;
import com.example.mymentoapp.model.CourseToTeachViewModel;
import com.example.mymentoapp.model.SpecificCourse;
import com.example.mymentoapp.model.SpecificCourseViewModel;
import com.example.mymentoapp.model.Student;
import com.example.mymentoapp.model.StudentViewModel;
import com.example.mymentoapp.model.Tutor;
import com.example.mymentoapp.model.TutorViewModel;

import java.util.ArrayList;

public class ViewProfileActivity extends AppCompatActivity {
    private StudentViewModel studentViewModel;
    private CourseToTeachViewModel courseToTeachViewModel;
    private TutorViewModel tutorViewModel;
    private SpecificCourseViewModel specificCourseViewModel;

    TextView firstName, lastName, phoneNumber, email, studyYear, domain, textViewToTeachCourse,
            textViewSpecificCourse, textView;
    Button editProfile;
    LinearLayout linearLayout;
    Button downloadButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);

        Bundle bundle = getIntent().getExtras();
        String studentName = bundle.getString("studentName");

        textViewSpecificCourse = (TextView) findViewById(R.id.text_view_course);
        textViewToTeachCourse = (TextView) findViewById(R.id.teach_courses);
        textView = (TextView) findViewById(R.id.text_view_course2);

        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        phoneNumber = findViewById(R.id.phoneNumber);
        email = findViewById(R.id.email_view);
        studyYear = findViewById(R.id.studyYear);
        domain = findViewById(R.id.domain);
        editProfile = findViewById(R.id.edit_btn);
        downloadButton = findViewById(R.id.download_btn);
        linearLayout = findViewById(R.id.layout_recommended);

        textViewToTeachCourse.setVisibility(View.VISIBLE);

        new Thread(() -> {

            studentViewModel = new StudentViewModel(this.getApplication());
            tutorViewModel = new TutorViewModel(this.getApplication());
            courseToTeachViewModel = new CourseToTeachViewModel(this.getApplication());
            specificCourseViewModel = new SpecificCourseViewModel(this.getApplication());
            Student student = studentViewModel.getStudentByUsername(studentName);

            ArrayList<SpecificCourse> courses =(ArrayList<SpecificCourse>) (specificCourseViewModel.getAllSpecificCoursesForStudent(student.getIdStudent()));
            Tutor tutor = tutorViewModel.getTutor(student.getUsername());
            ArrayList<CourseToTeach> courseToTeachArrayList = new ArrayList<CourseToTeach>();
            if(tutor != null){
                courseToTeachArrayList = (ArrayList<CourseToTeach>) courseToTeachViewModel.getAllToTeachCourses(tutor.getIdStudent());
            }

            ArrayList<CourseToTeach> finalCourseToTeachArrayList = courseToTeachArrayList;
            this.runOnUiThread(() -> {

                if(tutor != null){
                    textView.setVisibility(View.VISIBLE);
                    for(CourseToTeach courseToTeach : finalCourseToTeachArrayList){
                        textViewToTeachCourse.append(courseToTeach.getCourseName());
                        textViewToTeachCourse.append("\n");
                    }
                }

                for(SpecificCourse course : courses){

                    Button btn = new Button(this.getApplicationContext());
                    btn.setText(course.getCourseName());
                    btn.setOnClickListener(v->{
                        Intent newIntent = new Intent (ViewProfileActivity.this, ViewAvailableCoursesActivity.class);
                        newIntent.putExtra("courseName", course.getCourseName());
                        newIntent.putExtra("studentName", student.getUsername());
                        startActivity(newIntent);
                    });
                    linearLayout.addView(btn);
                }

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
            newIntent.putExtra("studentName", studentName);
            startActivity(newIntent);
        });

        downloadButton.setOnClickListener(v -> {
            Intent newIntent = new Intent (ViewProfileActivity.this, DownloadActivity.class);
            newIntent.putExtra("studentName", studentName);
            startActivity(newIntent);
        });

    }
}
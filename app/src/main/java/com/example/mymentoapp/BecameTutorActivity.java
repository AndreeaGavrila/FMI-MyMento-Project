package com.example.mymentoapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.UiThread;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mymentoapp.model.CourseToTeach;
import com.example.mymentoapp.model.CourseToTeachViewModel;
import com.example.mymentoapp.model.SpecificCourse;
import com.example.mymentoapp.model.SpecificCourseViewModel;
import com.example.mymentoapp.model.Student;
import com.example.mymentoapp.model.StudentViewModel;
import com.example.mymentoapp.model.Tutor;
import com.example.mymentoapp.model.TutorViewModel;
import com.example.mymentoapp.model.TutorWithCourse;

import java.util.ArrayList;
import java.util.List;

public class BecameTutorActivity extends AppCompatActivity {
    LinearLayout linearLayoutCourseToTeach;
    Button btnAddCourses;

    private TutorViewModel tutorViewModel;
    private StudentViewModel studentViewModel;
    private CourseToTeachViewModel courseToTeachViewModel;
    private SpecificCourseViewModel specificCourseViewModel;
    private ArrayList<SpecificCourse> specificCourseList =  new ArrayList<>();
    private final ArrayList<CourseToTeach> courseToTeachArrayList = new ArrayList<>();
    private Student student;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.became_tutor);

        linearLayoutCourseToTeach = findViewById(R.id.to_teach_course);
        btnAddCourses = findViewById(R.id.button_add_courses);
        Bundle bundle = getIntent().getExtras();
        String studentName = bundle.getString("idTutor");



        new Thread(()->{
            tutorViewModel = new TutorViewModel(this.getApplication());
            courseToTeachViewModel = new CourseToTeachViewModel(this.getApplication());
            specificCourseViewModel = new SpecificCourseViewModel(this.getApplication());
            studentViewModel = new StudentViewModel(this.getApplication());
            student = studentViewModel.getStudentByUsername(studentName);
            specificCourseList = (ArrayList<SpecificCourse>) specificCourseViewModel.getAllSpecificCoursesForStudent(student.getIdStudent());

            this.runOnUiThread(()-> {
                for (SpecificCourse specificCourse : specificCourseList) {
                    CheckBox checkBox = new CheckBox(BecameTutorActivity.this);
                    checkBox.setText(specificCourse.getCourseName());
                    checkBox.setVisibility(View.VISIBLE);
                    linearLayoutCourseToTeach.addView(checkBox);
                }

            });


        }).start();





        btnAddCourses.setOnClickListener(v -> {

            for (int i = 0; i < specificCourseList.size(); i++) {
                CheckBox checkBox = (CheckBox) linearLayoutCourseToTeach.getChildAt(i);
                if (checkBox.isChecked()) {
                    System.out.println("is checked" + i);
                    CourseToTeach courseToTeach = new CourseToTeach(specificCourseList.get(i).getCourseName(), specificCourseList.get(i).getDescription());
                    courseToTeachArrayList.add(courseToTeach);
                }
            }
            if (courseToTeachArrayList.size() == 0) {
                Toast.makeText(getApplicationContext(), "You have to choose at least one course", Toast.LENGTH_SHORT).show();
            }else{
                new Thread(()->{
                    Tutor tutor = new Tutor();
                    tutor.setEmail(student.getEmail());
                    tutor.setFirstName(student.getFirstName());
                    tutor.setLastName(student.getLastName());
                    tutor.setPassword(student.getPassword());
                    tutor.setIdStudent(student.getIdStudent());
                    tutor.setPhoneNumber(student.getPhoneNumber());
                    tutor.setStudyDomain(student.getStudyDomain());
                    tutor.setUsername(student.getUsername());
                    tutor.setStudyYear(student.getStudyYear());

                    TutorWithCourse tutorWithCourse = new TutorWithCourse(tutor, courseToTeachArrayList);
                    tutorViewModel.insertTutorWithCourses(tutorWithCourse);

                }).start();
            }

            Intent intent = new Intent(BecameTutorActivity.this, ViewProfileActivity.class);
            intent.putExtra("studentName", studentName);
            startActivity(intent);
        });








    }

}

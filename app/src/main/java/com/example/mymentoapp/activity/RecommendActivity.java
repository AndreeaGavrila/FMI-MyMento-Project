package com.example.mymentoapp.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mymentoapp.R;
import com.example.mymentoapp.model.CourseToTeach;
import com.example.mymentoapp.model.CourseToTeachViewModel;
import com.example.mymentoapp.model.SpecificCourse;
import com.example.mymentoapp.model.SpecificCourseViewModel;
import com.example.mymentoapp.model.Student;
import com.example.mymentoapp.model.StudentViewModel;
import com.example.mymentoapp.model.Tutor;
import com.example.mymentoapp.model.TutorViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecommendActivity  extends AppCompatActivity {

    LinearLayout linearLayout;

    private StudentViewModel studentViewModel;
    private TutorViewModel tutorViewModel;
    private SpecificCourseViewModel specificCourseViewModel;
    private CourseToTeachViewModel courseToTeachViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend);

        Bundle bundle = getIntent().getExtras();
        String studentName = bundle.getString("studentName");

        linearLayout = findViewById(R.id.layout_recommended);

        List<String> textTeach = new ArrayList<>();
        List<List<String>> coursesList = new ArrayList<>();

        new Thread(() -> {
            studentViewModel = new StudentViewModel(this.getApplication());
            tutorViewModel = new TutorViewModel(this.getApplication());
            specificCourseViewModel = new SpecificCourseViewModel(this.getApplication());
            courseToTeachViewModel = new CourseToTeachViewModel(this.getApplication());
            Student student = studentViewModel.getStudentByUsername(studentName);

            ArrayList<SpecificCourse> courses = (ArrayList<SpecificCourse>) (specificCourseViewModel.getAllSpecificCoursesForStudent(student.getIdStudent()));
            for (int i = 0; i < courses.size(); i++) {

                ArrayList<CourseToTeach> courseToTeach = (ArrayList<CourseToTeach>) (courseToTeachViewModel.getAllCoursesForSpecificCourse(courses.get(i).getCourseName()));

                for (int j = 0; j < courseToTeach.size(); j++) {

                    int tutorId=(int)courseToTeach.get(j).getId_FkTutor();
                    textTeach.add(courseToTeach.get(j).getCourseName());
                    Tutor tutor = tutorViewModel.getTutorById(tutorId);
                    textTeach.add(tutor.getLastName());
                    textTeach.add(tutor.getFirstName());
                    Double tutorRating = tutor.getRating();
                    textTeach.add(tutorRating.toString());

                    String tutorUsername = tutor.getUsername();
                    if (!tutorUsername.equals(studentName)) {
                        coursesList.add(new ArrayList<>(textTeach));
                    }
                    textTeach.clear();
                }
            }
            for(int i=0;i<coursesList.size()-1;i++)
                for(int j=i+1;j<coursesList.size();j++){
                    if((coursesList.get(j)).get(3).compareTo((coursesList.get(i)).get(3))>0){
                        Collections.swap(coursesList,i,j);
                    }
                    else if((coursesList.get(j)).get(0).compareTo((coursesList.get(i)).get(0))<0){
                        Collections.swap(coursesList,i,j);
                    }
                    else if((coursesList.get(j)).get(0).compareTo((coursesList.get(i)).get(0))==0 && (coursesList.get(j)).get(1).compareTo((coursesList.get(i)).get(1))<0){
                        Collections.swap(coursesList,i,j);
                    }
                    else if((coursesList.get(j)).get(0).compareTo((coursesList.get(i)).get(0))==0 && (coursesList.get(j)).get(1).compareTo((coursesList.get(i)).get(1))==0 && (coursesList.get(j)).get(2).compareTo((coursesList.get(i)).get(2))<0){
                        Collections.swap(coursesList,i,j);
                    }

                }
            List<List<String>> finalCoursesList = new ArrayList<>((coursesList));

            runOnUiThread((() -> {
                for(int i=0;i<finalCoursesList.size();i++){
                    LinearLayout groupLinear = new LinearLayout(this.getApplicationContext());
                    groupLinear.setGravity(Gravity.CENTER);
                    groupLinear.setOrientation(LinearLayout.VERTICAL);
                    groupLinear.setPadding(20, 10, 20, 10);
                    Button btn = new Button(this.getApplicationContext());
                    String course = finalCoursesList.get(i).get(0);
                    btn.setText(course);
                    btn.setOnClickListener(v->{
                        Intent newIntent = new Intent (RecommendActivity.this, ViewAvailableCoursesActivity.class);
                        newIntent.putExtra("courseName", course);
                        newIntent.putExtra("studentName", studentName);
                        startActivity(newIntent);
                    });

                    StringBuilder tutor_name = new StringBuilder();
                    String last_name = finalCoursesList.get(i).get(1);
                    String first_name = finalCoursesList.get(i).get(2);
                    tutor_name.append(finalCoursesList.get(i).get(1));
                    tutor_name.append(" ");
                    tutor_name.append(finalCoursesList.get(i).get(2));
                    Button btn_tutor = new Button(getApplicationContext());
                    btn_tutor.setText(tutor_name);
                    btn_tutor.setOnClickListener(v->{
                        Intent newIntent = new Intent (RecommendActivity.this, TutorContactActivity.class);
                        newIntent.putExtra("tutorLastName", last_name);
                        newIntent.putExtra("tutorFirstName", first_name);
                        newIntent.putExtra("studentUsername", studentName);
                        startActivity(newIntent);
                    });
                    btn.setBackgroundColor(Color.parseColor("#fbc304"));
                    btn.setTextSize(18);
                    btn.setTypeface(Typeface.DEFAULT_BOLD);
                    btn_tutor.setBackgroundColor(Color.parseColor("#2850a1"));
                    btn_tutor.setTextColor(Color.parseColor("#C4C9D0"));

                    TextView delimitator = new TextView(getApplicationContext());
                    groupLinear.addView(btn);
                    groupLinear.addView(btn_tutor);
                    groupLinear.addView(delimitator);
                    linearLayout.addView(groupLinear);
                }
            }));

        }).start();

    }
}
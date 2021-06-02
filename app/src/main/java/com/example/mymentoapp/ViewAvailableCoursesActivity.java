package com.example.mymentoapp;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mymentoapp.model.CourseToTeach;
import com.example.mymentoapp.model.CourseToTeachViewModel;
import com.example.mymentoapp.model.Student;
import com.example.mymentoapp.model.StudentViewModel;
import com.example.mymentoapp.model.TutorViewModel;

import java.util.ArrayList;
import java.util.List;

public class ViewAvailableCoursesActivity extends AppCompatActivity {
    private List<CourseToTeach> courseToTeachList;
    private List<CourseToTeach> courseToTeachList2;
    private CourseToTeachViewModel courseToTeachViewModel;
    private TutorViewModel tutorViewModel;
    private StudentViewModel studentViewModel;
    private String text;
    LinearLayout layout;
    private Student student;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_available_courses);
        layout = findViewById(R.id.layout_available_courses);

        Bundle bundle = getIntent().getExtras();
        String courseName = bundle.getString("courseName");
        String studentName = bundle.getString("studentName");

        new Thread(()->{
            tutorViewModel = new TutorViewModel(this.getApplication());
            courseToTeachViewModel = new CourseToTeachViewModel(this.getApplication());
            studentViewModel = new StudentViewModel(this.getApplication());
            student = studentViewModel.getStudentByUsername(studentName);
            courseToTeachList = courseToTeachViewModel.getAllCoursesWithout(student.getIdStudent());
            courseToTeachList2 = new ArrayList<>();
            for(CourseToTeach c : courseToTeachList){
                if(c.getCourseName().equals(courseName)){
                    courseToTeachList2.add(c);
                }
            }

            ArrayList<String> texts = new ArrayList<>();

            if(courseToTeachList2.size() > 0){
                for(CourseToTeach c : courseToTeachList2){
                    text = c.getCourseName() + "\n" + tutorViewModel.getTutorById((int) c.getId_FkTutor()).getFirstName()
                            + " "  +  tutorViewModel.getTutorById((int) c.getId_FkTutor()).getLastName() + "\n";
                    texts.add(text);
                }
                this.runOnUiThread(() ->{
                    for(String st : texts){
                        TextView textView = new TextView(this.getApplicationContext());
                        textView.setText(st);
                        layout.addView(textView);
                    }

                });
            }else{
                System.out.println("Nu sunt cursuri");
            }



        }).start();

    }
}

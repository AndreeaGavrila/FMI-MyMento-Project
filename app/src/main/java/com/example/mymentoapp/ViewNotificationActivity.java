package com.example.mymentoapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.mymentoapp.model.CourseToTeach;
import com.example.mymentoapp.model.CourseToTeachViewModel;
import com.example.mymentoapp.model.Notification;
import com.example.mymentoapp.model.NotificationViewModel;
import com.example.mymentoapp.model.Student;
import com.example.mymentoapp.model.StudentViewModel;
import com.example.mymentoapp.model.StudentWithTaughtCourses;
import com.example.mymentoapp.model.TaughtCourse;
import com.example.mymentoapp.model.Tutor;
import com.example.mymentoapp.model.TutorViewModel;

import java.util.ArrayList;
import java.util.List;

public class ViewNotificationActivity extends AppCompatActivity {

    private StudentViewModel studentViewModel;
    private TutorViewModel tutorViewModel;
    private NotificationViewModel notificationViewModel;
    private CourseToTeachViewModel courseToTeachViewModel;
    private ArrayList<Notification> notificationArrayList = new ArrayList<>();

    LinearLayout linearLayout, linearLayout1;
    TextView textView;
    Toolbar toolbar;
    Button backHome;
    private Student student;
    private Tutor tutor;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_notifications);

        linearLayout = findViewById(R.id.layout_notifications);
        toolbar = findViewById(R.id.toolbar_home);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        backHome = findViewById(R.id.back_home);

        linearLayout.removeAllViews();

        //username
        Bundle bundle = getIntent().getExtras();
        String studentName = bundle.getString("studentName");


        new Thread(() -> {

            studentViewModel = new StudentViewModel(this.getApplication());
            tutorViewModel = new TutorViewModel(this.getApplication());
            notificationViewModel = new NotificationViewModel(this.getApplication());
            courseToTeachViewModel = new CourseToTeachViewModel(this.getApplication());
            student = studentViewModel.getStudentByUsername(studentName);

            if(tutorViewModel.getTutor(studentName) != null){
                int idTutor = tutorViewModel.getTutor(studentName).getIdStudent();
                notificationArrayList = (ArrayList<Notification>) notificationViewModel.getAllNotificationsForTutor(idTutor);
                tutor = tutorViewModel.getTutor(studentName);
            }
            for(Notification n : notificationArrayList){

                Student student1 = studentViewModel.getStudent((int) n.getId_FkStudent());
                String text = n.getDescription()  + student1.getFirstName() + " " + student1.getLastName() + " \n" ;

                Button btn = new Button(this.getApplicationContext());
                Student student = studentViewModel.getStudent((int) n.getId_FkStudent());
                btn.setText("ACCEPT REQUEST");

                this.runOnUiThread(() ->{
                    textView = new TextView(this.getApplicationContext());
                    textView.setText(text);
                    linearLayout1 = new LinearLayout(this.getApplicationContext());

                    linearLayout1.setOrientation(LinearLayout.HORIZONTAL);
                    linearLayout1.setBackgroundColor(Color.rgb(25, 55, 106));
                    linearLayout1.setPadding(10, 10, 10, 10);


//                    if(textView.getParent()!=null){
//                        ((ViewGroup)textView.getParent()).removeView(textView);
//                    }
                    if(btn.getParent() !=null){
                        ((ViewGroup)btn.getParent()).removeView(btn);
                    }

                    linearLayout1.addView(textView);
                    linearLayout1.addView(btn);
                    linearLayout.addView(linearLayout1);

                    btn.setOnClickListener(v ->{
                        if(tutor!=null){
                            new Thread(() ->{
                                System.out.println("O sa se adauge cursul cu tutorele");
                                CourseToTeach t = courseToTeachViewModel.getCourseById((int)n.getId_FkCourseToTeach());
                                TaughtCourse t2 = new TaughtCourse(t.getCourseName(), t.getDescription());
                                t2.setId_FkTutor(tutor.getIdStudent());
                                t2.setIdCourseToTeach((int) n.getId_FkCourseToTeach());
                                List<TaughtCourse> taughtCourseList = new ArrayList<>();
                                taughtCourseList.add(t2);
                                StudentWithTaughtCourses studentWithTaughtCourses = new StudentWithTaughtCourses(student, taughtCourseList);
                                studentViewModel.insertStudentWithTaughtCourses(studentWithTaughtCourses);
                                System.out.println("s-a inserat");
                                notificationViewModel.deleteNotification(n.getIdNotification());

                                finish();
                                startActivity(getIntent());

                            }).start();
                        }
                    });
                });
                System.out.println(n.toString());
            }

        }).start();

        backHome.setOnClickListener(v -> {
            Intent intent = new Intent(ViewNotificationActivity.this, WelcomeActivity.class);
            intent.putExtra("studentName", studentName);
            startActivity(intent);
        });

    }
}

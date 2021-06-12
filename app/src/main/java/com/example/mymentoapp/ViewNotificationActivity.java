package com.example.mymentoapp;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymentoapp.model.CourseToTeach;
import com.example.mymentoapp.model.CourseToTeachViewModel;
import com.example.mymentoapp.model.Notification;
import com.example.mymentoapp.model.NotificationViewModel;
import com.example.mymentoapp.model.SpecificCourseViewModel;
import com.example.mymentoapp.model.Student;
import com.example.mymentoapp.model.StudentNotification;
import com.example.mymentoapp.model.StudentNotificationViewModel;
import com.example.mymentoapp.model.StudentViewModel;
import com.example.mymentoapp.model.StudentWithNotifications;
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
    private StudentNotificationViewModel studentNotificationViewModel;
    private CourseToTeachViewModel courseToTeachViewModel;
    private List<Notification> notificationArrayList = new ArrayList<>();
    private List<StudentNotification> studentNotifications  = new ArrayList<>();
    private Student student;
    private Tutor tutor;

    LinearLayout linearLayout, linearLayout1;
    TextView textView;



    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_notifications);

        linearLayout = findViewById(R.id.layout_notifications);


        //username
        Bundle bundle = getIntent().getExtras();
        String studentName = bundle.getString("studentName");


        new Thread(() -> {

            studentViewModel = new StudentViewModel(this.getApplication());
            tutorViewModel = new TutorViewModel(this.getApplication());
            notificationViewModel = new NotificationViewModel(this.getApplication());
            studentNotificationViewModel = new StudentNotificationViewModel(this.getApplication());
            courseToTeachViewModel = new CourseToTeachViewModel(this.getApplication());

            student = studentViewModel.getStudentByUsername(studentName);
            tutor = tutorViewModel.getTutor(studentName);

            if(tutor != null){
                System.out.println("il vede tutuor");
                notificationArrayList = notificationViewModel.getAllNotificationsForTutor(tutor.getIdStudent());
                for(Notification n : notificationArrayList){
                    Student student1 = studentViewModel.getStudent((int) n.getId_FkStudent());
                    String text = n.getDescription()  + student1.getFirstName() + " " + student1.getLastName() + " \n" ;

                    Button btn = new Button(this.getApplicationContext());

                    btn.setText("ACCEPT REQUEST");

                    this.runOnUiThread(() ->{
                        textView = new TextView(this.getApplicationContext());
                        textView.setText(text);
                        linearLayout1 = new LinearLayout(this.getApplicationContext());

                        linearLayout1.setOrientation(LinearLayout.HORIZONTAL);
                        linearLayout1.setBackgroundColor(Color.rgb(25, 55, 106));
                        linearLayout1.setPadding(10, 10, 10, 10);

                        if(btn.getParent() !=null){
                            ((ViewGroup)btn.getParent()).removeView(btn);
                        }

                        linearLayout1.addView(textView);
                        linearLayout1.addView(btn);
                        linearLayout.addView(linearLayout1);

                        btn.setOnClickListener(v ->{
                                new Thread(() ->{

                                    CourseToTeach t = courseToTeachViewModel.getCourseById((int)n.getId_FkCourseToTeach());
                                    TaughtCourse t2 = new TaughtCourse(t.getCourseName(), t.getDescription());
                                    t2.setId_FkTutor(tutor.getIdStudent());
                                    t2.setIdCourseToTeach((int) n.getId_FkCourseToTeach());
                                    List<TaughtCourse> taughtCourseList = new ArrayList<>();
                                    taughtCourseList.add(t2);
                                    StudentWithTaughtCourses studentWithTaughtCourses = new StudentWithTaughtCourses(student, taughtCourseList);
                                    studentViewModel.insertStudentWithTaughtCourses(studentWithTaughtCourses);

                                    StudentNotification studentNotification = new StudentNotification();
                                    studentNotification.setDescription("Tutor " + tutor.getFirstName() + " " + tutor.getLastName() + " accepted your request " +
                                            "for " + t2.getCourseName() + " course");
                                    studentNotification.setId_FkCourseToTeach(t.getIdCourseToTeach());
                                    studentNotification.setId_FkTutor(tutor.getIdStudent());
                                    studentNotification.setStatus("New");

                                    List<StudentNotification> studentNotificationsList =  new ArrayList<>();
                                    studentNotificationsList.add(studentNotification);

                                    StudentWithNotifications studentWithNotifications = new StudentWithNotifications(student, studentNotificationsList);
                                    studentViewModel.insertStudentWithNotifications(studentWithNotifications);

                                    notificationViewModel.deleteNotification(n.getIdNotification());

                                    finish();
                                    startActivity(getIntent());

                                }).start();
                        });
                    });

                }
            }
            else{
                studentNotifications = studentNotificationViewModel.getAllNotificationsForStudent(student.getIdStudent());

                    this.runOnUiThread(() ->{

                        for(StudentNotification sn : studentNotifications){
                            System.out.println("mptofcatipn " + sn.getStatus());
                            TextView textView1 = new TextView(this.getApplicationContext());
                            textView1.setText(sn.getDescription());
                            textView1.setBackgroundColor(Color.rgb(193, 145, 161));
                            textView1.setPadding(20, 10, 20, 10);
                            textView1.setTextColor(Color.rgb(0,0,0));
                            textView1.setTypeface(textView1.getTypeface(), Typeface.BOLD);


                            TextView textView2 = new TextView(this.getApplicationContext());
                            textView2.setBackgroundColor(Color.rgb(255,255,255));
                            textView2.setPadding(20, 5, 5, 10);

                            linearLayout.addView(textView2);
                            linearLayout.addView(textView1);
                        }
                    });


            }


        }).start();


    }
}

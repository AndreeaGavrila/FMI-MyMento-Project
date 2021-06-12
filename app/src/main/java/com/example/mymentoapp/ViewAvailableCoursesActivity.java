package com.example.mymentoapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mymentoapp.model.CourseToTeach;
import com.example.mymentoapp.model.CourseToTeachViewModel;
import com.example.mymentoapp.model.Notification;
import com.example.mymentoapp.model.NotificationViewModel;
import com.example.mymentoapp.model.Student;
import com.example.mymentoapp.model.StudentViewModel;
import com.example.mymentoapp.model.TaughtCourse;
import com.example.mymentoapp.model.TaughtCourseViewModel;
import com.example.mymentoapp.model.Tutor;
import com.example.mymentoapp.model.TutorViewModel;
import com.example.mymentoapp.model.TutorWithNotifications;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewAvailableCoursesActivity extends AppCompatActivity {
    private List<CourseToTeach> courseToTeachList;
    private List<CourseToTeach> courseToTeachList2;
    private List<TaughtCourse> taughtCoursesList;
    private List<Notification> notificationList;
    private final List<CourseToTeach> unavailableCourseList = new ArrayList<>();
    private final ArrayList<Map<Integer, String>> tutorAndCourses = new ArrayList<>();

    private CourseToTeachViewModel courseToTeachViewModel;
    private TutorViewModel tutorViewModel;
    private StudentViewModel studentViewModel;
    private TaughtCourseViewModel taughtCourseViewModel;
    private NotificationViewModel notificationViewModel;

    private Student student;
    private TutorWithNotifications tutorWithNotifications;
    private Tutor tutor;

    private LinearLayout layout, linearLayout;
    private Button btn;


    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.N)
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
            taughtCourseViewModel = new TaughtCourseViewModel(this.getApplication());
            notificationViewModel = new NotificationViewModel(this.getApplication());


            student = studentViewModel.getStudentByUsername(studentName);
            courseToTeachList = courseToTeachViewModel.getAllCoursesWithout(student.getIdStudent());
            taughtCoursesList = taughtCourseViewModel.getAllTaughtCourses();
            notificationList = notificationViewModel.getAllNotificationsSentByStudent(student.getIdStudent());

            courseToTeachList2 = new ArrayList<>();

            for(CourseToTeach c : courseToTeachList){
                if(c.getCourseName().equals(courseName)){
                    courseToTeachList2.add(c);
                }
            }

            for(CourseToTeach c : courseToTeachList2) {
                int gasit = 0;
                for(TaughtCourse t : taughtCoursesList){
                    if(c.getIdCourseToTeach() == t.getIdCourseToTeach() && c.getId_FkTutor() == t.getId_FkTutor()
                            && t.getId_FkStudent() == student.getIdStudent()){
                        gasit = 1;
                    }
                }
                for(Notification n : notificationList){
                    if(c.getId_FkTutor() == n.getId_FkTutor() && c.getIdCourseToTeach() == n.getId_FkCourseToTeach() &&
                            student.getIdStudent() == n.getId_FkStudent()){
                        gasit = 1;
                    }
                }

                if(gasit == 0){
                    Map<Integer, String> m1 = new HashMap<>();
                    m1.put((int) c.getId_FkTutor(), c.getCourseName() + "\n" + tutorViewModel.getTutorById((int) c.getId_FkTutor()).getFirstName()
                            + " " + tutorViewModel.getTutorById((int) c.getId_FkTutor()).getLastName() + "\n");
                    tutorAndCourses.add(m1);
                }

            }

            if(courseToTeachList2.size() > 0) {
                new Thread(() -> {
                    tutorAndCourses.forEach(item ->
                            item.forEach((k, v) -> {
                                this.runOnUiThread(() -> {

                                    TextView textView = new TextView(this.getApplicationContext());

                                    textView.setText(v);
                                    textView.setBackgroundColor(Color.rgb(214, 215, 215));
                                    linearLayout = new LinearLayout(this.getApplicationContext());
                                    linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                                    linearLayout.setBackgroundColor(Color.rgb(25, 55, 106));
                                    linearLayout.setPadding(10, 10, 10, 10);
                                    linearLayout.removeAllViews();
                                    linearLayout.addView(textView);

                                    new Thread(() -> {
                                        tutor = tutorViewModel.getTutorById(k);
                                    }).start();

                                    btn = new Button(this.getApplicationContext());
                                    btn.setText("SEND REQUEST");

                                    btn.setOnClickListener(t -> {
                                        Toast.makeText(getApplicationContext(), "REQUEST SENT", Toast.LENGTH_SHORT).show();

                                        Notification notification = new Notification();
                                        notification.setId_FkStudent(student.getIdStudent());
                                        for(CourseToTeach c : courseToTeachList2){
                                            if(c.getId_FkTutor() == tutor.getIdStudent()){
                                                notification.setId_FkCourseToTeach(c.getIdCourseToTeach());
                                            }
                                        }

                                        List<Notification> notificationList = new ArrayList<>();
                                        notificationList.add(notification);

                                        if (tutor != null) {
                                            System.out.println("in if tutor" + tutor.getFirstName());
                                            this.runOnUiThread(() -> {
                                                tutorWithNotifications = new TutorWithNotifications(tutor, notificationList);
                                                tutorViewModel.insertTutorWithNotifications(tutorWithNotifications);
                                            });
                                        }
                                        btn.setBackgroundColor(Color.DKGRAY);
                                        btn.setClickable(false);
                                        finish();
                                        startActivity(getIntent());

                                    });

                                    linearLayout.addView(btn);
                                    layout.addView(linearLayout);

                                });
                            }));
                }).start();

            }
            else {
                System.out.println("No courses!");

            }

        }).start();

    }
}

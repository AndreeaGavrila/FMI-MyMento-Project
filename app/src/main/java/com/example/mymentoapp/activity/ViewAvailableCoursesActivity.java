package com.example.mymentoapp.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.mymentoapp.R;
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
    private final ArrayList<Map<Integer, String>> tutorAndCourses = new ArrayList<>();

    private CourseToTeachViewModel courseToTeachViewModel;
    private TutorViewModel tutorViewModel;
    private StudentViewModel studentViewModel;
    private TaughtCourseViewModel taughtCourseViewModel;
    private NotificationViewModel notificationViewModel;

    private Student student;
    private TutorWithNotifications tutorWithNotifications;
    private Tutor tutor;

    LinearLayout layout, linearLayout;
    Button btn, backHome;
    Toolbar toolbar;
    TextView textViewCourses;


    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_available_courses);

        toolbar = findViewById(R.id.toolbar_home);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        layout = findViewById(R.id.layout_available_courses);
        backHome = findViewById(R.id.back_home);
        textViewCourses = findViewById(R.id.available_courses);

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
            int length = taughtCoursesList.size();

            courseToTeachList2 = new ArrayList<>();

            for(CourseToTeach c : courseToTeachList){
                if(c.getCourseName().equals(courseName)){
                    courseToTeachList2.add(c);
                }
            }

            for(CourseToTeach c : courseToTeachList2) {
                int gasit = 0;
                for(TaughtCourse t : taughtCoursesList){
                    if (c.getIdCourseToTeach() == t.getIdCourseToTeach() && c.getId_FkTutor() == t.getId_FkTutor()
                            && t.getId_FkStudent() == student.getIdStudent()) {
                        gasit = 1;
                        break;
                    }
                }
                for(Notification n : notificationList){
                    if (c.getId_FkTutor() == n.getId_FkTutor() && c.getIdCourseToTeach() == n.getId_FkCourseToTeach() &&
                            student.getIdStudent() == n.getId_FkStudent()) {
                        gasit = 1;
                        break;
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
                                    textViewCourses.setVisibility(View.VISIBLE);

                                    TextView textView = new TextView(this.getApplicationContext());
                                    textView.setText(v);

                                    textView.setPadding(10, 10, 5, 5);
                                    textView.setTextColor(Color.rgb(255, 255, 255));
                                    textView.setGravity(Gravity.CENTER);
                                    textView.setTextSize(18);;

                                    TextView textView1 =  new TextView(this.getApplicationContext());
                                    textView1.setBackgroundColor(Color.rgb(196, 201, 208));
                                    TextView textView3 = new TextView(this.getApplicationContext());
                                    textView3.setBackgroundColor(Color.rgb(1,24,71));

                                    linearLayout = new LinearLayout(this.getApplicationContext());
                                    linearLayout.setOrientation(LinearLayout.VERTICAL);
                                    linearLayout.setBackgroundColor(Color.rgb(25, 55, 106));

                                    btn = new Button(this.getApplicationContext());

                                    btn.setText("SEND REQUEST");
                                    btn.setTextColor(Color.rgb(0,0,0));
                                    btn.setTextSize(18);

                                    new Thread(() -> {
                                        tutor = tutorViewModel.getTutorById(k);
                                    }).start();

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

                                    linearLayout.addView(textView3);
                                    linearLayout.addView(textView);
                                    linearLayout.addView(btn);
                                    linearLayout.addView(textView1);
                                    layout.addView(linearLayout);

                                });
                            }));
                }).start();

            }
            else {
                this.runOnUiThread(() ->{
                    TextView txt = findViewById(R.id.available_courses);
                    txt.setVisibility(View.INVISIBLE);
                    TextView textView = new TextView(this.getApplicationContext());
                    textView.setPadding(10, 10, 5, 5);
                    textView.setTextColor(Color.rgb(0, 0, 0));
                    textView.setGravity(Gravity.CENTER);
                    textView.setTextSize(18);;
                    textView.setBackgroundColor(Color.rgb(196, 201, 208));
                    textView.setText("There are no courses here yet :(");

                    layout.addView(textView);
                });


            }

        }).start();

        backHome.setOnClickListener(v -> {
            Intent intent = new Intent(ViewAvailableCoursesActivity.this, WelcomeActivity.class);
            intent.putExtra("studentName", studentName);
            startActivity(intent);
        });

    }
}

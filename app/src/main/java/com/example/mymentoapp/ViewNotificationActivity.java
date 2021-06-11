package com.example.mymentoapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mymentoapp.model.CourseToTeachViewModel;
import com.example.mymentoapp.model.Notification;
import com.example.mymentoapp.model.NotificationViewModel;
import com.example.mymentoapp.model.SpecificCourseViewModel;
import com.example.mymentoapp.model.Student;
import com.example.mymentoapp.model.StudentViewModel;
import com.example.mymentoapp.model.TutorViewModel;

import java.util.ArrayList;

public class ViewNotificationActivity extends AppCompatActivity {

    private StudentViewModel studentViewModel;
    private TutorViewModel tutorViewModel;
    private NotificationViewModel notificationViewModel;
    private ArrayList<Notification> notificationArrayList = new ArrayList<>();

    LinearLayout linearLayout;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_notifications);

        linearLayout = findViewById(R.id.layout_notifications);
        textView = findViewById(R.id.notification_textview);

        //username
        Bundle bundle = getIntent().getExtras();
        String studentName = bundle.getString("studentName");


        new Thread(() -> {

            studentViewModel = new StudentViewModel(this.getApplication());
            tutorViewModel = new TutorViewModel(this.getApplication());
            notificationViewModel = new NotificationViewModel(this.getApplication());

            if(tutorViewModel.getTutor(studentName) != null){
                int idTutor = tutorViewModel.getTutor(studentName).getIdStudent();
                notificationArrayList = (ArrayList<Notification>) notificationViewModel.getAllNotificationsForTutor(idTutor);
            }
            for(Notification n : notificationArrayList){
                Button btn = new Button(this.getApplicationContext());
                Student student = studentViewModel.getStudent((int) n.getId_FkStudent());
                String text = n.getDescription()  + student.getFirstName() + " " + student.getLastName() + " \n" ;
                btn.setText(text);
                this.runOnUiThread(() ->{
                    linearLayout.addView(btn);
                });
                btn.setOnClickListener(v ->{

                });


                System.out.println(n.toString());
            }

        }).start();


    }
}

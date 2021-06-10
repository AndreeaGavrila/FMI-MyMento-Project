package com.example.mymentoapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mymentoapp.data.StudentDao;
import com.example.mymentoapp.data.TutorDao;
import com.example.mymentoapp.model.Tutor;
import com.example.mymentoapp.util.MyRoomDatabase;

public class TutorContactActivity extends AppCompatActivity {
    StudentDao studentDao;
    TutorDao tutorDao;
    MyRoomDatabase roomDatabase;
    TextView title;
    Button mail;
    Button phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.tutor_contact);
        Bundle bundle = getIntent().getExtras();
        String tutorLastName = bundle.getString("tutorLastName");
        String tutorFirstName = bundle.getString("tutorFirstName");

        title = findViewById(R.id.id_tutor_name);
        mail = findViewById(R.id.id_tutor_mail);
        phone = findViewById(R.id.id_tutor_phone);
        System.out.println(tutorLastName.concat(" ".concat(tutorFirstName)));
        title.setText(tutorLastName.concat(" ".concat(tutorFirstName)));
        roomDatabase = MyRoomDatabase.getDatabase(getApplicationContext());
        studentDao = roomDatabase.studentDao();
        tutorDao = roomDatabase.tutorDao();
        new Thread(() -> {
            Tutor currentTutor = tutorDao.getTutorByName(tutorLastName, tutorFirstName);
            System.out.println(currentTutor.getUsername());
            System.out.println(currentTutor.getPhoneNumber());
            System.out.println(currentTutor.getEmail());
            phone.setText(currentTutor.getPhoneNumber());
            mail.setText(currentTutor.getEmail());
            phone.setOnClickListener(v->{
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:"+currentTutor.getPhoneNumber()));
                startActivity(callIntent);
            });
            mail.setOnClickListener(v->{
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                String sendEmail = "mailto:"+currentTutor.getEmail();
                emailIntent.setType("message/rcf822");
                emailIntent.putExtra(Intent.EXTRA_EMAIL,sendEmail);
                startActivity(Intent.createChooser(emailIntent,"Send Email"));
            });
        }).start();

    }
}

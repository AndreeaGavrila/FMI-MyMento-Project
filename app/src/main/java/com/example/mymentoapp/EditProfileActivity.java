package com.example.mymentoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.mymentoapp.data.StudentDao;
import com.example.mymentoapp.data.StudentRepository;
import com.example.mymentoapp.data.TutorDao;
import com.example.mymentoapp.model.Student;
import com.example.mymentoapp.model.Tutor;
import com.example.mymentoapp.util.MyRoomDatabase;

import java.util.concurrent.atomic.AtomicReference;

public class EditProfileActivity extends AppCompatActivity {
    StudentRepository repository;
    EditText firstName, lastName, phoneNumber, email;
    RadioGroup radioGroupStudyYear, radioGroupDomain;
    RadioButton radioYear1, radioYear2, radioYear3, radioYear4, radioInfo, radioMath, radioCTI;
    Button edit;
    Student student;
    Button becameTutorBtn;
    MyRoomDatabase roomDatabase;
    StudentDao studentDao;
    TutorDao tutorDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        Bundle bundle = getIntent().getExtras();
        int studentId = bundle.getInt("studentId");

        firstName = findViewById(R.id.first_name_edit);
        lastName = findViewById(R.id.last_name_edit);
        phoneNumber = findViewById(R.id.phone_edit);
        email = findViewById(R.id.email_edit);
        radioGroupStudyYear = findViewById(R.id.radio_year_edit);
        radioGroupDomain = findViewById(R.id.radio_domain_edit);
        radioYear1 = findViewById(R.id.radio_year1);
        radioYear2 = findViewById(R.id.radio_year2);
        radioYear3 = findViewById(R.id.radio_year3);
        radioYear4 = findViewById(R.id.radio_year4);
        radioCTI = findViewById(R.id.radio_cti);
        radioInfo = findViewById(R.id.radio_info);
        radioMath = findViewById(R.id.radio_math);
        becameTutorBtn = findViewById(R.id.became_tutor_btn);
        edit = findViewById(R.id.btn_edit);


        repository = new StudentRepository(this.getApplication());


        new Thread(() -> {

            roomDatabase = MyRoomDatabase.getDatabase(getApplicationContext());
            studentDao = roomDatabase.studentDao();
            tutorDao = roomDatabase.tutorDao();
            student = studentDao.getStudent(studentId);
            Tutor t = tutorDao.getTutorByUserName(student.getEmail());
            System.out.println("id student este " + student.getIdStudent());

            if(t != null){
                System.out.println(student.getIdStudent() + student.getFirstName());
                System.out.println("este tutore");
                becameTutorBtn.setVisibility(View.GONE);
            }


            student = studentDao.getStudent(studentId);
            firstName.setText(student.getFirstName());
            lastName.setText(student.getLastName());
            phoneNumber.setText(student.getPhoneNumber());
            email.setText(student.getEmail());
            String year = student.getStudyYear();
            switch (year) {
                case "I":
                    radioYear1.setChecked(true);
                    break;
                case "II":
                    radioYear2.setChecked(true);
                    break;
                case "III":
                    radioYear3.setChecked(true);
                    break;
                default:
                    radioYear4.setChecked(true);
                    break;
            }
            String domain = student.getStudyDomain();
            switch (domain){
                case "Mathematics":
                    radioMath.setChecked(true);
                    break;
                case "Informatics":
                    radioInfo.setChecked(true);
                    break;
                case "CTI":
                    radioCTI.setChecked(true);
                    break;
            }
        }).start();

        edit.setOnClickListener(v -> {
            student.setFirstName(firstName.getText().toString());
            student.setLastName(lastName.getText().toString());
            student.setEmail(email.getText().toString());
            student.setPhoneNumber(phoneNumber.getText().toString());

            int checkedStudyYearId = radioGroupStudyYear.getCheckedRadioButtonId();
            RadioButton checkedStudyYear = findViewById(checkedStudyYearId);
            student.setStudyYear(checkedStudyYear.getText().toString());

            int checkedDomainId = radioGroupDomain.getCheckedRadioButtonId();
            RadioButton checkedDomain = findViewById(checkedDomainId);
            student.setStudyDomain(checkedDomain.getText().toString());

            new Thread(() -> {
                studentDao.updateStudent(student);
                Intent intent = new Intent(EditProfileActivity.this, ViewProfileActivity.class);
                intent.putExtra("idStudent", studentId);
                startActivity(intent);
            }).start();
        });

    }
}
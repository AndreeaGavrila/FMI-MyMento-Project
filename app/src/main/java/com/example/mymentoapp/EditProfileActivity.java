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
import com.example.mymentoapp.model.Student;
import com.example.mymentoapp.util.MyRoomDatabase;

import java.util.concurrent.atomic.AtomicReference;

public class EditProfileActivity extends AppCompatActivity {
    StudentRepository repository;
    EditText firstName, lastName, phoneNumber, email;
    RadioGroup radioGroupStudyYear, radioGroupDomain;
    RadioButton radioYear1, radioYear2, radioYear3, radioYear4, radioInfo, radioMath, radioCTI;
    Button edit;

    MyRoomDatabase roomDatabase;
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

        edit = findViewById(R.id.btn_edit);

        repository = new StudentRepository(this.getApplication());

        final Student[] student = new Student[1];

        roomDatabase = MyRoomDatabase.getDatabase(getApplicationContext());
        StudentDao studentDao = roomDatabase.studentDao();

        new Thread(() -> {
            student[0] = studentDao.getStudent(studentId);
            firstName.setText(student[0].getFirstName());
            lastName.setText(student[0].getLastName());
            phoneNumber.setText(student[0].getPhoneNumber());
            email.setText(student[0].getEmail());
            String year = student[0].getStudyYear();
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
            String domain = student[0].getStudyDomain();
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
            student[0].setFirstName(firstName.getText().toString());
            student[0].setLastName(lastName.getText().toString());
            student[0].setEmail(email.getText().toString());
            student[0].setPhoneNumber(phoneNumber.getText().toString());

            int checkedStudyYearId = radioGroupStudyYear.getCheckedRadioButtonId();
            RadioButton checkedStudyYear = findViewById(checkedStudyYearId);
            student[0].setStudyYear(checkedStudyYear.getText().toString());

            int checkedDomainId = radioGroupDomain.getCheckedRadioButtonId();
            RadioButton checkedDomain = findViewById(checkedDomainId);
            student[0].setStudyDomain(checkedDomain.getText().toString());

            new Thread(() -> {
                studentDao.updateStudent(student[0]);
                Intent intent = new Intent(EditProfileActivity.this, ViewProfileActivity.class);
                intent.putExtra("idStudent", studentId);
                startActivity(intent);
            }).start();
        });

    }
}
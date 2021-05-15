package com.example.mymentoapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mymentoapp.data.StudentDao;

import com.example.mymentoapp.data.TutorDao;

import com.example.mymentoapp.model.RegisterViewModel;

import com.example.mymentoapp.model.Student;
import com.example.mymentoapp.model.StudentViewModel;

import com.example.mymentoapp.model.TutorViewModel;
import com.example.mymentoapp.util.MyRoomDatabase;


public class ProfileStudentActivity extends AppCompatActivity {

    EditText lastName, firstName, phoneNumber, email;
    RadioGroup radioGroupStudyYear, radioGroupDomain;

    Button btn_submit_student;

//    DatabaseHelper databaseHelper;

    Boolean insert;

    private RegisterViewModel registerViewModel;

    private StudentViewModel studentViewModel;
    //private StudentDao studentDao;

    private TutorViewModel tutorViewModel;
    private TutorDao tutorDao;

    MyRoomDatabase roomDatabase;
    StudentDao studentDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_profile);
        lastName = (EditText) findViewById(R.id.last_name_edit);
        firstName = (EditText) findViewById(R.id.first_name_edit);
        phoneNumber = (EditText) findViewById(R.id.phone_edit);
        email = (EditText) findViewById(R.id.email_edit);


        radioGroupStudyYear = (RadioGroup) findViewById(R.id.radio_year_edit);
        radioGroupDomain = (RadioGroup) findViewById(R.id.radio_domain_edit);

        btn_submit_student = (Button) findViewById(R.id.btn_edit);


        btn_submit_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String firstname = firstName.getText().toString();
                String lastname = lastName.getText().toString();
                String phonenumber = phoneNumber.getText().toString();
                String email1 = email.getText().toString();

                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                String phonePattern = "^\\+[0-9]{10,13}$";

                int selectedId = radioGroupDomain.getCheckedRadioButtonId();
                RadioButton selecteddomainbutton = (RadioButton) findViewById(selectedId);
                String domain = (String)selecteddomainbutton.getText();

                int selectedId2 = radioGroupStudyYear.getCheckedRadioButtonId();
                RadioButton selectedyearbutton = findViewById(selectedId2);
                String studyYear = (String)selectedyearbutton.getText();

                if (firstname.equals("") || lastname.equals("") || phonenumber.equals("") || email1.equals("")  ||
                        radioGroupDomain.getCheckedRadioButtonId() == -1 || radioGroupStudyYear.getCheckedRadioButtonId() == -1) {

                    Toast.makeText(getApplicationContext(), "Fields Required", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if (!(email1.matches(emailPattern))) {

                        Toast.makeText(getApplicationContext(), "INVALID MAIL", Toast.LENGTH_SHORT).show();
                    }

                    if (!(phonenumber.matches(phonePattern))) {

                        Toast.makeText(getApplicationContext(), "INVALID PHONE NUMBER", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Bundle bundle = getIntent().getExtras();
                        String username  = bundle.getString("username");

                        System.out.println("username student: " + username);

                        //String password = auxIntent.getStringExtra("registeredPassword");
                        roomDatabase = MyRoomDatabase.getDatabase(getApplicationContext());
                        StudentDao studentDao = roomDatabase.studentDao();
                        new Thread(() -> {
                            System.out.println("in thread");
                            Student student = studentDao.getStudentByUsername(username);
                            student.setStudyDomain(domain);
                            student.setStudyYear(studyYear);
                            student.setPhoneNumber(phonenumber);
                            student.setEmail(email1);
                            student.setLastName(lastname);
                            student.setFirstName(firstname);
                            studentDao.updateStudent(student);
                            Intent intent = new Intent (ProfileStudentActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }).start();

                    }


                }

            }
        });


    }
}


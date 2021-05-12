package com.example.mymentoapp;

import android.app.AppComponentFactory;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mymentoapp.data.StudentDao;
import com.example.mymentoapp.data.StudentRepository;

import com.example.mymentoapp.data.TutorDao;
import com.example.mymentoapp.data.TutorRepository;

import com.example.mymentoapp.model.Login;
import com.example.mymentoapp.model.LoginViewModel;

import com.example.mymentoapp.model.Register;
import com.example.mymentoapp.model.RegisterViewModel;

import com.example.mymentoapp.model.Student;
import com.example.mymentoapp.model.StudentViewModel;

import com.example.mymentoapp.model.Tutor;
import com.example.mymentoapp.model.TutorViewModel;

import com.example.mymentoapp.util.MyRoomDatabase;

import java.util.List;
import java.util.Objects;


public class ProfileStudentActivity extends AppCompatActivity {

    EditText lastName, firstName, phoneNumber, email;
    RadioGroup radioGroupStudyYear, radioGroupDomain;

    Button btn_submit_student;

//    DatabaseHelper databaseHelper;

    Boolean insert;

    private RegisterViewModel registerViewModel;

    private StudentViewModel studentViewModel;
    private StudentDao studentDao;

    private TutorViewModel tutorViewModel;
    private TutorDao tutorDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_profile);

//        databaseHelper = new DatabaseHelper(this);

        lastName = (EditText) findViewById(R.id.last_name_text);
        firstName = (EditText) findViewById(R.id.first_name_text);
        phoneNumber = (EditText) findViewById(R.id.phone_text);
        email = (EditText) findViewById(R.id.email_text);


        radioGroupStudyYear = (RadioGroup) findViewById(R.id.radio_year);
        radioGroupDomain = (RadioGroup) findViewById(R.id.radio_domain);

        btn_submit_student = (Button) findViewById(R.id.button_submit_student);


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
                RadioButton selectedyearbutton = (RadioButton) findViewById(selectedId2);
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
                        Intent auxIntent = getIntent();

                        String username = auxIntent.getStringExtra("registeredUsername");
                        String password = auxIntent.getStringExtra("registeredPassword");

//                        if (databaseHelper.getStudent(username) != -1)
//                        {
//                            //update
//                            System.out.println("entered");
//                            if(databaseHelper.updateStudent(databaseHelper.getStudent(username),firstname,lastname,email1,phonenumber,studyYear,domain))
//                            {
//                                System.out.println("updated");
//
//                                Intent intent = new Intent(ProfileStudentActivity.this, Login.class);
//                                intent.putExtra("registeredUsername", username);
//
//                                startActivity(intent);
//
//                            }
//                        }

                    }


                }

            }
        });


    }
}


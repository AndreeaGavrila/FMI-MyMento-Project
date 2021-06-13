package com.example.mymentoapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mymentoapp.data.StudentDao;
import com.example.mymentoapp.model.AssignCourse;
import com.example.mymentoapp.model.SpecificCourse;
import com.example.mymentoapp.model.Student;
import com.example.mymentoapp.model.StudentViewModel;
import com.example.mymentoapp.model.StudentWithCourse;
import com.example.mymentoapp.util.MyRoomDatabase;

import java.util.ArrayList;


public class ProfileStudentActivity extends AppCompatActivity {


    private AssignCourse assignCourse;
    private StudentViewModel studentViewModel;

    EditText lastName, firstName, phoneNumber, email;
    RadioGroup radioGroupStudyYear, radioGroupDomain, radioGroupSpec;
    Button btn_submit_student;
    RadioButton radioCTI, radioMath, radioInfo;
    String studyYear1, domain1, specialization1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_profile);
        lastName = findViewById(R.id.last_name_edit);
        firstName = findViewById(R.id.first_name_edit);
        phoneNumber = findViewById(R.id.phone_edit);
        email = findViewById(R.id.email_edit);
        radioGroupSpec = findViewById(R.id.radio_group_spec);
        radioGroupStudyYear = findViewById(R.id.radio_year_edit);
        radioGroupDomain = findViewById(R.id.radio_domain_edit);
        btn_submit_student = findViewById(R.id.btn_edit);

        radioCTI = findViewById(R.id.radio_cti);
        radioInfo = findViewById(R.id.radio_info);
        radioMath = findViewById(R.id.radio_math);

        specialization1 = "" ;
        studyYear1 = "";
        domain1 = "";

        radioGroupStudyYear.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton rb2 = findViewById(checkedId);
            studyYear1 = rb2.getText().toString();
            if(studyYear1.equals("IV")){
                radioGroupSpec.setVisibility(View.GONE);
                radioInfo.setEnabled(false);
                radioMath.setEnabled(false);
                radioCTI.setChecked(true);
            }else{
                radioMath.setEnabled(true);
                radioInfo.setEnabled(true);
                radioCTI.setEnabled(true);
            }
        });

        radioGroupDomain.setOnCheckedChangeListener((group, checkedId) -> {

            RadioButton rb = findViewById(checkedId);
            domain1 = rb.getText().toString();
            System.out.println("study+year" +rb.getText());

            radioGroupStudyYear.setOnCheckedChangeListener((group1, checkedId2) -> {
                RadioButton rb2 = findViewById(checkedId2);
                studyYear1 = rb2.getText().toString();
                if(domain1.equals("Mathematics") && (studyYear1.equals("II") || studyYear1.equals("III"))){
                    radioGroupSpec.setVisibility(View.VISIBLE);
                    radioGroupSpec.setOnCheckedChangeListener((group11, checkedId3) -> {
                        RadioButton rb3 = findViewById(checkedId3);
                        specialization1 = rb3.getText().toString();
                    });
                }
                if(studyYear1.equals("IV")){
                    radioGroupSpec.setVisibility(View.GONE);
                    radioInfo.setEnabled(false);
                    radioMath.setEnabled(false);
                    radioCTI.setChecked(true);
                }else{
                    radioMath.setEnabled(true);
                    radioInfo.setEnabled(true);
                    radioCTI.setEnabled(true);
                }
                if(studyYear1.equals("I") || studyYear1.equals("IV")){
                    radioGroupSpec.setVisibility(View.GONE);
                }

            });
            if(domain1.equals("Mathematics") && (studyYear1.equals("II") || studyYear1.equals("III"))){
                radioGroupSpec.setVisibility(View.VISIBLE);
                radioGroupSpec.setOnCheckedChangeListener((group12, checkedId3) -> {
                    RadioButton rb3 = findViewById(checkedId3);
                    specialization1 = rb3.getText().toString();

                });
            }


            if(radioGroupSpec.getVisibility() == View.VISIBLE){
                radioGroupSpec.setOnCheckedChangeListener((group13, checkedId3) -> {
                    RadioButton rb3 = findViewById(checkedId3);
                    specialization1 = rb3.getText().toString();

                });
            }
            if(!(domain1.equals("Mathematics") && (studyYear1.equals("II") || studyYear1.equals("III")))){
                radioGroupSpec.setVisibility(View.GONE);
            }

        });

        if(radioGroupSpec.getVisibility() == View.VISIBLE){
            radioGroupSpec.setOnCheckedChangeListener((group, checkedId3) -> {
                RadioButton rb3 = findViewById(checkedId3);
                specialization1 = rb3.getText().toString();

            });
        }

        btn_submit_student.setOnClickListener(v -> {

            String firstname = firstName.getText().toString();
            String lastname = lastName.getText().toString();
            String number = phoneNumber.getText().toString();
            String email1 = email.getText().toString();
            String phonePattern = "^\\+[0-9]{10,13}$";


            if (firstname.equals("") || lastname.equals("") || number.equals("") || email1.equals("")  ||
                    radioGroupDomain.getCheckedRadioButtonId() == -1 || radioGroupStudyYear.getCheckedRadioButtonId() == -1) {

                Toast.makeText(getApplicationContext(), "Fields Required", Toast.LENGTH_SHORT).show();
            }
            else
            {
                if (!Patterns.EMAIL_ADDRESS.matcher(email1).matches()){
                    Toast.makeText(getApplicationContext(), "INVALID MAIL", Toast.LENGTH_SHORT).show();
                }

                if (!(number.matches(phonePattern))) {

                    Toast.makeText(getApplicationContext(), "INVALID PHONE NUMBER", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Bundle bundle = getIntent().getExtras();
                    String username  = bundle.getString("username");
                    System.out.println("username student: " + username);

                    new Thread(() -> {
                        studentViewModel = new StudentViewModel(ProfileStudentActivity.this.getApplication());
                        System.out.println("in thread");
                        Student student = studentViewModel.getStudentByUsername(username);
                        student.setStudyDomain(domain1);
                        student.setStudyYear(studyYear1);
                        student.setPhoneNumber(number);
                        student.setEmail(email1);
                        student.setLastName(lastname);
                        student.setFirstName(firstname);



                        System.out.println("specialization" + specialization1);
                        System.out.println("domain" + domain1);
                        System.out.println("year" + studyYear1);
                        assignCourse = new AssignCourse(studyYear1, domain1, specialization1);

                        StudentWithCourse studentWithCourse = new StudentWithCourse(student, assignCourse.getSpecificCourseList());
                        studentViewModel.insertStudentWithCourses(studentWithCourse);
                        studentViewModel.updateStudent(student);
                        Intent intent = new Intent (ProfileStudentActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }).start();

                }

            }

        });


    }
}


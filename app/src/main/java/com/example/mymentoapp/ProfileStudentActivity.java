package com.example.mymentoapp;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mymentoapp.data.StudentDao;

import com.example.mymentoapp.data.TutorDao;

import com.example.mymentoapp.model.AssignCourse;
import com.example.mymentoapp.model.RegisterViewModel;

import com.example.mymentoapp.model.SpecificCourse;
import com.example.mymentoapp.model.Student;
import com.example.mymentoapp.model.StudentViewModel;

import com.example.mymentoapp.model.StudentWithCourse;
import com.example.mymentoapp.model.TutorViewModel;
import com.example.mymentoapp.util.MyRoomDatabase;

import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;


public class ProfileStudentActivity extends AppCompatActivity {

    EditText lastName, firstName, phoneNumber, email;
    RadioGroup radioGroupStudyYear, radioGroupDomain, radioGroupSpec;
    Button btn_submit_student;
    MyRoomDatabase roomDatabase;
    String studyYear1, domain1, specialization1;
    AssignCourse assignCourse;
    private ArrayList<String> courseNameList;

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

//        int studyYearId = radioGroupStudyYear.getCheckedRadioButtonId();
//        RadioButton yearButton = (RadioButton) findViewById(studyYearId);
//
//        int domainId = radioGroupDomain.getCheckedRadioButtonId();
//        RadioButton domainButton = (RadioButton) findViewById(domainId);
        courseNameList = new ArrayList<>();
        specialization1 = "" ;
        studyYear1 = "";
        domain1 = "";

        radioGroupStudyYear.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton rb2 = findViewById(checkedId);
            studyYear1 = rb2.getText().toString();
            if(studyYear1.equals("I")){
                radioGroupSpec.setVisibility(View.GONE);
            }
        });


        radioGroupDomain.setOnCheckedChangeListener((group, checkedId) -> {

            // checkedId is the RadioButton selected
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
                if(studyYear1.equals("I") || studyYear1.equals("IV")){
                    radioGroupSpec.setVisibility(View.GONE);
                }

            });
            if(domain1.equals("Mathematics") && (studyYear1.equals("II") || studyYear1.equals("III"))){
                radioGroupSpec.setVisibility(View.VISIBLE);
                radioGroupSpec.setOnCheckedChangeListener((group13, checkedId3) -> {
                    RadioButton rb3 = findViewById(checkedId3);
                    specialization1 = rb3.getText().toString();

                });
            }


            if(radioGroupSpec.getVisibility() == View.VISIBLE){
                radioGroupSpec.setOnCheckedChangeListener((group12, checkedId3) -> {
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
            String phonenumber = phoneNumber.getText().toString();
            String email1 = email.getText().toString();

            String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
            String phonePattern = "^\\+[0-9]{10,13}$";


//                int selectedId = radioGroupDomain.getCheckedRadioButtonId();
//                RadioButton selecteddomainbutton = (RadioButton) findViewById(selectedId);
//                String domain = (String)selecteddomainbutton.getText();
//
//                String specialization = null;
//
//                int selectedId3 = radioGroupSpec.getCheckedRadioButtonId();
//                if(selectedId3 > -1){
//                    RadioButton specializationButton = (RadioButton) findViewById(selectedId3);
//                    specialization  = (String)selecteddomainbutton.getText();
//                }
//                int selectedId2 = radioGroupStudyYear.getCheckedRadioButtonId();
//                RadioButton selectedyearbutton = findViewById(selectedId2);
//                String studyYear = (String)selectedyearbutton.getText();



            if (firstname.equals("") || lastname.equals("") || phonenumber.equals("") || email1.equals("")  ||
                    radioGroupDomain.getCheckedRadioButtonId() == -1 || radioGroupStudyYear.getCheckedRadioButtonId() == -1) {

                Toast.makeText(getApplicationContext(), "Fields Required", Toast.LENGTH_SHORT).show();
            }
            if (!(email1.matches(emailPattern))) {

                    Toast.makeText(getApplicationContext(), "INVALID MAIL", Toast.LENGTH_SHORT).show();
                }

                if (!(phonenumber.matches(phonePattern))) {

                    Toast.makeText(getApplicationContext(), "INVALID PHONE NUMBER", Toast.LENGTH_SHORT).show();
                }
                if(!domain1.equals("CTI") && (studyYear1.equals("IV"))){
                    Toast.makeText(getApplicationContext(), "ONLY CTI HAS 4 YEARS", Toast.LENGTH_SHORT).show();
                }
                else
                {

                    Bundle bundle = getIntent().getExtras();
                    String username  = bundle.getString("username");

                    System.out.println("username student: " + username);

                    //String password = auxIntent.getStringExtra("registeredPassword");
                    roomDatabase = MyRoomDatabase.getDatabase(getApplicationContext());
                    StudentDao studentDao = roomDatabase.studentDao();

//                        String finalSpecialization = specialization;
                    new Thread(() -> {

                        System.out.println("in thread");
                        Student student = studentDao.getStudentByUsername(username);
                        student.setStudyDomain(domain1);
                        student.setStudyYear(studyYear1);
                        student.setPhoneNumber(phonenumber);
                        student.setEmail(email1);
                        student.setLastName(lastname);
                        student.setFirstName(firstname);



                        System.out.println("specialization" + specialization1);
                        System.out.println("domain" + domain1);
                        System.out.println("year" + studyYear1);
                        assignCourse = new AssignCourse(studyYear1, domain1, specialization1);

                        courseNameList.clear();
                        for(SpecificCourse specificCourse: assignCourse.getSpecificCourseList()) {
                            courseNameList.add(specificCourse.getCourseName());
                        }
                        System.out.println("CURSURILE");
                        for(SpecificCourse specificCourse: assignCourse.getSpecificCourseList()) {
                            System.out.println((specificCourse.getCourseName()));
                        }

                        StudentWithCourse studentWithCourse = new StudentWithCourse(student, assignCourse.getSpecificCourseList());
                        StudentViewModel.insertStudentWithCourses(studentWithCourse);
                        studentDao.updateStudent(student);
                        Intent intent = new Intent (ProfileStudentActivity.this, LoginActivity.class);
//                            intent.putExtra("lista_cursuri", courseNameList);
//                            intent.putExtra("from", "ProfileStudentActivity");
                        startActivity(intent);
                    }).start();

                }



        });


    }
}


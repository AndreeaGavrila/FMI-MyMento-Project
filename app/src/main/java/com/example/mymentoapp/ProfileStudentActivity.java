package com.example.mymentoapp;

import android.content.Intent;
import android.os.Bundle;
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

    AssignCourse assignCourse;
    private ArrayList<String> courseNameList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_profile);
        lastName = (EditText) findViewById(R.id.last_name_edit);
        firstName = (EditText) findViewById(R.id.first_name_edit);
        phoneNumber = (EditText) findViewById(R.id.phone_edit);
        email = (EditText) findViewById(R.id.email_edit);
        radioGroupSpec = findViewById(R.id.radio_group_spec);

        radioGroupStudyYear = (RadioGroup) findViewById(R.id.radio_year_edit);
        radioGroupDomain = (RadioGroup) findViewById(R.id.radio_domain_edit);

        btn_submit_student = (Button) findViewById(R.id.btn_edit);

        courseNameList = new ArrayList<String>();

        radioGroupDomain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {

            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                final String[] specialization = {""};
                final String[] studyYear = {""};
                RadioButton rb = (RadioButton)findViewById(checkedId);

                System.out.println("rb.gettext" +rb.getText());
                if(rb.getText().toString().equals("Mathematics")){
                    System.out.println("in if");
                    radioGroupStudyYear.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
                        public void onCheckedChanged(RadioGroup group, int checkedId2) {

                            System.out.println("in al doilea listener");;
                            RadioButton rb2 = (RadioButton)findViewById(checkedId2);
                            studyYear[0] = rb2.getText().toString();
                            if(rb2.getText().toString().equals("II") || rb2.getText().toString().equals("III")){
                                radioGroupSpec.setVisibility(View.VISIBLE);
                                radioGroupSpec.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                    public void onCheckedChanged(RadioGroup group, int checkedId3) {
                                        RadioButton rb3 = (RadioButton)findViewById(checkedId3);
                                        specialization[0] = rb.getText().toString();

                                    }
                                });
                            }



                        }
                    });
                }
                assignCourse = new AssignCourse(studyYear[0], rb.getText().toString(), specialization);

                for(SpecificCourse specificCourse: assignCourse.getSpecificCourseList()){
                    courseNameList.add(specificCourse.getCourseName());
                    System.out.println(specificCourse.getCourseName());
                }

            }
        });


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

                String specialization = null;

                int selectedId3 = radioGroupSpec.getCheckedRadioButtonId();
                if(selectedId3 > -1){
                    RadioButton specializationButton = (RadioButton) findViewById(selectedId3);
                    specialization  = (String)selecteddomainbutton.getText();
                }
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


                        String finalSpecialization = specialization;
                        new Thread(() -> {

                            System.out.println("in thread");
                            Student student = studentDao.getStudentByUsername(username);
                            student.setStudyDomain(domain);
                            student.setStudyYear(studyYear);
                            student.setPhoneNumber(phonenumber);
                            student.setEmail(email1);
                            student.setLastName(lastname);
                            student.setFirstName(firstname);


                            StudentWithCourse studentWithCourse = new StudentWithCourse(student, assignCourse.getSpecificCourseList());
                            StudentViewModel.insertStudentWithCourses(studentWithCourse);
                            studentDao.updateStudent(student);
                            Intent intent = new Intent (ProfileStudentActivity.this, LoginActivity.class);
                            intent.putExtra("lista_cursuri", courseNameList);
                            startActivity(intent);
                        }).start();

                    }


                }

            }
        });


    }
}


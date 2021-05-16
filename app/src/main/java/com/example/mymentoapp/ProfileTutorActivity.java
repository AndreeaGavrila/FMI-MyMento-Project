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

import com.example.mymentoapp.model.AssignCourse;
import com.example.mymentoapp.model.CourseToTeach;
import com.example.mymentoapp.model.RegisterViewModel;

import com.example.mymentoapp.model.SpecificCourse;
import com.example.mymentoapp.model.CourseToTeach;
import com.example.mymentoapp.model.Student;
import com.example.mymentoapp.model.StudentViewModel;

import com.example.mymentoapp.model.StudentWithCourse;
import com.example.mymentoapp.model.Tutor;
import com.example.mymentoapp.model.TutorViewModel;
import com.example.mymentoapp.model.TutorWithCourse;
import com.example.mymentoapp.util.MyRoomDatabase;

import java.util.ArrayList;


public class ProfileTutorActivity extends AppCompatActivity {
    EditText lastName, firstName, phoneNumber, email, iban;
    RadioGroup radioGroupStudyYear, radioGroupDomain, radioGroupSpec;
    Button btn_submit_tutor;

    private RegisterViewModel registerViewModel;
    private StudentViewModel studentViewModel;
    private StudentDao studentDao;
    private TutorViewModel tutorViewModel;
    private TutorDao tutorDao;

    MyRoomDatabase roomDatabase;

    AssignCourse assignCourse;
    private ArrayList<String> courseNameList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tutor_profile);

        lastName = (EditText) findViewById(R.id.last_name_edit);
        firstName = (EditText) findViewById(R.id.first_name_edit);
        phoneNumber = (EditText) findViewById(R.id.phone_edit);
        email = (EditText) findViewById(R.id.email_edit);
        iban = findViewById(R.id.iban_id);

        radioGroupStudyYear = (RadioGroup) findViewById(R.id.radio_year_edit);

        radioGroupStudyYear = (RadioGroup) findViewById(R.id.radio_year_edit);

        radioGroupDomain = (RadioGroup) findViewById(R.id.radio_domain_edit);

        btn_submit_tutor = (Button) findViewById(R.id.btn_edit);

        courseNameList = new ArrayList<>();

        radioGroupDomain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {

            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                final String[] specialization = {""};
                final String[] studyYear = {""};
                RadioButton rb = (RadioButton)findViewById(checkedId);

                System.out.println("rb.gettext" +rb.getText());
                if(rb.getText().toString().equals("Mathematics")){
                    //System.out.println("in if");
                    radioGroupStudyYear.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
                        public void onCheckedChanged(RadioGroup group, int checkedId2) {
                            //System.out.println("in al doilea listener");;
                            RadioButton rb2 = (RadioButton)findViewById(checkedId2);
                            studyYear[0] = rb2.getText().toString();
                            if(rb2.getText().toString().equals("II") || rb2.getText().toString().equals("III")){
                                radioGroupSpec.setVisibility(View.VISIBLE);
                                radioGroupSpec.setOnCheckedChangeListener((group1, checkedId3) -> {
                                    RadioButton rb3 = (RadioButton)findViewById(checkedId3);
                                    specialization[0] = rb.getText().toString();

                                });
                            }else{
                                radioGroupSpec.setVisibility(View.GONE);
                            }
                        }
                    });
                }else{
                    radioGroupSpec.setVisibility(View.GONE);
                }
                assignCourse = new AssignCourse(studyYear[0], rb.getText().toString(), specialization);

//                for(SpecificCourse specificCourse: assignCourse.getSpecificCourseList()){
//                    courseNameList.add(specificCourse.getCourseName());
//                    System.out.println(specificCourse.getCourseName());
//                }
                  for(CourseToTeach courseToTeach: assignCourse.getCourseToTeachList()){
                        courseNameList.add(courseToTeach.getCourseName());
                        System.out.println(courseToTeach.getCourseName());
                  }


            }
        });


        btn_submit_tutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String firstname = firstName.getText().toString();
                String lastname = lastName.getText().toString();
                String phonenumber = phoneNumber.getText().toString();
                String email1 = email.getText().toString();
                String iban1 = iban.getText().toString();

                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                String phonePattern = "^\\+[0-9]{10,13}$";

                int selectedId = radioGroupDomain.getCheckedRadioButtonId();
                RadioButton selecteddomainbutton = (RadioButton) findViewById(selectedId);
                String domain = (String) selecteddomainbutton.getText();

                String specialization = null;

                int selectedId3 = radioGroupSpec.getCheckedRadioButtonId();
                if(selectedId3 > -1){
                    RadioButton specializationButton = (RadioButton) findViewById(selectedId3);
                    specialization  = (String)selecteddomainbutton.getText();
                }

                int selectedId2 = radioGroupStudyYear.getCheckedRadioButtonId();
                RadioButton selectedyearbutton = findViewById(selectedId2);
                String studyYear = (String) selectedyearbutton.getText();


                if (firstname.equals("") || lastname.equals("") || phonenumber.equals("") || email1.equals("") ||
                        radioGroupDomain.getCheckedRadioButtonId() == -1 || radioGroupStudyYear.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(getApplicationContext(), "Fields Required", Toast.LENGTH_SHORT).show();
                } else
                    {
                        if (!(email1.matches(emailPattern))) {

                            Toast.makeText(getApplicationContext(), "INVALID MAIL", Toast.LENGTH_SHORT).show();
                        }
                        if (!(phonenumber.matches(phonePattern))) {

                            Toast.makeText(getApplicationContext(), "INVALID PHONE NUMBER", Toast.LENGTH_SHORT).show();
                        }
    //                    if (!(iban1.substring(0, 2).equals("RO")) || !(iban1.substring(2, iban1.length()).matches("[0-9]+")) || iban1.length() != 24) {//                        Toast.makeText(getApplicationContext(), "INVALID IBAN", Toast.LENGTH_SHORT).show();//                    }                    else                    {
                        Bundle bundle = getIntent().getExtras();
                        String username = bundle.getString("username");

                        System.out.println("username student: " + username);

                        roomDatabase = MyRoomDatabase.getDatabase(getApplicationContext());
                        StudentDao studentDao = roomDatabase.studentDao();
                        TutorDao tutorDao = roomDatabase.tutorDao();

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

                            studentDao.updateStudent(student);
                            Tutor tutor = new Tutor(firstname, lastname, studyYear, domain, phonenumber, email1, student.getUsername(), student.getPassword(), 0, iban1);
                            TutorViewModel.repository.insertTutor(tutor);

                            TutorWithCourse tutorWithCourse = new TutorWithCourse(tutor, assignCourse.getCourseToTeachList());
                            TutorViewModel.insertTutorWithCourses(tutorWithCourse);
                            studentDao.updateStudent(student);

                            Intent intent = new Intent(ProfileTutorActivity.this, LoginActivity.class);

                            intent.putExtra("lista_cursuri", courseNameList);
                            intent.putExtra("from", "ProfileTutorActivity");

                            startActivity(intent);
                        }).start();
                }
            }

        });
    }
}
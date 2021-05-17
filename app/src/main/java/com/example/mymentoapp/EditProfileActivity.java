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
import com.example.mymentoapp.model.AssignCourse;
import com.example.mymentoapp.model.SpecificCourse;
import com.example.mymentoapp.model.SpecificCourseViewModel;
import com.example.mymentoapp.model.Student;
import com.example.mymentoapp.model.StudentViewModel;
import com.example.mymentoapp.model.StudentWithCourse;
import com.example.mymentoapp.model.Tutor;
import com.example.mymentoapp.model.TutorViewModel;
import com.example.mymentoapp.util.MyRoomDatabase;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class EditProfileActivity extends AppCompatActivity {
    StudentRepository repository;
    EditText firstName, lastName, phoneNumber, email, iban;
    RadioGroup radioGroupStudyYear, radioGroupDomain, radioGroupSpec;
    RadioButton radioYear1, radioYear2, radioYear3, radioYear4, radioInfo, radioMath, radioCTI;
    Button edit;
    Student student;
    Button becameTutorBtn;
    MyRoomDatabase roomDatabase;
    StudentDao studentDao;
    TutorDao tutorDao;
    String studyYear1, domain1, specialization1;
    AssignCourse assignCourse;
    private ArrayList<String> courseNameList;
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
        radioGroupSpec = findViewById(R.id.radio_group_spec_edit);
        radioYear1 = findViewById(R.id.radio_year1);
        radioYear2 = findViewById(R.id.radio_year2);
        radioYear3 = findViewById(R.id.radio_year3);
        radioYear4 = findViewById(R.id.radio_year4);
        radioCTI = findViewById(R.id.radio_cti);
        radioInfo = findViewById(R.id.radio_info);
        radioMath = findViewById(R.id.radio_math);
        becameTutorBtn = findViewById(R.id.became_tutor_btn);
        edit = findViewById(R.id.btn_edit);
        iban = findViewById(R.id.iban_edit);

        repository = new StudentRepository(this.getApplication());
        final Tutor[] t = {null};

        new Thread(() -> {

            roomDatabase = MyRoomDatabase.getDatabase(getApplicationContext());
            studentDao = roomDatabase.studentDao();
            tutorDao = roomDatabase.tutorDao();
            student = studentDao.getStudent(studentId);
            t[0] = tutorDao.getTutorByUserName(student.getUsername());
            System.out.println("id student este " + student.getIdStudent());

            if(t[0] != null){
                System.out.println(student.getIdStudent() + student.getFirstName());
                System.out.println("este tutore");
                becameTutorBtn.setVisibility(View.GONE);
                iban.setVisibility(View.VISIBLE);
                iban.setText(t[0].getIban());
            }


            //student = studentDao.getStudent(studentId);
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

            //AssignCourse assignCourse = new AssignCourse(checkedStudyYear.getText().toString(),checkedDomain.getText().toString(), )


            assignCourse = new AssignCourse(studyYear1, domain1, specialization1);

            courseNameList.clear();
            for(SpecificCourse specificCourse: assignCourse.getSpecificCourseList()) {
                courseNameList.add(specificCourse.getCourseName());
            }
            System.out.println("CURSURILE");
            for(SpecificCourse specificCourse: assignCourse.getSpecificCourseList()) {
                System.out.println((specificCourse.getCourseName()));
            }

            SpecificCourseViewModel.deleteSpecificCourse(student.getIdStudent());
            StudentWithCourse studentWithCourse = new StudentWithCourse(student, assignCourse.getSpecificCourseList());
            StudentViewModel.insertStudentWithCourses(studentWithCourse);
            //StudentViewModel.updateStudentWithCourse(studentWithCourse);

            if(t[0] != null){
                System.out.println("before set tutor");
                t[0].setIban(iban.getText().toString());
                t[0].setFirstName(firstName.getText().toString());
                t[0].setLastName(lastName.getText().toString());
                t[0].setEmail(email.getText().toString());
                t[0].setPhoneNumber(phoneNumber.getText().toString());
                t[0].setStudyYear(checkedStudyYear.getText().toString());
                t[0].setStudyDomain(checkedDomain.getText().toString());
                System.out.println("after set tutor");
            }
            StudentViewModel.updateStudent(student);
            if(t[0] != null){
                    System.out.println("Before update tutor");
                    TutorViewModel.updateTutor(t[0]);
                    System.out.println("After update tutor");
                }
                Intent intent = new Intent(EditProfileActivity.this, ViewProfileActivity.class);
                intent.putExtra("idStudent", studentId);
                startActivity(intent);
        });

    }
}
package com.example.mymentoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.mymentoapp.data.StudentDao;
import com.example.mymentoapp.data.StudentRepository;
import com.example.mymentoapp.data.TutorDao;
import com.example.mymentoapp.model.AssignCourse;
import com.example.mymentoapp.model.CourseToTeachViewModel;
import com.example.mymentoapp.model.SpecificCourse;
import com.example.mymentoapp.model.SpecificCourseViewModel;
import com.example.mymentoapp.model.Student;
import com.example.mymentoapp.model.StudentViewModel;
import com.example.mymentoapp.model.StudentWithCourse;
import com.example.mymentoapp.model.TaughtCourseViewModel;
import com.example.mymentoapp.model.Tutor;
import com.example.mymentoapp.model.TutorViewModel;
import com.example.mymentoapp.util.MyRoomDatabase;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class EditProfileActivity extends AppCompatActivity {
    private StudentViewModel studentViewModel;
    private  CourseToTeachViewModel courseToTeachViewModel;
    private  TutorViewModel tutorViewModel;
    private  SpecificCourseViewModel specificCourseViewModel;
    private  TaughtCourseViewModel taughtCourseViewModel;

    EditText firstName, lastName, phoneNumber, email, iban;
    RadioGroup radioGroupStudyYear, radioGroupDomain, radioGroupSpec;
    RadioButton radioYear1, radioYear2, radioYear3, radioYear4, radioInfo, radioMath, radioCTI;
    Button edit;
    Student student;
    Button becameTutorBtn;
    Tutor tutor;
    String studyYear1, domain1, specialization1, oldStudyDomain;
    AssignCourse assignCourse;
    private ArrayList<String> courseNameList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        Bundle bundle = getIntent().getExtras();
        String studentName = bundle.getString("studentName");

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


        new Thread(() -> {
            studentViewModel = new StudentViewModel(this.getApplication());
            student = studentViewModel.getStudentByUsername(studentName);
            oldStudyDomain = student.getStudyDomain();
            tutorViewModel = new TutorViewModel(this.getApplication());

            System.out.println("USERNAME" +student.getUsername());
            tutor = tutorViewModel.getTutor(student.getUsername());
            if(tutor != null){
               this.runOnUiThread(()->{
                   becameTutorBtn.setVisibility(View.GONE);
                   iban.setVisibility(View.VISIBLE);
                   iban.setText(tutor.getIban());
               });

            }



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

        }).start();


    becameTutorBtn.setOnClickListener(v->{
        Intent intent = new Intent(EditProfileActivity.this, BecameTutorActivity.class);
        intent.putExtra("idTutor", studentName);
        startActivity(intent);
    });

    edit.setOnClickListener(v -> {
        new Thread(() -> {
            specificCourseViewModel = new SpecificCourseViewModel(this.getApplication());
            courseToTeachViewModel = new CourseToTeachViewModel(this.getApplication());
            student.setFirstName(firstName.getText().toString());
            student.setLastName(lastName.getText().toString());
            student.setEmail(email.getText().toString());
            student.setPhoneNumber(phoneNumber.getText().toString());


            String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
            String phonePattern = "^\\+[0-9]{10,13}$";
            if (firstName.getText().toString().equals("") || lastName.getText().toString().equals("") || phoneNumber.getText().toString().equals("") || email.getText().toString().equals("") || radioGroupDomain.getCheckedRadioButtonId() == -1 || radioGroupStudyYear.getCheckedRadioButtonId() == -1) {
                Toast.makeText(getApplicationContext(), "Fields Required", Toast.LENGTH_SHORT).show();
            } else if (!(email.getText().toString().matches(emailPattern))) {
                Toast.makeText(getApplicationContext(), "INVALID MAIL", Toast.LENGTH_SHORT).show();
            } else if (!(phoneNumber.getText().toString().matches(phonePattern))) {
                Toast.makeText(getApplicationContext(), "INVALID PHONE NUMBER", Toast.LENGTH_SHORT).show();
            } else if (!domain1.equals("CTI") && (studyYear1.equals("IV"))) {
                Toast.makeText(getApplicationContext(), "ONLY CTI HAS 4 YEARS", Toast.LENGTH_SHORT).show();
            }

            else{
                int checkedStudyYearId = radioGroupStudyYear.getCheckedRadioButtonId();
                RadioButton checkedStudyYear = findViewById(checkedStudyYearId);
                student.setStudyYear(checkedStudyYear.getText().toString());


                int checkedDomainId = radioGroupDomain.getCheckedRadioButtonId();
                RadioButton checkedDomain = findViewById(checkedDomainId);
                student.setStudyDomain(checkedDomain.getText().toString());



                assignCourse = new AssignCourse(studyYear1, domain1, specialization1);

                courseNameList.clear();
                for(SpecificCourse specificCourse: assignCourse.getSpecificCourseList()) {
                    courseNameList.add(specificCourse.getCourseName());
                }
                System.out.println("CURSURILE");
                for(SpecificCourse specificCourse: assignCourse.getSpecificCourseList()) {
                    System.out.println((specificCourse.getCourseName()));
                }

                specificCourseViewModel.deleteSpecificCourse(student.getIdStudent());
                StudentWithCourse studentWithCourse = new StudentWithCourse(student, assignCourse.getSpecificCourseList());
                studentViewModel.insertStudentWithCourses(studentWithCourse);
                //StudentViewModel.updateStudentWithCourse(studentWithCourse);

                if(tutor != null){
                    specificCourseViewModel.deleteSpecificCourse(tutor.getIdStudent());
                    System.out.println("before set tutor");
                    tutor.setIban(iban.getText().toString());
                    tutor.setFirstName(firstName.getText().toString());
                    tutor.setLastName(lastName.getText().toString());
                    tutor.setEmail(email.getText().toString());
                    tutor.setPhoneNumber(phoneNumber.getText().toString());
                    tutor.setStudyYear(checkedStudyYear.getText().toString());
                    tutor.setStudyDomain(checkedDomain.getText().toString());
                    System.out.println("after set tutor");
                    StudentWithCourse studentWithCourse1 = new StudentWithCourse(tutor, assignCourse.getSpecificCourseList());
                    studentViewModel.insertStudentWithCourses(studentWithCourse1);
                    if(oldStudyDomain!= null && !oldStudyDomain.equals(checkedDomain.getText().toString())){
                        courseToTeachViewModel.deleteCoursesForTutor(tutor.getIdStudent());
                    }

                    System.out.println("Before update tutor");
                    tutorViewModel.updateTutor(tutor);
                    System.out.println("After update tutor");
                }

                Intent intent = new Intent(EditProfileActivity.this, ViewProfileActivity.class);
                intent.putExtra("studentName", studentName);
                startActivity(intent);
            }
        }).start();



    });
    }
}
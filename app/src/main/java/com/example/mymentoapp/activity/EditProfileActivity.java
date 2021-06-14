package com.example.mymentoapp.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mymentoapp.R;
import com.example.mymentoapp.model.AssignCourse;
import com.example.mymentoapp.model.CourseToTeach;
import com.example.mymentoapp.model.CourseToTeachViewModel;
import com.example.mymentoapp.model.SpecificCourse;
import com.example.mymentoapp.model.SpecificCourseViewModel;
import com.example.mymentoapp.model.Student;
import com.example.mymentoapp.model.StudentViewModel;
import com.example.mymentoapp.model.StudentWithCourse;
import com.example.mymentoapp.model.Tutor;
import com.example.mymentoapp.model.TutorViewModel;
import com.example.mymentoapp.model.TutorWithCourse;

import java.util.List;

public class EditProfileActivity extends AppCompatActivity {

    private StudentViewModel studentViewModel;
    private  CourseToTeachViewModel courseToTeachViewModel;
    private  TutorViewModel tutorViewModel;
    private  SpecificCourseViewModel specificCourseViewModel;

    private EditText firstName, lastName, phoneNumber, email, iban;
    private RadioGroup radioGroupStudyYear, radioGroupDomain, radioGroupSpec;
    private RadioButton radioYear1, radioYear2, radioYear3, radioYear4, radioInfo, radioMath, radioCTI;
    Button edit,  becameTutorBtn, backToHome;
    private Student student;
    private Tutor tutor;
    private String studyYear1, domain1, specialization1, oldStudyDomain;
    private AssignCourse assignCourse, assignCourse2;
    private LinearLayout linearLayout;
    private TextView courseToTeach;
    private List<SpecificCourse> specificCourseList;
    private List<CourseToTeach> courseToTeachList;
    private Toolbar toolbar;

    @RequiresApi(api = Build.VERSION_CODES.N)
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
        linearLayout = findViewById(R.id.to_teach_course);
        courseToTeach = findViewById(R.id.text_view_course);
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

        toolbar = findViewById(R.id.toolbar_home);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        backToHome = findViewById(R.id.back_home);

        new Thread(() -> {

            studentViewModel = new StudentViewModel(this.getApplication());
            tutorViewModel = new TutorViewModel(this.getApplication());

            specificCourseViewModel = new SpecificCourseViewModel(this.getApplication());
            courseToTeachViewModel = new CourseToTeachViewModel(this.getApplication());

            student = studentViewModel.getStudentByUsername(studentName);
            tutor = tutorViewModel.getTutor(student.getUsername());

            studyYear1 = student.getStudyYear();
            domain1 = student.getStudyDomain();
            oldStudyDomain = student.getStudyDomain();

            if(tutor != null) {

                specificCourseList = specificCourseViewModel.getAllSpecificCoursesForStudent(tutor.getIdStudent());
                courseToTeachList = courseToTeachViewModel.getAllToTeachCourses(tutor.getIdStudent());
            }else{
                specificCourseList = specificCourseViewModel.getAllSpecificCoursesForStudent(student.getIdStudent());
            }

            this.runOnUiThread(()->{
                if(tutor != null){
                    becameTutorBtn.setVisibility(View.GONE);
                    iban.setVisibility(View.VISIBLE);
                    iban.setText(tutor.getIban());
                    for(SpecificCourse specificCourse: specificCourseList) {
                        CheckBox checkBox = new CheckBox(EditProfileActivity.this);
                        checkBox.setText(specificCourse.getCourseName());
                        checkBox.setVisibility(View.VISIBLE);
                        for(CourseToTeach c : courseToTeachList){
                            if(c.getCourseName().equals(specificCourse.getCourseName())){
                                checkBox.setChecked(true);
                            }
                        }
                        linearLayout.addView(checkBox);
                    }
                }

                radioMath.setEnabled(true);
                radioInfo.setEnabled(true);
                radioCTI.setEnabled(true);
                radioGroupSpec.setVisibility(View.GONE);
                linearLayout.removeAllViews();

                radioGroupStudyYear.setOnCheckedChangeListener((group, checkedId) -> {
                    linearLayout.removeAllViews();
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
                    if(tutor != null) {
                        courseToTeach.setVisibility(View.VISIBLE);
                    }
                    RadioButton rb = findViewById(checkedId);
                    domain1 = rb.getText().toString();
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
                        assignCourse2 = new AssignCourse(studyYear1, domain1, specialization1);
                        linearLayout.removeAllViews();
                        if(tutor!=null){
                            for(SpecificCourse specificCourse: assignCourse2.getSpecificCourseList()) {
                                CheckBox checkBox = new CheckBox(EditProfileActivity.this);
                                checkBox.setText(specificCourse.getCourseName());
                                checkBox.setVisibility(View.VISIBLE);
                                linearLayout.addView(checkBox);
                            }
                        }

                    });
                    if(domain1.equals("Mathematics") && (studyYear1.equals("II") || studyYear1.equals("III"))){
                        radioGroupSpec.setVisibility(View.VISIBLE);
                        RadioButton r = findViewById(R.id.btn_spec1_edit);
                        r.setChecked(true);
                        specialization1 = r.getText().toString();
                        linearLayout.removeAllViews();
                        assignCourse2 = new AssignCourse(studyYear1, domain1, specialization1);
                        if(tutor!=null){
                            for(SpecificCourse specificCourse: assignCourse2.getSpecificCourseList()) {
                                CheckBox checkBox = new CheckBox(EditProfileActivity.this);
                                checkBox.setText(specificCourse.getCourseName());
                                checkBox.setVisibility(View.VISIBLE);
                                linearLayout.addView(checkBox);
                            }
                        }


                        radioGroupSpec.setOnCheckedChangeListener((group12, checkedId3) -> {
                            RadioButton rb3 = findViewById(checkedId3);
                            specialization1 = rb3.getText().toString();
                            assignCourse2 = new AssignCourse(studyYear1, domain1, specialization1);
                            linearLayout.removeAllViews();
                            if(tutor!=null){
                                for(SpecificCourse specificCourse: assignCourse2.getSpecificCourseList()) {
                                    CheckBox checkBox = new CheckBox(EditProfileActivity.this);
                                    checkBox.setText(specificCourse.getCourseName());
                                    checkBox.setVisibility(View.VISIBLE);
                                    linearLayout.addView(checkBox);
                                }
                            }

                        });
                    }
                    if(radioGroupSpec.getVisibility() == View.VISIBLE){
                        radioGroupSpec.setOnCheckedChangeListener((group13, checkedId3) -> {
                            RadioButton rb3 = findViewById(checkedId3);
                            specialization1 = rb3.getText().toString();
                            assignCourse2 = new AssignCourse(studyYear1, domain1, specialization1);
                            linearLayout.removeAllViews();
                            if(tutor!=null){
                                for(SpecificCourse specificCourse: assignCourse2.getSpecificCourseList()) {
                                    CheckBox checkBox = new CheckBox(EditProfileActivity.this);
                                    checkBox.setText(specificCourse.getCourseName());
                                    checkBox.setVisibility(View.VISIBLE);
                                    linearLayout.addView(checkBox);
                                }
                            }

                        });
                    }
                    if(!(domain1.equals("Mathematics") && (studyYear1.equals("II") || studyYear1.equals("III")))){
                        radioGroupSpec.setVisibility(View.GONE);
                    }
                    assignCourse2 = new AssignCourse(studyYear1, domain1, specialization1);
                    linearLayout.removeAllViews();
                    if(tutor!=null){
                        for(SpecificCourse specificCourse: assignCourse2.getSpecificCourseList()) {
                            CheckBox checkBox = new CheckBox(EditProfileActivity.this);
                            checkBox.setText(specificCourse.getCourseName());
                            checkBox.setVisibility(View.VISIBLE);
                            linearLayout.addView(checkBox);
                        }
                    }

                });
                if(radioGroupSpec.getVisibility() == View.VISIBLE){
                    radioGroupSpec.setOnCheckedChangeListener((group, checkedId3) -> {
                        RadioButton rb3 = (RadioButton)findViewById(checkedId3);
                        specialization1 = rb3.getText().toString();
                        assignCourse2 = new AssignCourse(studyYear1, domain1, specialization1);
                        linearLayout.removeAllViews();
                        if(tutor!=null){
                            for(SpecificCourse specificCourse: assignCourse2.getSpecificCourseList()) {
                                CheckBox checkBox = new CheckBox(EditProfileActivity.this);
                                checkBox.setText(specificCourse.getCourseName());
                                checkBox.setVisibility(View.VISIBLE);
                                linearLayout.addView(checkBox);
                            }
                        }

                    });
                }

                firstName.setText(student.getFirstName());
                lastName.setText(student.getLastName());
                phoneNumber.setText(student.getPhoneNumber());
                email.setText(student.getEmail());
                String year = student.getStudyYear();
                String domain = student.getStudyDomain();
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
            });

        }).start();


        becameTutorBtn.setOnClickListener(v->{
            Intent intent = new Intent(EditProfileActivity.this, BecomeTutorActivity.class);
            intent.putExtra("idTutor", studentName);
            startActivity(intent);
        });

        edit.setOnClickListener(v -> {
            new Thread(() -> {

                specificCourseViewModel = new SpecificCourseViewModel(this.getApplication());

                student.setFirstName(firstName.getText().toString());
                student.setLastName(lastName.getText().toString());
                student.setEmail(email.getText().toString());
                student.setPhoneNumber(phoneNumber.getText().toString());


                if (assignCourse2!= null){
                    specificCourseList = assignCourse2.getSpecificCourseList();
                }
                else{
                    specificCourseList = specificCourseViewModel.getAllSpecificCoursesForStudent(student.getIdStudent());
                }

                if(tutor!=null) {
                    courseToTeachViewModel = new CourseToTeachViewModel(this.getApplication());
                    courseToTeachList.clear();
                    if (specificCourseList.size() > 0) {
                        for (int i = 0; i < specificCourseList.size(); i++) {
                            CheckBox checkBox = (CheckBox) linearLayout.getChildAt(i);
                            if (checkBox.isChecked()) {
                                CourseToTeach courseToTeach = new CourseToTeach(specificCourseList.get(i).getCourseName(), specificCourseList.get(i).getDescription());
                                courseToTeachList.add(courseToTeach);
                            }
                        }
                    }
                }

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
                    specificCourseViewModel.deleteSpecificCourse(student.getIdStudent());

                    StudentWithCourse studentWithCourse = new StudentWithCourse(student, assignCourse.getSpecificCourseList());
                    studentViewModel.insertStudentWithCourses(studentWithCourse);

                    if(tutor != null){

                        tutor.setIban(iban.getText().toString());
                        tutor.setFirstName(firstName.getText().toString());
                        tutor.setLastName(lastName.getText().toString());
                        tutor.setEmail(email.getText().toString());
                        tutor.setPhoneNumber(phoneNumber.getText().toString());
                        tutor.setStudyYear(checkedStudyYear.getText().toString());
                        tutor.setStudyDomain(checkedDomain.getText().toString());

                        StudentWithCourse studentWithCourse1 = new StudentWithCourse(tutor, assignCourse.getSpecificCourseList());
                        studentViewModel.insertStudentWithCourses(studentWithCourse1);
                        courseToTeachViewModel.deleteCoursesForTutor(tutor.getIdStudent());

                        TutorWithCourse tutorWithCourse = new TutorWithCourse(tutor, courseToTeachList);

                        tutorViewModel.insertTutorWithCourses(tutorWithCourse);
                        tutorViewModel.updateTutor(tutor);
                    }
                    Intent intent = new Intent(EditProfileActivity.this, ViewProfileActivity.class);
                    intent.putExtra("studentName", studentName);
                    startActivity(intent);
                }
            }).start();


        });
        backToHome.setOnClickListener(v -> {
            Intent intent = new Intent(EditProfileActivity.this, WelcomeActivity.class);
            intent.putExtra("studentName", studentName);
            startActivity(intent);
        });
    }
}
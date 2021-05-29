package com.example.mymentoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

import com.example.mymentoapp.data.StudentDao;
import com.example.mymentoapp.data.StudentRepository;
import com.example.mymentoapp.data.TutorDao;
import com.example.mymentoapp.model.AssignCourse;
import com.example.mymentoapp.model.CourseToTeach;
import com.example.mymentoapp.model.SpecificCourse;
import com.example.mymentoapp.model.Student;
import com.example.mymentoapp.model.StudentViewModel;
import com.example.mymentoapp.model.StudentWithCourse;
import com.example.mymentoapp.model.Tutor;
import com.example.mymentoapp.model.TutorViewModel;
import com.example.mymentoapp.model.TutorWithCourse;
import com.example.mymentoapp.util.MyRoomDatabase;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class EditProfileActivity extends AppCompatActivity {

    StudentViewModel studentViewModel = new StudentViewModel(this.getApplication());
    TutorViewModel tutorViewModel = new TutorViewModel(this.getApplication());

    EditText firstName, lastName, phoneNumber, email, iban;
    RadioGroup radioGroupStudyYear, radioGroupDomain, radioGroupSpec;
    RadioButton radioYear1, radioYear2, radioYear3, radioYear4, radioInfo, radioMath, radioCTI;
    Button edit;
    Student student;
    Button becameTutorBtn;
    String studyYear1, domain1, specialization1;
    AssignCourse assignCourse;
    LinearLayout linearLayout;
    AssignCourse assignCourse2;
    TextView course_to_teach;
    Tutor newTutor;

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
        linearLayout = findViewById(R.id.to_teach_course1);
        course_to_teach = findViewById(R.id.courses_to_teach1);


//        repository = new StudentRepository(this.getApplication());

        courseNameList = new ArrayList<>();
        specialization1 = "" ;
        studyYear1 = "";
        domain1 = "";

        radioGroupStudyYear.setOnCheckedChangeListener((RadioGroup.OnCheckedChangeListener) (group, checkedId) -> {
            RadioButton rb2 = (RadioButton)findViewById(checkedId);
            studyYear1 = rb2.getText().toString();
            linearLayout.removeAllViews();
        });


        radioGroupDomain.setOnCheckedChangeListener((RadioGroup.OnCheckedChangeListener) (group, checkedId) -> {

            course_to_teach.setVisibility(View.VISIBLE);
            // checkedId is the RadioButton selected
            RadioButton rb = (RadioButton)findViewById(checkedId);
            domain1 = rb.getText().toString();
            System.out.println("study+year" +rb.getText());

            if(studyYear1.equals("I") || studyYear1.equals("IV")){
                radioGroupSpec.setVisibility(View.GONE);
            }
            radioGroupStudyYear.setOnCheckedChangeListener((RadioGroup.OnCheckedChangeListener) (group1, checkedId2) -> {

                linearLayout.removeAllViews();
                RadioButton rb2 = (RadioButton)findViewById(checkedId2);
                studyYear1 = rb2.getText().toString();
                if(domain1.equals("Mathematics") && (studyYear1.equals("II") || studyYear1.equals("III"))){
                    radioGroupSpec.setVisibility(View.VISIBLE);
                    radioGroupSpec.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                        public void onCheckedChanged(RadioGroup group1, int checkedId3) {
                            RadioButton rb3 = (RadioButton)findViewById(checkedId3);
                            specialization1 = rb3.getText().toString();
                            System.out.println(studyYear1 + domain1);
                            assignCourse2 = new AssignCourse(studyYear1, domain1, specialization1);
                        }
                    });
                    if(studyYear1.equals("I") || studyYear1.equals("IV")){
                        radioGroupSpec.setVisibility(View.GONE);
                    }
                    assignCourse2 = new AssignCourse(studyYear1, domain1, specialization1);
                }
                System.out.println(studyYear1 + domain1);
                assignCourse2 = new AssignCourse(studyYear1, domain1, specialization1);
                linearLayout.removeAllViews();
                for(SpecificCourse specificCourse: assignCourse2.getSpecificCourseList()) {
                    CheckBox checkBox = new CheckBox(EditProfileActivity.this);
                    checkBox.setText(specificCourse.getCourseName());
                    checkBox.setVisibility(View.VISIBLE);
                    linearLayout.addView(checkBox);
                }

            });
            if(domain1.equals("Mathematics") && (studyYear1.equals("II") || studyYear1.equals("III"))){
                linearLayout.removeAllViews();
                radioGroupSpec.setVisibility(View.VISIBLE);
                radioGroupSpec.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    public void onCheckedChanged(RadioGroup group, int checkedId3) {
                        RadioButton rb3 = (RadioButton)findViewById(checkedId3);
                        specialization1 = rb3.getText().toString();
                        assignCourse2 = new AssignCourse(studyYear1, domain1, specialization1);
                        linearLayout.removeAllViews();
                        for(SpecificCourse specificCourse: assignCourse2.getSpecificCourseList()) {
                            CheckBox checkBox = new CheckBox(EditProfileActivity.this);
                            checkBox.setText(specificCourse.getCourseName());
                            checkBox.setVisibility(View.VISIBLE);
                            linearLayout.addView(checkBox);
                        }
                    }
                });
                assignCourse2 = new AssignCourse(studyYear1, domain1, specialization1);
            }


            if(radioGroupSpec.getVisibility() == View.VISIBLE){
                linearLayout.removeAllViews();
                radioGroupSpec.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    public void onCheckedChanged(RadioGroup group, int checkedId3) {
                        RadioButton rb3 = (RadioButton)findViewById(checkedId3);
                        specialization1 = rb3.getText().toString();
                        assignCourse2 = new AssignCourse(studyYear1, domain1, specialization1);
                        linearLayout.removeAllViews();
                        for(SpecificCourse specificCourse: assignCourse2.getSpecificCourseList()) {
                            CheckBox checkBox = new CheckBox(EditProfileActivity.this);
                            checkBox.setText(specificCourse.getCourseName());
                            checkBox.setVisibility(View.VISIBLE);
                            linearLayout.addView(checkBox);
                        }
                    }
                });
                if(!(domain1.equals("Mathematics") && (studyYear1.equals("II") || studyYear1.equals("III")))){
                    radioGroupSpec.setVisibility(View.GONE);
                }
            }



            System.out.println(studyYear1 + domain1);
            assignCourse2 = new AssignCourse(studyYear1, domain1, specialization1);
            linearLayout.removeAllViews();
            for(SpecificCourse specificCourse: assignCourse2.getSpecificCourseList()) {
                CheckBox checkBox = new CheckBox(EditProfileActivity.this);
                checkBox.setText(specificCourse.getCourseName());
                checkBox.setVisibility(View.VISIBLE);
                linearLayout.addView(checkBox);

            }


        });

        if(radioGroupSpec.getVisibility() == View.VISIBLE){
            radioGroupSpec.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                public void onCheckedChanged(RadioGroup group, int checkedId3) {
                    RadioButton rb3 = (RadioButton)findViewById(checkedId3);
                    specialization1 = rb3.getText().toString();
                    System.out.println(studyYear1 + domain1);
                    assignCourse2 = new AssignCourse(studyYear1, domain1, specialization1);
                    linearLayout.removeAllViews();
                    for(SpecificCourse specificCourse: assignCourse2.getSpecificCourseList()) {
                        CheckBox checkBox = new CheckBox(EditProfileActivity.this);
                        checkBox.setText(specificCourse.getCourseName());
                        checkBox.setVisibility(View.VISIBLE);
                        linearLayout.addView(checkBox);
                    }
                    if(studyYear1.equals("I") || studyYear1.equals("IV")){
                        radioGroupSpec.setVisibility(View.GONE);
                    }
                }

            });
        }




//        student = stude.getStudent(studentId);

        Tutor tutor = tutorViewModel.getTutorByUserName(student.getUsername());
        System.out.println("id student este " + student.getIdStudent());

        if(tutor != null){
                System.out.println(student.getIdStudent() + student.getFirstName());
                System.out.println("este tutore");
                becameTutorBtn.setVisibility(View.GONE);
        }
//        else{
//
//                Tutor newTutor = new Tutor();
//                newTutor.setIdStudent(student.getIdStudent());
//                newTutor.setFirstName(student.getFirstName());
//                newTutor.setLastName(student.getLastName());
//                newTutor.setEmail(student.getEmail());
//                newTutor.setPassword(student.getPassword());
//                newTutor.setPhoneNumber(student.getPhoneNumber());
//                newTutor.setStudyDomain(student.getStudyDomain());
//                newTutor.setStudyYear(student.getStudyYear());
//                newTutor.setUsername(student.getUsername());
//
//        }

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

            ArrayList<CourseToTeach> courseToTeachArrayList = new ArrayList<>();
            ArrayList<SpecificCourse> specificCourses = (ArrayList<SpecificCourse>) assignCourse2.getSpecificCourseList();

            for (int i = 0; i < specificCourses.size(); i++) {
                CheckBox checkBox = (CheckBox) linearLayout.getChildAt(i);
                if (checkBox.isChecked()) {
                    System.out.println("is checked" + i);
                    CourseToTeach courseToTeach = new CourseToTeach(specificCourses.get(i).getCourseName(), specificCourses.get(i).getDescription());
                    courseToTeachArrayList.add(courseToTeach);
                }
            }
            if (courseToTeachArrayList.size() == 0) {
                Toast.makeText(getApplicationContext(), "You have to choose at least one course", Toast.LENGTH_SHORT).show();
            }

            StudentWithCourse studentWithCourse = new StudentWithCourse(student, assignCourse.getSpecificCourseList());
            StudentViewModel.insertStudentWithCourses(studentWithCourse);
            //StudentViewModel.updateStudentWithCourse(studentWithCourse);

            if(newTutor != null){
                System.out.println("before set tutor");
                newTutor.setIban(iban.getText().toString());
                newTutor.setFirstName(firstName.getText().toString());
                newTutor.setLastName(lastName.getText().toString());
                newTutor.setEmail(email.getText().toString());
                newTutor.setPhoneNumber(phoneNumber.getText().toString());
                newTutor.setStudyYear(checkedStudyYear.getText().toString());
                newTutor.setStudyDomain(checkedDomain.getText().toString());
                System.out.println("after set tutor");
            }


            studentViewModel.updateStudent(student);
            assignCourse.setCourseToTeachList(courseToTeachArrayList);
            TutorWithCourse tutorWithCourse = new TutorWithCourse(newTutor, assignCourse.getCourseToTeachList());
            TutorViewModel.insertTutorWithCourses(tutorWithCourse);
            Intent intent = new Intent(EditProfileActivity.this, ViewProfileActivity.class);
            intent.putExtra("idStudent", studentId);
            startActivity(intent);
        });

    }
}
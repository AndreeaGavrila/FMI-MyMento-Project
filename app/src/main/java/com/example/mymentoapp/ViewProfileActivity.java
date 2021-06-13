package com.example.mymentoapp;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.mymentoapp.model.CourseToTeach;
import com.example.mymentoapp.model.CourseToTeachViewModel;
import com.example.mymentoapp.model.SpecificCourse;
import com.example.mymentoapp.model.SpecificCourseViewModel;
import com.example.mymentoapp.model.Student;
import com.example.mymentoapp.model.StudentViewModel;
import com.example.mymentoapp.model.TaughtCourse;
import com.example.mymentoapp.model.TaughtCourseViewModel;
import com.example.mymentoapp.model.Tutor;
import com.example.mymentoapp.model.TutorViewModel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class ViewProfileActivity extends AppCompatActivity {
    private StudentViewModel studentViewModel;
    private CourseToTeachViewModel courseToTeachViewModel;
    private TutorViewModel tutorViewModel;
    private SpecificCourseViewModel specificCourseViewModel;
    private TaughtCourseViewModel taughtCourseViewModel;
    private List<TaughtCourse> taughtCoursesList;

    private Tutor tutor;
    private Student student;

    TextView firstName, lastName, phoneNumber, email, studyYear, domain, textViewToTeachCourse,
            textViewSpecificCourse, textView, name;
    Button editProfile;
    LinearLayout linearLayout;
    Button downloadButton, backHome;
    Toolbar toolbar;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);

        Bundle bundle = getIntent().getExtras();
        String studentName = bundle.getString("studentName");

        toolbar = findViewById(R.id.toolbar_home);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        backHome = findViewById(R.id.back_home);
        name = findViewById(R.id.name);

        textViewSpecificCourse = findViewById(R.id.text_view_course);
        textViewToTeachCourse = findViewById(R.id.teach_courses);
        textView = findViewById(R.id.text_view_course2);
//
//        firstName = findViewById(R.id.firstName);
//        lastName = findViewById(R.id.lastName);
        phoneNumber = findViewById(R.id.phoneNumber);
        email = findViewById(R.id.email_view);
        studyYear = findViewById(R.id.studyYear);
        domain = findViewById(R.id.domain);

        editProfile = findViewById(R.id.edit_btn);
        downloadButton = findViewById(R.id.download_btn);

        linearLayout = findViewById(R.id.layout_recommended);

        textViewToTeachCourse.setVisibility(View.VISIBLE);
        downloadButton.setVisibility(View.INVISIBLE);

        new Thread(() -> {

            studentViewModel = new StudentViewModel(this.getApplication());
            tutorViewModel = new TutorViewModel(this.getApplication());
            courseToTeachViewModel = new CourseToTeachViewModel(this.getApplication());
            specificCourseViewModel = new SpecificCourseViewModel(this.getApplication());

            student = studentViewModel.getStudentByUsername(studentName);

            ArrayList<SpecificCourse> courses = (ArrayList<SpecificCourse>) (specificCourseViewModel.getAllSpecificCoursesForStudent(student.getIdStudent()));
            tutor = tutorViewModel.getTutor(student.getUsername());
            ArrayList<CourseToTeach> courseToTeachArrayList = new ArrayList<>();

            if (tutor != null) {
                courseToTeachArrayList = (ArrayList<CourseToTeach>) courseToTeachViewModel.getAllToTeachCourses(tutor.getIdStudent());
            }

            ArrayList<CourseToTeach> finalCourseToTeachArrayList = courseToTeachArrayList;
            this.runOnUiThread(() -> {

                if (tutor != null) {
                    textView.setVisibility(View.VISIBLE);
                    for (CourseToTeach courseToTeach : finalCourseToTeachArrayList) {
                        textViewToTeachCourse.append(courseToTeach.getCourseName());
                        textViewToTeachCourse.append("\n");
                    }
                    downloadButton.setVisibility(View.VISIBLE);
                }

                for (SpecificCourse course : courses) {

                    Button btn = new Button(this.getApplicationContext());
                    btn.setText(course.getCourseName());
                    btn.setOnClickListener(v -> {
                        Intent newIntent = new Intent(ViewProfileActivity.this, ViewAvailableCoursesActivity.class);
                        newIntent.putExtra("courseName", course.getCourseName());
                        newIntent.putExtra("studentName", student.getUsername());
                        startActivity(newIntent);
                    });
                    linearLayout.addView(btn);
                }

                String nameText = "Name: " + student.getFirstName() + " " + student.getLastName();
                name.setText(nameText);
                String phoneNumberText = "Phone number: " + student.getPhoneNumber();
                phoneNumber.setText(phoneNumberText);
                String emailText = "Email: " + student.getEmail();
                email.setText(emailText);
                String studyYearText = "Study year: " + student.getStudyYear();
                studyYear.setText(studyYearText);
                String domainText = "Domain: " + student.getStudyDomain();
                domain.setText(domainText);
            });
        }).start();

        editProfile.setOnClickListener(v -> {
            Intent newIntent = new Intent(ViewProfileActivity.this, EditProfileActivity.class);
            newIntent.putExtra("studentName", studentName);
            startActivity(newIntent);
        });

        //createDoc();
        //createPDF();

        downloadButton.setOnClickListener(v -> new Thread(() -> {
            taughtCourseViewModel = new TaughtCourseViewModel(this.getApplication());
            taughtCoursesList = taughtCourseViewModel.getAllTaughtCoursesForTutor(tutor.getIdStudent());
            //System.out.println("size " +  taughtCoursesList.size());

            int numberOfStudents = taughtCoursesList.size();

            int numberOfHours = numberOfStudents * 10;
            if (numberOfHours < 10) {
                this.runOnUiThread(() -> Toast.makeText(getApplicationContext(), "No. of Hours not reached!", Toast.LENGTH_SHORT).show());

            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    createPDF(tutor, numberOfHours, numberOfStudents, taughtCoursesList);
                }
                this.runOnUiThread(() -> Toast.makeText(getApplicationContext(), "Download finished!", Toast.LENGTH_SHORT).show());
            }
        }).start());

        backHome.setOnClickListener(v -> {
            Intent intent = new Intent(ViewProfileActivity.this, WelcomeActivity.class);
            intent.putExtra("studentName", studentName);
            startActivity(intent);
        });
    }


    @RequiresApi(api = Build.VERSION_CODES.Q)
    private  void createPDF(Tutor tutor, int nrOfHours, int nrOfStudents, List<TaughtCourse> taughtCoursesList){
        PdfDocument pdfDocument = new PdfDocument();
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(500,1000,1).create();
        PdfDocument.Page page = pdfDocument.startPage(pageInfo);

        String title = "Adeverință Oficială FMI MyMento\n";
        String domain;
        if (tutor.getStudyDomain().equals("Informatics")){
            domain = "Informatică";
        }else if (tutor.getStudyDomain().equals("CTI")){
            domain = "CTI";
        }else{
            domain = "Matematică";
        }
        String firstParagraph = "Studentul " + tutor.getLastName() + " " + tutor.getFirstName() + ", anul " + tutor.getStudyYear() +
                ", domeniul " + domain + " a acumulat un numar de " + nrOfHours + " de ore de activitate în cadrul aplicației FMI MyMento.\n\n";
        String secondParagraph = " A ocupat funcția de tutore pentru " + nrOfStudents + " studenți, predând materiile:\n";
        String paragraph = firstParagraph + secondParagraph;

        String date = "Data: " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        StringBuilder text = new StringBuilder(title + "\n" + paragraph + "\n");
        for(TaughtCourse taughtCourse : taughtCoursesList){
            text.append(taughtCourse.getCourseName());
            text.append("\n");
        }
        text.append("\n");
        text.append(date);

        Canvas canvas = page.getCanvas();
        TextPaint textPaint = new TextPaint();
        StaticLayout mTextLayout = new StaticLayout(text ,textPaint, canvas.getWidth() - 100, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
        canvas.save();
        canvas.translate(50, 50);
        mTextLayout.draw(canvas);
        canvas.restore();

        pdfDocument.finishPage(page);

        String filePath = this.getApplicationContext().getExternalFilesDir(null).toString();
        File directory = new File(filePath + "/saved");
        if (!directory.exists()) {
            directory.mkdirs();
        }

        String fileName = "Adeverință " + tutor.getLastName() + " " + tutor.getFirstName() + LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))+ ".pdf";
        File file = new File(directory, fileName);
        try {
            pdfDocument.writeTo(new FileOutputStream(file));
        }
        catch (Exception e){
            e.printStackTrace();
        }
        pdfDocument.close();
    }
}
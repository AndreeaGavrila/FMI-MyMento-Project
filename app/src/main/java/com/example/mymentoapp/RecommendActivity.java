package com.example.mymentoapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mymentoapp.data.CourseToTeachDao;
import com.example.mymentoapp.data.SpecificCourseDao;
import com.example.mymentoapp.data.StudentDao;
import com.example.mymentoapp.data.TutorDao;
import com.example.mymentoapp.model.CourseToTeach;
import com.example.mymentoapp.model.SpecificCourse;
import com.example.mymentoapp.model.Student;
import com.example.mymentoapp.model.Tutor;
import com.example.mymentoapp.util.MyRoomDatabase;

import java.util.ArrayList;

public class RecommendActivity  extends AppCompatActivity {
    StudentDao studentDao;
    TutorDao tutorDao;
    MyRoomDatabase roomDatabase;
    TextView textViewToTeachCourse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend);
        Bundle bundle = getIntent().getExtras();
        int idStudent = bundle.getInt("idStudent");

        textViewToTeachCourse = (TextView) findViewById(R.id.recommended_courses);

        roomDatabase = MyRoomDatabase.getDatabase(getApplicationContext());
        studentDao = roomDatabase.studentDao();
        tutorDao = roomDatabase.tutorDao();
        SpecificCourseDao specificCourseDao = roomDatabase.specificCourseDao();
        CourseToTeachDao courseToTeachDao = roomDatabase.courseToTeachDao();
        textViewToTeachCourse.setVisibility(View.VISIBLE);
        StringBuilder textTeach = new StringBuilder();
        System.out.println("in recommend");

        new Thread(() -> {
            Student student = studentDao.getStudent(idStudent);
            System.out.println("in thread");
//                if (student.getStudyDomain().equals("Informatics")) {
//                    System.out.println("info");
                    ArrayList<SpecificCourse> courses = (ArrayList<SpecificCourse>) (specificCourseDao.getAllSpecificCoursesForStudent(student.getIdStudent()));
                    for (int i = 0; i < courses.size(); i++) {
                        System.out.println(courses.get(i).getCourseName());
                        ArrayList<CourseToTeach> courseToTeach = (ArrayList<CourseToTeach>) (courseToTeachDao.getAllCoursesForSpecificCourse(courses.get(i).getCourseName()));
                        for (int j = 0; j < courseToTeach.size(); j++) {
                            System.out.println(courseToTeach.get(j).getId_FkTutor());
                            int tutorId=(int)courseToTeach.get(j).getId_FkTutor();
                            textTeach.append(courseToTeach.get(j).getCourseName());
                            textTeach.append("\n");
                            Tutor tutor = tutorDao.getTutor(tutorId);
                            textTeach.append(tutor.getFirstName());
                            textTeach.append(" ");
                            textTeach.append(tutor.getLastName());
                            textTeach.append("\n");
                            textTeach.append("\n");


                        }
                    }
            System.out.println(textTeach);
            this.runOnUiThread(() -> {

                        textViewToTeachCourse.append(textTeach.toString());
                    });
//                } else if (student.getStudyDomain().equals("Mathematics")) {
//                    ArrayList<SpecificCourse> courses = (ArrayList<SpecificCourse>) (specificCourseDao.getAllSpecificCoursesForStudent(student.getIdStudent()));
//                    for (int i = 0; i < courses.size(); i++) {
//                        System.out.println(courses.get(i).getCourseName());
//                        ArrayList<CourseToTeach> courseToTeach = (ArrayList<CourseToTeach>) (courseToTeachDao.getAllCoursesForSpecificCourse(courses.get(i).getCourseName()));
//                        for (int j = 0; j < courseToTeach.size(); j++) {
//                            System.out.println(courseToTeach.get(j).getId_FkTutor());
//                            int tutorId=(int)courseToTeach.get(j).getId_FkTutor();
//                            textTeach.concat(courseToTeach.get(j).getCourseName());
//                            textTeach.concat("\n");
//                            Tutor tutor = tutorDao.getTutor(tutorId);
//                            textTeach.concat(tutor.getFirstName());
//                            textTeach.concat(" ");
//                            textTeach.concat(tutor.getLastName());
//                            textTeach.concat("\n");
//                        }
//                    }
//
//                } else if (student.getStudyDomain().equals("CTI")) {
//
//                    ArrayList<SpecificCourse> courses = (ArrayList<SpecificCourse>) (specificCourseDao.getAllSpecificCoursesForStudent(student.getIdStudent()));
//                    for (int i = 0; i < courses.size(); i++) {
//                        System.out.println(courses.get(i).getCourseName());
//                        ArrayList<CourseToTeach> courseToTeach = (ArrayList<CourseToTeach>) (courseToTeachDao.getAllCoursesForSpecificCourse(courses.get(i).getCourseName()));
//                        for (int j = 0; j < courseToTeach.size(); j++) {
//                            System.out.println(courseToTeach.get(j).getId_FkTutor());
//                            int tutorId = (int) courseToTeach.get(j).getId_FkTutor();
//                            textTeach.concat(courseToTeach.get(j).getCourseName());
//                            textTeach.concat("\n");
//                            Tutor tutor = tutorDao.getTutor(tutorId);
//                            textTeach.concat(tutor.getFirstName());
//                            textTeach.concat(" ");
//                            textTeach.concat(tutor.getLastName());
//                            textTeach.concat("\n");
//                        }
//                    }
//                }

//                    this.runOnUiThread(() -> {
//            });



        }).start();
    }
}
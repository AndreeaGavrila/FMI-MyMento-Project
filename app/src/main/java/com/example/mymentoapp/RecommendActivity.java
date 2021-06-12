package com.example.mymentoapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

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
import java.util.Collections;
import java.util.List;

public class RecommendActivity  extends AppCompatActivity {
    StudentDao studentDao;
    TutorDao tutorDao;
    MyRoomDatabase roomDatabase;
//    TextView textViewToTeachCourse;
    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend);
        Bundle bundle = getIntent().getExtras();
        String studentName = bundle.getString("studentName");

        //textViewToTeachCourse = (TextView) findViewById(R.id.recommended_courses);
        linearLayout = findViewById(R.id.layout_recommended);
        roomDatabase = MyRoomDatabase.getDatabase(getApplicationContext());
        studentDao = roomDatabase.studentDao();
        tutorDao = roomDatabase.tutorDao();
        SpecificCourseDao specificCourseDao = roomDatabase.specificCourseDao();
        CourseToTeachDao courseToTeachDao = roomDatabase.courseToTeachDao();
//        textViewToTeachCourse.setVisibility(View.VISIBLE);
        List<String> textTeach = new ArrayList<>();
        List<List<String>> coursesList = new ArrayList<>();
        System.out.println("in recommend");

        new Thread(() -> {
            Student student = studentDao.getStudentByUsername(studentName);
            System.out.println("in thread");
            ArrayList<SpecificCourse> courses = (ArrayList<SpecificCourse>) (specificCourseDao.getAllSpecificCoursesForStudent(student.getIdStudent()));
            for (int i = 0; i < courses.size(); i++) {
                System.out.println(courses.get(i).getCourseName());
                ArrayList<CourseToTeach> courseToTeach = (ArrayList<CourseToTeach>) (courseToTeachDao.getAllCoursesForSpecificCourse(courses.get(i).getCourseName()));
                System.out.println(courseToTeach);
                for (int j = 0; j < courseToTeach.size(); j++) {
                    System.out.println(courseToTeach.get(j).getId_FkTutor());
                    int tutorId=(int)courseToTeach.get(j).getId_FkTutor();
                    textTeach.add(courseToTeach.get(j).getCourseName());
                    Tutor tutor = tutorDao.getTutor(tutorId);
                    textTeach.add(tutor.getLastName());
                    textTeach.add(tutor.getFirstName());
                    System.out.println(textTeach);
                    coursesList.add(new ArrayList<>(textTeach));
                    textTeach.clear();
                }
            }
            for(int i=0;i<coursesList.size()-1;i++)
                for(int j=i+1;j<coursesList.size();j++){
                    //Integer ii =i, jj=j;
                    //System.out.println(ii.toString());
                    //System.out.println(jj.toString());
                    //System.out.println(coursesList.size());
                    //System.out.println(coursesList);
                    if((coursesList.get(j)).get(0).compareTo((coursesList.get(i)).get(0))<0){
                        Collections.swap(coursesList,i,j);
                    }
                    else if((coursesList.get(j)).get(0).compareTo((coursesList.get(i)).get(0))==0 && (coursesList.get(j)).get(1).compareTo((coursesList.get(i)).get(1))<0){
                        Collections.swap(coursesList,i,j);
                    }
                    else if((coursesList.get(j)).get(0).compareTo((coursesList.get(i)).get(0))==0 && (coursesList.get(j)).get(1).compareTo((coursesList.get(i)).get(1))==0 && (coursesList.get(j)).get(2).compareTo((coursesList.get(i)).get(2))<0){
                        Collections.swap(coursesList,i,j);
                    }

                }
            List<List<String>> finalCoursesList = new ArrayList<>((coursesList));

            runOnUiThread((() -> {
                for(int i=0;i<finalCoursesList.size();i++){
                    Button btn = new Button(getApplicationContext());
                    String course = finalCoursesList.get(i).get(0);
                    btn.setText(course);
                    btn.setOnClickListener(v->{
                        Intent newIntent = new Intent (RecommendActivity.this, ViewAvailableCoursesActivity.class);
                        newIntent.putExtra("courseName", course);
                        newIntent.putExtra("studentName", studentName);
                        startActivity(newIntent);
                    });

                    StringBuilder tutor_name = new StringBuilder();
                    String last_name = finalCoursesList.get(i).get(1);
                    String first_name = finalCoursesList.get(i).get(2);
                    tutor_name.append(finalCoursesList.get(i).get(1));
                    tutor_name.append(" ");
                    tutor_name.append(finalCoursesList.get(i).get(2));
                    Button btn_tutor = new Button(getApplicationContext());
                    btn_tutor.setText(tutor_name);
                    btn_tutor.setOnClickListener(v->{
                        Intent newIntent = new Intent (RecommendActivity.this, TutorContactActivity.class);
                        //newIntent.putExtra("courseName", course);
                        newIntent.putExtra("tutorLastName", last_name);
                        newIntent.putExtra("tutorFirstName", first_name);
                        newIntent.putExtra("studentUsername", studentName);
                        startActivity(newIntent);
                    });
                    linearLayout.addView(btn);
                    linearLayout.addView(btn_tutor);
                }
            }));

        }).start();
    }
}
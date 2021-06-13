package com.example.mymentoapp;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mymentoapp.data.StudentDao;
import com.example.mymentoapp.data.TaughtCourseDao;
import com.example.mymentoapp.data.TutorDao;
import com.example.mymentoapp.model.StudentViewModel;
import com.example.mymentoapp.model.TaughtCourseViewModel;
import com.example.mymentoapp.model.Tutor;
import com.example.mymentoapp.model.TutorViewModel;
import com.example.mymentoapp.util.MyRoomDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StudentListingActivity extends AppCompatActivity {
//    StudentDao studentDao;
//    TutorDao tutorDao;
//    TaughtCourseDao taughtCourseDao;
    StudentViewModel studentViewModel;
    TutorViewModel tutorViewModel;
    TaughtCourseViewModel taughtCourseViewModel;
    //MyRoomDatabase roomDatabase;

    LinearLayout linearLayout;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_listing);

        Bundle bundle = getIntent().getExtras();
        String tutorName = bundle.getString("tutorName");
        String orderCriteria = bundle.getString("orderCriteria");

//        roomDatabase = MyRoomDatabase.getDatabase(getApplicationContext());
//        studentDao = roomDatabase.studentDao();
//        tutorDao = roomDatabase.tutorDao();
//        taughtCourseDao = roomDatabase.taughtCourseDao();

        linearLayout = findViewById(R.id.layout_student_list);


        new Thread(() -> {
            studentViewModel = new StudentViewModel(this.getApplication());
            taughtCourseViewModel = new TaughtCourseViewModel(this.getApplication());
            tutorViewModel = new TutorViewModel(this.getApplication());

            List<String> studentList = new ArrayList<>();
            Tutor tutor=tutorViewModel.getTutorByUsername(tutorName);
            studentList = taughtCourseViewModel.getStudentAndCourseByTutorId(tutor.getIdStudent());

            if (studentList.size() == 0) {
                TextView message = new TextView(getApplicationContext());
                message.setText("You don't have any students yet");
                linearLayout.addView(message);
            }
            else{
                if(orderCriteria.equals("alphabetic")){

                    System.out.println(studentList);
                    List<String> alphaList = new ArrayList<>();
                    for(String st : studentList){
                        String studentName;
                        studentName=st.split(",")[0];
                        System.out.println(studentName);
                        alphaList.add(studentName);
                    }

                    for(int i=0;i<alphaList.size()-1;i++)
                        for(int j=i+1;j<alphaList.size();j++){
                            if((alphaList.get(j)).compareTo((alphaList.get(i)))<0){
                                Collections.swap(alphaList,i,j);
                            }
                        }
                    for(int i=0;i<alphaList.size()-1;i++)       //remove duplicates
                        if((alphaList.get(i+1)).compareTo((alphaList.get(i)))==0){
                            alphaList.remove(i+1);
                            i--;
                        }
                    for(int i=0;i<alphaList.size();i++){
                        TextView studentView = new TextView(getApplicationContext());
                        studentView.setText(alphaList.get(i).concat("\n"));

                        this.runOnUiThread(() -> {
                            linearLayout.addView(studentView);
                        });
                    }
                }
                else if(orderCriteria.equals("course_order")) {
                    System.out.println(studentList);
                    List<List<String>> orderCourse = new ArrayList<>();
                    List<String> item = new ArrayList<>();
                    for (String st : studentList) {
                        String studentName, courseName;
                        studentName = st.split(",")[0];
                        System.out.println(st);
                        courseName = st.split(",")[1];
                        item.add(studentName);
                        item.add(courseName);
                        orderCourse.add(new ArrayList<>(item));
                        System.out.println(item);
                        item.clear();
                    }
                    System.out.println(orderCourse);
                    for (int i = 0; i < orderCourse.size() - 1; i++) //order by courses
                        for (int j = i + 1; j < orderCourse.size(); j++) {
                            if ((orderCourse.get(j).get(1)).compareTo((orderCourse.get(i).get(1))) < 0) {
                                Collections.swap(orderCourse, i, j);
                            } else if ((orderCourse.get(j)).get(1).compareTo((orderCourse.get(i)).get(1)) == 0 && (orderCourse.get(j)).get(0).compareTo((orderCourse.get(i)).get(0)) < 0) {
                                Collections.swap(orderCourse, i, j);
                            }
                        }
                    System.out.println(orderCourse);
                    String title = orderCourse.get(0).get(1);
                    TextView subjectTitle = new TextView(getApplicationContext());
                    subjectTitle.setText(title);
                    subjectTitle.setTypeface(Typeface.DEFAULT_BOLD);
                    TextView finalSubjectTitle = subjectTitle;
                    this.runOnUiThread(() -> linearLayout.addView(finalSubjectTitle));
                    TextView stName = new TextView(getApplicationContext());
                    TextView finalStName = stName;
                    //TextView finalStName1 = stName;
                    this.runOnUiThread(()->{
                        finalStName.setText(orderCourse.get(0).get(0));
                        linearLayout.addView(finalStName);
                    });

                    for (int i = 1; i < orderCourse.size(); i++) {
                        if (orderCourse.get(i).get(1).compareTo(title) != 0) {
                            title = orderCourse.get(i).get(1);
                            subjectTitle = new TextView(getApplicationContext());
                            subjectTitle.setText(title);
                            subjectTitle.setTypeface(Typeface.DEFAULT_BOLD);
                            TextView finalSubjectTitle1 = subjectTitle;
                            this.runOnUiThread(()->{
                                linearLayout.addView(finalSubjectTitle1);
                            });
                            stName = new TextView(getApplicationContext());
                            stName.setText(orderCourse.get(i).get(0));
                            TextView finalStName2 = stName;
                            this.runOnUiThread(()->{
                                linearLayout.addView(finalStName2);
                            });

                        } else if (orderCourse.get(i).get(1).compareTo(title) == 0) {
                            stName = new TextView(getApplicationContext());
                            stName.setText(orderCourse.get(i).get(0));
                            TextView finalStName1 = stName;
                            this.runOnUiThread(() -> {
                                linearLayout.addView(finalStName1);
                            });
                        }

                    }
                }
                else{
                    studentList = taughtCourseViewModel.getStudentAndAttendance(tutor.getIdStudent());
                    System.out.println(studentList);

                    List<String> attendanceList = new ArrayList<>();
                    for(String st : studentList){
                        String studentName;
                        studentName=st.split(",")[0];
                        System.out.println(studentName);
                        attendanceList.add(studentName);
                    }
                    for(int i=0;i<attendanceList.size()-1;i++)       //remove duplicates
                        if((attendanceList.get(i+1)).compareTo((attendanceList.get(i)))==0){
                            attendanceList.remove(i+1);
                            i--;
                        }
                    for(int i=0;i<attendanceList.size();i++){
                        TextView studentView = new TextView(getApplicationContext());
                        studentView.setText(attendanceList.get(i).concat("\n"));
                        this.runOnUiThread(()->{
                            linearLayout.addView(studentView);
                        });
                    }
                }
            }

        }).start();
    }

}
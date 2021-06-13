package com.example.mymentoapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Pair;
import android.view.Gravity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mymentoapp.data.StudentDao;
import com.example.mymentoapp.data.TaughtCourseDao;
import com.example.mymentoapp.data.TutorDao;
import com.example.mymentoapp.model.Student;
import com.example.mymentoapp.model.Tutor;
import com.example.mymentoapp.util.MyRoomDatabase;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;

import static android.drm.DrmStore.DrmObjectType.CONTENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static java.lang.Integer.parseInt;

public class StudentListingActivity extends AppCompatActivity {
    StudentDao studentDao;
    TutorDao tutorDao;
    TaughtCourseDao taughtCourseDao;
    MyRoomDatabase roomDatabase;

    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_listing);

        Bundle bundle = getIntent().getExtras();
        String tutorName = bundle.getString("tutorName");
        String orderCriteria = bundle.getString("orderCriteria");

        roomDatabase = MyRoomDatabase.getDatabase(getApplicationContext());
        studentDao = roomDatabase.studentDao();
        tutorDao = roomDatabase.tutorDao();
        taughtCourseDao = roomDatabase.taughtCourseDao();

        linearLayout = findViewById(R.id.layout_student_list);


        new Thread(() -> {
            List<String> studentList = new ArrayList<>();
            Tutor tutor = tutorDao.getTutorByUserName(tutorName);
            studentList = taughtCourseDao.getStudentAndCourseByTutorId(tutor.getIdStudent());
            if (studentList.size() == 0) {
                TextView mesaj = new TextView(getApplicationContext());
                mesaj.setText("You don't have any students yet");
                linearLayout.addView(mesaj);
            } else {
                if (orderCriteria.equals("alphabetic")) {

                    System.out.println(studentList);
                    List<String> alphaList = new ArrayList<>();
                    for (String st : studentList) {
                        String studentName;
                        studentName = st.split(",")[0];
                        System.out.println(studentName);
                        alphaList.add(studentName);
                    }

                    for (int i = 0; i < alphaList.size() - 1; i++)
                        for (int j = i + 1; j < alphaList.size(); j++) {
                            if ((alphaList.get(j)).compareTo((alphaList.get(i))) < 0) {
                                Collections.swap(alphaList, i, j);
                            }
                        }
                    for (int i = 0; i < alphaList.size() - 1; i++)       //remove duplicates
                        if ((alphaList.get(i + 1)).compareTo((alphaList.get(i))) == 0) {
                            alphaList.remove(i + 1);
                            i--;
                        }
                    linearLayout.setPadding(40,10,40,10);
                    for (int i = 0; i < alphaList.size(); i++) {
                        LinearLayout studentLinear = new LinearLayout(getApplicationContext());
                        studentLinear.setGravity(Gravity.CENTER);
                        studentLinear.setOrientation(LinearLayout.VERTICAL);
                        studentLinear.setPadding(70, 10, 70, 10);
                        TextView studentView = new TextView(getApplicationContext());
                        studentView.setGravity(Gravity.CENTER);
                        studentView.setText("\n".concat(alphaList.get(i).concat("\n")));
                        studentView.setWidth(WRAP_CONTENT);
                        if(i%2==0) {
                            studentView.setBackgroundColor(Color.parseColor("#2850a1"));
                            studentView.setTextColor(Color.parseColor("#C4C9D0"));
                        }else {
                            studentView.setBackgroundColor(Color.parseColor("#fbc304"));
                        }
                        studentLinear.addView(studentView);
                        linearLayout.addView(studentLinear);
                    }
                } else if (orderCriteria.equals("course_order")) {
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
                    linearLayout.setPadding(40,10,40,10);

                    LinearLayout groupLinear = new LinearLayout(getApplicationContext());
                    groupLinear.setGravity(Gravity.CENTER);
                    groupLinear.setOrientation(LinearLayout.VERTICAL);
                    groupLinear.setPadding(70, 10, 70, 10);
                    String title = orderCourse.get(0).get(1);
                    TextView subjectTitle = new TextView(getApplicationContext());
                    subjectTitle.setText(title);
                    subjectTitle.setTypeface(Typeface.DEFAULT_BOLD);
                    subjectTitle.setTextSize(16);
                    subjectTitle.setBackgroundColor(Color.parseColor("#fbc304"));
                    subjectTitle.setPadding(10,10,10,10);
                    subjectTitle.setGravity(Gravity.CENTER);
                    groupLinear.addView(subjectTitle);
                    TextView stName = new TextView(getApplicationContext());
                    stName.setText(orderCourse.get(0).get(0));
                    stName.setGravity(Gravity.CENTER);
                    stName.setTextColor(Color.parseColor("#C4C9D0"));
                    stName.setBackgroundColor(Color.parseColor("#2850a1"));
                    stName.setPadding(5,5,5,5);
                    groupLinear.addView(stName);
                    for (int i = 1; i < orderCourse.size(); i++) {
                        if (orderCourse.get(i).get(1).compareTo(title) != 0) {
                            linearLayout.addView(groupLinear);

                            groupLinear = new LinearLayout(getApplicationContext());
                            groupLinear.setGravity(Gravity.CENTER);
                            groupLinear.setOrientation(LinearLayout.VERTICAL);
                            groupLinear.setPadding(70, 10, 70, 10);
                            title = orderCourse.get(i).get(1);
                            subjectTitle = new TextView(getApplicationContext());
                            subjectTitle.setText(title);
                            subjectTitle.setTypeface(Typeface.DEFAULT_BOLD);
                            subjectTitle.setTextSize(16);
                            subjectTitle.setBackgroundColor(Color.parseColor("#fbc304"));
                            subjectTitle.setPadding(10,10,10,10);
                            subjectTitle.setGravity(Gravity.CENTER);
                            groupLinear.addView(subjectTitle);
                            stName = new TextView(getApplicationContext());
                            stName.setText(orderCourse.get(i).get(0));
                            stName.setGravity(Gravity.CENTER);
                            stName.setTextSize(13);
                            stName.setTextColor(Color.parseColor("#C4C9D0"));
                            stName.setBackgroundColor(Color.parseColor("#2850a1"));
                            stName.setPadding(5,5,5,5);
                            groupLinear.addView(stName);

                        } else if (orderCourse.get(i).get(1).compareTo(title) == 0) {
                            stName = new TextView(getApplicationContext());
                            stName.setText(orderCourse.get(i).get(0));
                            stName.setGravity(Gravity.CENTER);
                            stName.setTextSize(13);
                            stName.setTextColor(Color.parseColor("#C4C9D0"));
                            stName.setBackgroundColor(Color.parseColor("#2850a1"));
                            stName.setPadding(5,5,5,5);
                            groupLinear.addView(stName);
                        }

                    }
                    linearLayout.addView(groupLinear);
                } else {
                    studentList = taughtCourseDao.getStudentAndAttendace(tutor.getIdStudent());
                    System.out.println(studentList);

                    List<String> attendanceList = new ArrayList<>();
                    List<Integer> noAttendace = new ArrayList<>();
                    for (String st : studentList) {
                        String studentName;
                        Integer noAtt;
                        studentName = st.split(",")[0];
                        noAtt = parseInt(st.split(",")[1]);
                        System.out.println(studentName);
                        attendanceList.add(studentName);
                        noAttendace.add(noAtt);
                    }

                    for (int i = 0; i < attendanceList.size() - 1; i++) //order by courses
                        for (int j = i + 1; j < attendanceList.size(); j++) {
                            if (noAttendace.get(j)>noAttendace.get(i)) {
                                Collections.swap(noAttendace, i, j);
                                Collections.swap(attendanceList,i,j);
                            } else if (noAttendace.get(j)==noAttendace.get(i) && (attendanceList.get(j)).compareTo((attendanceList.get(i))) > 0) {
                                Collections.swap(attendanceList, i, j);
                                Collections.swap(noAttendace,i,j);
                            }
                        }

                    for (int i = 0; i < attendanceList.size() - 1; i++)       //remove duplicates
                        if ((attendanceList.get(i + 1)).compareTo((attendanceList.get(i))) == 0) {
                            attendanceList.remove(i + 1);
                            i--;
                        }
                    linearLayout.setPadding(40,10,40,10);
                    for (int i = 0; i < attendanceList.size(); i++) {
                        LinearLayout studentLinear = new LinearLayout(getApplicationContext());
                        studentLinear.setGravity(Gravity.CENTER);
                        studentLinear.setOrientation(LinearLayout.VERTICAL);
                        studentLinear.setPadding(70, 10, 70, 10);
                        TextView studentView = new TextView(getApplicationContext());
                        studentView.setText("\n".concat(attendanceList.get(i).concat("\n")));
                        studentView.setGravity(Gravity.CENTER);
                        if(i%2==0) {
                            studentView.setBackgroundColor(Color.parseColor("#2850a1"));
                            studentView.setTextColor(Color.parseColor("#C4C9D0"));
                        }else {
                            studentView.setBackgroundColor(Color.parseColor("#fbc304"));
                        }
                        studentLinear.addView(studentView);
                        linearLayout.addView(studentLinear);
                    }
                }
            }
        }).start();
    }

}

package com.example.mymentoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.GridLayout;

import com.example.mymentoapp.model.SpecificCourse;
import com.example.mymentoapp.model.SpecificCourseViewModel;
import com.example.mymentoapp.model.Student;
import com.example.mymentoapp.model.StudentViewModel;
import com.example.mymentoapp.model.StudentWithCourse;
import com.example.mymentoapp.model.Tutor;
import com.example.mymentoapp.model.TutorViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private StudentViewModel studentViewModel;
    private TutorViewModel tutorViewModel;
    private SpecificCourseViewModel specificCourseViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        studentViewModel = new ViewModelProvider.AndroidViewModelFactory(MainActivity.
                this.getApplication()).create(StudentViewModel.class);
        studentViewModel.getAllStudents().observe(this, students -> {
            StringBuilder builder =  new StringBuilder();
            for(Student cd : students){
                builder.append(" - ").append(cd.getFirstName()).append(" ").append(cd.getLastName());
                System.out.println("oncreate " + cd.getFirstName());
            }
        });
        tutorViewModel = new ViewModelProvider.AndroidViewModelFactory(MainActivity.
                this.getApplication()).create(TutorViewModel.class);

        specificCourseViewModel = new ViewModelProvider.AndroidViewModelFactory(MainActivity.
                this.getApplication()).create(SpecificCourseViewModel.class);


//        Tutor tutor = new Tutor("Ionel", "Mihaila", "III", "Mathematics", "0758848988", "mihaila@gmail.com", "mionel", "1243", 5, "142424242242422");
//        TutorViewModel.insert(tutor);

        Student student = new Student("Iulian", "Andronache", "II", "Mathematics", "0758848988", "mihaila@gmail.com", "mionel", "1243");
        //StudentViewModel.insert(student); asta doar daca nu ii atribui si cursuri

        SpecificCourse specificCourse = new SpecificCourse("OOP", "UN CURS FOARTE REUSIT");
//        SpecificCourse specificCourse2 =  new SpecificCourse("BD", "Curs sustinut la baze de date");

        List<SpecificCourse> specificCourseList =  new ArrayList<SpecificCourse>();
        specificCourseList.add(specificCourse);
//        specificCourseList.add(specificCourse2);

        StudentWithCourse studentWithCourse =  new StudentWithCourse(student, specificCourseList);
        StudentViewModel.repository.insertStudentWithCourses(studentWithCourse); // asta cand ii adaug direct cu cursuri


    }
}
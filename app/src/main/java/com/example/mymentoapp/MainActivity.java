package com.example.mymentoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

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

//        TutorViewModel.deleteAll();
//        StudentViewModel.deleteAll();
//        SpecificCourseViewModel.deleteAll();

        Tutor tutor = new Tutor("Ionel", "Mihaila", "III", "Mathematics", "0758848988", "mihaila@gmail.com", "mionel", "1243", 5, "142424242242422");
        TutorViewModel.repository.insertTutor(tutor);

//        CourseToTeach courseToTeach =  new CourseToTeach("OOP", "oricand");
//        System.out.println("course"+ courseToTeach.getCourseName());
//        CourseToTeachViewModel.repository.insert(courseToTeach);
//
//        List<CourseToTeach> courseToTeachList =  new ArrayList<CourseToTeach>();
//        courseToTeachList.add(courseToTeach);
//
//        TutorWithCourse tutorWithCourse =  new TutorWithCourse(tutor, courseToTeachList);
//        TutorViewModel.insertTutorWithCourses(tutorWithCourse);


        Student student = new Student("ROMICA", "Andronache", "II", "Mathematics", "0758848988", "romica@gmail.com", "mionel", "1243");
        StudentViewModel.repository.insertStudent(student);

        SpecificCourse specificCourse = new SpecificCourse("OOP2", "UN CURS FOARTE REUSIT");
        SpecificCourse specificCourse2 = new SpecificCourse("BD", "Curs sustinut la baze de date");

        List<SpecificCourse> specificCourseList =  new ArrayList<SpecificCourse>();
        specificCourseList.add(specificCourse);

        List<SpecificCourse> specificCourseList2 =  new ArrayList<SpecificCourse>();
        specificCourseList2.add(specificCourse);
        specificCourseList2.add(specificCourse2);

        StudentWithCourse studentWithCourse =  new StudentWithCourse(student, specificCourseList);
        StudentViewModel.insertStudentWithCourses(studentWithCourse); // asta cand ii adaug direct cu cursuri
        // TODO: 06.05.2021 daca fac asta asat mai jos se pune automat si in tabel chiar daca am adaugat mai sus

        // specificCourseList.add(specificCourse2);

        StudentWithCourse studentWithCourse1  =  new StudentWithCourse(tutor, specificCourseList2);
        StudentViewModel.insertStudentWithCourses(studentWithCourse1);

    }
}
// TODO: 29.04.2021 cand creez un profil de student in functie de an ii adaugi cursurile, la fel si la prof
// TODO: 29.04.2021  pentru profi faci alte cursuri pe care le pot preda -> toti anii de mai jos de el la toate domeniile
// ai cursurile, cand apesi pe unul ai optiune -> daca esti student ai optiune de cauta profesor curs
// daca estu prof ai optiune cauta prof sau preda curs...

// TODO: 06.05.2021 AI RAMAS LA UN TUTORE ARE MAI MULTE CURSURI PE CARE LE POATE PREDA I.E. CURSURI DISPONIBILE = TOATE CURSURILE
// PE CARE EL DEJA LE-A PARCURS ?
// DACA DIN ASTEA ISI ALEGE SA PREDEA LETS SAY 2, ATUNCI ASTEA DOUA CURSURI SE ADAUGA INTR-UN NOU TABEL CARE SA AIBA SI ID-UL PROFULUI
// DIN ACEST TABEL IAU ATUNCI CAND STUDENTU CAUTA UN ANUME CURS CU UN PROF
// CAND STUDENTUL ALEGE SA FACA UN CURS CU UN PROF, ACEL CURS SE ADAUGA IN ALT TABEL LETS SAY CURSURI_TINUTE CARE ARE
// ATAT ID-UL PROFULUI CAT SI AL STUDENTULUI 1-M DE LA STUDENT SI 1-M DE LA PROF CATRE ACEST TABEL


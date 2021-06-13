package com.example.mymentoapp.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.mymentoapp.data.StudentRepository;

import java.util.List;

public class StudentViewModel extends AndroidViewModel {
    public static StudentRepository repository;
    public final List<Student> allStudents;


    public StudentViewModel(@NonNull Application application) {
        super(application);
        repository =  new StudentRepository(application);
        allStudents = repository.getAllData();

    }

    public void insertStudentWithCourses(StudentWithCourse studentWithCourse){
        repository.insertStudentWithCourses(studentWithCourse);
    }
    public void insertStudentWithTaughtCourses(StudentWithTaughtCourses studentWithTaughtCourses){
        System.out.println("aici a ajuns");
        System.out.println(studentWithTaughtCourses.getStudent());
        repository.insertStudentWithTaughtCourses(studentWithTaughtCourses);
        System.out.println("dupa insert");
    }

    public List<Student> getAllStudents(){
        return allStudents;
    }
    public void insert(Student student){
        repository.insertStudent(student);
    }

    public void deleteAll(){
        repository.deleteAll();
    }

    public void updateStudent(Student student){repository.updateStudent(student);};

    public Student getStudent(int id){return  repository.getStudent(id);}
    public Student getStudentByUsername(String name){
        return repository.getStudentByUsername(name);
    }
    //public static void deleteSpecificCourse(int idFkInput){repository.deleteSpecificCourse(idFkInput);}

//    public static void updateStudentWithCourse(StudentWithCourse studentWithCourse){
//        repository.updateStudentWithCourse(studentWithCourse);
//    }
//    public Student getStudent(int studentIdInput){
//        return repository.getStudent(studentIdInput);
//    }

    public Student getStudentByUsernameAndPassword(String usernameInput, String passwordInput){
        return repository.getStudentByUsernameAndPassword(usernameInput, passwordInput);
    }
}

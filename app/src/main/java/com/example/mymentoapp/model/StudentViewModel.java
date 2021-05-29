package com.example.mymentoapp.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mymentoapp.data.StudentRepository;


import java.util.List;
import java.util.Objects;

public class StudentViewModel extends AndroidViewModel {
    public static StudentRepository repository;
    public final LiveData<List<Student>> allStudents;


    public StudentViewModel(@NonNull Application application) {
        super(application);
        repository =  new StudentRepository(application);
        allStudents = repository.getAllData();

    }

//    public Student getStudent(int id){
//        Student student = Objects.requireNonNull(repository.getAllData().getValue()).listIterator().next();
//            if(student.getIdStudent() == id){
//                return student;
//        }
//        return null;
//    }
    public void updateStudent(Student student){
        repository.updateStudent(student);
    }
    public static void insertStudentWithCourses(StudentWithCourse studentWithCourse){
        repository.insertStudentWithCourses(studentWithCourse);
    }
    public static void insertStudentWithTaughtCourses(StudentWithTaughtCourses studentWithTaughtCourses){
//        System.out.println("aici a ajuns");
//        System.out.println(studentWithTaughtCourses.getStudent());
//        repository.insertStudentWithTaughtCourses(studentWithTaughtCourses);
//        System.out.println("dupa insert");
    }

    public LiveData<List<Student>> getAllStudents(){
        return allStudents;
    }
    public static void insert(Student student){
        repository.insertStudent(student);
    }

    public static void deleteAll(){
        repository.deleteAll();
    }

//    public Student getStudent(int studentIdInput){
//        return repository.getStudent(studentIdInput);
//    }
}

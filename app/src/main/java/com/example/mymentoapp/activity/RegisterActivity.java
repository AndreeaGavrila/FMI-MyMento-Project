package com.example.mymentoapp.activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mymentoapp.R;
import com.example.mymentoapp.model.Student;
import com.example.mymentoapp.model.StudentViewModel;

public class RegisterActivity extends AppCompatActivity {

    Button login, studentProfile, tutorProfile;
    EditText et_username, et_password, et_cpassword;

    private StudentViewModel studentViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);
        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        et_cpassword = findViewById(R.id.et_cpassword);
        login = findViewById(R.id.btn_login);
        studentProfile = findViewById(R.id.btn_create_student_profile);
        tutorProfile = findViewById(R.id.btn_create_tutor_profile);

        studentProfile.setOnClickListener(v -> {
            String username = et_username.getText().toString();
            String password = et_password.getText().toString();
            String confirmPassword = et_cpassword.getText().toString();
            createPerson(password, confirmPassword, username, 0);
        });

        tutorProfile.setOnClickListener(v -> {
            String username = et_username.getText().toString();
            String password = et_password.getText().toString();
            String confirmPassword = et_cpassword.getText().toString();
            createPerson(password, confirmPassword, username, 1);
        });
        login.setOnClickListener(v -> startActivity(new Intent(RegisterActivity.this, LoginActivity.class)));
    }

    private Boolean validateInput(Student student, String confirmPassword) {
        return !student.getUsername().isEmpty() &&
                !student.getPassword().isEmpty() &&
                !confirmPassword.equals("");
    }

    private void createPerson(String password, String confirmPassword, String username, int option){

        Student student = new Student();
        student.setUsername(username);
        student.setPassword(password);
        if(!validateInput(student, confirmPassword))
        {
            Toast.makeText(getApplicationContext(), "Fields Required", Toast.LENGTH_SHORT).show();
        }else if(!password.equals(confirmPassword)){
            Toast.makeText(getApplicationContext(), "Password and confirm password must be the same!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            new Thread(() -> {
                studentViewModel = new StudentViewModel(this.getApplication());
                Student existingUsername = studentViewModel.getStudentByUsername(username);
                new Thread(() -> {
                    if(existingUsername != null){
                        runOnUiThread(() -> Toast.makeText(getApplicationContext(), "Username already used!", Toast.LENGTH_SHORT).show());
                    }
                    else{
                        studentViewModel.registerStudent(student);
                        runOnUiThread(() -> {
                            Toast.makeText(getApplicationContext(), "Student Registered!", Toast.LENGTH_SHORT).show();
                            Intent intent;
                            if (option == 0){
                                intent = new Intent(RegisterActivity.this, ProfileStudentActivity.class);
                            }else{
                                intent = new Intent(RegisterActivity.this, ProfileTutorActivity.class);
                            }
                            intent.putExtra("username", username);
                            intent.putExtra("password", password);
                            startActivity(intent);
                        });
                    }
                }).start();
            }).start();
        }
    }



}
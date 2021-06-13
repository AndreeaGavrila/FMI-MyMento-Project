package com.example.mymentoapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mymentoapp.data.StudentDao;
import com.example.mymentoapp.data.TutorDao;
import com.example.mymentoapp.model.Login;
import com.example.mymentoapp.model.RegisterViewModel;
import com.example.mymentoapp.model.Student;
import com.example.mymentoapp.model.StudentViewModel;
import com.example.mymentoapp.model.TutorViewModel;
import com.example.mymentoapp.util.MyRoomDatabase;

public class RegisterActivity extends AppCompatActivity {

    Button register, login;
    EditText et_username, et_password, et_cpassword;


    private RegisterViewModel registerViewModel;

    private StudentViewModel studentViewModel;
    private StudentDao studentDao;

    private TutorViewModel tutorViewModel;
    private TutorDao tutorDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        et_cpassword = findViewById(R.id.et_cpassword);

        login = (Button) findViewById(R.id.btn_login);
        register = (Button) findViewById(R.id.btn_register);


        register.setOnClickListener(v -> {

            // Creating Student entity
            Student student = new Student();
            student.setUsername(et_username.getText().toString());
            student.setPassword(et_password.getText().toString());

            String username = et_username.getText().toString();
            String password = et_password.getText().toString();
            String confirm_password = et_cpassword.getText().toString();


            if( !validateInput(student))
            {
                Toast.makeText(getApplicationContext(), "Fields Required", Toast.LENGTH_SHORT).show();
            }else if(!password.equals(confirm_password)){
                Toast.makeText(getApplicationContext(), "Password and confirm password must be the same!", Toast.LENGTH_SHORT).show();
            }
            else
            {
                MyRoomDatabase roomDatabase = MyRoomDatabase.getDatabase(getApplicationContext());
                StudentDao studentDao = roomDatabase.studentDao();

                new Thread(() -> {
                    Student student1 = studentDao.getStudentByUsername(username);
                    new Thread(() -> {
                        if(student1 != null){
                            runOnUiThread(() -> Toast.makeText(getApplicationContext(), "Username already used!", Toast.LENGTH_SHORT).show());
                        }
                        else{
                            studentDao.registerStudent(student);
                            runOnUiThread(() -> {
                                Toast.makeText(getApplicationContext(), "Student Registred!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegisterActivity.this, ChooseStatusActivity.class);
                                intent.putExtra("username", username);
                                intent.putExtra("password", password);
                                startActivity(intent);
                            });
                        }
                    }).start();

                }).start();
            }
        });

        login.setOnClickListener(v -> startActivity(new Intent(RegisterActivity.this, Login.class)));
    }


    private Boolean validateInput(Student student) {
        return !student.getUsername().isEmpty() &&
                !student.getPassword().isEmpty() &&
                !et_cpassword.getText().toString().equals("");
    }




}

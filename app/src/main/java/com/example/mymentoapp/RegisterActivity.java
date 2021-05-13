package com.example.mymentoapp;

import android.app.AppComponentFactory;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mymentoapp.data.StudentDao;
import com.example.mymentoapp.data.StudentRepository;

import com.example.mymentoapp.data.TutorDao;
import com.example.mymentoapp.data.TutorRepository;

import com.example.mymentoapp.model.Login;
import com.example.mymentoapp.model.LoginViewModel;

import com.example.mymentoapp.model.Register;
import com.example.mymentoapp.model.RegisterViewModel;

import com.example.mymentoapp.model.Student;
import com.example.mymentoapp.model.StudentViewModel;

import com.example.mymentoapp.model.Tutor;
import com.example.mymentoapp.model.TutorViewModel;

import com.example.mymentoapp.util.MyRoomDatabase;

import java.util.List;
import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {

    Button register, login;
    EditText et_username, et_password, et_cpassword;

    Boolean insert;

    private RegisterViewModel registerViewModel;

    private StudentViewModel studentViewModel;
    private StudentDao studentDao;

    private TutorViewModel tutorViewModel;
    private TutorDao tutorDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        et_username = (EditText) findViewById(R.id.et_username);
        et_password = (EditText) findViewById(R.id.et_password);
        et_cpassword = (EditText) findViewById(R.id.et_cpassword);

        login = (Button) findViewById(R.id.btn_login);
        register = (Button) findViewById(R.id.btn_register);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Creating Student entity
                Student student = new Student();
                student.setUsername(et_username.getText().toString());
                student.setPassword(et_password.getText().toString());

                String username = et_username.getText().toString();
                String password = et_password.getText().toString();
                String confirm_password = et_cpassword.getText().toString();


                if( !validateInput(student) )
                {
                    Toast.makeText(getApplicationContext(), "Fields Required", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    MyRoomDatabase roomDatabase = MyRoomDatabase.getDatabase(getApplicationContext());
                    StudentDao studentDao = roomDatabase.studentDao();

                    new Thread(new Runnable() {
                        @Override
                        public void run()
                        {
                            // Register Student
                            studentDao.registerStudent(student);

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "Student Registred!", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }).start();
                }


//-------------------------------------------------------------------------------------------------
//------------------------------------ OLD VERSION  -----------------------------------------------
//-------------------------------------------------------------------------------------------------

//                    if(password.equals(confirm_password))
//                        {
//
//                        Boolean checkusername = databaseHelper.CheckStudentUsername(username);
//                        System.out.println(checkusername);
//
//                        if(checkusername == true)
//                            {
//                            Student s = new Student(username, "", "", password, "", "", "", "");
//
////                            Intent intent2 = new Intent(Register.this, ProfileTutorActivity.class);
////                            intent2.putExtra("registeredUsername", s.getUserName());
//                            // startActivity(intent2);
//
//                            insert = databaseHelper.InsertStudent(s);
//
//                            if(insert == true)
//                                {
//                                Toast.makeText(getApplicationContext(), "Registered", Toast.LENGTH_SHORT).show();
//
//                                et_username.setText("");
//                                et_password.setText("");
//                                et_cpassword.setText("");
//
//                                Intent intent = new Intent(RegisterActivity.this, ChooseStatusActivity.class);
//
//                                intent.putExtra("registeredUsername", s.getUsername());
//                                intent.putExtra("registeredPassword", s.getPassword());
//
//                                startActivity(intent);
//                                }
//                            }
//                        else
//                            {
//                            Toast.makeText(getApplicationContext(), "Username already taken", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    else
//                        {
//                        Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//-------------------------------------------------------------------------------------------------

            }
        });
    }


    private Boolean validateInput(Student student) {
        if (student.getUsername().isEmpty() ||
                student.getPassword().isEmpty() ||
                et_cpassword.equals(""))
        {
            return false;
        }
        return true;
    }




}

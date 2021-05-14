package com.example.mymentoapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mymentoapp.data.StudentDao;

import com.example.mymentoapp.model.LoginViewModel;

import com.example.mymentoapp.model.Student;
import com.example.mymentoapp.model.StudentViewModel;

import com.example.mymentoapp.util.MyRoomDatabase;

public class LoginActivity extends AppCompatActivity {
    Button register, login;
    EditText et_lusername, et_lpassword;

    private LoginViewModel loginViewModel;

    private StudentViewModel studentViewModel;
    private StudentDao studentDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_lusername = (EditText) findViewById(R.id.username_input);
        et_lpassword = (EditText) findViewById(R.id.password_input);

        login = (Button) findViewById(R.id.btnl_login);
        register = (Button) findViewById(R.id.btnl_register);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = et_lusername.getText().toString();
                String password = et_lpassword.getText().toString();

                if(username.isEmpty() || password.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Enter both username and password for login",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    MyRoomDatabase roomDatabase = MyRoomDatabase.getDatabase(getApplicationContext());
                    StudentDao studentDao = roomDatabase.studentDao();

                    new Thread(new Runnable() {
                        @Override
                        public void run()
                        {
                            Student student = studentDao.getStudentbyUsername(username,password);

                            if( student == null)
                            {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(), "Invalid Credentials", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                            else
                            {
                                Intent intent = new Intent(LoginActivity.this, WelcomeActivity.class);
                                intent.putExtra("idStudent", student.getIdStudent());
                                startActivity(intent);
                            }
                        }

                    }).start();
                }
            }
        });

    }
}
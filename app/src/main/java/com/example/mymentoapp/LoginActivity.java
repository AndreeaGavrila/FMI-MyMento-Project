package com.example.mymentoapp;

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
import com.example.mymentoapp.model.Login;
import com.example.mymentoapp.model.LoginViewModel;
import com.example.mymentoapp.model.Student;
import com.example.mymentoapp.model.StudentViewModel;
import com.example.mymentoapp.model.TutorViewModel;
import com.example.mymentoapp.util.MyRoomDatabase;

import java.util.List;
import java.util.Objects;

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
                } else {
//                    if(studentDao.getStudentbyUsername(username,password)>0){
//                        Intent intent = new Intent(LoginActivity.this, Welcome.class);
//                        startActivity(intent);
//                        System.out.println("login");
//                    }else{
//                        System.out.println("notlogin");
//                    }
                }
            }
        });


    }
}
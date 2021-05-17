package com.example.mymentoapp;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import com.example.mymentoapp.data.SpecificCourseDao;
import com.example.mymentoapp.data.SpecificCourseRepository;
import com.example.mymentoapp.data.StudentDao;
import com.example.mymentoapp.model.AssignCourse;
import com.example.mymentoapp.model.LoginViewModel;
import com.example.mymentoapp.model.SpecificCourse;
import com.example.mymentoapp.model.SpecificCourseViewModel;
import com.example.mymentoapp.model.Student;
import com.example.mymentoapp.model.StudentViewModel;
import com.example.mymentoapp.util.MyRoomDatabase;
import java.util.ArrayList;
import java.util.List;
public class LoginActivity extends AppCompatActivity {
    Button register, login;
    EditText et_lusername, et_lpassword;
    private LoginViewModel loginViewModel;
    private StudentViewModel studentViewModel;
    private StudentDao studentDao;
    private SpecificCourseDao specificCourseDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        et_lusername = findViewById(R.id.username_input);
        et_lpassword = findViewById(R.id.password_input);
        login = findViewById(R.id.btnl_login);
        register = findViewById(R.id.btnl_register);
        Bundle bundle = getIntent().getExtras();

        login.setOnClickListener(v -> {
            String username = et_lusername.getText().toString();
            String password = et_lpassword.getText().toString();
            if(username.isEmpty() || password.isEmpty()){
                Toast.makeText(getApplicationContext(),"Enter both username and password for login",Toast.LENGTH_SHORT).show();
            }
            else
            {
                MyRoomDatabase roomDatabase = MyRoomDatabase.getDatabase(getApplicationContext());
                studentDao = roomDatabase.studentDao();
                specificCourseDao = roomDatabase.specificCourseDao();

                new Thread(() -> {
                    Student student = studentDao.getStudentByUsernameAndPassword2(username,password);

                    if( student == null)
                    {
                        runOnUiThread(() -> Toast.makeText(getApplicationContext(), "Invalid Credentials", Toast.LENGTH_SHORT).show());
                    }
                    else
                    {
                        Intent intent = new Intent(LoginActivity.this, WelcomeActivity.class);
                        intent.putExtra("idStudent", student.getIdStudent());
                        startActivity(intent);
                    }
                }).start();
            }
        });
    }
}
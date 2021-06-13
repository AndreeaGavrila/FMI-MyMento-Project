package com.example.mymentoapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mymentoapp.data.StudentDao;

import com.example.mymentoapp.data.TutorDao;

import com.example.mymentoapp.model.RegisterViewModel;

import com.example.mymentoapp.model.StudentViewModel;

import com.example.mymentoapp.model.TutorViewModel;


public class ChooseStatusActivity extends AppCompatActivity {


    Button chooseStudent, chooseTutor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_status);

        chooseStudent = findViewById(R.id.student_button);
        chooseTutor = findViewById(R.id.tutor_button);

        Bundle bundle = getIntent().getExtras();
        String username  = bundle.getString("username");

        System.out.println("Username choose: " + username) ;

        chooseStudent.setOnClickListener(v -> {
            Intent intent = new Intent(ChooseStatusActivity.this, ProfileStudentActivity.class);
            intent.putExtra("username", username);
            startActivity(intent);
        });

        chooseTutor.setOnClickListener(v -> {
            Intent intent = new Intent(ChooseStatusActivity.this, ProfileTutorActivity.class);
            intent.putExtra("username", username);
            startActivity(intent);
        });
    }


}

//

//        btn_submit_student = (Button) findViewById(R.id.btn_edit);
//
//
//        btn_submit_student.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                String firstname = firstName.getText().toString();
//                String lastname = lastName.getText().toString();
//                String phonenumber = phoneNumber.getText().toString();
//                String email1 = email.getText().toString();
//
//                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
//                String phonePattern = "^\\+[0-9]{10,13}$";
//
//                int selectedId = radioGroupDomain.getCheckedRadioButtonId();
//                RadioButton selecteddomainbutton = (RadioButton) findViewById(selectedId);
//                String domain = (String)selecteddomainbutton.getText();
//
//                int selectedId2 = radioGroupStudyYear.getCheckedRadioButtonId();
//                RadioButton selectedyearbutton = (RadioButton) findViewById(selectedId2);
//                String studyYear = (String)selectedyearbutton.getText();
//
//                if (firstname.equals("") || lastname.equals("") || phonenumber.equals("") || email1.equals("")  ||
//                        radioGroupDomain.getCheckedRadioButtonId() == -1 || radioGroupStudyYear.getCheckedRadioButtonId() == -1) {
//
//                    Toast.makeText(getApplicationContext(), "Fields Required", Toast.LENGTH_SHORT).show();
//                }
//                else
//                {
//                    if (!(email1.matches(emailPattern))) {
//
//                        Toast.makeText(getApplicationContext(), "INVALID MAIL", Toast.LENGTH_SHORT).show();
//                    }
//
//                    if (!(phonenumber.matches(phonePattern)))
//                    {
//                        Toast.makeText(getApplicationContext(), "INVALID PHONE NUMBER", Toast.LENGTH_SHORT).show();
//                    }
//                    else
//                    {
//                        Intent auxIntent = getIntent();
//
//                        String username = auxIntent.getStringExtra("registeredUsername");
//                        String password = auxIntent.getStringExtra("registeredPassword");
//
////                        if (databaseHelper.getStudent(username) != -1)
////                        {
////                            //update
////                            System.out.println("entered");
////                            if(databaseHelper.updateStudent(databaseHelper.getStudent(username),firstname,lastname,email1,phonenumber,studyYear,domain))
////                            {
////                                System.out.println("updated");
////
////                                Intent intent = new Intent(ProfileStudentActivity.this, Login.class);
////                                intent.putExtra("registeredUsername", username);
////
////                                startActivity(intent);
////
////                            }
////                        }
//
//                    }
//
//
//                }
//
//            }
//        });




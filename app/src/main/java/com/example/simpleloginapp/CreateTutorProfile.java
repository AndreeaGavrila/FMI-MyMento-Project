package com.example.simpleloginapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CreateTutorProfile extends AppCompatActivity {


    EditText lastName, firstName, phoneNumber, email, iban;
    RadioGroup radioGroupStudyYear, radioGroupDomain;
    Button btn_submit_tutor;
    DatabaseHelper databaseHelper;
    Boolean insert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tutor_profile);
        databaseHelper = new DatabaseHelper(this);

        lastName = (EditText) findViewById(R.id.last_name_text);
        firstName = (EditText) findViewById(R.id.first_name_text);
        phoneNumber = (EditText) findViewById(R.id.phone_text);
        email = (EditText) findViewById(R.id.email_text);
        iban = (EditText) findViewById(R.id.iban_id);

        radioGroupStudyYear = (RadioGroup) findViewById(R.id.radio_year);
        radioGroupDomain = (RadioGroup) findViewById(R.id.radio_domain);
        btn_submit_tutor = (Button) findViewById(R.id.button_submit_student);


        btn_submit_tutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String firstname = firstName.getText().toString();
                String lastname = lastName.getText().toString();
                String phonenumber = phoneNumber.getText().toString();
                String email1 = email.getText().toString();
                String iban1 = iban.getText().toString();
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                String phonePattern = "^\\+[0-9]{10,13}$";
                int selectedId = radioGroupDomain.getCheckedRadioButtonId();
                RadioButton selecteddomainbutton = (RadioButton) findViewById(selectedId);
                String domain = (String)selecteddomainbutton.getText();
                int selectedId2 = radioGroupStudyYear.getCheckedRadioButtonId();
                RadioButton selectedyearbutton = (RadioButton) findViewById(selectedId2);
                String studyYear = (String)selectedyearbutton.getText();

                if (firstname.equals("") || lastname.equals("") || phonenumber.equals("") || email1.equals("")  ||
                        radioGroupDomain.getCheckedRadioButtonId() == -1 || radioGroupStudyYear.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(getApplicationContext(), "Fields Required", Toast.LENGTH_SHORT).show();
                } else {
//                    if (!(iban1.substring(0, 2).equals("RO")) || !(iban1.substring(2, iban1.length()).matches("[0-9]+")) || iban1.length() != 24) {
//                        Toast.makeText(getApplicationContext(), "INVALID IBAN", Toast.LENGTH_SHORT).show();
//                    }
                    if (!(email1.matches(emailPattern))) {
                        Toast.makeText(getApplicationContext(), "INVALID MAIL", Toast.LENGTH_SHORT).show();
                    }
                    if (!(phonenumber.matches(phonePattern))) {
                        Toast.makeText(getApplicationContext(), "INVALID PHONE NUMBER", Toast.LENGTH_SHORT).show();
                    } else {

                        Intent auxIntent = getIntent();
                        String username = auxIntent.getStringExtra("registeredUsername");
                        String password = auxIntent.getStringExtra("registeredPassword");
//                        Student foundStudent = databaseHelper.getStudent(username);
                        System.out.println("before enter");
                        if (databaseHelper.getStudent(username) != -1)
                        {
                            //update
                            System.out.println("entered");
                            if(databaseHelper.updateStudent(databaseHelper.getStudent(username),firstname,lastname,email1,phonenumber,studyYear,domain))
                            {
                                System.out.println("updated");
                                Tutor newTutor = new Tutor();
                                newTutor.setUserName(username);
                                newTutor.setFirstName(firstname);
                                newTutor.setLastName(lastname);
                                newTutor.setEmail(email1);
                                newTutor.setPhoneNumber(phonenumber);
                                newTutor.setStudentDomain(domain);
                                newTutor.setPhoneNumber(phonenumber);
                                newTutor.setStudyYear(studyYear);
                                newTutor.setIbanNumber(iban1);
                                newTutor.setUserPassword(password);
                                databaseHelper.InsertTutor(newTutor);
                                Intent intent = new Intent(CreateTutorProfile.this, Login.class);
                                intent.putExtra("registeredUsername", username);
                                startActivity(intent);
                            }
                        }


                    }


                }

            }
        });


    }
}

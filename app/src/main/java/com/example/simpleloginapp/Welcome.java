package com.example.simpleloginapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Welcome extends AppCompatActivity {
     Button btn_ldelete;
     Button btn_lnewcourse;
     DatabaseHelper databaseHelper;

     @Override
     protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.welcome_page);
          databaseHelper = new DatabaseHelper(this);
          btn_ldelete = (Button)findViewById(R.id.deleteAccount);
          btn_lnewcourse = findViewById(R.id.new_course);
          btn_ldelete.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                    Intent auxIntent = getIntent();
                    String username = auxIntent.getStringExtra("registeredUsername");
                    System.out.println("what u want");
                    System.out.println(username);
                    if(databaseHelper.getTutor(username)!=-1)
                         {databaseHelper.deleteTutor(username);
                         System.out.println("DELETEEEEEEE TUTOREEEEEEEEEE");
                         System.out.println(username);
                         Intent intent = new Intent(Welcome.this, MainActivity.class);
                         startActivity(intent);
                         }
                    else{
                         databaseHelper.deleteStudent(username);
                         System.out.println("DELETEEEEEEE STUDENTULEEEEEEEEEEEE");
                         System.out.println(username);
                         Intent intent = new Intent(Welcome.this, MainActivity.class);
                         startActivity(intent);
                         }
               }

          });
          btn_lnewcourse.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                    Intent auxIntent = getIntent();
                    String username = auxIntent.getStringExtra("registeredUsername");
                    System.out.println("i want to create course");
                    if(databaseHelper.getTutor(username)!=-1)
                    {
                         Integer idTutore = databaseHelper.getTutor(username);
                         Course c =  new Course(1, "analiza", idTutore, "I");
                         databaseHelper.InsertCourse(c);
                         System.out.println("am inserat curs");
                    }
                    else{
                         System.out.println("Nu ai voie");
                    }
               }
          });
     }

}

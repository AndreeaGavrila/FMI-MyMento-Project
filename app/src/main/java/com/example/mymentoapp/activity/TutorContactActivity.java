package com.example.mymentoapp.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.mymentoapp.R;
import com.example.mymentoapp.model.RatingStudent;
import com.example.mymentoapp.model.RatingStudentViewModel;
import com.example.mymentoapp.model.Student;
import com.example.mymentoapp.model.StudentViewModel;
import com.example.mymentoapp.model.Tutor;
import com.example.mymentoapp.model.TutorViewModel;

import java.text.DecimalFormat;
import java.util.List;

public class TutorContactActivity extends AppCompatActivity {

    TutorViewModel tutorViewModel;
    StudentViewModel studentViewModel;
    RatingStudentViewModel ratingStudentViewModel;

    TextView title, tutorRating;
    Button mail, phone, giveStars, backHome;
    RatingBar ratingBar;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.tutor_contact);

        toolbar = findViewById(R.id.toolbar_home);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        Bundle bundle = getIntent().getExtras();
        String tutorLastName = bundle.getString("tutorLastName");
        String tutorFirstName = bundle.getString("tutorFirstName");
        String studentUsername = bundle.getString("studentUsername");

        title = findViewById(R.id.id_tutor_name);
        mail = findViewById(R.id.id_tutor_mail);
        phone = findViewById(R.id.id_tutor_phone);
        giveStars = findViewById(R.id.give_rating);
        ratingBar = findViewById(R.id.rating_bar);
        backHome = findViewById(R.id.back_home);
        tutorRating = findViewById(R.id.id_rating_tutor);

        title.setText(tutorLastName.concat(" ".concat(tutorFirstName)));

        new Thread(() -> {
            tutorViewModel = new TutorViewModel(this.getApplication());
            studentViewModel = new StudentViewModel(this.getApplication());
            ratingStudentViewModel = new RatingStudentViewModel(this.getApplication());
            Student student = studentViewModel.getStudentByUsername(studentUsername);
            Tutor currentTutor = tutorViewModel.getTutorByName(tutorLastName, tutorFirstName);

            runOnUiThread(()->{
                phone.setText(currentTutor.getPhoneNumber());
                mail.setText(currentTutor.getEmail());
                String rating = "Rating: " + currentTutor.getRating();
                tutorRating.setText(rating);
            });

            phone.setOnClickListener(v->{
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:"+currentTutor.getPhoneNumber()));
                startActivity(callIntent);
            });

            mail.setOnClickListener(v->{
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                String sendEmail = "mailto:"+currentTutor.getEmail();
                emailIntent.setType("message/rcf822");
                emailIntent.putExtra(Intent.EXTRA_EMAIL,sendEmail);
                startActivity(Intent.createChooser(emailIntent,"Send Email"));
            });

            giveStars.setOnClickListener(v -> new Thread(()->{
                List<String> tutorWithRating = ratingStudentViewModel.getStudentsWithRatingForTutor(currentTutor.getUsername());
                if(!tutorWithRating.contains(student.getUsername())){
                    if (ratingBar.getRating() > 0){
                        int nrReviews =  currentTutor.getNrOfReviews();
                        double nrStars = currentTutor.getNrOfStars();
                        currentTutor.setNrOfReviews(nrReviews+ 1);
                        currentTutor.setNrOfStars(nrStars + ratingBar.getRating());
                        currentTutor.setRating(Double.parseDouble(new DecimalFormat("#.##").format(currentTutor.getNrOfStars() / currentTutor.getNrOfReviews())));
                        ratingStudentViewModel.insertStudentForTutor(new RatingStudent(studentUsername, currentTutor.getUsername()));
                        tutorViewModel.updateTutor(currentTutor);
                        runOnUiThread(() -> Toast.makeText(getApplicationContext(), "Your review has been added.", Toast.LENGTH_SHORT).show());
                    }else{
                        runOnUiThread(() -> Toast.makeText(getApplicationContext(), "You should give at least one star.", Toast.LENGTH_SHORT).show());
                    }
                }else{
                    runOnUiThread(() -> Toast.makeText(getApplicationContext(), "You've already given stars to this tutor.", Toast.LENGTH_SHORT).show());
                }
            }).start());


            backHome.setOnClickListener(v -> {
                Intent intent = new Intent(TutorContactActivity.this, WelcomeActivity.class);
                intent.putExtra("studentName", studentUsername);
                startActivity(intent);
            });
        }).start();
    }
}


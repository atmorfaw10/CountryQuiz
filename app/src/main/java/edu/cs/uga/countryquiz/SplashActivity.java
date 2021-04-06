package edu.cs.uga.countryquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class SplashActivity extends AppCompatActivity {

    private static final String DEBUG_TAG = "SplashActivity"; //DEBUG_TAG
    private Button newQuiz; // button for new quiz
    private Button reviewQuiz; // button for reviewing past quizzes

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Log.d(DEBUG_TAG, "onCreate: started");

        newQuiz = (Button) findViewById(R.id.button); // initializes new quiz button to take you to quiz fragment
        reviewQuiz = (Button) findViewById(R.id.button2); // initializes button that takes you to review quiz fragment


        /*
         * When new Quiz button pressed, takes the user to the country quiz fragment
         */
        newQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent countryQ = new Intent(SplashActivity.this, CountryQuizActivity.class);
                startActivity(countryQ);
            }
        });

        /*
         * When review quiz button pressed, takes the user to the review quiz fragment
         */
        reviewQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction reviewTransaction = getSupportFragmentManager().beginTransaction();
                reviewTransaction.replace(R.id.mainContainer, new ReviewQuiz()).commit();
            }
        });
    }
}
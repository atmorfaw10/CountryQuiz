package edu.cs.uga.countryquiz;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SplashActivity extends AppCompatActivity {

    private static final String DEBUG_TAG = "SplashActivity"; //DEBUG_TAG
    private Button newQuiz; // button for new quiz
    private Button reviewQuiz; // button for reviewing past quizzes

    private CountryQuizData countryQuizData = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Log.d(DEBUG_TAG, "onCreate: started");

        countryQuizData = new CountryQuizData(this);

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
                new RetrievaAllPastQuizzesTask().execute();
                FragmentTransaction reviewTransaction = getSupportFragmentManager().beginTransaction();
                reviewTransaction.replace(R.id.mainContainer, new ReviewQuizActivity()).commit()
            }
        });
    }

    // This is an AsyncTask class (it extends AsyncTask) to perform DB reading of the countries for the quiz, asynchronously.
    private class RetrieveCountriesForQuizTask extends AsyncTask<Void, Void, List<Country>> {

        // This method will run as a background process to read from db.
        @Override
        protected List<Country> doInBackground(Void... params ) {
            List<Country> countriesForQuiz = countryQuizData.retrieveCountriesForQuiz();
            return countriesForQuiz;
        }

        // This method will be automatically called by Android once the writing to the database
        // in a background process has finished.
        @Override
        protected void onPostExecute( List<Country> countriesForQuiz ) {
            super.onPostExecute( countriesForQuiz);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            String date = simpleDateFormat.format(new Date());
            List<String> questions = new ArrayList<>();
            List<String> questionAnswers = new ArrayList<>();
            for(Country country: countriesForQuiz){
                questions.add(country.getCountry());
                questionAnswers.add(country.getContinent());
            }
            int result = 0;
            Quiz newQuiz = new Quiz(date, questions, questionAnswers, result);

            CountryQuizFragment newQuizFragment = new CountryQuizFragment();
            //save the new quiz data in the new quiz fragment's Bundle data
            Bundle args = new Bundle();
            args.putSerializable("newQuiz", newQuiz);
            newQuizFragment.setArguments(args);

            //Code below will determine when newQuiz will be stored in database
            Log.d( DEBUG_TAG, "New quiz has been created");

            Intent countryQ = new Intent(SplashActivity.this, CountryQuizActivity.class);
            countryQ.putExtras(args);
            startActivity(countryQ);
        }
    }

    // This is an AsyncTask class (it extends AsyncTask) to perform DB reading of the countries for the quiz, asynchronously.
    private class RetrieveAllPastQuizzesTask extends AsyncTask<Void, Void, ArrayList<Quiz>> {

        // This method will run as a background process to read from db.
        @Override
        protected ArrayList<Quiz> doInBackground( Void... params ) {
            ArrayList<Quiz> pastQuizzes = countryQuizData.retrieveAllQuizzes();
            return pastQuizzes;
        }

        // This method will be automatically called by Android once the writing to the database
        // in a background process has finished.
        @Override
        protected void onPostExecute( ArrayList<Quiz> pastQuizzes ) {
            super.onPostExecute( pastQuizzes);

            if(pastQuizzes.size() > 0) {
                //Code below will determine how pastQuizzes will get sent to fragment
                CountryQuizFragment newQuizFragment = new CountryQuizFragment();
                //save all the past quiz data in the new quiz fragment's Bundle data
                Bundle args = new Bundle();
                args.putSerializable("pastQuizzes", pastQuizzes);
                newQuizFragment.setArguments(args);
            }

            Log.d( DEBUG_TAG, "Retrieved all past quizzes");
        }
    }

    @Override
    protected void onResume() {
        Log.d( DEBUG_TAG, "SplashActivity.onResume()" );
        // open the database in onResume
        if( countryQuizData != null )
            countryQuizData.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d( DEBUG_TAG, "SplashActivity.onPause()" );
        // close the database in onPause
        if( countryQuizData != null )
            countryQuizData.close();
        super.onPause();
    }
}
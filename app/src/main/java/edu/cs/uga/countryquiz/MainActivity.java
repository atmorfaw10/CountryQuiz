package edu.cs.uga.countryquiz;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    
    public static final String DEBUG_TAG = "MainActivity";
    
    private static int SPLASH_TIME = 3500;
    
    private CountryQuizData countryQuizData = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.black)));

        //Create a CountryQuizData instance, since we will need to save the countries to the database
        countryQuizData = new CountryQuizData(this);
        countryQuizData.open();
        Resources res = getResources();

        //Execute the initializing of the database with the countries csv
        new InitializeCountryQuizDatabaseTask().execute(res);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent main = new Intent(MainActivity.this, SplashActivity.class);
                startActivity(main);
                finish();
            }
        }, SPLASH_TIME);
    }

    // This is an AsyncTask class (it extends AsyncTask) to perform DB writing of all the countries, asynchronously.
    private class InitializeCountryQuizDatabaseTask extends AsyncTask<Resources, Void, Resources> {

        // This method will run as a background process to write into db.
        @Override
        protected Resources doInBackground( Resources... res ) {
            countryQuizData.storeCountries( res[0] );
            return res[0];
        }

        // This method will be automatically called by Android once the writing to the database
        // in a background process has finished.
        @Override
        protected void onPostExecute( Resources res ) {
            super.onPostExecute( res );

            Log.d( DEBUG_TAG, "Database has been created");
        }
    }

    @Override
    protected void onResume() {
        Log.d( DEBUG_TAG, "MainActivity.onResume()" );
        // open the database in onResume
        if( countryQuizData != null )
            countryQuizData.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d( DEBUG_TAG, "MainActivity.onPause()" );
        // close the database in onPause
        if( countryQuizData != null )
            countryQuizData.close();
        super.onPause();
    }
}

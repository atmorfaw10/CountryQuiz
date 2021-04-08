package edu.cs.uga.countryquiz;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.loader.content.AsyncTaskLoader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;

import java.util.List;

public class ReviewQuizActivity extends AppCompatActivity {
    public static final String DEBUG_TAG = "ReviewQuizActivity";

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter recyclerAdapter;

    private CountryQuizData countryQuizData = null;
    private List<Quiz> countryQuizList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(DEBUG_TAG, "ReviewQuizActivity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_quiz);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        //use a linear layout manager for the recycler view
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //Create a QuizData instance, since we will need to save a new Quiz to the dn.
        countryQuizData = new CountryQuizData(this);

        //Execute the retrieval of the job leads in an asynchronous way,
        //without blocking the UI thread.
        new CountryQuizDBReaderTask().execute();
    }

   // This is an AsyncTask class (it extends AsyncTask) to perform DB reading of job leads, asynchronously.
   private class CountryQuizDBReaderTask extends AsyncTask<Void, Void, List<Quiz>> {

        // This method will run as background process to read from db
        // It returns a list of retrieved quiz objects
        // It will be automatically invoked by Android, when we call the excute method
        // in the onCreate callback (the job leads review activity is started)

       @Override
       protected List<Quiz> doInBackground(Void... params)
       {
           countryQuizData.open();
           countryQuizList = countryQuizData.retrieveAllQuizzes();

           Log.d(DEBUG_TAG, "CountryQuizDBReaderTask: Quizzes retrieved: " + countryQuizList.size());

           return countryQuizList;
       }

       // This method will be automatically called by Android once the db reading
       // background process is finished. It will then create and set an adapter to provide
       // values for the RecyclerView.
       // onPostExecute is like the notify method in an asynchronous method call discussed in class
       @Override
       protected void onPostExecute(List<Quiz> countryQuizList)
       {
           super.onPostExecute(countryQuizList);
           recyclerAdapter = new QuizLeadRecyclerAdapter(countryQuizList);
           recyclerView.setAdapter(recyclerAdapter);
       }

   }
    @Override
    protected void onResume() {
        Log.d( DEBUG_TAG, "ReviewQuizDataActivity.onResume()" );
        // open the database in onResume
        if( countryQuizData != null )
            countryQuizData.open();
        super.onResume();
        //recyclerView.setAdapter(recyclerAdapter);
    }

    @Override
    protected void onPause() {
        Log.d( DEBUG_TAG, "ReviewQuizDataActivity.onPause()" );
        // close the database in onPause
        if( countryQuizData != null )
            countryQuizData.close();
        super.onPause();
    }

    // These activity callback methods are not needed and are for edational purposes only
    @Override
    protected void onStart() {
        Log.d( DEBUG_TAG, "ReviewQuizDataActivity.onStart()" );
        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.d( DEBUG_TAG, "ReviewQuizDataActivity.onStop()" );
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d( DEBUG_TAG, "ReviewQuizDataActivity.onDestroy()" );
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        Log.d( DEBUG_TAG, "ReviewQuizDataActivity.onRestart()" );
        super.onRestart();
    }
}
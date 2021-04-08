package edu.cs.uga.countryquiz;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ResultsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ResultsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private static final String DEBUG_TAG = "ResultsFragment"; //DEBUG_TAG
    public static int results = 0;
    private Button newerQuizButton;
    private Button reviewPageButton;
    private TextView dateTextView;
    protected TextView quizScoreTextView;

    private CountryQuizData countryQuizData = null;

    public ResultsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ResultsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ResultsFragment newInstance(String param1, String param2) {
        ResultsFragment fragment = new ResultsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View resultsView = inflater.inflate(R.layout.fragment_results, container, false);

        countryQuizData = new CountryQuizData(getActivity());
        countryQuizData.open();

        dateTextView = (TextView) resultsView.findViewById(R.id.date);
        quizScoreTextView = (TextView) resultsView.findViewById(R.id.quiz_score);
        newerQuizButton = (Button) resultsView.findViewById(R.id.new_quiz_button);
        reviewPageButton = (Button) resultsView.findViewById(R.id.review_quizzes);

        Bundle bundle = getArguments();
        int result = bundle.getInt("result");

        //get newQuiz date retrieved from database
        Quiz newQuiz = (Quiz) bundle.getSerializable("newQuiz");
        newQuiz.setResult(result);
        dateTextView.setText("Date: " + newQuiz.getDate());
//        if(ResultsFragment.results == 1){
//            result++;
//            ResultsFragment.results = 0;
//        }else if(ResultsFragment.results == 2){
//            result--;
//            ResultsFragment.results = 0;
//        }
        newQuiz.setResult(ResultsFragment.results);
        quizScoreTextView.setText("Quiz Score: " + ResultsFragment.results + "/6");
        new StoreQuizTask().execute(newQuiz);

        /*
         * When new Quiz button pressed, takes the user to the country quiz fragment
         */
        newerQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                new RetrieveCountriesForQuizTask().execute();
            }
        });


        /*
         * When review quiz button pressed, takes the user to the review quiz fragment
         */
        reviewPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new RetrieveAllPastQuizzesTask().execute();
                Intent review = new Intent(getActivity(), ReviewQuizActivity.class);
                startActivity(review);
            }
        });

        return resultsView;
    }

    // This is an AsyncTask class (it extends AsyncTask) to writing new quiz to db, asynchronously.
    private class StoreQuizTask extends AsyncTask<Quiz, Void, Quiz> {

        // This method will run as a background process to read from db.
        @Override
        protected Quiz doInBackground(Quiz... newQuiz) {
            return countryQuizData.storeQuiz(newQuiz[0]);
        }

        // This method will be automatically called by Android once the writing to the database
        // in a background process has finished.
        @Override
        protected void onPostExecute(Quiz newQuiz) {
            super.onPostExecute(newQuiz);
        }
    }

    // This is an AsyncTask class (it extends AsyncTask) to perform DB reading of the countries for the quiz, asynchronously.
    private class RetrieveCountriesForQuizTask extends AsyncTask<Void, Void, List<Country>> {

        // This method will run as a background process to read from db.
        @Override
        protected List<Country> doInBackground(Void... params) {
            List<Country> countriesForQuiz = countryQuizData.retrieveCountriesForQuiz();
            return countriesForQuiz;
        }

        // This method will be automatically called by Android once the writing to the database
        // in a background process has finished.
        @Override
        protected void onPostExecute(List<Country> countriesForQuiz) {
            super.onPostExecute(countriesForQuiz);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            String date = simpleDateFormat.format(new Date());
            List<String> questions = new ArrayList<>();
            List<String> questionAnswers = new ArrayList<>();
            for (Country country : countriesForQuiz) {
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
            Log.d(DEBUG_TAG, "New quiz has been created");

            Intent countryQ = new Intent(getActivity(), CountryQuizActivity.class);
            countryQ.putExtras(args);
            startActivity(countryQ);
        }
    }

    // This is an AsyncTask class (it extends AsyncTask) to perform DB reading of the countries for the quiz, asynchronously.
    private class RetrieveAllPastQuizzesTask extends AsyncTask<Void, Void, ArrayList<Quiz>> {

        // This method will run as a background process to read from db.
        @Override
        protected ArrayList<Quiz> doInBackground(Void... params) {
            ArrayList<Quiz> pastQuizzes = countryQuizData.retrieveAllQuizzes();
            return pastQuizzes;
        }

        // This method will be automatically called by Android once the writing to the database
        // in a background process has finished.
        @Override
        protected void onPostExecute(ArrayList<Quiz> pastQuizzes) {
            super.onPostExecute(pastQuizzes);

            if (pastQuizzes.size() > 0) {
                //Code below will determine how pastQuizzes will get sent to fragment
                CountryQuizFragment newQuizFragment = new CountryQuizFragment();
                //save all the past quiz data in the new quiz fragment's Bundle data
                Bundle args = new Bundle();
                args.putSerializable("pastQuizzes", pastQuizzes);
                newQuizFragment.setArguments(args);
            }

            Log.d(DEBUG_TAG, "Retrieved all past quizzes");
        }

    }

}
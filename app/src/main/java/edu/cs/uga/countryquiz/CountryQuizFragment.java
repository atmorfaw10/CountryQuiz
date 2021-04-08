package edu.cs.uga.countryquiz;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CountryQuizFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CountryQuizFragment extends Fragment {

    public static final String DEBUG_TAG = "CountryQuizFragment";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private String country_Name;
    private String continent1;
    private String continent2;
    private String continent3;
    private TextView question;
    private TextView questionNumber;
    private RadioGroup radioGroup;
    private RadioButton choice1;
    private RadioButton choice2;
    private RadioButton choice3;
    protected String selectedAnswer;

    private ArrayList<String> continents = new ArrayList<>();
    boolean isTheSame = true;
    int index;
    private int questionIndex;


    public CountryQuizFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CountryQuiz.
     */
    // TODO: Rename and change types and number of parameters
    public static CountryQuizFragment newInstance(String param1, String param2) {
        CountryQuizFragment fragment = new CountryQuizFragment();
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
        View quizView = inflater.inflate(R.layout.fragment_country_quiz, container, false);


        Bundle bundle = getArguments();
        questionIndex = bundle.getInt("questionNumber");

        //questionNumber = (TextView) quizView.findViewById(R.id.questionNumber);
        //questionNumber.setText(Integer.toString(questionIndex));


        //get newQuiz questions retrieved from database
        Quiz newQuiz = (Quiz) bundle.getSerializable("newQuiz");
        List<String> newQuizQuestions = newQuiz.getQuestions();
        List<String> newQuizQuestionsAnswers = newQuiz.getQuestionAnswers();

        Random random = new Random();


        //country_Name = "Angola"; //Using Angola for testing purpose, will use random generator later
        country_Name = newQuizQuestions.get(questionIndex-1);
        question = (TextView) quizView.findViewById(R.id.questionText); // initialize question text view
        radioGroup = (RadioGroup) quizView.findViewById(R.id.radio_group);
        radioGroup.check(R.id.radioButton);
        choice1 = (RadioButton) quizView.findViewById(R.id.radioButton); // radio button 1
        choice2 = (RadioButton) quizView.findViewById(R.id.radioButton2); // radio button 2
        choice3 = (RadioButton) quizView.findViewById(R.id.radioButton3); // radio button 3

        RadioButton[] buttons = {choice1, choice2, choice3};

        continents.add("North America");
        continents.add("South America");
        continents.add("Europe");
        continents.add("Africa");
        continents.add("Asia");
        continents.add("Oceania");

        int correctAnswerIndex = random.nextInt(3) + 1;
        String correctAnswer = newQuizQuestionsAnswers.get(questionIndex-1);
        String continent1 = "";
        String continent2 = "";
        String continent3 = "";
        int nextAvailableChoiceIndex = 1;
        if(correctAnswerIndex == 1){
            choice1.setText(correctAnswer);
            continent1 = correctAnswer;
            nextAvailableChoiceIndex = 2;
        } else if(correctAnswerIndex == 2){
            choice2.setText(correctAnswer);
            continent2 = correctAnswer;
        }
        else if(correctAnswerIndex == 3){
            choice3.setText(correctAnswer);
            continent3 = correctAnswer;
        }

        int numChoicesDetermined = 1;
        while (numChoicesDetermined != 3){
            index = random.nextInt(continents.size());
            String randomContinent = continents.get(index);

            if(!randomContinent.equalsIgnoreCase(continent1) && !randomContinent.equalsIgnoreCase(continent2) && !randomContinent.equalsIgnoreCase(continent3)){
                numChoicesDetermined++;

                if(nextAvailableChoiceIndex == 1) {
                    choice1.setText(randomContinent);
                    continent1 = randomContinent;
                }
                else if(nextAvailableChoiceIndex == 2){
                    choice2.setText(randomContinent);
                    continent2 = randomContinent;
                }
                else if(nextAvailableChoiceIndex == 3){
                    choice3.setText(randomContinent);
                    continent3 = randomContinent;
                }

                nextAvailableChoiceIndex++;
                if(nextAvailableChoiceIndex == correctAnswerIndex)
                    nextAvailableChoiceIndex++;
            }
        }

        question.setText(questionIndex + ". What continent is " + country_Name + " a part of?");
        selectedAnswer = continent1;

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch(checkedId){
                    case R.id.radioButton:
                        selectedAnswer = choice1.getText().toString();
                        break;
                    case R.id.radioButton2:
                        selectedAnswer = choice2.getText().toString();
                        break;
                    case R.id.radioButton3:
                        selectedAnswer = choice3.getText().toString();
                        break;
                }
                if(questionIndex == 6 && selectedAnswer.equalsIgnoreCase(correctAnswer)){
                    ResultsFragment.results = 1;
                } else if(questionIndex == 6 && selectedAnswer.equalsIgnoreCase(correctAnswer)){
                    ResultsFragment.results = 2;
                }

                Log.d(DEBUG_TAG, "Choice Selected: " + selectedAnswer);
            }
        });

        return quizView;
    }
}
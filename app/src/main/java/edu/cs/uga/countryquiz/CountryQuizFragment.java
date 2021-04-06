package edu.cs.uga.countryquiz;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CountryQuizFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CountryQuizFragment extends Fragment {

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
    private RadioButton choice1;
    private RadioButton choice2;
    private RadioButton choice3;
    private ArrayList<String> continents = new ArrayList<String>();
    boolean isTheSame = true;
    int index;


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
        int questionIndex = bundle.getInt("questionNumber");

        questionNumber = (TextView) quizView.findViewById(R.id.questionNumber);
        questionNumber.setText(Integer.toString(questionIndex));


        Random random = new Random();


        country_Name = "Angola"; //Using Angola for testing purpose, will use random generator later
        question = (TextView) quizView.findViewById(R.id.questionText); // initialize question text view
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

        while (true) {
            index = random.nextInt(continents.size());
            continent1 = continents.get(index);
            index = random.nextInt(continents.size());
            continent2 = continents.get(index);

            if(continent1.equals(continent2))
            {
                continent1 =  continents.get(index);
            }
            index = random.nextInt(continents.size());
            continent3 = continents.get(index);

            if(continent2.equals(continent3))
            {
                index = random.nextInt(continents.size());
                continent2 = continents.get(index);
            }

            if(continent1.equals(continent3))
            {
                continent3 = continents.get(index);
            }

            if (!(continent1.equals(continent2)) && !(continent2.equals(continent3)) && !(continent1.equals(continent3)))
            {
                break;
            } else {
                continue;
            }
        }

        question.setText("What continent is " + country_Name + " a part of?");
        choice1.setText(continent1);
        choice2.setText(continent2);
        choice3.setText(continent3);


        return quizView;
    }
}
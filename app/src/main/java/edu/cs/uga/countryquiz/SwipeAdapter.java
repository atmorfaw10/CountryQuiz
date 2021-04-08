package edu.cs.uga.countryquiz;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class SwipeAdapter extends FragmentStatePagerAdapter {

    public static final String DEBUG_TAG = "SwipeAdapter";

    private Quiz newQuiz;
    private List<CountryQuizFragment> pageFragments;
    private int quizResults;

    public SwipeAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    public SwipeAdapter(FragmentManager fragmentManager, Quiz newQuiz){
        super(fragmentManager);
        this.newQuiz = newQuiz;
        this.quizResults = 0;
        this.pageFragments = new ArrayList<>();
        for(int i = 0; i < 6; i++){
            pageFragments.add(new CountryQuizFragment());
        }
    }

    /**
     * Return the Fragment associated with a specified position.
     *
     * @param position
     */
    @NonNull
    @Override
    public Fragment getItem(int position) {
        Log.d(DEBUG_TAG, "Page: " + position);
        /*
            Here, check the position, and if position is past the last question [6]
            create a quiz result fragment
         */
       if(position >= 6) {
           Fragment pageFragment = new ResultsFragment();
           checkUserAnswerChoices();
           Bundle bundle = new Bundle();
           bundle.putInt("result", this.quizResults);
           bundle.putSerializable("newQuiz", newQuiz);
           pageFragment.setArguments(bundle);
           return pageFragment;
       }
        Bundle bundle = new Bundle();
        bundle.putInt("questionNumber", position+1);
        bundle.putSerializable("newQuiz", newQuiz);
        pageFragments.get(position).setArguments(bundle);

        return pageFragments.get(position);
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return 7;
    }

    private void checkUserAnswerChoices(){
        List<String> quizAnswers = newQuiz.getQuestionAnswers();
        for(int i = 0; i < 6; i++){
            if(pageFragments.get(i).selectedAnswer.equalsIgnoreCase(quizAnswers.get(i)))
                quizResults++;
        }
        Log.d(DEBUG_TAG, "Quiz Results: " + this.quizResults);
    }

}

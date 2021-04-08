package edu.cs.uga.countryquiz;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class SwipeAdapter extends FragmentStatePagerAdapter {

    private Quiz newQuiz;

    public SwipeAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    public SwipeAdapter(FragmentManager fragmentManager, Quiz newQuiz){
        super(fragmentManager);
        this.newQuiz = newQuiz;
    }

    /**
     * Return the Fragment associated with a specified position.
     *
     * @param position
     */
    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment pageFragment;
        /*
            Here, check the position, and if position is past the last question [6]
            create a quiz result fragment
         */
       if(position <= 5)
       {
           pageFragment = new CountryQuizFragment();
       } else
       {
           pageFragment = new ResultsFragment();
       }

        Bundle bundle = new Bundle();
        bundle.putInt("questionNumber", position+1);
        bundle.putSerializable("newQuiz", newQuiz);
        pageFragment.setArguments(bundle);

        return pageFragment;
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return 7;
    }

}

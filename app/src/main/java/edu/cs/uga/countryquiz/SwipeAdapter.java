package edu.cs.uga.countryquiz;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class SwipeAdapter extends FragmentStatePagerAdapter {

    public SwipeAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    /**
     * Return the Fragment associated with a specified position.
     *
     * @param position
     */
    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment pageFragment = new CountryQuizFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("questionNumber", position+1);
        pageFragment.setArguments(bundle);

        return pageFragment;
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return 6;
    }
}

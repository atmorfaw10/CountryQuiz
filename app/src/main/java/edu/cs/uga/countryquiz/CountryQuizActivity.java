package edu.cs.uga.countryquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.RadioGroup;
import android.widget.RadioButton;


public class CountryQuizActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_quiz);

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radio_group);

        ViewPager viewPager = (ViewPager) findViewById(R.id.view_Pager);
        viewPager.setOffscreenPageLimit(1);
        SwipeAdapter swipeAdapter = new SwipeAdapter(getSupportFragmentManager());
        viewPager.setAdapter(swipeAdapter);
        viewPager.setCurrentItem(0);






    }
}
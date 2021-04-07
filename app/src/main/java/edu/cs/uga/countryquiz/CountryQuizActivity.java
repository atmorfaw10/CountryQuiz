package edu.cs.uga.countryquiz;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;


public class CountryQuizActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_quiz);

        Intent intent = this.getIntent();
        Bundle args = intent.getExtras();
        Quiz newQuiz = (Quiz) args.getSerializable("newQuiz");

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radio_group);

        ViewPager viewPager = (ViewPager) findViewById(R.id.view_Pager);
        viewPager.setOffscreenPageLimit(1);
        SwipeAdapter swipeAdapter = new SwipeAdapter(getSupportFragmentManager(), newQuiz);
        viewPager.setAdapter(swipeAdapter);
        viewPager.setCurrentItem(0);






    }
}
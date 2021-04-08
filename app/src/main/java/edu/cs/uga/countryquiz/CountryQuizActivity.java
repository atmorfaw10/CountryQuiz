package edu.cs.uga.countryquiz;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import java.text.DateFormat;
import java.util.Calendar;

public class CountryQuizActivity extends AppCompatActivity {
    private SwipeAdapter swipeAdapter;
    private ViewPager viewPager;
    private int thePos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_quiz);

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radio_group);

        Intent intent = this.getIntent();
        Bundle args = intent.getExtras();
        Quiz newQuiz = (Quiz) args.getSerializable("newQuiz");

        Calendar calendar = Calendar.getInstance();
        String quizDate = DateFormat.getDateInstance().format(calendar.getTime());
        newQuiz.setDate(quizDate);

        viewPager = (ViewPager) findViewById(R.id.view_Pager);
        viewPager.setOffscreenPageLimit(0);
        swipeAdapter = new SwipeAdapter(getSupportFragmentManager(), newQuiz);
        viewPager.setAdapter(swipeAdapter);
        viewPager.setCurrentItem(0);
    }

    @Override
    protected void onPause()
    {
        thePos = swipeAdapter.getFragPosition();
        super.onPause();
    }

    @Override
    protected void onResume()
    {
        viewPager.setCurrentItem(thePos - 1);
        super.onResume();
    }


}
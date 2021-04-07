package edu.cs.uga.countryquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;


public class CountryQuizActivity extends AppCompatActivity {
    RadioGroup radioGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_quiz);

        radioGroup = (RadioGroup) findViewById(R.id.radio_group);

        Calendar calendar = Calendar.getInstance();
        String quizDate = DateFormat.getDateInstance().format(calendar.getTime());
        Quiz newQuiz = new Quiz();

        newQuiz.setDate(quizDate);

        ViewPager viewPager = (ViewPager) findViewById(R.id.view_Pager);
        viewPager.setOffscreenPageLimit(1);
        SwipeAdapter swipeAdapter = new SwipeAdapter(getSupportFragmentManager());
        viewPager.setAdapter(swipeAdapter);
        viewPager.setCurrentItem(0);
        //validation();
        
    }
    public void validation()
    {
        int isSelected = radioGroup.getCheckedRadioButtonId();

        if(isSelected == -1)
        {
            Toast.makeText(CountryQuizActivity.this, "Please select a continent", Toast.LENGTH_LONG).show();
            return;
        }

    }
}
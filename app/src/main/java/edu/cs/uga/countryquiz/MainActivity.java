package edu.cs.uga.countryquiz;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
//    private static int SPLASH_TIME = 1000;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        getSupportActionBar().hide();
//        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.black)));
//
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent main = new Intent(MainActivity.this, SplashActivity.class);
//                startActivity(main);
//                finish();
//            }
//        }, SPLASH_TIME);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.black)));
    }
}
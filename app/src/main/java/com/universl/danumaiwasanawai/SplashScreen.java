package com.universl.danumaiwasanawai;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.universl.danumaiwasanawai.quiz.QuizstartActivity;

public class SplashScreen  extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //startActivity(new Intent(SplashScreen.this, MainActivity.class));
                 //startActivity(new Intent(SplashScreen.this, QuizstartActivity.class));
                startActivity(new Intent(SplashScreen.this, Menuactivity.class));
                finish();
            }
        }, 2000);


    }
}

package com.universl.danumaiwasanawai;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.universl.danumaiwasanawai.Quizresult.Quizresult;
import com.universl.danumaiwasanawai.quiz.CommonActivity;

public class Menuactivity extends AppCompatActivity implements View.OnClickListener {

    private TextView quiz;
    private String catagory;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    private CommonActivity commonActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuactivity);
        setTitle("දැනුමයි දිනුමයි");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_home_24);


        TextView button1 = findViewById(R.id.Awards);
        TextView button2 = findViewById(R.id.Geography);
        TextView button3 = findViewById(R.id.Great_Personalities);
        TextView button4 = findViewById(R.id.History);
        TextView button5 = findViewById(R.id.Literature);
        TextView button6 = findViewById(R.id.World_and_Politics);
        TextView button7 = findViewById(R.id.Others);




        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);


        quiz = findViewById(R.id.quiz);
        TextView button8 = findViewById(R.id.result);
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        gsc = GoogleSignIn.getClient(this, gso);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);


        if (account != null) {
            String Name = account.getDisplayName();
            String id = account.getId();

        }


        quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });


        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Quizresult.class);
                startActivity(intent);
            }
        });




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                Sinout(getApplicationContext());
//                finish();
//                System.exit(0);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void Sinout(Context applicationContext) {
        GoogleSignInOptions gso;
        GoogleSignInClient gsc;
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        gsc = GoogleSignIn.getClient(applicationContext, gso);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(applicationContext);
        gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        finish();
                        moveTaskToBack(true);
                        android.os.Process.killProcess(android.os.Process.myPid());
                        System.exit(1);
                    }
                }, 1000);   //5 seconds


            }
        });
    }

    private void gotoExit() {
        Intent i = new Intent(this, Menuactivity.class);
        startActivity(i);

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.Awards:
                catagory = "Awards";
                break;
            case R.id.Geography:
                catagory = "Geography";
                break;
            case R.id.Great_Personalities:
                catagory = "Great_Personalities";
                break;
            case R.id.History:
                catagory = "History";
                break;
            case R.id.Literature:
                catagory = "Literature";
                break;
            case R.id.World_and_Politics:
                catagory = "World_and_Politics";
                break;
            case R.id.Others:
                catagory = "Others";
                break;
        }


        Intent i = new Intent(this, HistrylistActivity.class);
        i.putExtra("catagory", catagory);
        startActivity(i);



    }


}
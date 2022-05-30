package com.universl.danumaiwasanawai.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.universl.danumaiwasanawai.QuizLordActivity;
import com.universl.danumaiwasanawai.R;

public class QuizstartActivity extends AppCompatActivity {
    Button btnStart;
    TextView quiztitle;
    String gogleid;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizstart);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        gsc = GoogleSignIn.getClient(this, gso);


        gsc = GoogleSignIn.getClient(this, gso);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);


        Intent intentget = getIntent();
        String Quiz_title = intentget.getStringExtra("Quiz_title");
        String Quiz_id = intentget.getStringExtra("Quiz_id");
        String Quiz_time_hours = intentget.getStringExtra("Quiz_time_hours");
        String Quiz_time_minutes = intentget.getStringExtra("Quiz_time_minutes");
        quiztitle = findViewById(R.id.quiztitle);
        quiztitle.setText(Quiz_title);

        setTitle("ප්\u200Dරශ්නාවලිය " + Quiz_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_home_24);

        if (account != null) {
            gogleid = account.getId();

        }


//      Toast.makeText(getApplicationContext(),Quiz_title+" "+Quiz_id+" "+Quiz_time_hours+" "+Quiz_time_minutes,Toast.LENGTH_LONG).show() ;

        btnStart = findViewById(R.id.startButton);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuizstartActivity.this, QuizActivity.class);
                intent.putExtra("Quiz_title", Quiz_title);
                intent.putExtra("Quiz_id", Quiz_id);
                intent.putExtra("Quiz_time_hours", Quiz_time_hours);
                intent.putExtra("Quiz_time_minutes", Quiz_time_minutes);
                intent.putExtra("gogleid", gogleid);
                startActivity(intent);
                finish();
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

}
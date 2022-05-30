package com.universl.danumaiwasanawai.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.universl.danumaiwasanawai.Menuactivity;
import com.universl.danumaiwasanawai.QuizLordActivity;
import com.universl.danumaiwasanawai.R;
import com.universl.danumaiwasanawai.RetrofitClient;
import com.universl.danumaiwasanawai.model.Quiz;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuizActivity extends AppCompatActivity {

    private TextView score, textqutionNo, texttimer, quizresut;
    private RadioGroup radioGroup;
    private RadioButton rb1, rb2, rb3, rb4;
    private Button buttoneNext;
    private WebView textqution;
    private RelativeLayout RelativeLayout1;
    int totalQuestions;
    int qCounter = 0;
    int curantScore = 0;
    String QuizId;
    ColorStateList dfRbColor;
    Drawable dfRbColor2;
    ProgressBar progressBar;
    Dialog dialog;


    String Quiz_title;
    String Quiz_id;
    String Quiz_time_hours;
    String Quiz_time_minutes;
    String gogleid;

    boolean answere;
    CountDownTimer countdownTimmer;
    long timescore;

    private QuestionModel currentQuestion;

    private List<QuestionModel> quetionlist;
    long quiztime;


    public long getQuiztime() {
        return quiztime;
    }

    public void setQuiztime(long quiztime) {
        this.quiztime = quiztime * 1000;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz2);

        Intent intentget = getIntent();
        Quiz_title = intentget.getStringExtra("Quiz_title");
        Quiz_id = intentget.getStringExtra("Quiz_id");
        Quiz_time_hours = intentget.getStringExtra("Quiz_time_hours");
        Quiz_time_minutes = intentget.getStringExtra("Quiz_time_minutes");
        gogleid = intentget.getStringExtra("gogleid");

        setTitle("ප්\u200Dරශ්නාවලිය "+Quiz_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_home_24);

        QuizId = Quiz_id;
        progressBar = findViewById(R.id.progressBar2);
        RelativeLayout1 = findViewById(R.id.RelativeLayout1);
        quetionlist = new ArrayList<>();

        textqution = findViewById(R.id.textqution);

        textqution.setBackgroundColor(0);
        score = findViewById(R.id.score);
        textqutionNo = findViewById(R.id.textqutionNo);
        texttimer = findViewById(R.id.texttimer);

        radioGroup = findViewById(R.id.radioGroup);
        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rb3 = findViewById(R.id.rb3);
        rb4 = findViewById(R.id.rb4);

        dfRbColor = rb1.getTextColors();
        dfRbColor2 = rb1.getBackground();
        buttoneNext = findViewById(R.id.buttoneNext);

        dialog = new Dialog(this);
        progressBar.setVisibility(View.VISIBLE);
        RelativeLayout1.setVisibility(View.GONE);

        //Toast.makeText(QuizActivity.this, String.valueOf(Long.parseLong(Quiz_time_minutes)), Toast.LENGTH_LONG).show();
        setQuiztime(Long.parseLong(Quiz_time_minutes));
        addQuestions();


        buttoneNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (answere == false) {

                    if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked() || rb4.isChecked()) {
                        checkAnswer();

                    } else {
                       Toast.makeText(QuizActivity.this, "Please Select One an Option", Toast.LENGTH_LONG).show();
                    }

                } else {
                    showNextQuestion();
                }


            }

            private void checkAnswer() {
                answere = true;
                RadioButton rbSelected = findViewById(radioGroup.getCheckedRadioButtonId());
                int anserNo = radioGroup.indexOfChild(rbSelected) + 1;

                if (anserNo == currentQuestion.getCorrectAnsNo()) {
                    curantScore++;
                    score.setText("Score" + curantScore);
                }
                rb1.setTextColor(Color.WHITE);
                rb2.setTextColor(Color.WHITE);
                rb3.setTextColor(Color.WHITE);
                rb4.setTextColor(Color.WHITE);

                rb1.setBackgroundColor(Color.RED);
                rb2.setBackgroundColor(Color.RED);
                rb3.setBackgroundColor(Color.RED);
                rb4.setBackgroundColor(Color.RED);

                switch (currentQuestion.getCorrectAnsNo()) {
                    case 1:
                        rb1.setTextColor(Color.WHITE);
                        rb1.setBackgroundColor(Color.GREEN);
                        break;
                    case 2:
                        rb2.setTextColor(Color.WHITE);
                        rb2.setBackgroundColor(Color.GREEN);
                        break;
                    case 3:
                        rb3.setTextColor(Color.WHITE);
                        rb3.setBackgroundColor(Color.GREEN);
                        break;
                    case 4:
                        rb4.setTextColor(Color.WHITE);
                        rb4.setBackgroundColor(Color.GREEN);
                        break;
                }

                if (qCounter < totalQuestions) {
                    buttoneNext.setText("Next");
                } else {
                    buttoneNext.setText("Finish");
                }

            }
        });
    }


    @Override
    public void onBackPressed() {
        insertUserResult();
        Intent intent = new Intent(QuizActivity.this, Menuactivity.class);
        startActivity(intent);
        finish();

    }


    private void showNextQuestion() {

        radioGroup.clearCheck();
        rb1.setTextColor(dfRbColor);
        rb2.setTextColor(dfRbColor);
        rb3.setTextColor(dfRbColor);
        rb4.setTextColor(dfRbColor);


        rb1.setBackground(dfRbColor2);
        rb2.setBackground(dfRbColor2);
        rb3.setBackground(dfRbColor2);
        rb4.setBackground(dfRbColor2);

        if (qCounter < totalQuestions) {

            currentQuestion = quetionlist.get(qCounter);
            textqution.loadDataWithBaseURL(null, String.valueOf(currentQuestion.getQuestion()), "text/html", "utf-8", null);

            rb1.setText(currentQuestion.getOption1());
            rb2.setText(currentQuestion.getOption2());
            rb3.setText(currentQuestion.getOption3());
            rb4.setText(currentQuestion.getOption4());
            qCounter++;
            buttoneNext.setText("Submit");
            textqutionNo.setText("Question:" + qCounter + "/" + totalQuestions);
            answere = false;

        } else {
// need to go another activitoy withe some data and need to store user
//            use complte funtione to this
            openResultDiakog(curantScore);


        }

    }

    private void openResultDiakog(int curantScore) {
        dialog.setContentView(R.layout.resuresult_layout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button btnok = dialog.findViewById(R.id.resulpk);
        quizresut = dialog.findViewById(R.id.quizresut);
        quizresut.setText("Score " + curantScore);
        dialog.show();


//        add data base to currant data
//        user id, user score ,time  submitQuizResults

        insertUserResult();


        btnok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(QuizActivity.this, Menuactivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void insertUserResult() {
        countdownTimmer.cancel();
        buttoneNext.setVisibility(View.INVISIBLE);
        RetrofitClient.getRetrofitClient().submitQuizResults(Quiz_id,gogleid,Integer.toString(curantScore) ,Long.toString(timescore)).enqueue(new Callback<List<QuestionModel>>() {
            @Override
            public void onResponse(Call<List<QuestionModel>> call, Response<List<QuestionModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.i("RESPONDQIUZ", "insertsuasdasd");
                   // Toast.makeText(QuizActivity.this, "insert succcess", Toast.LENGTH_LONG).show();
                    totalQuestions = quetionlist.size();
                    showNextQuestion();
                    progressBar.setVisibility(View.GONE);
                    RelativeLayout1.setVisibility(View.VISIBLE);
                }else
                {
                  //  Toast.makeText(QuizActivity.this, "not insert succcess", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<List<QuestionModel>> call, Throwable t) {
               // Toast.makeText(getApplicationContext(), "Error" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }


    private void time() {

        countdownTimmer = new CountDownTimer(quiztime, 1000) {
            @Override
            public void onTick(long l) {
                texttimer.setText("00:" + l / 1000);
                timescore = timescore + 1;
                // texttimer.setText("00:" + timescore);
            }


            @Override
            public void onFinish() {

                openResultDiakog(curantScore);


            }
        }.start();
    }

    private void addQuestions() {
        time();

        RetrofitClient.getRetrofitClient().getQuestion(QuizId).enqueue(new Callback<List<QuestionModel>>() {
            @Override
            public void onResponse(Call<List<QuestionModel>> call, Response<List<QuestionModel>> response) {

                if (response.isSuccessful() && response.body() != null) {

                    quetionlist = response.body();
//                    String responseTest = "";
//                    for (QuestionModel questionw : quetionlist2) {
//                        responseTest += questionw.getCorrectAnsNo();
//                        quetionlist.add(new QuestionModel(questionw.getQuestion(), questionw.getOption1(), questionw.getOption2(), questionw.getOption3(), questionw.getOption4(), 3));
//                    }
                    Log.i("RESPONDQIUZ", String.valueOf(quetionlist));


                    totalQuestions = quetionlist.size();


                    showNextQuestion();
                    progressBar.setVisibility(View.GONE);
                    RelativeLayout1.setVisibility(View.VISIBLE);

                }
            }

            @Override
            public void onFailure(Call<List<QuestionModel>> call, Throwable t) {
            //    Toast.makeText(getApplicationContext(), "Error" + t.getMessage(), Toast.LENGTH_LONG).show();
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
        switch (item.getItemId())
        {
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
package com.universl.danumaiwasanawai;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class BookoneActivity2 extends AppCompatActivity {

    TextView title;
    WebView description;
    ImageView imageView2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookone2);

        Intent intent =getIntent();
        String ArticleId = intent.getStringExtra("ArticleId");
        String ArticleImg = intent.getStringExtra("ArticleImg");
        String ArticleTitle = intent.getStringExtra("ArticleTitle");

        String ArticleDescription = intent.getStringExtra("ArticleDescription");

        setTitle("දැනුමයි දිනුමයි ");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_home_24);




        title = findViewById(R.id.title);
        description = findViewById(R.id.description);
        imageView2 = findViewById(R.id.imageView2);

        Glide.with(this).load(ArticleImg).into(imageView2);

        title.setText(ArticleTitle);
        description.loadDataWithBaseURL(null, String.valueOf(ArticleDescription), "text/html", "utf-8", null);

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
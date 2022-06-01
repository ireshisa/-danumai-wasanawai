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

    String catagory;
 String menuname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookone2);

        Intent intent =getIntent();
        String ArticleId = intent.getStringExtra("ArticleId");
        String ArticleImg = intent.getStringExtra("ArticleImg");
        String ArticleTitle = intent.getStringExtra("ArticleTitle");
        String Category2 = intent.getStringExtra("Category");


        switch (Category2) {
            case "Awards":
                catagory = "Awards";
                menuname="සම්මාන";
                break;
            case "Geography":
                catagory = "Geography";
                menuname="භූගෝලය";
                break;
            case "Great_Personalities":
                catagory = "Great_Personalities";
                menuname="ශ්\u200Dරේෂ්ඨ චරිත";
                break;
            case "Sri_Lanka":
                catagory = "Sri_Lanka";
                menuname="ශ්\u200Dරී ලංකාව ";
                break;
            case "Literature":
                catagory = "Literature";
                menuname="සාහිත්\u200Dය";
                break;
            case "World_and_Politics":
                catagory = "World_and_Politics";
                menuname="ඉතිහාසය හා දේශපාලනය";
                break;
            case "Others":
                catagory = "Others";
                menuname="වෙනත්";
                break;
            case "Inventions_and_Discoveries":
                catagory = "Inventions_and_Discoveries";
                menuname="නව නිපැයුම් සහ සොයාගැනීම්";
                break;

            case "Sports":
                catagory = "Sports";
                menuname="ක්\u200Dරීඩා";
                break;

        }






        String ArticleDescription = intent.getStringExtra("ArticleDescription");

        setTitle(menuname);

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
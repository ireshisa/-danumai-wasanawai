package com.universl.danumaiwasanawai;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.universl.danumaiwasanawai.model.User;
import com.universl.danumaiwasanawai.quiz.QuestionModel;
import com.universl.danumaiwasanawai.quiz.QuizActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ImageView google_img;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    String gogleid;
    String DisplayName;
    String Email;
    Uri gogleimage;
    private List<User> Userlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        google_img = findViewById(R.id.google);
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        gsc = GoogleSignIn.getClient(this, gso);
        gsc = GoogleSignIn.getClient(this, gso);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (account != null) {

            gogleid = account.getId();
            DisplayName = account.getDisplayName();
            Email = account.getEmail();
            gogleimage =account.getPhotoUrl();

            inseruser();
            HomeActivity();
        }
        google_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignIn();
            }
        });
    }

    private void inseruser() {
        RetrofitClient.getRetrofitClient().Insertuser(gogleid, DisplayName, Email,String.valueOf(gogleimage)).enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful() && response.body() != null) {

                    HomeActivity();
                } else {
                    HomeActivity();
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                 Toast.makeText(getApplicationContext(), "Error" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


    }

    private void SignIn() {

        Intent intent = gsc.getSignInIntent();
        startActivityForResult(intent, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == 100 || resultCode == -1) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                task.getResult(ApiException.class);
                HomeActivity();
            } catch (ApiException e) {
//                e.printStackTrace();
                // Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            }

        }
    }


    private void HomeActivity() {
        //   Toast.makeText(MainActivity.this, gogleid, Toast.LENGTH_LONG).show();
        finish();
        Intent intent = new Intent(getApplicationContext(), QuizLordActivity.class);
        intent.putExtra("gogleid", gogleid);
        intent.putExtra("DisplayName", DisplayName);
        intent.putExtra("Email", Email);
        startActivity(intent);


    }
}
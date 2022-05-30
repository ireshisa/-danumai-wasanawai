package com.universl.danumaiwasanawai.quiz;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.universl.danumaiwasanawai.MainActivity;

public class CommonActivity {





    public CommonActivity(Context activity) {
        super();


            GoogleSignInOptions gso;
            GoogleSignInClient gsc;
            gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestEmail()
                    .build();
            gsc = GoogleSignIn.getClient(activity, gso);
            GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(activity);
            gsc.signOut();













    }

    public void Sinout(Context applicationContext) {
    }
}

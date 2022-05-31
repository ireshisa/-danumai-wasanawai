package com.universl.danumaiwasanawai.Quizresult;
        import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;

        import android.content.Context;
        import android.content.Intent;
        import android.os.Bundle;
        import android.os.Handler;
        import android.util.Log;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.ProgressBar;
        import android.widget.Toast;

        import com.google.android.gms.auth.api.signin.GoogleSignIn;
        import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
        import com.google.android.gms.auth.api.signin.GoogleSignInClient;
        import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
        import com.google.android.gms.tasks.OnCompleteListener;
        import com.google.android.gms.tasks.Task;


        import com.universl.danumaiwasanawai.QuizLordActivity;
        import com.universl.danumaiwasanawai.R;
        import com.universl.danumaiwasanawai.RetrofitClient;

        import com.universl.danumaiwasanawai.model.Quiz;

        import com.universl.danumaiwasanawai.model.UserResult;
        import com.universl.danumaiwasanawai.quiz.QuizstartActivity;

        import java.util.ArrayList;
        import java.util.List;

        import retrofit2.Call;
        import retrofit2.Callback;
        import retrofit2.Response;
public class QuizresultItemView extends AppCompatActivity implements QuizResultItemviewAdapter.onQuizListemer {
    RecyclerView recyclerView;
    ProgressBar progressBar;
    LinearLayoutManager layoutManager;
    QuizResultItemviewAdapter adapter;
    List<UserResult> quizList = new ArrayList<>();
    String gogleid;
    String Quiz_id;
    String Quiz_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizresult_item_view);





        Intent intent = getIntent();
        gogleid = intent.getStringExtra("gogleid");

        Quiz_id = intent.getStringExtra("Quiz_id");
        Quiz_title= intent.getStringExtra("Quiz_title");

        setTitle("ලකුණු පුවරුව "+ Quiz_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_home_24);


        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar2);


        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new QuizResultItemviewAdapter(quizList, this);
        recyclerView.setAdapter(adapter);
        fetchPost();
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
                finish();
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


    private void fetchPost() {

        progressBar.setVisibility(View.VISIBLE);
        RetrofitClient.getRetrofitClient().getresult(Quiz_id).enqueue(new Callback<List<UserResult>>() {
            @Override
            public void onResponse(Call<List<UserResult>> call, Response<List<UserResult>> response) {

if(response.code()==404)
{

    Toast myToast = Toast.makeText(QuizresultItemView.this, "No results found!", Toast.LENGTH_LONG);
    myToast.show();
    progressBar.setVisibility(View.GONE);

}
                if (response.isSuccessful() && response.body() != null) {

                    List<UserResult> qises = response.body();
                    String responseTest = null;
                    for (UserResult quiz : qises) {
                        responseTest += quiz.getQuiz_result_ID();

                    }
                    Log.i("RESPONqweqweDQIUZ", responseTest);

                    quizList.addAll(response.body());

                    adapter.notifyDataSetChanged();
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<List<UserResult>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);


                Toast.makeText(getApplicationContext(),"Error"+t.getMessage(),Toast.LENGTH_LONG).show() ;
            }
        });
    }

    @Override
    public void onQuizClick(int position) {


        //Intent intent = new Intent(QuizresultItemView.this, QuizresultItemView.class);
//         intent.putExtra("Quiz_title", quizList.get(position).getQuiz_title());
//          intent.putExtra("Quiz_id", quizList.get(position).getQuiz_id());
//         intent.putExtra("Quiz_time_hours", quizList.get(position).getQuiz_time_hours());
//       intent.putExtra("Quiz_time_minutes", quizList.get(position).getQuiz_time_minutes());
      //  startActivity(intent);
//       finish();


    }
}
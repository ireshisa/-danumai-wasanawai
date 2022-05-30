package com.universl.danumaiwasanawai.Adapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.universl.danumaiwasanawai.R;
import com.universl.danumaiwasanawai.model.Quiz;

import java.text.BreakIterator;
import java.util.List;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.ViewHolder>  {
    private List<Quiz> QuizList;
    private onQuizListemer onQuizListemer;

    public QuizAdapter(List<Quiz> quizList,onQuizListemer onQuizListemer) {
        this.QuizList = quizList;
this.onQuizListemer=onQuizListemer;


    }




//activity_quiz_adapter

    @NonNull
    @Override
    public QuizAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_quiz_adapter,parent,false);
        return new QuizAdapter.ViewHolder(view,onQuizListemer);
    }


    @Override
    public void onBindViewHolder(@NonNull QuizAdapter.ViewHolder holder, int position) {

        holder.QuizTitle.setText(QuizList.get(position).getQuiz_title());


    }

    @Override
    public int getItemCount() {
        return QuizList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView QuizTitle;
        onQuizListemer onQuizListemer;
        public ViewHolder(View view , onQuizListemer onQuizListemer) {
            super(view);
            QuizTitle =itemView.findViewById(R.id.QuizTitle);
            this.onQuizListemer=onQuizListemer;

            view.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            onQuizListemer.onQuizClick(getAdapterPosition());
        }
    }

    public interface onQuizListemer{
        void onQuizClick(int position);
    }

}
package com.universl.danumaiwasanawai.Quizresult;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.universl.danumaiwasanawai.R;
import com.universl.danumaiwasanawai.model.Quiz;

import java.util.List;

public class QuizResultAdapter extends RecyclerView.Adapter<QuizResultAdapter.ViewHolder>  {
    private List<Quiz> QuizList;
    private onQuizListemer onQuizListemer;

    public QuizResultAdapter(List<Quiz> quizList, onQuizListemer onQuizListemer)
    {
        this.QuizList = quizList;
        this.onQuizListemer=onQuizListemer;
    }




//activity_quiz_adapter

    @NonNull
    @Override
    public QuizResultAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_quiz_result_adapter,parent,false);
        return new QuizResultAdapter.ViewHolder(view,onQuizListemer);
    }


    @Override
    public void onBindViewHolder(@NonNull QuizResultAdapter.ViewHolder holder, int position) {

        holder.QuizTitle.setText(QuizList.get(position).getQuiz_title());

        
       if(QuizList.get(position).getQuiz_status().equals("Active") )
       {
           holder.resultimg.setImageResource(R.drawable.activated);
       }


    }

    @Override
    public int getItemCount() {
        return QuizList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView QuizTitle;
        ImageView resultimg;
        onQuizListemer onQuizListemer;
        public ViewHolder(View view , onQuizListemer onQuizListemer) {
            super(view);
            QuizTitle =itemView.findViewById(R.id.QuizTitle);
            resultimg =itemView.findViewById(R.id.resultimg);
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
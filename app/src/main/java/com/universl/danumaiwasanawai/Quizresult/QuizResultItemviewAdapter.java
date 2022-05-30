package com.universl.danumaiwasanawai.Quizresult;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.universl.danumaiwasanawai.R;
import com.universl.danumaiwasanawai.model.Quiz;
import com.universl.danumaiwasanawai.model.UserResult;

import java.util.List;



public class QuizResultItemviewAdapter extends RecyclerView.Adapter<QuizResultItemviewAdapter.ViewHolder>  {
    private List<UserResult> QuizList;
    private onQuizListemer onQuizListemer;

 Context mycontext;
    public QuizResultItemviewAdapter(List<UserResult> quizList, onQuizListemer onQuizListemer) {
        this.QuizList = quizList;
this.onQuizListemer=onQuizListemer;


    }




//activity_quiz_adapter

    @NonNull
    @Override
    public QuizResultItemviewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mycontext= parent.getContext();
        View view = LayoutInflater.from(parent.getContext())

                .inflate(R.layout.activity_quiz_result_adapter2,parent,false);
        return new QuizResultItemviewAdapter.ViewHolder(view,onQuizListemer);
    }


    @Override
    public void onBindViewHolder(@NonNull QuizResultItemviewAdapter.ViewHolder holder, int position) {

        holder.QuizTitle.setText(QuizList.get(position).getUser_name());
        holder.textView44.setText(QuizList.get(position).getResult());
        holder.resulttime.setText(QuizList.get(position).getTime());
       holder.imageView755.setText(toString().valueOf(position+1));

        Glide.with(mycontext).load(QuizList.get(position).getImage()).into(holder.imageView3);


    }

    @Override
    public int getItemCount() {
        return QuizList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView QuizTitle;
        ImageView imageView3;
        TextView textView44;
        TextView resulttime;
        TextView  imageView755;
        onQuizListemer onQuizListemer;
        public ViewHolder(View view , onQuizListemer onQuizListemer) {
            super(view);
            QuizTitle =itemView.findViewById(R.id.QuizTitle);
            imageView3=itemView.findViewById(R.id.imageView4);
            textView44=itemView.findViewById(R.id.textView44);
            resulttime=itemView.findViewById(R.id.resulttime);
            imageView755=itemView.findViewById(R.id.imageView755);
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
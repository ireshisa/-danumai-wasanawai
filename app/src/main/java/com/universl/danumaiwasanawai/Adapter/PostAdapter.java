package com.universl.danumaiwasanawai.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.universl.danumaiwasanawai.BookoneActivity2;
import com.universl.danumaiwasanawai.R;
import com.universl.danumaiwasanawai.model.Posts;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder>  {


    private List<Posts> postsList;

    public PostAdapter(List<Posts> postsList) {
        this.postsList = postsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_story,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvTitle.setText(postsList.get(position).getArticle_title());
        holder.tvbody.setText(postsList.get(position).getArticle_id());

    }

    @Override
    public int getItemCount() {
        return postsList.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
     TextView tvTitle,tvbody;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle =itemView.findViewById(R.id.tvTitle);
            tvbody =itemView.findViewById(R.id.tvbody);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
//            Toast.makeText(itemView.getContext(), "position"+position, Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(itemView.getContext(), BookoneActivity2.class);

            intent.putExtra("ArticleId",postsList.get(position).getArticle_id());
            intent.putExtra("ArticleImg",postsList.get(position).getArticle_img());
            intent.putExtra("ArticleTitle",postsList.get(position).getArticle_title());
            intent.putExtra("ArticleDescription",postsList.get(position).getArticle_description());

            itemView.getContext().startActivity(intent);

        }
    }




}

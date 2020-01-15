package com.example.tumblr_copy.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tumblr_copy.R;
import com.example.tumblr_copy.models.tumblrPost;

import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder>{

    Context context;
    List<tumblrPost> postsList;

    public PhotoAdapter(Context context, List<tumblrPost> postsList) {
        this.context = context;
        this.postsList = postsList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPost;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPost = itemView.findViewById(R.id.ivPost);
        }

        public void bind(tumblrPost post) {
            Glide.with(context).load(post.getPhotoPath()).into(ivPost);
        }
    }


    @NonNull
    @Override
    public PhotoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View postView = LayoutInflater.from(context).inflate(R.layout.item_photo, parent, false);
        return new ViewHolder(postView);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoAdapter.ViewHolder holder, int position) {
        tumblrPost post = postsList.get(position);
        holder.bind(post);
    }

    @Override
    public int getItemCount() {
        return postsList.size();
    }
}

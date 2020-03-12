package com.example.kidapp.details;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kidapp.R;
import com.example.kidapp.model.Comment;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.CommentViewHolder> {

    private List<Comment> commentList = new ArrayList<>();
    public CommentsAdapter(List<Comment> commentList) {
        this.commentList = commentList;
    }


    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CommentViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        holder.bindComment(commentList.get(position));
    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

    public class CommentViewHolder extends RecyclerView.ViewHolder{
        private TextView titleTv;
        private TextView contentTv;
        private TextView dareTv;
        private TextView autherTv;

        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTv=itemView.findViewById(R.id.tv_comment_title);
            contentTv=itemView.findViewById(R.id.tv_comment_content);
            dareTv=itemView.findViewById(R.id.tv_comment_date);
            autherTv=itemView.findViewById(R.id.tv_comment_authorMame);
        }
        public void bindComment(Comment comment){
            titleTv.setText(comment.getTitle());
            contentTv.setText(comment.getContent());
            dareTv.setText(comment.getDate());
            autherTv.setText(comment.getAuthor().getUsername());
        }
    }
}

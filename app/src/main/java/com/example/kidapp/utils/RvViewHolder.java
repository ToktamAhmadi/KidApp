package com.example.kidapp.utils;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public abstract class RvViewHolder<T> extends RecyclerView.ViewHolder {
    public RvViewHolder(@NonNull View itemView) {
        super(itemView);
    }
    public abstract void bind(T item);
}

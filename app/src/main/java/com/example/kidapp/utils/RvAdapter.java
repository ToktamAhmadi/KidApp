package com.example.kidapp.utils;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public abstract class RvAdapter<T,E extends RvViewHolder<T>> extends RecyclerView.Adapter<E> {

    private List<T> item;
    public RvAdapter (List<T> item){
        this.item = item;
    }

    //if method for get list of data we have to constructor without parameter
    public RvAdapter(){}
    public void setItem(List<T> item){
        this.item=item;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    @Override
    public void onBindViewHolder(@NonNull E holder, int position) {
        holder.bind(item.get(position));
    }
}

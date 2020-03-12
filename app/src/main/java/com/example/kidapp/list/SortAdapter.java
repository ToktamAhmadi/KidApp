package com.example.kidapp.list;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kidapp.R;
import com.example.kidapp.main.adapters.ProductAdapter;
import com.example.kidapp.model.Product;
import com.example.kidapp.utils.PriceConvertor;
import com.example.kidapp.utils.RvAdapter;
import com.example.kidapp.utils.RvViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

public class SortAdapter extends RecyclerView.Adapter<SortAdapter.SortViewHolder>{
    private String[] sortTypeIds=new String[]{"جدیدترین","پربازدیدترین","قیمت از زیاد به کم","قیمت از کم به زیاد"};
    private Context context;
    private int selectedSortType;
    private OnSortClickListener onSortClickListener;

    public SortAdapter(Context context,int selectedSortType,OnSortClickListener onSortClickListener) {
        this.context = context;
        this.selectedSortType = selectedSortType;
        this.onSortClickListener = onSortClickListener;
    }

    @NonNull
    @Override
    public SortViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SortViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sort_chips,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull SortViewHolder holder, int position) {
        holder.tv_title_sort.setText(sortTypeIds[position]);
        if (position==selectedSortType){
            holder.tv_title_sort.setBackgroundResource(R.drawable.background_chips);
            holder.tv_title_sort.setTextColor(ContextCompat.getColor(context,R.color.white));
        }
        else {
            holder.tv_title_sort.setBackgroundResource(R.drawable.shape_chips_unselector);
            holder.tv_title_sort.setTextColor(ContextCompat.getColor(context,R.color.colorAccent));
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.getAdapterPosition()!= selectedSortType){
                    onSortClickListener.onClick(holder.getAdapterPosition());
                   notifyItemChanged(selectedSortType);
                    selectedSortType=holder.getAdapterPosition();
                    notifyItemChanged(selectedSortType);
                }
            }
        });
    }



    @Override
    public int getItemCount() {
        return sortTypeIds.length;
    }

    public class SortViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_title_sort;

        public SortViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title_sort= (TextView) itemView;
        }
    }
    public interface OnSortClickListener{
        void onClick(int sortType);
    }
}

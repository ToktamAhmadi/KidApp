package com.example.kidapp.main.adapters;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kidapp.R;
import com.example.kidapp.model.Banner;
import com.example.kidapp.model.Product;
import com.example.kidapp.utils.PriceConvertor;
import com.example.kidapp.utils.RvAdapter;
import com.example.kidapp.utils.RvViewHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.BannerViewHolder> {
    private List<Banner> banners=new ArrayList<>();
    public BannerAdapter() { }

    public void setBanners(List<Banner> banners){
        this.banners=banners;
    }

    @NonNull
    @Override
    public BannerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ImageView imageView= new ImageView(parent.getContext());
        imageView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
        ));
        imageView.setAdjustViewBounds(true);
        return new BannerViewHolder(imageView);
    }

    @Override
    public void onBindViewHolder(@NonNull BannerViewHolder holder, int position) {
        holder.bind(banners.get(position));
    }

    @Override
    public int getItemCount() {
        return banners.size();
    }


    public class BannerViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;


        public BannerViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = (ImageView) itemView;

        }


        public void bind(Banner banner) {
            Picasso.get().load(banner.getImage()).into(imageView);

        }
    }
}


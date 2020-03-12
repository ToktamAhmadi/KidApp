package com.example.kidapp.main.adapters;

import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kidapp.R;
import com.example.kidapp.details.ProductDetailsActivity;
import com.example.kidapp.utils.PriceConvertor;
import com.example.kidapp.utils.RvAdapter;
import com.example.kidapp.utils.RvViewHolder;
import com.example.kidapp.model.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProductAdapter extends RecyclerView.Adapter< ProductAdapter.ProductViewHolder> {

    private List<Product> products=new ArrayList<>();
    public ProductAdapter() {

    }
    public void setProducts(List<Product> products){
        this.products=products;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_product, parent, false
        ));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.bind(products.get(position));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }


    public class ProductViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView tv_title;
        private TextView tv_prevPrice;
        private TextView tv_price;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_product_image);
            tv_title = itemView.findViewById(R.id.tv_product_title);
            tv_prevPrice = itemView.findViewById(R.id.tv_product_prevPrice);
            tv_price = itemView.findViewById(R.id.tv_product_price);
        }


        public void bind(Product product) {
            Picasso.get().load(product.getImage()).into(imageView);
            tv_title.setText(product.getTitle());
            tv_prevPrice.setText(PriceConvertor.convert(product.getPreviousPrice()));
            tv_prevPrice.setPaintFlags(tv_prevPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            tv_price.setText(PriceConvertor.convert(product.getPrice()));
            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(v.getContext(), ProductDetailsActivity.class);
                intent.putExtra(ProductDetailsActivity.EXTRA_KEY_PRODUCT,product);
                v.getContext().startActivity(intent);
            });
        }
    }
}


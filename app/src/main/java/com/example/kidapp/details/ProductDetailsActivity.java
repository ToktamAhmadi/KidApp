package com.example.kidapp.details;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.kidapp.BaseActivity;
import com.example.kidapp.R;
import com.example.kidapp.model.Comment;
import com.example.kidapp.model.Product;
import com.example.kidapp.model.api.MySingleObserver;
import com.example.kidapp.utils.PriceConvertor;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductDetailsActivity extends BaseActivity {

    public static final String EXTRA_KEY_PRODUCT = "product";
    private ImageView productIv, backBtn;
    private TextView productTitlePage, productTitleTv, productPrePriceTv, productPriceTv, addCommentBtn;
    private Button addToCartTv;
    private RecyclerView commentRv;
    private Product product;
    private ProductDetailsViewModel viewModel=new ProductDetailsViewModel() ;
    private ProgressBar progressBar;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        product = getIntent().getParcelableExtra(EXTRA_KEY_PRODUCT);
        if (product == null) {
            finish();
        }
        setContentView(R.layout.activity_product_details);
        setupView();

        viewModel.comment(product.getId()).subscribeOn(Schedulers.newThread())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new MySingleObserver<List<Comment>>(this) {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(List<Comment> comments) {
                        commentRv.setAdapter(new CommentsAdapter(comments));
                    }
                });

        Disposable disposable = viewModel.PrograssBarVibility().subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aBoolean -> progressBar.setVisibility(aBoolean ? View.VISIBLE : View.GONE));

        compositeDisposable.add(disposable);
    }

    private void setupView() {
        productIv = findViewById(R.id.iv_details_image);
        Picasso.get().load(product.getImage()).into(productIv);

        productTitlePage = findViewById(R.id.tv_details_titlePage);
        productTitlePage.setText(product.getTitle());

        productPrePriceTv = findViewById(R.id.tv_details_prePrice);
        productPrePriceTv.setPaintFlags(productPrePriceTv.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        productPrePriceTv.setText(PriceConvertor.convert(product.getPreviousPrice()));

        productPriceTv = findViewById(R.id.tv_details_price);
        productPriceTv.setText(PriceConvertor.convert(product.getPrice()));

        productTitleTv = findViewById(R.id.tv_details_titleProduct);
        productTitleTv.setText(product.getTitle());

        addCommentBtn = findViewById(R.id.tv_details_addComment);
        addToCartTv = findViewById(R.id.btn_details_addToCart);

        backBtn = findViewById(R.id.iv_details_back);
        backBtn.setOnClickListener(v -> finish());

        commentRv = findViewById(R.id.rv_details_comments);
        commentRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        progressBar = findViewById(R.id.progressBar_details);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.dispose();
    }

    @Override
    public int getCordinatorLayoutId() {
        return R.id.rl_details_root;
    }
}

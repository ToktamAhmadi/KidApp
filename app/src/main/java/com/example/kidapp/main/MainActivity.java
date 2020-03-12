package com.example.kidapp.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.kidapp.BaseActivity;
import com.example.kidapp.R;
import com.example.kidapp.auth.AuthenticationActivity;
import com.example.kidapp.list.ProductListActivity;
import com.example.kidapp.main.adapters.BannerAdapter;
import com.example.kidapp.main.adapters.ProductAdapter;
import com.example.kidapp.model.Banner;
import com.example.kidapp.model.Product;
import com.example.kidapp.model.api.MySingleObserver;

import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private MainViewModel mainViewModel;
    private CompositeDisposable compositeDisposable=new CompositeDisposable();
    private ProductAdapter latestProductAdapter;
    private ProductAdapter popularProductAdapter;
    //private BannerAdapter bannerAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainViewModel =new MainViewModel();
        setUpView();
        observe();
    }

    private void observe() {

        mainViewModel.latestProducts().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MySingleObserver<List<Product>>(this) {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(List<Product> products) {
                        latestProductAdapter.setProducts(products);
                    }
                });
        mainViewModel.popularProducts().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MySingleObserver<List<Product>>(this) {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(List<Product> products) {
                        popularProductAdapter.setProducts(products);
                          }
                });
//        mainViewModel.banners().subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new MySingleObserver<List<Banner>>(this) {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        compositeDisposable.add(d);
//                    }
//
//                    @Override
//                    public void onSuccess(List<Banner> banners) {
//                        bannerAdapter.setBanners(banners);
//                    }
//                });
    }
//-----
    private void setUpView() {
        View cartItemIcon=findViewById(R.id.iv_main_cart);
        cartItemIcon.setOnClickListener(v -> startActivity(new Intent(this, AuthenticationActivity.class)));

        RecyclerView rv_latestProduct =findViewById(R.id.rv_main_latestProducts);
        rv_latestProduct.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,true));
        latestProductAdapter=new ProductAdapter();
        rv_latestProduct.setAdapter(latestProductAdapter);

        RecyclerView rv_popularProduct =findViewById(R.id.rv_main_popularProduct);
        rv_popularProduct.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,true));
        popularProductAdapter=new ProductAdapter();
        rv_popularProduct.setAdapter(popularProductAdapter);

//        RecyclerView rv_banner =findViewById(R.id.rv_main_banner);
//        rv_banner.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,true));
//        SnapHelper snapHelper=new PagerSnapHelper();
//        snapHelper.attachToRecyclerView(rv_banner);
//        bannerAdapter=new BannerAdapter();
//        rv_banner.setAdapter(bannerAdapter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }

    @Override
    public int getCordinatorLayoutId() {
        return R.id.coordinator_main;
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent(this,ProductListActivity.class);
        switch (v.getId()) {
            case R.id.tv_main_viewAllLatestProducts:
              intent.putExtra(ProductListActivity.EXTRA_KEY_SORT,Product.SORT_LATEST);
              break;
            case R.id.tv_main_viewAllPopularProducts:
                intent.putExtra(ProductListActivity.EXTRA_KEY_SORT,Product.SORT_POPULAR);
                break;
        }
        startActivity(intent);
    }
}

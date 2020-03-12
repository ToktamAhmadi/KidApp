package com.example.kidapp.list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.kidapp.BaseActivity;
import com.example.kidapp.R;
import com.example.kidapp.main.adapters.ProductAdapter;
import com.example.kidapp.model.Product;
import com.example.kidapp.model.api.MySingleObserver;
import com.example.kidapp.utils.RvAdapter;

import java.util.List;

public class ProductListActivity extends BaseActivity {

    public static final String EXTRA_KEY_SORT="sort";
    private ProductListViewModel viewModel=new ProductListViewModel();
    private int sortType;
    private ProductAdapter productAdapter;
    private CompositeDisposable compositeDisposable=new CompositeDisposable();

    private MySingleObserver<List<Product>> productObserver=new MySingleObserver<List<Product>>(this) {
        @Override
        public void onSubscribe(Disposable d) {
            compositeDisposable.add(d);
        }

        @Override
        public void onSuccess(List<Product> products) {
            productAdapter.setProducts(products);
        }

    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producst_list);
        sortType=getIntent().getIntExtra(EXTRA_KEY_SORT, Product.SORT_LATEST);
        setUpView();
        observe();


    }

    private void setUpView() {
        RecyclerView rv_product=findViewById(R.id.rv_list_products);
        rv_product.setLayoutManager(new GridLayoutManager(this,2));
        productAdapter=new ProductAdapter();
        rv_product.setAdapter(productAdapter);

        RecyclerView rv_sortChips=findViewById(R.id.rv_list_sort);
        rv_sortChips.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,true));
        rv_sortChips.setAdapter(new SortAdapter(this, sortType, new SortAdapter.OnSortClickListener() {
            @Override
            public void onClick(int sortType) {
                ProductListActivity.this.sortType=sortType;
                observe();

            }
        }));

        ImageView backButton=findViewById(R.id.iv_list_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
//----
    private void observe() {
        viewModel.products(sortType).subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(productObserver);
    }

    @Override
    public int getCordinatorLayoutId() {
        return R.id.coordinator_list;
    }
}

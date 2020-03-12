package com.example.kidapp.auth;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kidapp.BaseActivity;
import com.example.kidapp.R;
import com.example.kidapp.model.api.MyCompletableObserver;
import com.example.kidapp.utils.KeyboardUtil;

/**
 * this activity for login and sign up in app
* */
public class AuthenticationActivity extends BaseActivity {

    private EditText usernameET,passwordET;
    private Button authenticationButton;
    private TextView changeToRegister;
    private AuthenticationViewModel authenticationViewModel;
    private CompositeDisposable compositeDisposable=new CompositeDisposable();
    private View progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
        authenticationViewModel=new AuthenticationViewModel();
        setUpView();
        observe();
    }



    private void setUpView() {
        usernameET=findViewById(R.id.et_auth_username);
        passwordET=findViewById(R.id.et_auth_password);
        progressBar=findViewById(R.id.liner_auth_progressBar);
        changeToRegister=findViewById(R.id.tv_auth_register);
        changeToRegister.setOnClickListener(v -> authenticationViewModel.onChangeAuthenticationModeButtonClick() );
        authenticationButton=findViewById(R.id.btn_auth_authentication);
        authenticationButton.setOnClickListener(v ->{
            KeyboardUtil.closeKeyboard(getCurrentFocus());//close keyboard after user click button
            authenticationViewModel.authenticat(AuthenticationActivity.this,usernameET.getText().toString(),
                passwordET.getText().toString()).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyCompletableObserver(AuthenticationActivity.this) {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onComplete() {
                        Toast.makeText(AuthenticationActivity.this,"well cone",Toast.LENGTH_LONG).show();
                        finish();
                    }
                });
        });
    }

    private void observe() {
        Disposable isInLoginMoodDisposable =authenticationViewModel.getIsLoginMode().subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(isInLiginMood -> {
                    if(isInLiginMood){
                        changeToRegister.setText("ثبت نام");
                        authenticationButton.setText("ورود به حساب کاربری");
                    }
                    else {
                        changeToRegister.setText("ورود به حساب کاربری");
                        authenticationButton.setText("ثبت نام");
                    }
                });
        Disposable progressBarDisposable1 = authenticationViewModel.getProgressBarVisibilitySubject().subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(shouldProgressBar -> progressBar.setVisibility(shouldProgressBar ? View.VISIBLE: View.GONE));
        compositeDisposable.add(isInLoginMoodDisposable);
        compositeDisposable.add(progressBarDisposable1);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }

    @Override
    public int getCordinatorLayoutId() {
        return R.id.rl_auth_root;
    }
}

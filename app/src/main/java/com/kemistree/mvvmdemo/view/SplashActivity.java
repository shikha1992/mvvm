package com.kemistree.mvvmdemo.view;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.kemistree.mvvmdemo.R;
import com.kemistree.mvvmdemo.base.BaseActivity;
import com.kemistree.mvvmdemo.databinding.ActivitySplashBinding;
import com.kemistree.mvvmdemo.viewmodel.SplashViewModel;

import java.util.Observable;
import java.util.Observer;


public class SplashActivity extends AppCompatActivity implements Observer {

    ActivitySplashBinding activitySplashBinding;
    SplashViewModel splashViewModel;

    @Override
    protected void onCreate( @Nullable Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        initDataBinding();
    }

    private void initDataBinding() {
        activitySplashBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
        splashViewModel = new SplashViewModel(SplashActivity.this);
        activitySplashBinding.setSplashmodel(splashViewModel);
    }

    @Override
    public void update( Observable observable, Object o ) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

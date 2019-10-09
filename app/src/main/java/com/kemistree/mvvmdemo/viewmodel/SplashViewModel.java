package com.kemistree.mvvmdemo.viewmodel;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;

import androidx.databinding.ObservableField;

import com.kemistree.mvvmdemo.view.MainActivity;
import com.kemistree.mvvmdemo.view.SplashActivity;

import java.util.Observable;

import io.reactivex.disposables.CompositeDisposable;


public class SplashViewModel extends Observable {

    CompositeDisposable compositeDisposable = new CompositeDisposable();
    public ObservableField<String> splashText;
    Context context;
    int SPLASH_TIMER      = 3000;

    public SplashViewModel( Activity context ) {
        this.context = context;
        splashText = new ObservableField<>("Splash Activity");
        moveNext();
    }


    private void moveNext(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                context.startActivity(MainActivity.launchDetail(context));
            }
        }, SPLASH_TIMER);
    }

}

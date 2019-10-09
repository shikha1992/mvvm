package com.kemistree.mvvmdemo.view;


import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Contacts.People;

import com.kemistree.mvvmdemo.R;
import com.kemistree.mvvmdemo.databinding.ActivityMainBinding;
import com.kemistree.mvvmdemo.viewmodel.MainViewModel;

import java.util.Observable;
import java.util.Observer;


public class MainActivity extends AppCompatActivity implements Observer {


    ActivityMainBinding mainbind;
    MainViewModel mainViewModel;


    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDataBinding();
    }

    private void initDataBinding() {
        mainbind = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainViewModel = new MainViewModel(MainActivity.this,"");
        mainbind.setMainmodel(mainViewModel);
    }

    @Override
    public void update( Observable observable, Object o ) {

    }

    public static Intent launchDetail( Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

}

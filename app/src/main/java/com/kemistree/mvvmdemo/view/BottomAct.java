package com.kemistree.mvvmdemo.view;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kemistree.mvvmdemo.R;
import com.kemistree.mvvmdemo.databinding.ActivityBottomBinding;
import com.kemistree.mvvmdemo.view.ui.dashboard.DashboardViewModel;
import com.kemistree.mvvmdemo.viewmodel.BottomViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.Observable;
import java.util.Observer;


public class BottomAct extends AppCompatActivity implements Observer {

    ActivityBottomBinding activityBottomBinding;
    BottomViewModel bottomViewModel;
    public DashboardViewModel model;


    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        initBindView();

        model =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        DashboardViewModel sharedViewModel = (DashboardViewModel)ViewModelProviders.of((FragmentActivity)this).
                get(DashboardViewModel.class);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

    private void initBindView() {
        activityBottomBinding = DataBindingUtil.setContentView(this,R.layout.activity_bottom);
        bottomViewModel = new BottomViewModel(BottomAct.this);
        activityBottomBinding.setBottom(bottomViewModel);
    }

    @Override
    public void update( Observable observable, Object o ) {

        if (observable instanceof BottomViewModel) {

        }
    }

    public static Intent getIntent( Context ctx){
        Intent intent =new Intent(ctx, BottomAct.class);
        return intent;
    }

}

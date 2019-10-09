package com.kemistree.mvvmdemo.viewmodel;


import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.kemistree.mvvmdemo.data.ApiClient;
import com.kemistree.mvvmdemo.data.ApiService;
import com.kemistree.mvvmdemo.model.MainPojo;
import com.kemistree.mvvmdemo.view.BottomAct;
import com.kemistree.mvvmdemo.view.MainActivity;

import java.util.Observable;
import java.util.Observer;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;


public class MainViewModel extends Observable {

    CompositeDisposable compositeDisposable = new CompositeDisposable();
    Context context;
    private String phnNo ;
    private ApiService apiService;

    public MainViewModel( Context ctx, String phn ) {
        this.context = ctx;
        this.phnNo = phn;
        apiService = ApiClient.getClient(ctx).create(ApiService.class);
    }

    public void getLoginDetail( View view){
        compositeDisposable .add(
                apiService
                        .validate("+917696137554","hh","android")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<MainPojo>() {
                            @Override
                            public void onSuccess(MainPojo user) {
                                // Storing user API Key in preferences

                                Toast.makeText(context,
                                        " ApiKey: " + user.getMessage(),
                                        Toast.LENGTH_LONG).show();
                                context.startActivity(BottomAct.getIntent(context));
                            }

                            @Override
                            public void onError(Throwable e) {
                                Toast.makeText(context,
                                        " ApiKey: " + "err",
                                        Toast.LENGTH_LONG).show();
                                Log.e("err: ",e.toString());
                            }
                        }));
    }
}

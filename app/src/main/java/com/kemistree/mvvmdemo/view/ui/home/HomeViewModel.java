package com.kemistree.mvvmdemo.view.ui.home;


import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.kemistree.mvvmdemo.R;
import com.kemistree.mvvmdemo.data.ApiClient;
import com.kemistree.mvvmdemo.data.ApiService;
import com.kemistree.mvvmdemo.model.MainPojo;
import com.kemistree.mvvmdemo.model.People;
import com.kemistree.mvvmdemo.model.PeopleResponse;
import com.kemistree.mvvmdemo.view.BottomAct;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;


public class HomeViewModel extends ViewModel {

    private MutableLiveData<List<People>> mPeopleList;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    private ApiService apiService;
    private Context ctx;
    private List<People> peopleList;

    public HomeViewModel( Context ctx ) {
        mPeopleList = new MutableLiveData<>();
        this.ctx = ctx;
        this.peopleList = new ArrayList<>();

        apiService = ApiClient.getClient(ctx).create(ApiService.class);
        api_setdata();
    }

    public HomeViewModel(  ) {
        mPeopleList = new MutableLiveData<>();
        this.ctx = ctx;
        this.peopleList = new ArrayList<>();

        apiService = ApiClient.getClient(ctx).create(ApiService.class);
        api_setdata();
    }

    public LiveData<List<People>> getText() {
        return mPeopleList;
    }


    public void api_setdata(){
        compositeDisposable .add(
                apiService
                        .fetchAllNotes("https://api.randomuser.me/?results=30&nat=en")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<PeopleResponse>() {
                            @Override
                            public void accept( PeopleResponse people ) {
                                peopleList.addAll(people.getPeopleList());
                                mPeopleList.setValue(peopleList);
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept( Throwable throwable ) throws Exception {
                                throwable.printStackTrace();
                            }
                        }));

    }



    private void unSubscribeFromObservable() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }

    public void reset() {
        unSubscribeFromObservable();
        compositeDisposable = null;
        ctx = null;
    }
}
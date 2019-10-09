package com.kemistree.mvvmdemo.viewmodel;


import android.content.Context;
import android.content.Intent;

import java.util.Observable;


public class BottomViewModel extends Observable {

    Context context;

    public BottomViewModel(Context ctx) {
        this.context = ctx;
    }


}

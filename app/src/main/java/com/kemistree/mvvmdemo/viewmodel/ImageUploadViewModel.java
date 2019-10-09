package com.kemistree.mvvmdemo.viewmodel;


import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;

import com.kemistree.mvvmdemo.view.ImageUploadActivity;

import java.util.Observable;

import io.reactivex.disposables.CompositeDisposable;


public class ImageUploadViewModel extends Observable {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private Context context;
    public ObservableField<String> mbtnText = null;
    public MutableLiveData<Boolean> mselDialog = new MutableLiveData<>(false);

    public ImageUploadViewModel( Context ctx ) {
        this.context = ctx;
        mbtnText = new ObservableField<String>("Click here to upload image");
    }

    public void selectImage( View view){
//        setUser2(true);
//        setChanged();
//        notifyObservers();
        selectDialog("Just Test");
    }

    public MutableLiveData<Boolean> getUser2() {
        return mselDialog;
    }

    public void setUser2(final Boolean user) {
        mselDialog.postValue(user);
    }

    public void selectDialog(String message){
        new AlertDialog.Builder(context)
                .setTitle("Delete entry")
                .setMessage("Are you sure you want to delete this entry?")

                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        setUser2(true);
        setChanged();
        notifyObservers();
                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

}

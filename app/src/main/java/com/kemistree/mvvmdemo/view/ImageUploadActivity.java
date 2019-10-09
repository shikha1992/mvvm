package com.kemistree.mvvmdemo.view;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;

import com.kemistree.mvvmdemo.R;
import com.kemistree.mvvmdemo.base.BaseActivity;
import com.kemistree.mvvmdemo.databinding.ActivityImgUploadBinding;
import com.kemistree.mvvmdemo.viewmodel.ImageUploadViewModel;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;


public class ImageUploadActivity extends BaseActivity implements Observer {


    ImageUploadViewModel imageUploadViewModel;
    ActivityImgUploadBinding imgUploadBinding;


    @Override
    public void onCreate() {
        initBinding();
    }

    private void initBinding() {
        imgUploadBinding = DataBindingUtil.setContentView(this, R.layout.activity_img_upload);
        imageUploadViewModel = new ImageUploadViewModel(ImageUploadActivity.this);
        imgUploadBinding.setImagepicker(imageUploadViewModel);

        imageUploadViewModel.getUser2().observe(this, new androidx.lifecycle.Observer<Boolean>() {
            @Override
            public void onChanged( Boolean aBoolean ) {
                if(aBoolean){
                    if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                    {
                        requestPermissions(new String[]{Manifest.permission.CAMERA}, 1);
                    }
                    else
                    {
                        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(cameraIntent, 1);
                    }
                }
            }
        });

    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults[ 0 ] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, 1);
            } else {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            ImageView img_bit = (ImageView)findViewById(R.id.img_bit);
            img_bit.setImageBitmap(photo);
        }
    }



    @Override
    public void update( Observable observable, Object o ) {
        if(observable instanceof ImageUploadViewModel){
            selectDialog("Just Test");      }
    }

    @Override
    public void selectDialog( String message ) {
        super.selectDialog(message);
    }

    public static Intent launchImagePicker( Context ctx ){
        Intent intent = new Intent(ctx, ImageUploadActivity.class);
        return intent;
    }
}

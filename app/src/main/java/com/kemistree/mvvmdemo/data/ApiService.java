package com.kemistree.mvvmdemo.data;


import android.provider.ContactsContract.CommonDataKinds.Note;

import com.kemistree.mvvmdemo.model.MainPojo;
import com.kemistree.mvvmdemo.model.People;
import com.kemistree.mvvmdemo.model.PeopleResponse;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;


public interface ApiService {

    @FormUrlEncoded
    @POST("login")
    Single<MainPojo> validate( @Field("phone_number") String phone_number, @Field("device_token") String device_token,
                               @Field("device_type") String device_type);


//    @GET("/?results=10&nat=en")
//    Single<List<People>> fetchAllNotes();

    @GET Observable<PeopleResponse> fetchAllNotes( @Url String url);
}

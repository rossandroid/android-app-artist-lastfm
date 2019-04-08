package com.lfm.rossellamorgante.lfm.RepoNet;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lfm.rossellamorgante.lfm.Model.Results;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LFMNet implements Callback<Results> {

    Gson gson;
    LFMNetInterface service;
    Retrofit retrofit;

    public LFMNet(){
        gson = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder()
                .baseUrl("https://ws.audioscrobbler.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        service = retrofit.create(LFMNetInterface.class);
    }

    public void search(String s){
        Call<Results> r = service.getArtist("artist.search","json","81fe83e0d4bbc21ed53f739b2d51b598",s);
        r.enqueue(this);
    }

    @Override
    public void onResponse(Call<Results> call, Response<Results> response) {
        if(response.isSuccessful()) {

            Results changesList = response.body();


        } else {
            Log.e("retrofit",response.message());
        }
    }

    @Override
    public void onFailure(Call<Results> call, Throwable t) {
        t.printStackTrace();
    }
}

package com.lfm.rossellamorgante.lfm.RepoNet;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lfm.rossellamorgante.lfm.Model.Results;
import com.lfm.rossellamorgante.lfm.ViewModel.LFMViewModel;

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
    public static LFMNet instance;
    private LFMViewModel mLFMViewModel;


    public LFMNet(){
        // Retrofit
        gson = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder()
                .baseUrl("https://ws.audioscrobbler.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        service = retrofit.create(LFMNetInterface.class);

    }

    public static LFMNet getInstance(){
        if(instance==null){
            instance= new LFMNet();
        }
        return instance;
    }



    public void search(String s,  LFMViewModel m ){
        mLFMViewModel = m;
        Call<Results> r = service.getArtist("artist.search","json","81fe83e0d4bbc21ed53f739b2d51b598",s);
        r.enqueue(this);
    }

    @Override
    public void onResponse(Call<Results> call, Response<Results> response) {
        if(response.isSuccessful()) {

            Results output = response.body();
            mLFMViewModel.setArtist(output.results.artistmatches.artist);

        } else {
            mLFMViewModel.setArtist(null);
        }
    }

    @Override
    public void onFailure(Call<Results> call, Throwable t) {
        mLFMViewModel.setArtist(null);
    }
}

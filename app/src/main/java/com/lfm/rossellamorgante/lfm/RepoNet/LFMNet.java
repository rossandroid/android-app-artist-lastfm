/*
 * LFMNet
 * This Application implements the MVVM Pattern
 *
 * This LFMNet is a Singleton and permits:
 * - Search for an Artist
 * - Get Artist from the server
 * - Update ViewModel
 *
 * Third-party Libraries:
 * - Retrofit2
 * - GSON
 */
package com.lfm.rossellamorgante.lfm.RepoNet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lfm.rossellamorgante.lfm.Model.Results;
import com.lfm.rossellamorgante.lfm.ViewModel.LFMViewModel;

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
        // Retrofit uses GSON to parse data
        gson = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder()
                .baseUrl(NetParams.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        //Service create accordint the LFMNetInterface
        service = retrofit.create(LFMNetInterface.class);

    }

    public static LFMNet getInstance(){
        if(instance==null){
            instance= new LFMNet();
        }
        return instance;
    }


    //Search for an Artist
    public void search(String s,  LFMViewModel m ){
        // Save the viewModel, it is used after to update the List of Artist
        mLFMViewModel = m;
        // Make the request
        Call<Results> r = service.getArtist(NetParams.METHOD,NetParams.FORMAT,NetParams.API_KEY,s);
        r.enqueue(this);
    }

    //Communication with the server is ok, check if data are good, otherwise list of artist is null
    @Override
    public void onResponse(Call<Results> call, Response<Results> response) {
        if(response.isSuccessful()) {
            Results output = response.body();
            mLFMViewModel.setArtist(output.results.artistmatches.artist);

        } else {
            mLFMViewModel.setArtist(null);
        }
    }

    //Communication with the server is wrong, list of artist is null
    @Override
    public void onFailure(Call<Results> call, Throwable t) {
        mLFMViewModel.setArtist(null);
    }
}

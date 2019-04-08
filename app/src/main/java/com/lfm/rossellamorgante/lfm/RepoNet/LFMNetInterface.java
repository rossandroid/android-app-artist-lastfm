package com.lfm.rossellamorgante.lfm.RepoNet;

/*
Application name	LFM
API key	81fe83e0d4bbc21ed53f739b2d51b598
Shared secret	24ef6ce26dd364bfbe6907e941998396
Registered to	rosmorg
*/

import com.lfm.rossellamorgante.lfm.Model.Results;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LFMNetInterface {

    @GET("/2.0/")
    Call<Results> getArtist(
                            @Query("method") String method,
                            @Query("format") String format,
                            @Query("api_key") String api_key,
                            @Query("artist") String artist);

}

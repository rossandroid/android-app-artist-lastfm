/*
 * LFMNetInterface
 * This Application implements the MVVM Pattern
 *
 * This Interface permits:
 * - Call the Server
 *
 * Third-party Libraries:
 * - Retrofit2
 */

package com.lfm.rossellamorgante.lfm.RepoNet;

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

package com.si.uinam.mymoviecatalogueapi.services.retrofit;

import com.si.uinam.mymoviecatalogueapi.model.TvShowModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TvShowInterface {
    @GET("/3/tv/{listType}")
    Call<TvShow> getTvShowList(@Path("listType") String listType, @Query("api_key") String apiKey, @Query("language") String language, @Query("page") int page);

}

package com.si.uinam.mymoviecatalogueapi.services.retrofit;

import com.si.uinam.mymoviecatalogueapi.model.MovieModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieInterface {

    //url = "/3/movie/" + POPULAR_LIST_TYPE + "?api_key=" + API_KEY + "&language=" + languageId + "&page=1";
    @GET("/3/movie/{listType}")
    Call<Movie> getMovieList(@Path("listType") String listType, @Query("api_key") String apiKey, @Query("language") String language, @Query("page") int page);

}

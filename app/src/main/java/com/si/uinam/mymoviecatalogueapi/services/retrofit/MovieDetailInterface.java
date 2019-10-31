package com.si.uinam.mymoviecatalogueapi.services.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieDetailInterface {

    //url = /3/movie/558?api_key=2f766223589e24c61b0aecdf89ec841d&language=en-US
    @GET("/3/movie/{id}")
    Call<MovieDetail> getMovieDetail(@Path("id") int groupId, @Query("api_key") String apiKey, @Query("language") String language);

    //url= /3/movie/475557/credits?api_key=2f766223589e24c61b0aecdf89ec841d
    @GET("/3/movie/{id}/credits")
    Call<MovieCredit> getMovieCredit(@Path("id") int groupId, @Query("api_key") String apiKey);

    //url = /3/movie/47557/reviews?api_key=2f766223589e24c61b0aecdf89ec841d&language=en-US&page=1
    @GET("/3/movie/{id}/reviews")
    Call<MovieReview> getMovieReview(@Path("id") int groupId, @Query("api_key") String apiKey, @Query("language") String language);

}

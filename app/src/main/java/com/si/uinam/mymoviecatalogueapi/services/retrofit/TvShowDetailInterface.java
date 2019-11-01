package com.si.uinam.mymoviecatalogueapi.services.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TvShowDetailInterface {
    //url = /3/tv/558?api_key=2f766223589e24c61b0aecdf89ec841d&language=en-US
    @GET("/3/tv/{id}")
    Call<TvShowDetail> getTvShowDetail(@Path("id") int groupId, @Query("api_key") String apiKey, @Query("language") String language);

    //url= /3/tv/475557/credits?api_key=2f766223589e24c61b0aecdf89ec841d
    @GET("/3/tv/{id}/credits")
    Call<TvShowCredit> getTvShowCredit(@Path("id") int groupId, @Query("api_key") String apiKey, @Query("language") String language);

    //url = /3/tv/47557/reviews?api_key=2f766223589e24c61b0aecdf89ec841d&language=en-US&page=1
    @GET("/3/tv/{id}/reviews")
    Call<TvShowReview> getTvShowReview(@Path("id") int groupId, @Query("api_key") String apiKey, @Query("language") String language, @Query("page") String page);

}

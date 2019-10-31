package com.si.uinam.mymoviecatalogueapi.services.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieDetailService {

    //url = /3/movie/558?api_key=2f766223589e24c61b0aecdf89ec841d&language=en-US
    @GET("/3/movie/558?api_key=2f766223589e24c61b0aecdf89ec841d&language=en-US")
    Call<MovieDetail> getMovieDetail();

}

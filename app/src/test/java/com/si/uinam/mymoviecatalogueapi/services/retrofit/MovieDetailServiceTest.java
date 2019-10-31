package com.si.uinam.mymoviecatalogueapi.services.retrofit;

import android.util.Log;

import org.junit.Before;
import org.junit.Test;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class MovieDetailServiceTest {

    Log log;

    public void printOut(MovieDetail movieDetail) {
        Log.d("RETROFIT-TEST", movieDetail.title);
        System.out.println(movieDetail.title);
        assertEquals(null,movieDetail);
    }

    @Before
    public void before() {
        log = mock(Log.class);
    }

    @Test
    public void getMovieDetail() {

        //Log.d("RETROFIT-TEST", "movieDetail.title");
        System.out.println("movieDetail.title");
        MovieDetailService mdServ = RetrofitClientInstance.getRetrofitInstance()
                .create(MovieDetailService.class);
        Call<MovieDetail> call = mdServ.getMovieDetail();
        call.enqueue(new Callback<MovieDetail>() {
            @Override
            public void onResponse(Call<MovieDetail> call, Response<MovieDetail> response) {
                Log.d("RETROFIT-TEST", response.body().title);
                printOut(response.body());
            }

            @Override
            public void onFailure(Call<MovieDetail> call, Throwable t) {
                Log.d("RETROFIT-TEST-ERROR", t.getMessage());
            }
        });

    }
}
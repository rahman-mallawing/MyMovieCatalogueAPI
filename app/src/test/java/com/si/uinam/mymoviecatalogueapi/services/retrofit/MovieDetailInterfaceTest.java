package com.si.uinam.mymoviecatalogueapi.services.retrofit;

import android.util.Log;

import org.junit.Before;
import org.junit.Test;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class MovieDetailInterfaceTest {

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

        int id = 558;
        String API_KEY = "2f766223589e24c61b0aecdf89ec841d";
        String language = "en-US";
        //Log.d("RETROFIT-TEST", "movieDetail.title");
        System.out.println("movieDetail.title");
        MovieDetailInterface mdServ = RetrofitClientInstance.getRetrofitInstance()
                .create(MovieDetailInterface.class);
        Call<MovieDetail> call = mdServ.getMovieDetail(id, API_KEY, language);
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
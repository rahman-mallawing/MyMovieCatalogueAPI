package com.si.uinam.mymoviecatalogueapi.services.retrofit;

import org.junit.Test;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

import static org.junit.Assert.*;

public class MovieDetailTest {

    final String tittle = "KINGKONG";

    @Test
    public void getPopularity() {
    }

    @Test
    public void getTitle() {
        System.out.println("movieDetail.title");
        MovieDetailService mdServ = RetrofitClientInstance.getRetrofitInstance()
                .create(MovieDetailService.class);
        Call<MovieDetail> call = mdServ.getMovieDetail();
        try {
            Response<MovieDetail> response = call.execute();
            MovieDetail movieDetail = response.body();
            System.out.println(movieDetail.toString());
            assertTrue(response.isSuccessful());
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
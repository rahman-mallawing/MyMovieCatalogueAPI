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
    public void getMovieDetail() {
        int id = 558;
        String API_KEY = "2f766223589e24c61b0aecdf89ec841d";
        String language = "id-ID";
        System.out.println("movieDetail.title");
        MovieDetailInterface mdServ = RetrofitClientInstance.getRetrofitInstance()
                .create(MovieDetailInterface.class);
        Call<MovieDetail> call = mdServ.getMovieDetail(id, API_KEY, language);
        try {
            Response<MovieDetail> response = call.execute();
            MovieDetail movieDetail = response.body();
            System.out.println(movieDetail.toString());
            assertTrue(response.isSuccessful() && printObject(movieDetail.getCollection()));
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getMovieCredit() {
        int id = 558;
        String API_KEY = "2f766223589e24c61b0aecdf89ec841d";
        String language = "id-ID";
        System.out.println("================================================================");
        MovieDetailInterface mdServ = RetrofitClientInstance.getRetrofitInstance()
                .create(MovieDetailInterface.class);
        Call<MovieCredit> call = mdServ.getMovieCredit(id, API_KEY);
        try {
            Response<MovieCredit> response = call.execute();
            MovieCredit movieCredit = response.body();
            System.out.println(movieCredit.toString());
            assertTrue(response.isSuccessful() && printObject(movieCredit.getCasts()));
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getMovieReview() {
        int id = 558;
        String API_KEY = "2f766223589e24c61b0aecdf89ec841d";
        String language = "id-ID";
        System.out.println("movieDetail.title");
        MovieDetailInterface mdServ = RetrofitClientInstance.getRetrofitInstance()
                .create(MovieDetailInterface.class);
        Call<MovieReview> call = mdServ.getMovieReview(id, API_KEY, language);
        try {
            Response<MovieReview> response = call.execute();
            MovieReview movieReview = response.body();
            System.out.println(movieReview.toString());
            assertTrue(response.isSuccessful() && printObject(movieReview.getReviews()));
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean printObject(Object object) {
        System.out.println(object.toString());
        return true;
    }
}
package com.si.uinam.mymoviecatalogueapi.services.retrofit;

import android.util.Log;

import com.si.uinam.mymoviecatalogueapi.helper.ApiHelper;
import com.si.uinam.mymoviecatalogueapi.model.MovieDetailModel;
import com.si.uinam.mymoviecatalogueapi.model.MovieModel;

import java.io.IOException;
import java.lang.ref.WeakReference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailService {

    private static String API_KEY = ApiHelper.getApiKey();
    private static String CREDIT_LIST_TYPE = ApiHelper.getCreditListType();
    private static String REVIEW_LIST_TYPE = ApiHelper.getReviewListType();

    private MovieDetail movieDetail;private MovieModel movieModel;
    private String langId;
    MovieDetailInterface mdServ;
    private WeakReference<MovieDetailServiceCallback> movieDetailServiceCallback;

    private MovieDetailService(MovieDetailInterface mdServ) {
        this.mdServ = mdServ;
    }

    public MovieDetailService setCallback(MovieDetailServiceCallback movieDetailServiceCallback) {
        this.movieDetailServiceCallback = new WeakReference<>(movieDetailServiceCallback);
        return this;
    }

    public MovieDetailService setInputOption(MovieModel movieModel, String languangeId) {
        this.movieModel = movieModel;
        this.langId = languangeId;
        return this;
    }

    public static MovieDetailService create() {
        return new MovieDetailService(
                RetrofitClientInstance.getRetrofitInstance()
                        .create(MovieDetailInterface.class)
        );
    }

    public void executeService() {
        this.getMovieDetail();
    }

    private void onMovieDetailPostExecuted(MovieDetail movieDetail) {
        Log.i("ASYN_TAG", "onPreExecute inside DemoAsynch class");

        if(movieDetail != null) {
            this.getMovieCredit(movieDetail);
        } else {
            MovieDetailServiceCallback myListener = this.movieDetailServiceCallback.get();
            if(myListener != null){
                myListener.onPostExecute(
                        new MovieDetailModel(movieModel, movieDetail, null, null)
                );
            }
        }
    }

    private void onMovieCreditPostExecuted(MovieDetail movieDetail, MovieCredit movieCredit) {
        Log.i("ASYN_TAG", "onPreExecute inside DemoAsynch class");

        if(movieDetail != null) {
            this.getMovieReview(movieDetail, movieCredit);
        } else {
            MovieDetailServiceCallback myListener = this.movieDetailServiceCallback.get();
            if(myListener != null){
                myListener.onPostExecute(
                        new MovieDetailModel(movieModel, movieDetail, movieCredit, null)
                );
            }
        }
    }

    private void onMovieReviewPostExecuted(MovieDetail movieDetail, MovieCredit movieCredit, MovieReview movieReview) {
        Log.i("ASYN_TAG", "onPreExecute inside DemoAsynch class");
        MovieDetailServiceCallback myListener = this.movieDetailServiceCallback.get();
        if(myListener != null){
            myListener.onPostExecute(
                    new MovieDetailModel(movieModel, movieDetail, movieCredit, movieReview)
            );
        }
    }

    private void getMovieDetail() {
        Call<MovieDetail> call = mdServ.getMovieDetail(this.movieModel.getId(), API_KEY, this.langId);
        call.enqueue(new Callback<MovieDetail>() {
            @Override
            public void onResponse(Call<MovieDetail> call, Response<MovieDetail> response) {
                Log.d("RETROFIT-TEST", response.body().title);
                onMovieDetailPostExecuted(response.body());
            }

            @Override
            public void onFailure(Call<MovieDetail> call, Throwable t) {
                Log.d("RETROFIT-TEST-ERROR", t.getMessage());
            }
        });
    }

    private void getMovieCredit(final MovieDetail movieDetail) {

        Call<MovieCredit> call = mdServ.getMovieCredit(this.movieModel.getId(), API_KEY);
        call.enqueue(new Callback<MovieCredit>() {
            @Override
            public void onResponse(Call<MovieCredit> call, Response<MovieCredit> response) {
                onMovieCreditPostExecuted(movieDetail, response.body());
            }

            @Override
            public void onFailure(Call<MovieCredit> call, Throwable t) {

            }
        });
    }

    public void getMovieReview(final MovieDetail movieDetail, final MovieCredit movieCredit) {

        Call<MovieReview> call = mdServ.getMovieReview(this.movieModel.getId(), API_KEY, this.langId);
        call.enqueue(new Callback<MovieReview>() {
            @Override
            public void onResponse(Call<MovieReview> call, Response<MovieReview> response) {
                onMovieReviewPostExecuted(movieDetail, movieCredit, response.body());
            }

            @Override
            public void onFailure(Call<MovieReview> call, Throwable t) {

            }
        });
    }



}

package com.si.uinam.mymoviecatalogueapi.services.retrofit;

import android.util.Log;

import com.si.uinam.mymoviecatalogueapi.helper.ApiHelper;
import com.si.uinam.mymoviecatalogueapi.model.MovieModel;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieService {

    private static String API_KEY = ApiHelper.getApiKey();
    private static String POPULAR_LIST_TYPE = ApiHelper.getPopularListType();

    private String langId;
    MovieInterface mdServ;
    private WeakReference<MovieServiceCallback> movieServiceCallbackWeakReference;

    private MovieService(MovieInterface mdServ) {
        this.mdServ = mdServ;
    }

    public MovieService setCallback(MovieServiceCallback movieServiceCallback) {
        this.movieServiceCallbackWeakReference = new WeakReference<>(movieServiceCallback);
        return this;
    }

    public MovieService setInputOption(String languangeId) {
        this.langId = languangeId;
        return this;
    }

    public static MovieService create() {
        return new MovieService(
                RetrofitClientInstance.getRetrofitInstance()
                        .create(MovieInterface.class)
        );
    }

    public void executeService() {
        this.execute(this.langId);
    }

    private void onPostExecuted(ArrayList<MovieModel> movieModelArrayList) {
        Log.i("ASYN_TAG", "onPreExecute inside DemoAsynch class");
        MovieServiceCallback myListener = this.movieServiceCallbackWeakReference.get();
        if(myListener != null){
            myListener.onPostExecute(movieModelArrayList);
        }
    }

    private void execute(String languageId) {
        Call<Movie> call = mdServ.getMovieList(POPULAR_LIST_TYPE, API_KEY, languageId, 1);
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                onPostExecuted(response.body().getMovieModels());
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Log.d("RETROFIT-TEST-ERROR", t.getMessage());
            }
        });
    }

}

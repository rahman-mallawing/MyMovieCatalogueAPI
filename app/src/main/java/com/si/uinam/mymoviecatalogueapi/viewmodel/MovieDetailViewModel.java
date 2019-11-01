package com.si.uinam.mymoviecatalogueapi.viewmodel;


import com.si.uinam.mymoviecatalogueapi.model.MovieDetailModel;
import com.si.uinam.mymoviecatalogueapi.model.MovieModel;
import com.si.uinam.mymoviecatalogueapi.services.asynchttp.AsyncHttpCallback;
import com.si.uinam.mymoviecatalogueapi.services.asynchttp.MovieAsyncHttpService;
import com.si.uinam.mymoviecatalogueapi.services.retrofit.MovieDetailService;
import com.si.uinam.mymoviecatalogueapi.services.retrofit.MovieDetailServiceCallback;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MovieDetailViewModel extends ViewModel {

    private MutableLiveData<MovieDetailModel> movieDetailInstance = new MutableLiveData<>();


    public void loadMovieDetail(MovieModel movieModel, String languageId) {

        //this.loadMovieDetailWithAsyncHttp(movieModel, languageId);
        this.loadMovieDetailWithRetrofit(movieModel, languageId);
    }

    private void loadMovieDetailWithRetrofit(final MovieModel movieModel, String languageId) {
        MovieDetailService.create()
                .setCallback(new MovieDetailServiceCallback() {
                    @Override
                    public void onPostExecute(MovieDetailModel movieDetailModel) {

                        movieDetailInstance.setValue(movieDetailModel);
                    }
                })
                .setInputOption(movieModel, languageId)
                .executeService();
    }

    private void loadMovieDetailWithAsyncHttp(MovieModel movieModel, String languageId) {
        MovieAsyncHttpService.create(new AsyncHttpCallback() {
            @Override
            public void onPostExecute(MovieDetailModel movieDetailModel) {
                movieDetailInstance.setValue(movieDetailModel);
            }
        }).setInputOption(movieModel, languageId).executeService();
    }

    public LiveData<MovieDetailModel> getMovieDetailInstance() {
        return movieDetailInstance;
    }
}

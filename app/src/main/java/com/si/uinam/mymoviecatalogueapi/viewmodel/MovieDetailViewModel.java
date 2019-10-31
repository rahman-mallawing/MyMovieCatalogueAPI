package com.si.uinam.mymoviecatalogueapi.viewmodel;


import com.si.uinam.mymoviecatalogueapi.helper.ApiHelper;
import com.si.uinam.mymoviecatalogueapi.model.MovieDetailModel;
import com.si.uinam.mymoviecatalogueapi.model.MovieModel;
import com.si.uinam.mymoviecatalogueapi.services.asynctask.AsyncTaskCallback;
import com.si.uinam.mymoviecatalogueapi.services.asynctask.MovieAsyncTaskService;
import com.si.uinam.mymoviecatalogueapi.services.retrofit.MovieCredit;
import com.si.uinam.mymoviecatalogueapi.services.retrofit.MovieDetail;
import com.si.uinam.mymoviecatalogueapi.services.retrofit.MovieDetailService;
import com.si.uinam.mymoviecatalogueapi.services.retrofit.MovieDetailServiceCallback;
import com.si.uinam.mymoviecatalogueapi.services.retrofit.MovieReview;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MovieDetailViewModel extends ViewModel {

    private MutableLiveData<MovieDetailModel> movieDetailInstance = new MutableLiveData<>();


    public void loadMovieDetail(MovieModel movieModel, String languageId) {

        this.loadMovieDetailWithAsyncTask(movieModel, languageId);

    }

    private void loadMovieDetailWithRetrofit(final MovieModel movieModel, String languageId) {
        MovieDetailService.create()
                .setCallback(new MovieDetailServiceCallback() {
                    @Override
                    public void onPostExecute(MovieDetail movieDetail, MovieCredit movieCredit, MovieReview movieReview) {

                        movieDetailInstance.setValue(
                                ApiHelper.mapMovieDetailToMovieDetailModel(movieModel, movieDetail, movieCredit, movieReview)
                        );
                    }
                })
                .setInputOption(movieModel, languageId)
                .executeService();
    }

    private void loadMovieDetailWithAsyncTask(MovieModel movieModel, String languageId) {
        MovieAsyncTaskService.create(new AsyncTaskCallback() {
            @Override
            public void onPreExecute() {

            }

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

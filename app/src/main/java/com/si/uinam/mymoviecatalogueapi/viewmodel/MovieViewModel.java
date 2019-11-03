package com.si.uinam.mymoviecatalogueapi.viewmodel;


import com.si.uinam.mymoviecatalogueapi.model.MovieModel;
import com.si.uinam.mymoviecatalogueapi.services.asynchttp.AsyncMovieHttpCallback;
import com.si.uinam.mymoviecatalogueapi.services.asynchttp.MovieAsyncHttpService;
import com.si.uinam.mymoviecatalogueapi.services.retrofit.MovieService;
import com.si.uinam.mymoviecatalogueapi.services.retrofit.MovieServiceCallback;

import java.util.ArrayList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MovieViewModel extends ViewModel {

    private MutableLiveData<ArrayList<MovieModel>> movieCollection = new MutableLiveData<>();
    private MutableLiveData<String> errorMessage = new MutableLiveData<>();

    public void loadMovieList(String languageId){
        this.getMovieListWithRetrofitService(languageId);
        // ATAU bisa juga menggunakan service AsyncHttp
        //this.getMovieListWithAsyncHttpService(languageId);
    }

    private void getMovieListWithRetrofitService(String languageId) {
        MovieService.create()
                .setCallback(new MovieServiceCallback() {
                    @Override
                    public void onPostExecute(ArrayList<MovieModel> movieModelArrayList) {
                        movieCollection.setValue(movieModelArrayList);
                    }

                    @Override
                    public void onFailure(String err) {
                        errorMessage.setValue(err);
                    }
                })
                .setInputOption(languageId)
                .executeService();
    }

    private void getMovieListWithAsyncHttpService(String languageId) {
        MovieAsyncHttpService.create(new AsyncMovieHttpCallback() {
            @Override
            public void onPostExecute(ArrayList<MovieModel> movieModelArrayList) {
                movieCollection.setValue(movieModelArrayList);
            }
        }).setInputOption(languageId).executeService();
    }

    public LiveData<ArrayList<MovieModel>> getMovieCollection() {
        return movieCollection;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }
}

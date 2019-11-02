package com.si.uinam.mymoviecatalogueapi.viewmodel;


import com.si.uinam.mymoviecatalogueapi.model.MovieModel;
import com.si.uinam.mymoviecatalogueapi.services.asynchttp.AsyncMovieHttpCallback;
import com.si.uinam.mymoviecatalogueapi.services.asynchttp.MovieAsyncHttpService;

import java.util.ArrayList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MovieViewModel extends ViewModel {

    private MutableLiveData<ArrayList<MovieModel>> movieCollection = new MutableLiveData<>();

    public void loadMovieList(String languageId){
        this.getMovieListWithAsyncHttpService(languageId);

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
}

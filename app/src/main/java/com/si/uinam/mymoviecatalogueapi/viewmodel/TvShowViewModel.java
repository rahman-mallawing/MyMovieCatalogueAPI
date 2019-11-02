package com.si.uinam.mymoviecatalogueapi.viewmodel;

import com.si.uinam.mymoviecatalogueapi.model.TvShowModel;
import com.si.uinam.mymoviecatalogueapi.services.asynchttp.AsyncTvShowHttpCallback;
import com.si.uinam.mymoviecatalogueapi.services.asynchttp.TvShowAsyncHttpService;

import java.util.ArrayList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TvShowViewModel extends ViewModel {

    private MutableLiveData<ArrayList<TvShowModel>> tvShowCollection = new MutableLiveData<>();

    public void loadTvShow(String languageId){

        this.getTvShowListWithAsyncHttpService(languageId);

    }

    private void getTvShowListWithAsyncHttpService(String languageId) {
        TvShowAsyncHttpService.create(new AsyncTvShowHttpCallback() {
            @Override
            public void onPostExecute(ArrayList<TvShowModel> tvShowModelArrayList) {
                tvShowCollection.setValue(tvShowModelArrayList);
            }
        }).setInputOption(languageId).executeService();
    }

    public LiveData<ArrayList<TvShowModel>> getTvShowCollection() {
        return tvShowCollection;
    }

}

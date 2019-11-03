package com.si.uinam.mymoviecatalogueapi.viewmodel;

import com.si.uinam.mymoviecatalogueapi.model.TvShowModel;
import com.si.uinam.mymoviecatalogueapi.services.asynchttp.AsyncTvShowHttpCallback;
import com.si.uinam.mymoviecatalogueapi.services.asynchttp.TvShowAsyncHttpService;
import com.si.uinam.mymoviecatalogueapi.services.retrofit.TvShowService;
import com.si.uinam.mymoviecatalogueapi.services.retrofit.TvShowServiceCallback;

import java.util.ArrayList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TvShowViewModel extends ViewModel {

    private MutableLiveData<ArrayList<TvShowModel>> tvShowCollection = new MutableLiveData<>();
    private MutableLiveData<String> errorMessage = new MutableLiveData<>();

    public void loadTvShow(String languageId){
        this.getTvShowListWithRetrofitService(languageId);
        // ATAU  bisa juga menggunakan service AsyncHttp
        // this.getTvShowListWithAsyncHttpService(languageId);
    }

    private void getTvShowListWithRetrofitService(String languageId) {
        TvShowService.create()
                .setCallback(new TvShowServiceCallback() {
                    @Override
                    public void onPostExecute(ArrayList<TvShowModel> tvShowModelArrayList) {
                        tvShowCollection.setValue(tvShowModelArrayList);
                    }

                    @Override
                    public void onFailure(String err) {
                        errorMessage.setValue(err);
                    }
                })
                .setInputOption(languageId)
                .executeService();
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

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

}

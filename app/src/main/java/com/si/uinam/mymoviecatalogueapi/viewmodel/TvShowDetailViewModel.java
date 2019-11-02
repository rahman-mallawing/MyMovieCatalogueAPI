package com.si.uinam.mymoviecatalogueapi.viewmodel;


import android.util.Log;

import com.si.uinam.mymoviecatalogueapi.model.TvShowModel;
import com.si.uinam.mymoviecatalogueapi.services.retrofit.TvShowDetail;
import com.si.uinam.mymoviecatalogueapi.services.retrofit.TvShowDetailService;
import com.si.uinam.mymoviecatalogueapi.services.retrofit.TvShowDetailServiceCallback;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TvShowDetailViewModel extends ViewModel {

    private MutableLiveData<TvShowDetail> tvShowDetailInstance = new MutableLiveData<>();

    public void loadTvShowDetail(TvShowModel tvShowModel, String languageId) {

        this.loadTvShowDetailWithRetrofit(tvShowModel, languageId);
    }

    public LiveData<TvShowDetail> getTvShowDetailInstance() {
        return tvShowDetailInstance;
    }

    private void loadTvShowDetailWithRetrofit(TvShowModel tvShowModel, String languageId) {
        TvShowDetailService.create(new TvShowDetailServiceCallback() {
            @Override
            public void onPostExecute(TvShowDetail tvShowDetailIns) {
                Log.d("VIEW-MODEL", tvShowDetailIns.toString());
                tvShowDetailInstance.setValue(tvShowDetailIns);
            }
        }).setInputOption(tvShowModel, languageId).executeService();
    }
}

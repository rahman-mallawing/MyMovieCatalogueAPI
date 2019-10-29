package com.si.uinam.mymoviecatalogueapi.viewmodel;

import java.util.ArrayList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TvShowViewModel extends ViewModel {

    private static final String API_KEY = "2f766223589e24c61b0aecdf89ec841d";
    private String langId = "en-US";
    private String listType = "popular";
    private MutableLiveData<ArrayList<TvShowViewModel>> tvShowCollection = new MutableLiveData<>();



    private String getListType() {
        return listType;
    }

    private String getLangId() {
        //String localeId = LocaleHelper.getLocale(context);
        //if(localeId.equals("in")){
        return langId;
    }

    public LiveData<ArrayList<TvShowViewModel>> getTvShowCollection() {
        return tvShowCollection;
    }

}

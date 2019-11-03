package com.si.uinam.mymoviecatalogueapi.services.retrofit;

import com.si.uinam.mymoviecatalogueapi.model.TvShowModel;

import java.util.ArrayList;

public interface TvShowServiceCallback {
    void onPostExecute(ArrayList<TvShowModel> tvShowModelArrayList);
    void onFailure(String err);
}

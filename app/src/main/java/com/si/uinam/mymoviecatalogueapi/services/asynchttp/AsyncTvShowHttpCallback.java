package com.si.uinam.mymoviecatalogueapi.services.asynchttp;

import com.si.uinam.mymoviecatalogueapi.model.TvShowModel;

import java.util.ArrayList;

public interface AsyncTvShowHttpCallback {
    void onPostExecute(ArrayList<TvShowModel> tvShowModelArrayList);
}

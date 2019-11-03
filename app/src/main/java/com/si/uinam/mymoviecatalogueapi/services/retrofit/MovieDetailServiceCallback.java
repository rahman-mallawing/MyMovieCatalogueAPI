package com.si.uinam.mymoviecatalogueapi.services.retrofit;

import com.si.uinam.mymoviecatalogueapi.model.MovieDetailModel;

public interface MovieDetailServiceCallback {
    void onPostExecute(MovieDetailModel movieDetailModel);
    void onFailure(String err);
}

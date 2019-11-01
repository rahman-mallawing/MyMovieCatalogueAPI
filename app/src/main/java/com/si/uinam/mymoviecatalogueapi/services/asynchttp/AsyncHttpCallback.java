package com.si.uinam.mymoviecatalogueapi.services.asynchttp;

import com.si.uinam.mymoviecatalogueapi.model.MovieDetailModel;

public interface AsyncHttpCallback {
    void onPostExecute(MovieDetailModel movieDetailModel);
}

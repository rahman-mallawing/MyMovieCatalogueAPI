package com.si.uinam.mymoviecatalogueapi.services.asynctask;

import com.si.uinam.mymoviecatalogueapi.model.MovieDetailModel;

public interface AsyncTaskCallback {
    void onPreExecute();
    void onPostExecute(MovieDetailModel movieDetailModel);
}

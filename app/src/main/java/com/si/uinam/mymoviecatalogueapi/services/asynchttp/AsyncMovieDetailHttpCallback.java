package com.si.uinam.mymoviecatalogueapi.services.asynchttp;

import com.si.uinam.mymoviecatalogueapi.model.MovieDetailModel;

public interface AsyncMovieDetailHttpCallback {
    void onPostExecute(MovieDetailModel movieDetailModel);
}

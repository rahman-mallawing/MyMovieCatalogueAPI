package com.si.uinam.mymoviecatalogueapi.services.asynchttp;

import com.si.uinam.mymoviecatalogueapi.model.MovieModel;

import java.util.ArrayList;

public interface AsyncMovieHttpCallback {
    void onPostExecute(ArrayList<MovieModel> movieModelArrayList);
}

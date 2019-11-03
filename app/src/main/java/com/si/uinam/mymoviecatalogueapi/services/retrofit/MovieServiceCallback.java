package com.si.uinam.mymoviecatalogueapi.services.retrofit;

import com.si.uinam.mymoviecatalogueapi.model.MovieModel;

import java.util.ArrayList;

public interface MovieServiceCallback {
    void onPostExecute(ArrayList<MovieModel> movieModelArrayList);
    void onFailure(String err);
}

package com.si.uinam.mymoviecatalogueapi.services.retrofit;

public interface TvShowDetailServiceCallback {
    void onPostExecute(TvShowDetail tvShowDetail);
    void onFailure(String err);
}

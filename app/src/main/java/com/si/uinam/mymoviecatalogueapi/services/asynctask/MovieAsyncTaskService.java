package com.si.uinam.mymoviecatalogueapi.services.asynctask;

import android.os.AsyncTask;

import com.si.uinam.mymoviecatalogueapi.model.MovieDetailModel;

public class MovieAsyncTaskService extends AsyncTask<String, Integer, MovieDetailModel> {

    private AsyncTaskCallback asyncTaskCallback;

    public MovieAsyncTaskService(AsyncTaskCallback asyncTaskCallback) {
        this.asyncTaskCallback = asyncTaskCallback;
    }

    @Override
    protected MovieDetailModel doInBackground(String... strings) {
        return null;
    }
}

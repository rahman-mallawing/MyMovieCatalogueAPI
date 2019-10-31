package com.si.uinam.mymoviecatalogueapi.services.asynctask;

public interface AsyncTaskCallback {
    void onPreExecute();
    void onPostExecute(String result);
}

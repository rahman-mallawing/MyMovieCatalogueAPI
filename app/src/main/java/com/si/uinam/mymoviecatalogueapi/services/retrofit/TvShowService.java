package com.si.uinam.mymoviecatalogueapi.services.retrofit;

import android.util.Log;

import com.si.uinam.mymoviecatalogueapi.helper.ApiHelper;
import com.si.uinam.mymoviecatalogueapi.model.TvShowModel;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TvShowService {

    private static String API_KEY = ApiHelper.getApiKey();
    private static String POPULAR_LIST_TYPE = ApiHelper.getPopularListType();

    private String langId;
    TvShowInterface mdServ;
    private WeakReference<TvShowServiceCallback> tvShowServiceCallbackWeakReference;

    private TvShowService(TvShowInterface mdServ) {
        this.mdServ = mdServ;
    }

    public TvShowService setCallback(TvShowServiceCallback tvShowServiceCallback) {
        this.tvShowServiceCallbackWeakReference = new WeakReference<>(tvShowServiceCallback);
        return this;
    }

    public TvShowService setInputOption(String languangeId) {
        this.langId = languangeId;
        return this;
    }

    public static TvShowService create() {
        return new TvShowService(
                RetrofitClientInstance.getRetrofitInstance()
                        .create(TvShowInterface.class)
        );
    }

    public void executeService() {
        this.execute(this.langId);
    }

    private void onPostExecuted(ArrayList<TvShowModel> tvShowModelArrayList) {
        Log.i("ASYN_TAG", "onPreExecute inside DemoAsynch class");
        TvShowServiceCallback myListener = this.tvShowServiceCallbackWeakReference.get();
        if(myListener != null){
            myListener.onPostExecute(tvShowModelArrayList);
        }
    }

    private void execute(String languageId) {
        Call<TvShow> call = mdServ.getTvShowList(POPULAR_LIST_TYPE, API_KEY, languageId, 1);
        call.enqueue(new Callback<TvShow>() {
            @Override
            public void onResponse(Call<TvShow> call, Response<TvShow> response) {
                onPostExecuted(response.body().getTvShowModelArrayList());
            }

            @Override
            public void onFailure(Call<TvShow> call, Throwable t) {
                Log.d("RETROFIT-TEST-ERROR", t.getMessage());
            }
        });
    }

}

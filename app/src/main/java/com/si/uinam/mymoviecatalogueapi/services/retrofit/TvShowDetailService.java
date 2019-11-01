package com.si.uinam.mymoviecatalogueapi.services.retrofit;

import android.util.Log;

import com.si.uinam.mymoviecatalogueapi.helper.ApiHelper;
import com.si.uinam.mymoviecatalogueapi.model.TvShowModel;

import java.lang.ref.WeakReference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TvShowDetailService {

    private static String API_KEY = ApiHelper.getApiKey();
    private static String CREDIT_LIST_TYPE = ApiHelper.getCreditListType();
    private static String REVIEW_LIST_TYPE = ApiHelper.getReviewListType();
    private WeakReference<TvShowDetailServiceCallback> tvShowDetailServiceCallback;
    private TvShowModel tvShowModel;
    private String langId;
    private TvShowDetailInterface tvShowServ;

    private TvShowDetailService(TvShowDetailServiceCallback tvShowDetailServiceCallback) {
        this.tvShowDetailServiceCallback = new WeakReference<>(tvShowDetailServiceCallback);
        this.tvShowServ = RetrofitClientInstance.getRetrofitInstance()
                .create(TvShowDetailInterface.class);
    }

    public static TvShowDetailService create(TvShowDetailServiceCallback tvShowDetailServiceCallback) {
        return new TvShowDetailService(tvShowDetailServiceCallback);
    }

    public TvShowDetailService setInputOption(TvShowModel tvShowModel, String languangeId) {
        this.langId = languangeId;
        this.tvShowModel = tvShowModel;
        return this;
    }

    public void executeService() {
        this.getTvShowDetail();
    }

    private void onCallResponded(TvShowDetail tvShowDetail) {
        Log.i("ASYN_TAG", "onPreExecute inside DemoAsynch class");
        TvShowDetailServiceCallback myListener = this.tvShowDetailServiceCallback.get();
        if(myListener != null){
            myListener.onPostExecute(tvShowDetail);
        }
    }

    private void getTvShowDetail() {

        Call<TvShowDetail> call = tvShowServ.getTvShowDetail(tvShowModel.getId(), API_KEY, this.langId);
        call.enqueue(new Callback<TvShowDetail>() {
            @Override
            public void onResponse(Call<TvShowDetail> call, Response<TvShowDetail> response) {

            }

            @Override
            public void onFailure(Call<TvShowDetail> call, Throwable t) {

            }
        });
    }

}

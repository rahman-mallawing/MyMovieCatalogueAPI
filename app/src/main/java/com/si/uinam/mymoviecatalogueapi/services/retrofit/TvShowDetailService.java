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

    private void onTvShowDetailPostExecuted(TvShowDetail tvShowDetail) {
        Log.i("ASYN_TAG", "onTvShowDetailPostExecuted inside DemoAsynch class");
        if(tvShowDetail != null) {
            Log.i("REVIEW-DETAIL", tvShowDetail.toString() );
            this.getTvShowCredit(tvShowDetail);
        } else {
            TvShowDetailServiceCallback myListener = this.tvShowDetailServiceCallback.get();
            if(myListener != null){
                myListener.onPostExecute(new TvShowDetail());
            }
        }
    }

    private void onTvShowCreditPostExecuted(TvShowDetail tvShowDetail, TvShowCredit tvShowCredit) {
        Log.i("ASYN_TAG", "onTvShowCreditPostExecuted inside DemoAsynch class");
        if(tvShowDetail != null) {
            tvShowDetail.setCasts(tvShowCredit.getCasts());
            tvShowDetail.setCrews(tvShowCredit.getCrews());
            Log.i("CREDIT-POST", tvShowDetail.toString() );
            this.getTvShowReview(tvShowDetail);
        } else {
            TvShowDetailServiceCallback myListener = this.tvShowDetailServiceCallback.get();
            if(myListener != null){
                myListener.onPostExecute(new TvShowDetail());
            }
        }
    }

    private void onTvShowReviewPostExecuted(TvShowDetail tvShowDetail, TvShowReview tvShowReview) {
        Log.i("ASYN_TAG", "onTvShowReviewPostExecuted inside DemoAsynch class");
        TvShowDetailServiceCallback myListener = this.tvShowDetailServiceCallback.get();
        Log.i("REVIEW-POST0", tvShowDetail.toString() );
        if(tvShowDetail != null) {
            tvShowDetail.setReviews(tvShowReview.getReviews());
            if(myListener != null){
                Log.i("REVIEW-POST1", tvShowDetail.toString() );
                myListener.onPostExecute(tvShowDetail);
            }
        } else {
            if(myListener != null){
                myListener.onPostExecute(new TvShowDetail());
            }
        }
    }

    private void getTvShowDetail() {

        Call<TvShowDetail> call = tvShowServ.getTvShowDetail(tvShowModel.getId(), API_KEY, this.langId);
        call.enqueue(new Callback<TvShowDetail>() {
            @Override
            public void onResponse(Call<TvShowDetail> call, Response<TvShowDetail> response) {
                Log.d("RETROFIT-TV-DETAIL", response.body().getName()+":getTvShowDetail");
                onTvShowDetailPostExecuted(response.body());
            }

            @Override
            public void onFailure(Call<TvShowDetail> call, Throwable t) {
                Log.d("RETROFIT-TEST-ERROR", t.getMessage());
                TvShowDetailServiceCallback myListener = tvShowDetailServiceCallback.get();
                if(myListener != null){
                    myListener.onFailure(t.getMessage());
                }
            }
        });
    }

    private void getTvShowCredit(final TvShowDetail tvShowDetail) {
        Call<TvShowCredit> call = tvShowServ.getTvShowCredit(tvShowModel.getId(), API_KEY, this.langId);
        call.enqueue(new Callback<TvShowCredit>() {
            @Override
            public void onResponse(Call<TvShowCredit> call, Response<TvShowCredit> response) {
                Log.d("RETROFIT-TEST-CREDIT", response.body().toString());
                onTvShowCreditPostExecuted(tvShowDetail, response.body());
            }

            @Override
            public void onFailure(Call<TvShowCredit> call, Throwable t) {
                Log.d("RETROFIT-TEST-ERROR", t.getMessage());
                TvShowDetailServiceCallback myListener = tvShowDetailServiceCallback.get();
                if(myListener != null){
                    myListener.onFailure(t.getMessage());
                }
            }
        });
    }

    private void getTvShowReview(final TvShowDetail tvShowDetail) {

        Call<TvShowReview> call = tvShowServ.getTvShowReview(tvShowModel.getId(), API_KEY, this.langId, 1);
        call.enqueue(new Callback<TvShowReview>() {
            @Override
            public void onResponse(Call<TvShowReview> call, Response<TvShowReview> response) {
                Log.d("RETROFIT-TEST-REVIEW", response.body().toString());
                onTvShowReviewPostExecuted(tvShowDetail, response.body());
            }

            @Override
            public void onFailure(Call<TvShowReview> call, Throwable t) {
                Log.d("RETROFIT-TEST-ERROR", t.getMessage());
                TvShowDetailServiceCallback myListener = tvShowDetailServiceCallback.get();
                if(myListener != null){
                    myListener.onFailure(t.getMessage());
                }
            }
        });
    }

}

package com.si.uinam.mymoviecatalogueapi.services.asynchttp;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.si.uinam.mymoviecatalogueapi.helper.ApiHelper;
import com.si.uinam.mymoviecatalogueapi.model.TvShowModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.sql.Date;
import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

import static java.lang.Thread.sleep;

public class TvShowAsyncHttpService {

    private static String API_KEY = ApiHelper.getApiKey();
    private static String BASE_URL = ApiHelper.getBaseUrl();
    private static String POPULAR_LIST_TYPE = ApiHelper.getPopularListType();
    private static String NOW_PALYING_LIST_TYPE = ApiHelper.getNowPlayingListType();
    private WeakReference<AsyncTvShowHttpCallback> asyncTaskCallback;
    private String langId;

    public static TvShowAsyncHttpService create(AsyncTvShowHttpCallback asyncTvShowHttpCallback) {
        return new TvShowAsyncHttpService(asyncTvShowHttpCallback);
    }

    public TvShowAsyncHttpService setInputOption(String languageId) {
        this.langId = languageId;
        return this;
    }

    public void executeService() {
        this.execute(this.langId);
    }

    private TvShowAsyncHttpService(AsyncTvShowHttpCallback asyncTvShowHttpCallback) {
        this.asyncTaskCallback = new WeakReference<>(asyncTvShowHttpCallback);
    }

    private void execute(String languageId) {

        AsyncHttpClient client = new AsyncHttpClient();
        String url = ApiHelper.getPopularTvShowListUrl(languageId);

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                try {
                    ArrayList<TvShowModel> tvShowListModels = new ArrayList<>();
                    sleep(5000);
                    Log.d("TES-VIEW-MODEL-TV-SHOW", "Trying Parsing");
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("results");
                    for (int i = 0; i < list.length(); i++) {
                        JSONObject tvShowJson = list.getJSONObject(i);
                        TvShowModel tvShowModel = new TvShowModel();
                        tvShowModel.setOriginal_name(tvShowJson.getString("original_name"));

                        ArrayList<Integer> listGenreId = new ArrayList<Integer>();
                        JSONArray jsonArrayGenre = tvShowJson.getJSONArray("genre_ids");
                        if (jsonArrayGenre != null && jsonArrayGenre.length() > 0) {
                            int lenGenre = jsonArrayGenre.length();
                            for (int l=0;l<lenGenre;l++){
                                listGenreId.add(jsonArrayGenre.getInt(l));
                            }
                        }
                        tvShowModel.setGenre_ids(listGenreId);

                        tvShowModel.setName(tvShowJson.getString("name"));
                        tvShowModel.setPopularity(tvShowJson.getDouble("popularity"));

                        ArrayList<String> listCountry = new ArrayList<String>();
                        JSONArray jsonArrayCountry = tvShowJson.getJSONArray("origin_country");
                        if (jsonArrayCountry != null && jsonArrayCountry.length() > 0) {
                            int len = jsonArrayCountry.length();
                            for (int k=0;k<len;k++){
                                listCountry.add(jsonArrayCountry.getString(k));
                            }
                        }
                        tvShowModel.setOrigin_country(listCountry);

                        tvShowModel.setVote_count(tvShowJson.getDouble("vote_count"));
                        tvShowModel.setFirst_air_date(Date.valueOf(tvShowJson.getString("first_air_date")));
                        tvShowModel.setBackdrop_path(tvShowJson.getString("backdrop_path"));
                        tvShowModel.setOriginal_language(tvShowJson.getString("original_language"));
                        tvShowModel.setId(tvShowJson.getInt("id"));
                        tvShowModel.setVote_average(tvShowJson.getDouble("vote_average"));
                        tvShowModel.setOverview(tvShowJson.getString("overview"));
                        tvShowModel.setPoster_path(tvShowJson.getString("poster_path"));

                        Log.d("TES-VIEW-MODEL-TITLE-TV", tvShowJson.getString("name"));

                        tvShowListModels.add(tvShowModel);
                    }

                    //tvShowCollection.postValue(tvShowListModels);
                    onPostExecute(tvShowListModels);

                    Log.d("TES-VIEW-MODEL-TV-SHOW", "Inside view model setMovieList: " + tvShowListModels.size());
                    Log.d("TES-VIEW-MODEL-TV-SHOW", "tvShowListModels.postValue");
                } catch (Exception e) {
                    Log.d("TES-VIEW-MODEL-TV-SHOW", e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("TES-VIEW-MODEL-onFail", error.getMessage());
            }
        });

    }

    private void onPostExecute(ArrayList<TvShowModel> tvShowModelArrayItems) {
        Log.i("ASYN_TAG", "onPostExecute inside DemoAsynch class");
        AsyncTvShowHttpCallback myListener = this.asyncTaskCallback.get();
        if(myListener != null){
            myListener.onPostExecute(tvShowModelArrayItems);
        }
    }

}

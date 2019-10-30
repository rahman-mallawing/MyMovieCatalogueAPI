package com.si.uinam.mymoviecatalogueapi.viewmodel;

import android.content.Context;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.si.uinam.mymoviecatalogueapi.helper.LocaleHelper;
import com.si.uinam.mymoviecatalogueapi.model.TvShowModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Date;
import java.util.ArrayList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import cz.msebera.android.httpclient.Header;

import static java.lang.Thread.sleep;

public class TvShowViewModel extends ViewModel {

    private static final String API_KEY = "2f766223589e24c61b0aecdf89ec841d";
    private String langId = "en-US";
    private String listType = "popular";
    private MutableLiveData<ArrayList<TvShowModel>> tvShowCollection = new MutableLiveData<>();

    public void loadTvShow(Context context){
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "https://api.themoviedb.org/3/tv/" + getListType() + "?api_key=" + API_KEY + "&language=" + getLangId(context) + "&page=1";

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                try {
                    ArrayList<TvShowModel> tvShowListModels = new ArrayList<>();
                    sleep(100);
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

                    tvShowCollection.postValue(tvShowListModels);

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

    private String getListType() {
        return listType;
    }

    private String getLangId(Context context) {
        String localeId = LocaleHelper.getLocale(context);
        if(localeId.equals("in")){
            return "id-ID";
        }
        return langId;
    }

    public LiveData<ArrayList<TvShowModel>> getTvShowCollection() {
        return tvShowCollection;
    }

}

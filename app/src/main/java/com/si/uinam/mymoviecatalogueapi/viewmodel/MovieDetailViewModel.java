package com.si.uinam.mymoviecatalogueapi.viewmodel;

import android.content.Context;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.si.uinam.mymoviecatalogueapi.helper.LocaleHelper;
import com.si.uinam.mymoviecatalogueapi.model.MovieDetailModel;
import com.si.uinam.mymoviecatalogueapi.model.MovieModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Date;
import java.util.ArrayList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import cz.msebera.android.httpclient.Header;

import static java.lang.Thread.sleep;

public class MovieDetailViewModel extends ViewModel {

    private static final String API_KEY = "2f766223589e24c61b0aecdf89ec841d";
    private String langId = "en-US";
    private static final String REVIEW_LIST_TYPE = "reviews";
    private static final String CREDIT_LIST_TYPE = "credits";
    private MutableLiveData<ArrayList<MovieDetailModel>> movieDetailInstance = new MutableLiveData<>();

    public void loadMovieDetail(Context context, MovieModel movieModel) {
        //credit https://api.themoviedb.org/3/movie/475557/credits?api_key=2f766223589e24c61b0aecdf89ec841d
        // review https://api.themoviedb.org/3/movie/475557/reviews?api_key=2f766223589e24c61b0aecdf89ec841d&language=en-US&page=1
        // detail https://api.themoviedb.org/3/movie/558?api_key=2f766223589e24c61b0aecdf89ec841d&language=en-US
        AsyncHttpClient client = new AsyncHttpClient();
        final MovieDetailModel movieDetailModel = new MovieDetailModel(movieModel);
        String urlDetail = "https://api.themoviedb.org/3/movie/" + movieModel.getId() + "?api_key=" + API_KEY + "&language=" + getLangId(context);
        String urlCredit = "https://api.themoviedb.org/3/movie/" + movieModel.getId() + "/" + CREDIT_LIST_TYPE + "?api_key=" + API_KEY;
        String urlReview = "https://api.themoviedb.org/3/movie/" + movieModel.getId() + "/" + REVIEW_LIST_TYPE + "?api_key=" + API_KEY + "&language=" + getLangId(context) + "&page=1";

        client.get(urlDetail, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {

                    sleep(9000);
                    Log.d("TES-MOVIE-DETAIL", "4. AFTER SLEEP");
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    movieDetailModel.setBudget(responseObject.getDouble("budget"));
                    movieDetailModel.setHomepage(responseObject.getString("homepage"));
                    movieDetailModel.setImdb_id(responseObject.getString("imdb_id"));
                    movieDetailModel.setStatus(responseObject.getString("status"));
                    movieDetailModel.setTagline(responseObject.getString("tagline"));

                    ArrayList<String> listGenre = new ArrayList<String>();
                    JSONArray jsonArrayGenre = responseObject.getJSONArray("genres");
                    if (jsonArrayGenre != null) {
                        int len = jsonArrayGenre.length();
                        for (int k=0;k<len;k++){
                            listGenre.add(jsonArrayGenre.getJSONObject(k).getString("name"));
                        }
                    }
                    movieDetailModel.setGenres(listGenre);
                    Log.d("TES-VIEW-MOVIE-DETAIL", "TES: "+responseObject.getString("tagline"));
                } catch (Exception e) {
                    Log.d("TES-VIEW-MODEL-Except", e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("TES-VIEW-MODEL-onFail", error.getMessage());
            }
        });

        //request credit

        client.get(urlCredit, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    sleep(9000);
                    Log.d("CREDIT-MOVIE-DETAIL", "4. AFTER SLEEP");
                    MovieDetailModel.Cast cast = movieDetailModel.new Cast();
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);



                    movieDetailModel.setBudget(responseObject.getDouble("budget"));
                    movieDetailModel.setHomepage(responseObject.getString("homepage"));
                    movieDetailModel.setImdb_id(responseObject.getString("imdb_id"));
                    movieDetailModel.setStatus(responseObject.getString("status"));
                    movieDetailModel.setTagline(responseObject.getString("tagline"));

                    ArrayList<String> listGenre = new ArrayList<String>();
                    JSONArray jsonArrayGenre = responseObject.getJSONArray("genres");
                    if (jsonArrayGenre != null) {
                        int len = jsonArrayGenre.length();
                        for (int k=0;k<len;k++){
                            listGenre.add(jsonArrayGenre.getJSONObject(k).getString("name"));
                        }
                    }
                    movieDetailModel.setGenres(listGenre);
                    Log.d("TES-VIEW-MOVIE-DETAIL", "TES: "+responseObject.getString("tagline"));
                } catch (Exception e) {
                    Log.d("TES-VIEW-MODEL-Except", e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("VIEW-MOVIE-DETAIL-Fail", error.getMessage());
            }
        });

    }

    private String getLangId(Context context) {
        String localeId = LocaleHelper.getLocale(context);
        if(localeId.equals("in")){
            return "id-ID";
        }
        return langId;
    }

    public LiveData<ArrayList<MovieDetailModel>> getMovieDetailInstance() {
        return movieDetailInstance;
    }
}

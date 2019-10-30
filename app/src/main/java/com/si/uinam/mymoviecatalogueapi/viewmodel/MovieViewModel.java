package com.si.uinam.mymoviecatalogueapi.viewmodel;

import android.content.Context;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.si.uinam.mymoviecatalogueapi.helper.LocaleHelper;
import com.si.uinam.mymoviecatalogueapi.model.MovieModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import cz.msebera.android.httpclient.Header;

import static java.lang.Thread.sleep;

public class MovieViewModel extends ViewModel {

    private static final String API_KEY = "2f766223589e24c61b0aecdf89ec841d";
    private String langId = "en-US";
    private String listType = "popular";
    private MutableLiveData<ArrayList<MovieModel>> movieCollection = new MutableLiveData<>();

    public void loadMovieList(Context context){
        Log.d("TES-VIEW-MODEL", "2. Load Connect internet API");
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<MovieModel> listItems = new ArrayList<>();
        //2f766223589e24c61b0aecdf89ec841d&language=en-US&page=1
        String url = "https://api.themoviedb.org/3/movie/" + getListType() + "?api_key=" + API_KEY + "&language=" + getLangId(context) + "&page=1";

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Log.d("TES-VIEW-MODEL", "3. SUCCESS Connect internet API");
                try {
                    //ArrayList<MovieModel> listItems = new ArrayList<>();

                    sleep(19000);
                    Log.d("TES-VIEW-MODEL", "4. AFTER SLEEP");
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("results");
                    for (int i = 0; i < list.length(); i++) {
                        JSONObject movieJson = list.getJSONObject(i);
                        MovieModel movieModel = new MovieModel();
                        movieModel.setPopularity(movieJson.getDouble("popularity"));
                        movieModel.setVote_count(movieJson.getDouble("vote_count"));
                        movieModel.setVideo(movieJson.getBoolean("video"));
                        movieModel.setPoster_path(movieJson.getString("poster_path"));
                        movieModel.setId(movieJson.getInt("id"));
                        movieModel.setAdult(movieJson.getBoolean("adult"));
                        movieModel.setBackdrop_path(movieJson.getString("backdrop_path"));
                        movieModel.setOriginal_language(movieJson.getString("original_language"));
                        movieModel.setOriginal_title(movieJson.getString("original_title"));

                        ArrayList<Integer> listGenreId = new ArrayList<Integer>();
                        JSONArray jsonArrayGenre = movieJson.getJSONArray("genre_ids");
                        if (jsonArrayGenre != null && jsonArrayGenre.length() > 0) {
                            int lenGenre = jsonArrayGenre.length();
                            for (int l=0;l<lenGenre;l++){
                                listGenreId.add(jsonArrayGenre.getInt(l));
                            }
                        }
                        movieModel.setGenre_ids(listGenreId);

                        movieModel.setTitle(movieJson.getString("title"));
                        Log.d("TES-VIEW-MODEL-TITLE", movieJson.getString("title"));
                        movieModel.setVote_average(movieJson.getDouble("vote_average"));
                        movieModel.setOverview(movieJson.getString("overview"));
                        movieModel.setRelease_date(Date.valueOf(movieJson.getString("release_date")));
                        listItems.add(movieModel);
                    }
                    //Log.d("TES-VIEW-MODEL", "50. sebelum set or post: " + listItems.size());

                    //movieCollection.postValue(listItems);

                    movieCollection.setValue(listItems);

                    Log.d("TES-VIEW-MODEL", "5. Inside view model setMovieList: " + listItems.size());
                    //Log.d("TES-VIEW-MODEL", "5a. Inside view model setMovieList: " + movieList.getValue().toString());
                    Log.d("TES-VIEW-MODEL", "6. movieList.postValue");
                } catch (Exception e) {
                    Log.d("TES-VIEW-MODEL-Except", e.getMessage());
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

    public LiveData<ArrayList<MovieModel>> getMovieCollection() {
        return movieCollection;
    }
}

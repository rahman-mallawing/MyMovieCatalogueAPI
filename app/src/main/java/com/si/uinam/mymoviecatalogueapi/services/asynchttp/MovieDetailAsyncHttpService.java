package com.si.uinam.mymoviecatalogueapi.services.asynchttp;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.si.uinam.mymoviecatalogueapi.helper.ApiHelper;
import com.si.uinam.mymoviecatalogueapi.model.MovieDetailModel;
import com.si.uinam.mymoviecatalogueapi.model.MovieModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Objects;

import cz.msebera.android.httpclient.Header;
import static java.lang.Thread.sleep;

public class MovieDetailAsyncHttpService {

    private static String API_KEY = ApiHelper.getApiKey();
    private static String CREDIT_LIST_TYPE = ApiHelper.getCreditListType();
    private static String REVIEW_LIST_TYPE = ApiHelper.getReviewListType();
    private WeakReference<AsyncMovieDetailHttpCallback> asyncTaskCallback;
    private MovieModel movieModel;
    private String langId;

    public static MovieDetailAsyncHttpService create(AsyncMovieDetailHttpCallback asyncMovieDetailHttpCallback) {
        return new MovieDetailAsyncHttpService(asyncMovieDetailHttpCallback);
    }

    public MovieDetailAsyncHttpService setInputOption(MovieModel mModel, String languageId) {
        this.movieModel = mModel;
        this.langId = languageId;
        return this;
    }

    public void executeService() {
        this.execute(this.movieModel, this.langId);
    }

    private MovieDetailAsyncHttpService(AsyncMovieDetailHttpCallback asyncMovieDetailHttpCallback) {
        this.asyncTaskCallback = new WeakReference<>(asyncMovieDetailHttpCallback);
    }

    private void execute(MovieModel movieModel, String languageId) {

        //credit https://api.themoviedb.org/3/movie/475557/credits?api_key=2f766223589e24c61b0aecdf89ec841d
        // review https://api.themoviedb.org/3/movie/475557/reviews?api_key=2f766223589e24c61b0aecdf89ec841d&language=en-US&page=1
        // detail https://api.themoviedb.org/3/movie/558?api_key=2f766223589e24c61b0aecdf89ec841d&language=en-US
        AsyncHttpClient client = new AsyncHttpClient();

        final MovieDetailModel movieDetailModel = new MovieDetailModel(movieModel);
        String urlDetail = "https://api.themoviedb.org/3/movie/" + movieModel.getId() + "?api_key=" + API_KEY + "&language=" + languageId;
        String urlCredit = "https://api.themoviedb.org/3/movie/" + movieModel.getId() + "/" + CREDIT_LIST_TYPE + "?api_key=" + API_KEY;
        String urlReview = "https://api.themoviedb.org/3/movie/" + movieModel.getId() + "/" + REVIEW_LIST_TYPE + "?api_key=" + API_KEY + "&language=" + languageId + "&page=1";

        client.get(urlDetail, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {

                    sleep(100);
                    Log.d("TES-MOVIE-DETAIL", "4. AFTER SLEEP");
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    movieDetailModel.setBudget(responseObject.getDouble("budget"));
                    movieDetailModel.setHomepage(responseObject.getString("homepage"));
                    movieDetailModel.setImdb_id(responseObject.getString("imdb_id"));
                    movieDetailModel.setStatus(responseObject.getString("status"));
                    movieDetailModel.setTagline(responseObject.getString("tagline"));

                    ArrayList<String> listGenre = new ArrayList<>();
                    JSONArray jsonArrayGenre = responseObject.getJSONArray("genres");
                    if (jsonArrayGenre.length() > 0) {
                        int len = jsonArrayGenre.length();
                        for (int k=0;k<len;k++){
                            listGenre.add(jsonArrayGenre.getJSONObject(k).getString("name"));
                        }
                    }
                    movieDetailModel.setGenres(listGenre);
                    Log.d("TES-VIEW-MOVIE-DETAIL", "TES: "+responseObject.getString("tagline"));
                } catch (Exception e) {
                    Log.d("TES-VIEW-MODEL-Except", Objects.requireNonNull(e.getMessage()));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("TES-VIEW-MODEL-onFail", Objects.requireNonNull(error.getMessage()));
            }
        });

        //request credit

        client.get(urlCredit, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    sleep(200);
                    Log.d("CREDIT-MOVIE-DETAIL", "4. AFTER SLEEP");
                    ArrayList<MovieDetailModel.Cast> castArrayList = new ArrayList<>();
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray castList = responseObject.getJSONArray("cast");

                    for (int i = 0; i < castList.length(); i++) {
                        JSONObject castJson = castList.getJSONObject(i);
                        MovieDetailModel.Cast cast = movieDetailModel.new Cast();
                        cast.setCast_id(castJson.getInt("cast_id"));
                        cast.setCharacter(castJson.getString("character"));
                        cast.setCredit_id(castJson.getString("credit_id"));
                        cast.setGender(castJson.getInt("gender"));
                        cast.setId(castJson.getInt("id"));
                        cast.setName(castJson.getString("name"));
                        cast.setOrder(castJson.getInt("order"));
                        cast.setProfile_path(castJson.getString("profile_path"));
                        Log.d("TES-VIEW-MODEL-DETAIL", castJson.getString("name"));
                        castArrayList.add(cast);
                    }

                    movieDetailModel.setCasts(castArrayList);
                    // crew
                    ArrayList<MovieDetailModel.Crew> crewArrayList = new ArrayList<>();
                    JSONArray crewList = responseObject.getJSONArray("crew");
                    for (int i = 0; i < crewList.length(); i++) {
                        JSONObject crewJson = crewList.getJSONObject(i);
                        MovieDetailModel.Crew crew = movieDetailModel.new Crew();
                        crew.setCredit_id(crewJson.getString("credit_id"));
                        crew.setDepartment(crewJson.getString("department"));
                        crew.setGender(crewJson.getInt("gender"));
                        crew.setId(crewJson.getInt("id"));
                        crew.setJob(crewJson.getString("job"));
                        crew.setName(crewJson.getString("name"));
                        crew.setProfile_path(crewJson.getString("profile_path"));
                        Log.d("TES-VIEW-MODEL-DETAIL", crewJson.getString("name"));
                        crewArrayList.add(crew);
                    }

                    movieDetailModel.setCrews(crewArrayList);

                } catch (Exception e) {
                    Log.d("TES-VIEW-MODEL-Except", Objects.requireNonNull(e.getMessage()));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("VIEW-MOVIE-DETAIL-Fail", Objects.requireNonNull(error.getMessage()));
            }
        });

        //request review

        client.get(urlReview, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {

                    sleep(300);
                    Log.d("TES-MOVIE-DETAIL", "4. AFTER SLEEP -> REVIEW");
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray reviewArrayJson = responseObject.getJSONArray("results");

                    if (reviewArrayJson.length() > 0) {
                        MovieDetailModel.Review review = movieDetailModel.new Review();
                        String author = reviewArrayJson.getJSONObject(0).getString("author");
                        String content = reviewArrayJson.getJSONObject(0).getString("content");
                        review.setAuthor(author);
                        review.setContent(content);
                        movieDetailModel.setReview(review);
                    }
                    //movieDetailInstance.postValue(movieDetailModel);
                    onPostExecute(movieDetailModel);
                    Log.d("VIEW-MOVIE-DETAIL-REV", "TES AKHIR ");
                } catch (Exception e) {
                    Log.d("TES-VIEW-MODEL-Except", Objects.requireNonNull(e.getMessage()));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("VIEW-MOVIE-DETAIL-Fail", Objects.requireNonNull(error.getMessage()));
            }
        });


    }

    private void onPostExecute(MovieDetailModel movieDetailModel) {
        Log.i("ASYN_TAG", "onPostExecute inside DemoAsynch class");
        AsyncMovieDetailHttpCallback myListener = this.asyncTaskCallback.get();
        if(myListener != null){
            myListener.onPostExecute(movieDetailModel);
        }
    }

}

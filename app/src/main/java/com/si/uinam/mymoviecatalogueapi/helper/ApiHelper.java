package com.si.uinam.mymoviecatalogueapi.helper;

import com.si.uinam.mymoviecatalogueapi.model.MovieDetailModel;
import com.si.uinam.mymoviecatalogueapi.model.MovieModel;
import com.si.uinam.mymoviecatalogueapi.services.retrofit.MovieCredit;
import com.si.uinam.mymoviecatalogueapi.services.retrofit.MovieDetail;
import com.si.uinam.mymoviecatalogueapi.services.retrofit.MovieReview;

import java.util.ArrayList;

public class ApiHelper {

    private static final String API_KEY = "2f766223589e24c61b0aecdf89ec841d";
    private static final String ENGLISH_LANGUAGE_ID = "en-US";
    private static final String INDONESIAN_LANGUAGE_ID = "id-ID";
    private static final String REVIEW_LIST_TYPE = "reviews";
    private static final String CREDIT_LIST_TYPE = "credits";
    private static final String POPULAR_LIST_TYPE = "popular";
    private static final String UPCOMING_LIST_TYPE = "upcoming";
    private static final String NOW_PLAYING_LIST_TYPE = "now_playing";

    public static String getApiKey() {
        return API_KEY;
    }

    public static String getReviewListType() {
        return REVIEW_LIST_TYPE;
    }

    public static String getCreditListType() {
        return CREDIT_LIST_TYPE;
    }

    public static String getPopularListType() {
        return POPULAR_LIST_TYPE;
    }

    public static String getUpcomingListType() {
        return UPCOMING_LIST_TYPE;
    }

    public static String getNowPlayingListType() {
        return NOW_PLAYING_LIST_TYPE;
    }

    public static String getLanguageId(String localeId) {
        return localeId.equalsIgnoreCase("in")? INDONESIAN_LANGUAGE_ID :
                ENGLISH_LANGUAGE_ID;
    }

    public static MovieDetailModel mapMovieDetailToMovieDetailModel(MovieModel movieModel, MovieDetail movieDetail, MovieCredit movieCredit, MovieReview movieReview) {
        MovieDetailModel mdm = new MovieDetailModel(movieModel);
        //ArrayList<Mo>
        //mdm.setCasts(movieCredit.getCasts());
        return mdm;
    }
}

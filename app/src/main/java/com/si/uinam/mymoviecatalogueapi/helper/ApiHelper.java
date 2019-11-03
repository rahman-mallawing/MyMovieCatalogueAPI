package com.si.uinam.mymoviecatalogueapi.helper;

public class ApiHelper {

    private static final String BASE_URL = "https://api.themoviedb.org";
    private static final String IMG_BASE_URL = "https://image.tmdb.org/t/p/w500";
    private static final String IMG_POSTER_PLACEHOLDER = "https://via.placeholder.com/500x750.jpg";
    private static final String IMG_CAST_PLACEHOLDER = "https://via.placeholder.com/276x350.jpg";
    private static final String API_KEY = "2f766223589e24c61b0aecdf89ec841d";
    private static final String ENGLISH_LANGUAGE_ID = "en-US";
    private static final String INDONESIAN_LANGUAGE_ID = "id-ID";
    private static final String REVIEW_LIST_TYPE = "reviews";
    private static final String CREDIT_LIST_TYPE = "credits";
    private static final String POPULAR_LIST_TYPE = "popular";
    //private static final String UPCOMING_LIST_TYPE = "upcoming";
    private static final String NOW_PLAYING_LIST_TYPE = "now_playing";

    public static String getPopularTvShowListUrl(String languageId) {
        return BASE_URL + "/3/tv/" + POPULAR_LIST_TYPE + "?api_key=" + API_KEY + "&language=" + languageId + "&page=1";
    }

    public static String getPopularMovieListUrl(String languageId) {
        return BASE_URL + "/3/movie/" + POPULAR_LIST_TYPE + "?api_key=" + API_KEY + "&language=" + languageId + "&page=1";
    }

    public static String getImgPosterPlaceholder() {
        return IMG_POSTER_PLACEHOLDER;
    }

    public static String getImgCastPlaceholder() {
        return IMG_CAST_PLACEHOLDER;
    }

    public static String getBaseUrl() {
        return BASE_URL;
    }

    public static String getImgBaseUrl() {
        return IMG_BASE_URL;
    }

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

    public static String getNowPlayingListType() {
        return NOW_PLAYING_LIST_TYPE;
    }

    public static String getLanguageId(String localeId) {
        return localeId.equalsIgnoreCase("in")? INDONESIAN_LANGUAGE_ID :
                ENGLISH_LANGUAGE_ID;
    }

}

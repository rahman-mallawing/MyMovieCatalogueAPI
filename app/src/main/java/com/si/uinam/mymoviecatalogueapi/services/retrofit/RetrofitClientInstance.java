package com.si.uinam.mymoviecatalogueapi.services.retrofit;

import com.si.uinam.mymoviecatalogueapi.helper.ApiHelper;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {

    //url = https://api.themoviedb.org/3/movie/558?api_key=2f766223589e24c61b0aecdf89ec841d&language=en-US
    private static Retrofit retrofit;
    private static final String BASE_URL = ApiHelper.getBaseUrl();

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}

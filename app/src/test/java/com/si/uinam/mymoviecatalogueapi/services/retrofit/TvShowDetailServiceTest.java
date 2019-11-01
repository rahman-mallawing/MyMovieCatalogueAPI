package com.si.uinam.mymoviecatalogueapi.services.retrofit;

import org.junit.Test;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

import static org.junit.Assert.*;

public class TvShowDetailServiceTest {

    @Test
    public void executeService() {
        int id = 1412;
        String API_KEY = "2f766223589e24c61b0aecdf89ec841d";
        String langId = "id-ID";
        String langEn = "en-US";
        System.out.println("movieDetail.title");
        TvShowDetailInterface tvServ = RetrofitClientInstance.getRetrofitInstance()
                .create(TvShowDetailInterface.class);
        Call<TvShowDetail> call = tvServ.getTvShowDetail(id, API_KEY, langEn);
        try {
            Response<TvShowDetail> response = call.execute();
            TvShowDetail tvShowDetail = response.body();
            System.out.println(tvShowDetail.toString());
            assertTrue(response.isSuccessful() && printObject(tvShowDetail.getCreators()));
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean printObject(Object object) {
        System.out.println(object.toString());
        return true;
    }

}
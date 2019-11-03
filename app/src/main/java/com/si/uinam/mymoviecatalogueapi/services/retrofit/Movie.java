package com.si.uinam.mymoviecatalogueapi.services.retrofit;

import com.google.gson.annotations.SerializedName;
import com.si.uinam.mymoviecatalogueapi.model.MovieModel;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class Movie {

    @SerializedName("total_results")
    private int total_results;

    @SerializedName("total_pages")
    private int total_pages;

    @SerializedName("page")
    private int page;

    @SerializedName("results")
    protected ArrayList<MovieModel> movieModels;


    public Movie(int total_results, int total_pages, int page, ArrayList<MovieModel> movieModels) {
        this.total_results = total_results;
        this.total_pages = total_pages;
        this.page = page;
        this.movieModels = movieModels;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public ArrayList<MovieModel> getMovieModels() {
        return movieModels;
    }

    public void setMovieModels(ArrayList<MovieModel> movieModels) {
        this.movieModels = movieModels;
    }
}

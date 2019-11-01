package com.si.uinam.mymoviecatalogueapi.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;

public class TvShowModel implements Parcelable {
    protected double popularity;
    protected double vote_count;
    protected String poster_path;
    protected int id;
    protected String backdrop_path;
    protected String original_language;
    protected ArrayList<String> origin_country;
    protected String original_name;
    protected ArrayList<Integer> genre_ids;
    protected String name;
    protected double vote_average;
    protected String overview;
    protected Date first_air_date;

    public ArrayList<String> getOrigin_country() {
        return origin_country;
    }

    public void setOrigin_country(ArrayList<String> origin_country) {
        this.origin_country = origin_country;
    }

    public ArrayList<Integer> getGenre_ids() {
        return genre_ids;
    }

    public void setGenre_ids(ArrayList<Integer> genre_ids) {
        this.genre_ids = genre_ids;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public double getVote_count() {
        return vote_count;
    }

    public void setVote_count(double vote_count) {
        this.vote_count = vote_count;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getOriginal_name() {
        return original_name;
    }

    public void setOriginal_name(String original_name) {
        this.original_name = original_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Date getFirst_air_date() {
        return first_air_date;
    }

    public void setFirst_air_date(Date first_air_date) {
        this.first_air_date = first_air_date;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(this.popularity);
        dest.writeDouble(this.vote_count);
        dest.writeString(this.poster_path);
        dest.writeInt(this.id);
        dest.writeString(this.backdrop_path);
        dest.writeString(this.original_language);
        dest.writeStringList(this.origin_country);
        dest.writeString(this.original_name);
        dest.writeList(this.genre_ids);
        dest.writeString(this.name);
        dest.writeDouble(this.vote_average);
        dest.writeString(this.overview);
        dest.writeLong(this.first_air_date != null ? this.first_air_date.getTime() : -1);
    }

    public TvShowModel() {
    }

    protected TvShowModel(Parcel in) {
        this.popularity = in.readDouble();
        this.vote_count = in.readDouble();
        this.poster_path = in.readString();
        this.id = in.readInt();
        this.backdrop_path = in.readString();
        this.original_language = in.readString();
        this.origin_country = in.createStringArrayList();
        this.original_name = in.readString();
        this.genre_ids = new ArrayList<Integer>();
        in.readList(this.genre_ids, Integer.class.getClassLoader());
        this.name = in.readString();
        this.vote_average = in.readDouble();
        this.overview = in.readString();
        long tmpFirst_air_date = in.readLong();
        this.first_air_date = tmpFirst_air_date == -1 ? null : new Date(tmpFirst_air_date);
    }

    public static final Parcelable.Creator<TvShowModel> CREATOR = new Parcelable.Creator<TvShowModel>() {
        @Override
        public TvShowModel createFromParcel(Parcel source) {
            return new TvShowModel(source);
        }

        @Override
        public TvShowModel[] newArray(int size) {
            return new TvShowModel[size];
        }
    };
}

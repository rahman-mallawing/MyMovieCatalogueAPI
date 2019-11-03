package com.si.uinam.mymoviecatalogueapi.services.retrofit;

import com.google.gson.annotations.SerializedName;
import com.si.uinam.mymoviecatalogueapi.helper.ApiHelper;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;

public class TvShowDetail {

    public TvShowDetail() {
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        String newLine = System.getProperty("line.separator");

        result.append( this.getClass().getName() );
        result.append( " Object {" );
        result.append(newLine);

        //determine fields declared in this class only (no fields of superclass)
        Field[] fields = this.getClass().getDeclaredFields();

        //print field names paired with their values
        for ( Field field : fields  ) {
            result.append("  ");
            try {
                result.append( field.getName() );
                result.append(": ");
                //requires access to private field:
                result.append( field.get(this) );
            } catch ( IllegalAccessException ex ) {
                System.out.println(ex);
            }
            result.append(newLine);
        }
        result.append("}");

        return result.toString();
    }

    @SerializedName("backdrop_path")
    protected String backdrop_path;

    @SerializedName("created_by")
    protected ArrayList<Creator> creators;

    @SerializedName("first_air_date")
    protected Date first_air_date;

    @SerializedName("genres")
    protected ArrayList<Genre> genres;

    @SerializedName("homepage")
    protected String homepage;

    @SerializedName("id")
    protected int id;

    @SerializedName("in_production")
    protected boolean in_production;

    @SerializedName("last_air_date")
    protected Date last_air_date;

    @SerializedName("name")
    protected String name;

    @SerializedName("number_of_episodes")
    protected int number_of_episodes;

    @SerializedName("number_of_seasons")
    protected int number_of_seasons;

    @SerializedName("original_language")
    protected String original_language;

    @SerializedName("original_name")
    protected String original_name;

    @SerializedName("overview")
    protected String overview;

    @SerializedName("popularity")
    protected double popularity;

    @SerializedName("poster_path")
    protected String poster_path;

    @SerializedName("production_companies")
    protected ArrayList<Company> companies;

    @SerializedName("status")
    protected String status;

    @SerializedName("type")
    protected String type;

    @SerializedName("vote_average")
    protected double vote_average;

    @SerializedName("vote_count")
    protected double vote_count;

    private ArrayList<TvShowCredit.Cast> casts;
    private ArrayList<TvShowCredit.Crew> crews;
    private ArrayList<TvShowReview.Review> reviews;

    public TvShowDetail(String backdrop_path, ArrayList<Creator> creators, Date first_air_date, ArrayList<Genre> genres, String homepage, int id, boolean in_production, Date last_air_date, String name, int number_of_episodes, int number_of_seasons, String original_language, String original_name, String overview, double popularity, String poster_path, ArrayList<Company> companies, String status, String type, double vote_average, double vote_count) {
        this.backdrop_path = backdrop_path;
        this.creators = creators;
        this.first_air_date = first_air_date;
        this.genres = genres;
        this.homepage = homepage;
        this.id = id;
        this.in_production = in_production;
        this.last_air_date = last_air_date;
        this.name = name;
        this.number_of_episodes = number_of_episodes;
        this.number_of_seasons = number_of_seasons;
        this.original_language = original_language;
        this.original_name = original_name;
        this.overview = overview;
        this.popularity = popularity;
        this.poster_path = poster_path;
        this.companies = companies;
        this.status = status;
        this.type = type;
        this.vote_average = vote_average;
        this.vote_count = vote_count;
    }

    public TvShowCredit.Cast getCast(int index) {
        TvShowCredit.Cast cast;
        if(this.casts == null || index >= this.casts.size()) {
            cast = new TvShowCredit().new Cast();
            cast.setProfile_path(ApiHelper.getImgPosterPlaceholder());
            cast.setName("No data");
            cast.setCharacter("No data");
            return cast;
        }else{
            cast = this.casts.get(index);
            cast.setProfile_path(ApiHelper.getImgBaseUrl() + cast.getProfile_path());
            return cast;
        }
    }

    public TvShowCredit.Crew getCrew(int index) {
        TvShowCredit.Crew crew;
        if(this.crews == null || index >= this.crews.size()) {
            crew = new TvShowCredit().new Crew();
            crew.setName("No data");
            crew.setJob("No data");
            crew.setProfile_path(ApiHelper.getImgPosterPlaceholder());
            return crew;
        }else{
            crew = this.crews.get(index);
            crew.setProfile_path(ApiHelper.getImgBaseUrl() + crew.getProfile_path());
            return crew;
        }
    }

    public TvShowReview.Review getReview(int index) {
        TvShowReview.Review review;
        if(this.reviews == null || index >= this.reviews.size()) {
            review = new TvShowReview().new Review();
            review.setAuthor("No data");
            review.setContent("No data");
            return review;
        }else{
            review = this.reviews.get(index);
            return review;
        }
    }

    public String getGenreName() {
        if (this.genres == null) return "";
        String gen = "";
        for(TvShowDetail.Genre genre : this.genres) {
            gen = gen + genre.getName() + " ";
        }
        return  gen;
    }

    public ArrayList<TvShowCredit.Cast> getCasts() {
        return casts;
    }

    public void setCasts(ArrayList<TvShowCredit.Cast> casts) {
        this.casts = casts;
    }

    public ArrayList<TvShowCredit.Crew> getCrews() {
        return crews;
    }

    public void setCrews(ArrayList<TvShowCredit.Crew> crews) {
        this.crews = crews;
    }

    public ArrayList<TvShowReview.Review> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<TvShowReview.Review> reviews) {
        this.reviews = reviews;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public ArrayList<Creator> getCreators() {
        return creators;
    }

    public void setCreators(ArrayList<Creator> creators) {
        this.creators = creators;
    }

    public Date getFirst_air_date() {
        return first_air_date;
    }

    public void setFirst_air_date(Date first_air_date) {
        this.first_air_date = first_air_date;
    }

    public ArrayList<Genre> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<Genre> genres) {
        this.genres = genres;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isIn_production() {
        return in_production;
    }

    public void setIn_production(boolean in_production) {
        this.in_production = in_production;
    }

    public Date getLast_air_date() {
        return last_air_date;
    }

    public void setLast_air_date(Date last_air_date) {
        this.last_air_date = last_air_date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber_of_episodes() {
        return number_of_episodes;
    }

    public void setNumber_of_episodes(int number_of_episodes) {
        this.number_of_episodes = number_of_episodes;
    }

    public int getNumber_of_seasons() {
        return number_of_seasons;
    }

    public void setNumber_of_seasons(int number_of_seasons) {
        this.number_of_seasons = number_of_seasons;
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

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public ArrayList<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(ArrayList<Company> companies) {
        this.companies = companies;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public double getVote_count() {
        return vote_count;
    }

    public void setVote_count(double vote_count) {
        this.vote_count = vote_count;
    }

    public class Creator {

        public String toString() {
            StringBuilder result = new StringBuilder();
            String newLine = System.getProperty("line.separator");

            result.append( this.getClass().getName() );
            result.append( " Object {" );
            result.append(newLine);

            //determine fields declared in this class only (no fields of superclass)
            Field[] fields = this.getClass().getDeclaredFields();

            //print field names paired with their values
            for ( Field field : fields  ) {
                result.append("  ");
                try {
                    result.append( field.getName() );
                    result.append(": ");
                    //requires access to private field:
                    result.append( field.get(this) );
                } catch ( IllegalAccessException ex ) {
                    System.out.println(ex);
                }
                result.append(newLine);
            }
            result.append("}");

            return result.toString();
        }

        @SerializedName("id")
        private int id;

        @SerializedName("credit_id")
        private String credit_id;

        @SerializedName("name")
        private String name;

        @SerializedName("gender")
        private int gender;

        @SerializedName("profile_path")
        private String profile_path;

        public Creator(int id, String credit_id, String name, int gender, String profile_path) {
            this.id = id;
            this.credit_id = credit_id;
            this.name = name;
            this.gender = gender;
            this.profile_path = profile_path;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCredit_id() {
            return credit_id;
        }

        public void setCredit_id(String credit_id) {
            this.credit_id = credit_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public String getProfile_path() {
            return profile_path;
        }

        public void setProfile_path(String profile_path) {
            this.profile_path = profile_path;
        }
    }

    public class Genre {

        public String toString() {
            StringBuilder result = new StringBuilder();
            String newLine = System.getProperty("line.separator");

            result.append( this.getClass().getName() );
            result.append( " Object {" );
            result.append(newLine);

            //determine fields declared in this class only (no fields of superclass)
            Field[] fields = this.getClass().getDeclaredFields();

            //print field names paired with their values
            for ( Field field : fields  ) {
                result.append("  ");
                try {
                    result.append( field.getName() );
                    result.append(": ");
                    //requires access to private field:
                    result.append( field.get(this) );
                } catch ( IllegalAccessException ex ) {
                    System.out.println(ex);
                }
                result.append(newLine);
            }
            result.append("}");

            return result.toString();
        }

        @SerializedName("id")
        protected int id;

        @SerializedName("name")
        protected String name;

        public Genre(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


    public class Company {

        public String toString() {
            StringBuilder result = new StringBuilder();
            String newLine = System.getProperty("line.separator");

            result.append( this.getClass().getName() );
            result.append( " Object {" );
            result.append(newLine);

            //determine fields declared in this class only (no fields of superclass)
            Field[] fields = this.getClass().getDeclaredFields();

            //print field names paired with their values
            for ( Field field : fields  ) {
                result.append("  ");
                try {
                    result.append( field.getName() );
                    result.append(": ");
                    //requires access to private field:
                    result.append( field.get(this) );
                } catch ( IllegalAccessException ex ) {
                    System.out.println(ex);
                }
                result.append(newLine);
            }
            result.append("}");

            return result.toString();
        }

        @SerializedName("id")
        protected int id;

        @SerializedName("logo_path")
        protected String logo_path;

        @SerializedName("name")
        protected String name;

        @SerializedName("origin_country")
        protected String origin_country;

        public Company(int id, String logo_path, String name, String origin_country) {
            this.id = id;
            this.logo_path = logo_path;
            this.name = name;
            this.origin_country = origin_country;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLogo_path() {
            return logo_path;
        }

        public void setLogo_path(String logo_path) {
            this.logo_path = logo_path;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getOrigin_country() {
            return origin_country;
        }

        public void setOrigin_country(String origin_country) {
            this.origin_country = origin_country;
        }
    }

}

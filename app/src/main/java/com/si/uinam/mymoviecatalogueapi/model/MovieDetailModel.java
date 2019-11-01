package com.si.uinam.mymoviecatalogueapi.model;

import com.si.uinam.mymoviecatalogueapi.services.retrofit.MovieCredit;
import com.si.uinam.mymoviecatalogueapi.services.retrofit.MovieDetail;
import com.si.uinam.mymoviecatalogueapi.services.retrofit.MovieReview;

import java.util.ArrayList;
import java.util.Date;

public class MovieDetailModel extends MovieModel{

    private double budget;
    private ArrayList<String> genres;
    private String homepage;
    private String imdb_id;
    private String status;
    private String tagline;
    private ArrayList<Cast> casts;
    private ArrayList<Crew> crews;
    private Review review;

    public MovieDetailModel (MovieModel movieModel, MovieDetail movieDetail, MovieCredit movieCredit, MovieReview movieReview) {
        this.setAdult(movieModel.isAdult());
        this.setId(movieModel.getId());
        this.setPopularity(movieModel.getPopularity());
        this.setVote_count(movieModel.getVote_count());
        this.setVideo(movieModel.isVideo());
        this.setPoster_path(movieModel.getPoster_path());
        this.setBackdrop_path(movieModel.getBackdrop_path());
        this.setOriginal_language(movieModel.getOriginal_language());
        this.setOriginal_title(movieModel.getOriginal_title());
        this.setGenre_ids(movieModel.getGenre_ids());
        this.setTitle(movieModel.getTitle());
        this.setVote_average(movieModel.getVote_average());
        this.setOverview(movieModel.getOverview());
        this.setRelease_date(movieModel.getRelease_date());
        if(movieDetail != null) {
            this.setBudget(movieDetail.getBudget());
            this.setHomepage(movieDetail.getHomepage());
            this.setImdb_id(movieDetail.getImdb_id());
            this.setStatus(movieDetail.getStatus());
            this.setTagline(movieDetail.getTagline());
        }
        MovieDetailModel.Review movieDetailModelReview = this.new Review();
        if(movieReview != null){
            ArrayList<MovieReview.Review> mReviewArray = movieReview.getReviews();
            if(mReviewArray != null && mReviewArray.size() > 0) {
                MovieReview.Review rv = mReviewArray.get(0);
                movieDetailModelReview.setContent(rv.getContent());
                movieDetailModelReview.setAuthor(rv.getAuthor());
            }
        }
        this.setReview(movieDetailModelReview);
        ArrayList<String> gens = new ArrayList<>();
        if(movieDetail != null){
            ArrayList<MovieDetail.Genre> genreMovieDetailArray = movieDetail.getGenres();
            for(MovieDetail.Genre gen : genreMovieDetailArray) {
                gens.add(gen.getName());
            }
        }
        this.setGenres(gens);
        ArrayList<MovieDetailModel.Cast> castsMovieDetailModel = new ArrayList<>();
        ArrayList<MovieDetailModel.Crew> crewsMovieDetailModel = new ArrayList<>();
        if(movieCredit != null){
            ArrayList<MovieCredit.Cast> castsMovieCredit = movieCredit.getCasts();
            for(MovieCredit.Cast castCredit : castsMovieCredit) {
                MovieDetailModel.Cast castDetail = this.new Cast();
                castDetail.setCast_id(castCredit.getCast_id());
                castDetail.setCharacter(castCredit.getCharacter());
                castDetail.setCredit_id(castCredit.getCredit_id());
                castDetail.setGender(castCredit.getGender());
                castDetail.setId(castCredit.getId());
                castDetail.setName(castCredit.getName());
                castDetail.setOrder(castCredit.getOrder());
                castDetail.setProfile_path(castCredit.getProfile_path());
                castsMovieDetailModel.add(castDetail);
            }
            ArrayList<MovieCredit.Crew> crewsMovieCredit = movieCredit.getCrews();
            for(MovieCredit.Crew crewCredit : crewsMovieCredit) {
                MovieDetailModel.Crew crewDetail = this.new Crew();
                crewDetail.setId(crewCredit.getId());
                crewDetail.setCredit_id(crewCredit.getCredit_id());
                crewDetail.setDepartment(crewCredit.getDepartment());
                crewDetail.setGender(crewCredit.getGender());
                crewDetail.setJob(crewCredit.getJob());
                crewDetail.setName(crewCredit.getName());
                crewDetail.setProfile_path(crewCredit.getProfile_path());
                crewsMovieDetailModel.add(crewDetail);
            }
        }
        this.setCasts(castsMovieDetailModel);
        this.setCrews(crewsMovieDetailModel);

    }

    public MovieDetailModel(MovieModel movieModel) {
        this.setAdult(movieModel.isAdult());
        this.setId(movieModel.getId());
        this.setPopularity(movieModel.getPopularity());
        this.setVote_count(movieModel.getVote_count());
        this.setVideo(movieModel.isVideo());
        this.setPoster_path(movieModel.getPoster_path());
        this.setBackdrop_path(movieModel.getBackdrop_path());
        this.setOriginal_language(movieModel.getOriginal_language());
        this.setOriginal_title(movieModel.getOriginal_title());
        this.setGenre_ids(movieModel.getGenre_ids());
        this.setTitle(movieModel.getTitle());
        this.setVote_average(movieModel.getVote_average());
        this.setOverview(movieModel.getOverview());
        this.setRelease_date(movieModel.getRelease_date());
    }

    public String getPoster_path(){
        return this.poster_path;
    }

    public ArrayList<Cast> getCasts() {
        return casts;
    }

    public void setCasts(ArrayList<Cast> casts) {
        this.casts = casts;
    }

    public ArrayList<Crew> getCrews() {
        return crews;
    }

    public void setCrews(ArrayList<Crew> crews) {
        this.crews = crews;
    }

    public Review getReview() {
        return review != null? review : new Review();
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<String> genres) {
        this.genres = genres;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getImdb_id() {
        return imdb_id;
    }

    public void setImdb_id(String imdb_id) {
        this.imdb_id = imdb_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public class Cast {

        private int cast_id;
        private String character;
        private String credit_id;
        private int gender;
        private int id;
        private String name;
        private int order;
        private String profile_path;

        public int getCast_id() {
            return cast_id;
        }

        public void setCast_id(int cast_id) {
            this.cast_id = cast_id;
        }

        public String getCharacter() {
            return character;
        }

        public void setCharacter(String character) {
            this.character = character;
        }

        public String getCredit_id() {
            return credit_id;
        }

        public void setCredit_id(String credit_id) {
            this.credit_id = credit_id;
        }

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
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

        public int getOrder() {
            return order;
        }

        public void setOrder(int order) {
            this.order = order;
        }

        public String getProfile_path() {
            return profile_path;
        }

        public void setProfile_path(String profile_path) {
            this.profile_path = profile_path;
        }
    }

    public class Crew {

        private String credit_id;
        private String department;
        private int gender;
        private int id;
        private String job;
        private String name;
        private String profile_path;

        public String getCredit_id() {
            return credit_id;
        }

        public void setCredit_id(String credit_id) {
            this.credit_id = credit_id;
        }

        public String getDepartment() {
            return department;
        }

        public void setDepartment(String department) {
            this.department = department;
        }

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getJob() {
            return job;
        }

        public void setJob(String job) {
            this.job = job;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getProfile_path() {
            return profile_path;
        }

        public void setProfile_path(String profile_path) {
            this.profile_path = profile_path;
        }
    }

    public class Review {

        private String author;
        private String content;

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

}

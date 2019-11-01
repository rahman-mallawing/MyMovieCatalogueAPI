package com.si.uinam.mymoviecatalogueapi.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.si.uinam.mymoviecatalogueapi.R;
import com.si.uinam.mymoviecatalogueapi.helper.ApiHelper;
import com.si.uinam.mymoviecatalogueapi.helper.LocaleHelper;
import com.si.uinam.mymoviecatalogueapi.model.MovieDetailModel;
import com.si.uinam.mymoviecatalogueapi.model.MovieModel;
import com.si.uinam.mymoviecatalogueapi.viewmodel.MovieDetailViewModel;

import java.util.ArrayList;

public class MovieDetailActivity extends AppCompatActivity {

    public static final String EXTRA_WATCHABLE = "extra_watchable";
    private TextView txvTittle, txvTittleDesc, txvCrew1, txvCrew2, txvCrew3,
            txvCrew1Desc, txvCrew2Desc, txvCrew3Desc, txvCast1, txvCast2,
            txvCast3, txvOverview, txvOverviewDesc, txvGenre, txvGenreDesc,
            txvReview, txvReviewWrittenBy, txvReviewWrittenOn, txvReviewDesc;
    private ImageView imgPoster, imgCast1, imgCast2, imgCast3;
    private MovieDetailViewModel movieDetailViewModel;
    private ProgressBar progressBar;
    private ScrollView lytContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        txvTittle = findViewById(R.id.txv_title);
        txvTittleDesc = findViewById(R.id.txv_title_desc);
        txvCrew1 = findViewById(R.id.txv_crew1);
        txvCrew2 = findViewById(R.id.txv_crew2);
        txvCrew3 = findViewById(R.id.txv_crew3);
        txvCrew1Desc = findViewById(R.id.txv_crew1_desc);
        txvCrew2Desc = findViewById(R.id.txv_crew2_desc);
        txvCrew3Desc = findViewById(R.id.txv_crew3_desc);
        txvCast1 = findViewById(R.id.txv_cast1_desc);
        txvCast2 = findViewById(R.id.txv_cast2_desc);
        txvCast3 = findViewById(R.id.txv_cast3_desc);
        txvOverview = findViewById(R.id.txv_overview);
        txvOverviewDesc = findViewById(R.id.txv_overview_desc);
        txvGenre = findViewById(R.id.txv_genre);
        txvGenreDesc = findViewById(R.id.txv_genre_desc);
        txvReview = findViewById(R.id.txv_review);
        txvReviewWrittenBy = findViewById(R.id.txv_review_written_by);
        txvReviewWrittenOn = findViewById(R.id.txv_review_written_on);
        txvReviewDesc = findViewById(R.id.txv_review_desc);

        imgPoster = findViewById(R.id.img_poster);
        imgCast1 = findViewById(R.id.img_cast1);
        imgCast2 = findViewById(R.id.img_cast2);
        imgCast3 = findViewById(R.id.img_cast3);

        lytContainer = findViewById(R.id.lyt_container);

        progressBar = findViewById(R.id.mDetailProgressBar);
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setIndeterminate(true);

        MovieModel movieModel = getIntent().getParcelableExtra(MovieDetailActivity.EXTRA_WATCHABLE);


        movieDetailViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MovieDetailViewModel.class);
        movieDetailViewModel.getMovieDetailInstance().observe(this, new Observer<MovieDetailModel>() {
            @Override
            public void onChanged(MovieDetailModel movieDetailModel) {
                Log.d("TES-MOVIE-DETAIL", "TITEL: "+ movieDetailModel.getTitle());
                displayMovieDetail(movieDetailModel);
                showLoading(false);
            }
        });

        String localeId = LocaleHelper.getLocale(this);
        movieDetailViewModel.loadMovieDetail(movieModel,
                ApiHelper.getLanguageId(localeId));
        showLoading(true);
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
            lytContainer.setVisibility(View.GONE);
        } else {
            progressBar.setVisibility(View.GONE);
            lytContainer.setVisibility(View.VISIBLE);
        }
    }

    public void displayMovieDetail(MovieDetailModel movieDetailModel) {
        Log.d("TES-MOVIE-ID", "ID: "+ movieDetailModel.getId());
        MovieDetailModel.Cast castDefault = movieDetailModel.new Cast();
        castDefault.setProfile_path("");
        MovieDetailModel.Crew crewDefault = movieDetailModel.new Crew();
        MovieDetailModel.Review review = movieDetailModel.getReview();
        ArrayList<MovieDetailModel.Cast> casts = movieDetailModel.getCasts();
        ArrayList<MovieDetailModel.Crew> crews = movieDetailModel.getCrews();

        //Log.d("TES-MOVIE-CAST", "ID: "+ casts.toString());
        //Log.d("TES-MOVIE-CREW", "ID: "+ crews.toString());
        MovieDetailModel.Cast cast1, cast2, cast3;
        if(casts != null){
            cast1 = casts.size() >= 0? casts.get(0) : castDefault;
            cast2 = casts.size() >= 1? casts.get(1) : castDefault;
            cast3 = casts.size() >= 2? casts.get(2) : castDefault;
        } else {
            cast1 = cast2 = cast3 = castDefault;
        }

        MovieDetailModel.Crew crew1, crew2, crew3;
        if(crews != null) {
            crew1 = crews.size() >= 0? crews.get(0) : crewDefault;
            crew2 = crews.size() >= 1? crews.get(1) : crewDefault;
            crew3 = crews.size() >= 2? crews.get(2) : crewDefault;
        } else {
            crew1 = crew2 = crew3 = crewDefault;
        }
        ArrayList<String> genresList = movieDetailModel.getGenres();
        String genre = "";
        for (String object: genresList) {
            genre = genre + " " + object;
        }

        String IMG_BASE_URL = ApiHelper.getImgBaseUrl();
        String IMG_POSTER_DEFAULT = ApiHelper.getImgPosterPlaceholder();
        String IMG_CAST_DEFAULT = ApiHelper.getImgCastPlaceholder();
        String urlPoster = movieDetailModel.getPoster_path().isEmpty()? IMG_POSTER_DEFAULT
                : IMG_BASE_URL + movieDetailModel.getPoster_path();
        Glide.with(getBaseContext())
                .load(urlPoster)
                .apply(new RequestOptions().override(160, 240))
                .into(this.imgPoster);

        String imgCast1 = cast1.getProfile_path().isEmpty()? IMG_CAST_DEFAULT
                : IMG_BASE_URL + cast1.getProfile_path();
        Glide.with(getBaseContext())
                .load(imgCast1)
                .apply(new RequestOptions().override(110, 165))
                .into(this.imgCast1);

        String imgCast2 = cast2.getProfile_path().isEmpty()? IMG_CAST_DEFAULT
                : IMG_BASE_URL + cast2.getProfile_path();
        Glide.with(getBaseContext())
                .load(imgCast2)
                .apply(new RequestOptions().override(110, 165))
                .into(this.imgCast2);

        String imgCast3 = cast3.getProfile_path().isEmpty()? IMG_CAST_DEFAULT
                : IMG_BASE_URL + cast3.getProfile_path();
        Glide.with(getBaseContext())
                .load(imgCast3)
                .apply(new RequestOptions().override(110, 165))
                .into(this.imgCast3);
        txvTittle.setText(movieDetailModel.getTitle());
        txvTittleDesc.setText(txvTittleDesc.getText()+": "+movieDetailModel.getVote_average()+"%");
        txvCrew1.setText(crew1.getName());
        txvCrew2.setText(crew2.getName());
        txvCrew3.setText(crew3.getName());
        txvCrew1Desc.setText(crew1.getJob());
        txvCrew2Desc.setText(crew2.getJob());
        txvCrew3Desc.setText(crew3.getJob());
        txvCast1.setText(cast1.getName() + " \n[" + cast1.getCharacter() + "]");
        txvCast2.setText(cast2.getName() + " \n[" + cast2.getCharacter() + "]");
        txvCast3.setText(cast3.getName() + " \n[" + cast3.getCharacter() + "]");
        //txvOverview.setText("OVERVIEW");
        txvOverviewDesc.setText(movieDetailModel.getOverview());
        //txvGenre.setText("GENRE");
        txvGenreDesc.setText(genre);
        //txvReview.setText("REVIEW");
        txvReviewWrittenBy.setText(review.getAuthor());
        txvReviewWrittenOn.setText("-");
        txvReviewDesc.setText(review.getContent());

    }

}

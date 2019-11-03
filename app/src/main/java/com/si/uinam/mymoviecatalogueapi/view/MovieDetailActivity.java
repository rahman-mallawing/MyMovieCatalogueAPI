package com.si.uinam.mymoviecatalogueapi.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

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

    public static final String EXTRA_MOVIE = "extra_movie";
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

        MovieModel movieModel = getIntent().getParcelableExtra(MovieDetailActivity.EXTRA_MOVIE);


        movieDetailViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MovieDetailViewModel.class);
        movieDetailViewModel.getMovieDetailInstance().observe(this, new Observer<MovieDetailModel>() {
            @Override
            public void onChanged(MovieDetailModel movieDetailModel) {
                Log.d("TES-MOVIE-DETAIL", "TITEL: "+ movieDetailModel.getTitle());
                displayMovieDetail(movieDetailModel);
                showLoading(false);
            }
        });
        movieDetailViewModel.getErrorMessage().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                String msg = "Error: " + s;
                Toast toast = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG);
                View vi = toast.getView();
                vi.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
                TextView text = vi.findViewById(android.R.id.message);
                text.setTextColor(Color.WHITE);
                toast.show();
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

        MovieDetailModel.Review review = movieDetailModel.getReviewHaldeNull();

        MovieDetailModel.Cast cast1 = movieDetailModel.getCast(0);
        MovieDetailModel.Cast cast2 = movieDetailModel.getCast(1);
        MovieDetailModel.Cast cast3 = movieDetailModel.getCast(2);


        MovieDetailModel.Crew crew1 = movieDetailModel.getCrew(0);
        MovieDetailModel.Crew crew2 = movieDetailModel.getCrew(1);
        MovieDetailModel.Crew crew3 = movieDetailModel.getCrew(2);

        String genre = movieDetailModel.getGenreName();

        Log.d("movieDetailModel", movieDetailModel.getPosterImgUrl());
        Glide.with(getBaseContext())
                .load(movieDetailModel.getPosterImgUrl())
                .apply(new RequestOptions().override(160, 240))
                .into(this.imgPoster);

        Glide.with(getBaseContext())
                .load(cast1.getProfile_path())
                .apply(new RequestOptions().override(110, 165))
                .into(this.imgCast1);

        Glide.with(getBaseContext())
                .load(cast2.getProfile_path())
                .apply(new RequestOptions().override(110, 165))
                .into(this.imgCast2);

        Glide.with(getBaseContext())
                .load(cast3.getProfile_path())
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

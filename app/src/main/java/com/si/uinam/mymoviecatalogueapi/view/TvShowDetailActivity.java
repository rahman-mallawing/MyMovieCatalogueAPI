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
import com.si.uinam.mymoviecatalogueapi.model.TvShowModel;
import com.si.uinam.mymoviecatalogueapi.services.retrofit.TvShowCredit;
import com.si.uinam.mymoviecatalogueapi.services.retrofit.TvShowDetail;
import com.si.uinam.mymoviecatalogueapi.services.retrofit.TvShowReview;
import com.si.uinam.mymoviecatalogueapi.viewmodel.TvShowDetailViewModel;
import com.si.uinam.mymoviecatalogueapi.viewmodel.TvShowViewModel;

import java.util.ArrayList;

public class TvShowDetailActivity extends AppCompatActivity {

    public static final String EXTRA_TV_SHOW = "extra_tv_show";
    private TextView txvTittle, txvTittleDesc, txvCrew1, txvCrew2, txvCrew3,
            txvCrew1Desc, txvCrew2Desc, txvCrew3Desc, txvCast1, txvCast2,
            txvCast3, txvOverview, txvOverviewDesc, txvGenre, txvGenreDesc,
            txvReview, txvReviewWrittenBy, txvReviewWrittenOn, txvReviewDesc;
    private ImageView imgPoster, imgCast1, imgCast2, imgCast3;
    private TvShowDetailViewModel tvShowDetailViewModel;
    private ProgressBar progressBar;
    private ScrollView lytContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_show_detail);

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

        lytContainer = findViewById(R.id.tv_lyt_container);

        progressBar = findViewById(R.id.tvDetailProgressBar);
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setIndeterminate(true);

        TvShowModel tvShowModel = getIntent().getParcelableExtra(TvShowDetailActivity.EXTRA_TV_SHOW);
        tvShowDetailViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(TvShowDetailViewModel.class);
        tvShowDetailViewModel.getTvShowDetailInstance().observe(this, new Observer<TvShowDetail>() {
            @Override
            public void onChanged(TvShowDetail tvShowDetail) {
                Log.d("TvSHOW-Activity", tvShowDetail.toString());
                displayTvShowDetail(tvShowDetail);
                showLoading(false);
            }
        });

        String localeId = LocaleHelper.getLocale(this);
        tvShowDetailViewModel.loadTvShowDetail(tvShowModel,
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

    private void displayTvShowDetail(TvShowDetail tvShowDetail) {

        Log.d("TES-MOVIE-ID", "ID: "+ tvShowDetail.getId());

        TvShowCredit.Cast cast1 = tvShowDetail.getCast(0);
        TvShowCredit.Cast cast2 = tvShowDetail.getCast(1);
        TvShowCredit.Cast cast3 = tvShowDetail.getCast(2);

        TvShowCredit.Crew crew1 = tvShowDetail.getCrew(0);
        TvShowCredit.Crew crew2 = tvShowDetail.getCrew(1);
        TvShowCredit.Crew crew3 = tvShowDetail.getCrew(2);
        TvShowReview.Review review = tvShowDetail.getReview(0);
        String IMG_BASE_URL = ApiHelper.getImgBaseUrl();
        String IMG_POSTER_DEFAULT = ApiHelper.getImgPosterPlaceholder();
        String IMG_CAST_DEFAULT = ApiHelper.getImgCastPlaceholder();
        String urlPoster = (tvShowDetail.getPoster_path()==null || tvShowDetail.getPoster_path().isEmpty())? IMG_POSTER_DEFAULT
                : IMG_BASE_URL + tvShowDetail.getPoster_path();
        Glide.with(getBaseContext())
                .load(urlPoster)
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

        txvTittle.setText(tvShowDetail.getName());
        txvTittleDesc.setText(txvTittleDesc.getText()+": "+tvShowDetail.getVote_average()+"%");
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
        txvOverviewDesc.setText(tvShowDetail.getOverview());
        //txvGenre.setText("GENRE");
        txvGenreDesc.setText(tvShowDetail.getGenreName());
        //txvReview.setText("REVIEW");
        txvReviewWrittenBy.setText(review.getAuthor());
        txvReviewWrittenOn.setText("-");
        txvReviewDesc.setText(review.getContent());
    }

}

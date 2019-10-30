package com.si.uinam.mymoviecatalogueapi.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.si.uinam.mymoviecatalogueapi.R;
import com.si.uinam.mymoviecatalogueapi.model.MovieModel;

public class MovieDetailActivity extends AppCompatActivity {

    public static final String EXTRA_WATCHABLE = "extra_watchable";
    private TextView txvTittle, txvTittleDesc, txvCrew1, txvCrew2, txvCrew3,
            txvCrew1Desc, txvCrew2Desc, txvCrew3Desc, txvCast1, txvCast2,
            txvCast3, txvOverview, txvOverviewDesc, txvGenre, txvGenreDesc,
            txvReview, txvReviewWrittenBy, txvReviewWrittenOn, txvReviewDesc;
    private ImageView imgPoster, imgCast1, imgCast2, imgCast3;

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

        MovieModel movieModel = getIntent().getParcelableExtra(MovieDetailActivity.EXTRA_WATCHABLE);
        txvTittle.setText(movieModel.getTitle());
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

}

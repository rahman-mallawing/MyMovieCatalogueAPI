package com.si.uinam.mymoviecatalogueapi.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.si.uinam.mymoviecatalogueapi.R;

public class TvShowDetailActivity extends AppCompatActivity {

    public static final String EXTRA_TV_SHOW = "extra_tv_show";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_show_detail);
    }
}

package com.si.uinam.mymoviecatalogueapi.view;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.si.uinam.mymoviecatalogueapi.R;
import com.si.uinam.mymoviecatalogueapi.model.TvShowModel;
import com.si.uinam.mymoviecatalogueapi.viewmodel.TvShowViewModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowFragment extends Fragment {

    private ProgressBar progressBar;
    private TvShowListAdapter tvShowListAdapter;
    private RecyclerView rcvMovies;
    private TvShowViewModel tvShowViewModel;


    public TvShowFragment() {
        // Required empty public constructor
    }

    public static TvShowFragment newInstance() {
        TvShowFragment fragment = new TvShowFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tvShowViewModel = ViewModelProviders.of(this).get(TvShowViewModel.class);

        tvShowViewModel.getTvShowCollection().observe(this, new Observer<ArrayList<TvShowModel>>() {
            @Override
            public void onChanged(ArrayList<TvShowModel> tvShowModels) {
                if(tvShowModels != null){
                    Log.d("TES-VIEW-MODEL-TV-SHOW", "Live collection adalah: " + tvShowModels.size());
                    Log.d("TES-VIEW-MODEL-TV-SHOW", "Live collection adalah: " + tvShowModels.get(0).getPoster_path());
                    tvShowListAdapter.setTvShowList(tvShowModels);
                    Log.d("TES-VIEW-MODEL-TV-SHOW", "Inside observer setTvList: " + tvShowModels.size());
                    showLoading(false);
                }
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tv_show, container, false);

        progressBar = view.findViewById(R.id.tvProgressBar);
        rcvMovies = view.findViewById(R.id.rcv_movies);
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setIndeterminate(true);
        rcvMovies.setLayoutManager(new LinearLayoutManager(getContext()));
        tvShowListAdapter = new TvShowListAdapter();
        tvShowListAdapter.setItemClickCallback(new TvShowListAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(TvShowModel tvShow) {
                Toast.makeText(getContext(), "Kamu memilih: " + tvShow.getName(), Toast.LENGTH_SHORT).show();
                Intent detailIntent = new Intent(getActivity(), TvShowDetailActivity.class);
                detailIntent.putExtra(TvShowDetailActivity.EXTRA_TV_SHOW, tvShow);
                startActivity(detailIntent);
            }
        });
        tvShowListAdapter.notifyDataSetChanged();
        rcvMovies.setAdapter(tvShowListAdapter);
        showLoading(true);
        Log.d("TES-VIEW-MODEL-TV-SHOW", "Inside onCreateView" );
        tvShowViewModel.loadTvShow(getContext());

        return view;
    }

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

}

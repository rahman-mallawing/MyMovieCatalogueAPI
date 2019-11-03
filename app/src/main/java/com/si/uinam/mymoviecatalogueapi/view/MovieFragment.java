package com.si.uinam.mymoviecatalogueapi.view;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.si.uinam.mymoviecatalogueapi.R;
import com.si.uinam.mymoviecatalogueapi.helper.ApiHelper;
import com.si.uinam.mymoviecatalogueapi.helper.LocaleHelper;
import com.si.uinam.mymoviecatalogueapi.model.MovieModel;
import com.si.uinam.mymoviecatalogueapi.viewmodel.MovieViewModel;

import java.util.ArrayList;

import static java.lang.Thread.sleep;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {

    private ProgressBar progressBar;
    private MovieListAdapter movieListAdapter;
    private RecyclerView rcvMovies;
    private MovieViewModel movieViewModel;

    public MovieFragment() {
        // Required empty public constructor
    }

    public static MovieFragment newInstance() {
        Log.d("FRAGMENT-CREATE", "onResume Movie");
        MovieFragment fragment = new MovieFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //movieViewModel = ViewModelProviders.of(getActivity()).get(MovieViewModel.class);
        movieViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MovieViewModel.class);
        movieViewModel.getMovieCollection().observe(this, new Observer<ArrayList<MovieModel>>() {
            @Override
            public void onChanged(ArrayList<MovieModel> collection) {
                if(collection != null){
                    movieListAdapter.setMovieList(collection);
                    showLoading(false);
                }
            }
        });

        movieViewModel.getErrorMessage().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                String msg = "Error: " + s;
                Toast toast = Toast.makeText(getContext(), msg, Toast.LENGTH_LONG);
                View vi = toast.getView();
                vi.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
                TextView text = vi.findViewById(android.R.id.message);
                text.setTextColor(Color.WHITE);
                toast.show();
                showLoading(false);
            }
        });

        movieViewModel.loadMovieList(
                ApiHelper.getLanguageId(LocaleHelper.getLocale(getContext()))
        );
        Log.d("TES-onCreate", "onCreateonCreateonCreateonCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie, container, false);
        progressBar = view.findViewById(R.id.movieProgressBar);
        rcvMovies = view.findViewById(R.id.rcv_movies);
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setIndeterminate(true);


        Log.d("TES-VIEW-MODEL", "1. assdd Connect internet API");
        rcvMovies.setLayoutManager(new LinearLayoutManager(getContext()));
        movieListAdapter = new MovieListAdapter();
        movieListAdapter.setItemClickCallback(new MovieListAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(MovieModel movie) {
                Toast.makeText(getContext(), getResources().getString(R.string.choice) + movie.getTitle(), Toast.LENGTH_SHORT).show();
                Intent detailIntent = new Intent(getActivity(), MovieDetailActivity.class);
                detailIntent.putExtra(MovieDetailActivity.EXTRA_MOVIE, movie);
                startActivity(detailIntent);
            }
        });
        movieListAdapter.notifyDataSetChanged();
        rcvMovies.setAdapter(movieListAdapter);

       //movieViewModel.loadMovieList(getContext());

        showLoading(true);
        return view;
    }

    private void showLoading(Boolean state) {
        if(progressBar == null) {
            Log.d("TES-progressBar", "NULL NULL NULL");
            return;
        }
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
            rcvMovies.setVisibility(View.GONE);
        } else {
            progressBar.setVisibility(View.GONE);
            rcvMovies.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        if(menuVisible){

            if(movieViewModel == null) {
                Log.d("TES-movieViewModel", "NULL NULL NULL");
                return;
            }
            showLoading(true);
            movieViewModel.loadMovieList(
                    ApiHelper.getLanguageId(LocaleHelper.getLocale(getContext()))
            );
            Log.d("TES-setMenuVisibility", "setMenuVisibilitysetMenuVisibility");
        }

    }


}

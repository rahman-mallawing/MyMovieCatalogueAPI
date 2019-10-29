package com.si.uinam.mymoviecatalogueapi.view;


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
import android.widget.Toast;

import com.si.uinam.mymoviecatalogueapi.R;
import com.si.uinam.mymoviecatalogueapi.model.MovieModel;
import com.si.uinam.mymoviecatalogueapi.viewmodel.MovieViewModel;

import java.util.ArrayList;

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
        MovieFragment fragment = new MovieFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);

        movieViewModel.getMovieCollection().observe(this, new Observer<ArrayList<MovieModel>>() {
            @Override
            public void onChanged(ArrayList<MovieModel> collection) {
                Log.d("TES-VIEW-MODEL", "1212. Live collection adalah: " + collection.size());
                Log.d("TES-VIEW-MODEL", "1212. Live collection adalah: " + collection.get(0).getPoster_path());
                if(collection != null){
                    movieListAdapter.setMovieList(collection);
                    Log.d("TES-VIEW-MODEL", "8. Inside observer setMovieList: " + collection.size());
                    showLoading(false);
                }
            }
        });




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
                Toast.makeText(getContext(), "Kamu memilih: " + movie.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
        movieListAdapter.notifyDataSetChanged();
        rcvMovies.setAdapter(movieListAdapter);
        showLoading(true);
       movieViewModel.loadMovieList(getContext());

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

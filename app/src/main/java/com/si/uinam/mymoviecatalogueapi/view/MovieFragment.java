package com.si.uinam.mymoviecatalogueapi.view;


import android.content.Context;
import android.content.Intent;
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
import com.si.uinam.mymoviecatalogueapi.helper.ApiHelper;
import com.si.uinam.mymoviecatalogueapi.helper.LocaleHelper;
import com.si.uinam.mymoviecatalogueapi.model.MovieModel;
import com.si.uinam.mymoviecatalogueapi.viewmodel.MovieViewModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment implements FragmentLifecycle{

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


        ///




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
                Intent detailIntent = new Intent(getActivity(), MovieDetailActivity.class);
                detailIntent.putExtra(MovieDetailActivity.EXTRA_MOVIE, movie);
                startActivity(detailIntent);
            }
        });
        movieListAdapter.notifyDataSetChanged();
        rcvMovies.setAdapter(movieListAdapter);
        showLoading(true);
       //movieViewModel.loadMovieList(getContext());

        return view;
    }

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            //progressBar.setVisibility(View.GONE);
        }
    }


    @Override
    public void onPauseFragment(Context context) {
        Log.i("Movie-Pause", "onPauseFragment()");
        //Toast.makeText(getActivity(), "onPauseFragment():" + "Movie-Pause", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResumeFragment(Context context) {
        Log.i("Movie-Resume", "onResumeFragment()");
        //Toast.makeText(getActivity(), "onResumeFragment():" + "Movie-Resume", Toast.LENGTH_SHORT).show();
        //movieViewModel.loadMovieList(getParentFragment().getContext());
        Log.i("Movie-Pause", context.toString());
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);

        movieViewModel.getMovieCollection().observe(this, new Observer<ArrayList<MovieModel>>() {
            @Override
            public void onChanged(ArrayList<MovieModel> collection) {
                Log.d("TES-VIEW-MODEL", "1212. Live collection adalah: " + collection.size());
                //Log.d("TES-VIEW-MODEL", "1212. Live collection adalah: " + collection.get(0).getPoster_path());
                if(collection != null){
                    movieListAdapter.setMovieList(collection);
                    Log.d("TES-VIEW-MODEL", "8. Inside observer setMovieList: " + collection.size());
                    showLoading(false);
                }
            }
        });
        movieViewModel.loadMovieList(
                ApiHelper.getLanguageId(LocaleHelper.getLocale(context))
        );
    }



}

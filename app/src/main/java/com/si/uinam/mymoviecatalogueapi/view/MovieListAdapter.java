package com.si.uinam.mymoviecatalogueapi.view;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.si.uinam.mymoviecatalogueapi.R;
import com.si.uinam.mymoviecatalogueapi.model.MovieModel;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieViewHolder> {

    private ArrayList<MovieModel> movieList = new ArrayList<>();
    private OnItemClickCallback itemClickCallback;

    public void setMovieList(ArrayList<MovieModel> movieCollection) {
        movieList.clear();
        movieList.addAll(movieCollection);
        notifyDataSetChanged();
        Log.d("TES-VIEW-MODEL", "Notifed adapter: " + movieCollection.size());
    }

    public void setItemClickCallback(OnItemClickCallback itemClickCallback) {
        this.itemClickCallback = itemClickCallback;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_movie,parent,false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {

        holder.bind(movieList.get(position));
    }

    @Override
    public int getItemCount() {
        return this.movieList.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {

        TextView txvTitle;
        TextView txvOverview;
        ImageView imgPoster;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);

            txvTitle = itemView.findViewById(R.id.txv_title);
            txvOverview = itemView.findViewById(R.id.txv_overview);
            imgPoster = itemView.findViewById(R.id.img_poster);
        }

        void bind(MovieModel movieModel){

            Glide.with(itemView.getContext())
                    .load(movieModel.getPoster_path())
                    .apply(new RequestOptions().override(100, 150))
                    .into(imgPoster);
            txvTitle.setText(movieModel.getTitle());
            txvOverview.setText(movieModel.getOverview());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickCallback.onItemClicked(movieList.get(getAdapterPosition()));
                }
            });

        }

    }

    public interface OnItemClickCallback{
        void onItemClicked(MovieModel movie);
    }
}

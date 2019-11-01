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
import com.si.uinam.mymoviecatalogueapi.helper.ApiHelper;
import com.si.uinam.mymoviecatalogueapi.model.TvShowModel;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TvShowListAdapter extends RecyclerView.Adapter<TvShowListAdapter.TvShowViewHolder> {

    private ArrayList<TvShowModel> tvShowList = new ArrayList<>();
    private OnItemClickCallback itemClickCallback;

    public void setTvShowList(ArrayList<TvShowModel> tvShowCollection) {
        tvShowList.clear();
        tvShowList.addAll(tvShowCollection);
        notifyDataSetChanged();
        Log.d("TES-VIEW-MODEL-TV-SHOW", "Notifed adapter: " + tvShowCollection.size());
    }

    public void setItemClickCallback(OnItemClickCallback itemClickCallback) {
        this.itemClickCallback = itemClickCallback;
    }

    @NonNull
    @Override
    public TvShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_tv_show,parent,false);
        return new TvShowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final TvShowViewHolder holder, int position) {
        holder.bind(tvShowList.get(position));
    }

    @Override
    public int getItemCount() {
        return this.tvShowList.size();
    }

    public class TvShowViewHolder extends RecyclerView.ViewHolder {

        TextView txvTitle;
        TextView txvOverview;
        ImageView imgPoster;

        public TvShowViewHolder(@NonNull View itemView) {
            super(itemView);
            txvTitle = itemView.findViewById(R.id.txv_title);
            txvOverview = itemView.findViewById(R.id.txv_overview);
            imgPoster = itemView.findViewById(R.id.img_poster);
        }

        public void bind(TvShowModel tvShowModel){
            Glide.with(itemView.getContext())
                    .load(ApiHelper.getImgBaseUrl() + tvShowModel.getPoster_path())
                    .apply(new RequestOptions().override(100, 150))
                    .into(imgPoster);
            txvTitle.setText(tvShowModel.getName());
            txvOverview.setText(tvShowModel.getOverview());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickCallback.onItemClicked(tvShowList.get(getAdapterPosition()));
                }
            });
        }

    }

    public interface OnItemClickCallback{
        void onItemClicked(TvShowModel tvShow);
    }
}

package com.example.tvtracker.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tvtracker.Models.TvShow;
import com.example.tvtracker.R;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;
import java.util.List;

public class WatchlistAdapter extends RecyclerView.Adapter<WatchlistAdapter.TvShowCombinedViewHolder> {

    public interface OnItemClickListener {
    void onItemClick(TvShow tvShow);
    }


    private List<TvShow> tvShows = new ArrayList<>();
    private OnItemClickListener listener;


    @NonNull
    @Override
    public TvShowCombinedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.watchlist_item, parent, false);

        return new TvShowCombinedViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TvShowCombinedViewHolder holder, int position) {
        TvShow currentTvShow = tvShows.get(position);

        Picasso.get().load(currentTvShow.getTvShowImagePath()).into(holder.textViewTvShowImageThumbnail);
        holder.textViewTvShowId.setText(Integer.toString(currentTvShow.getTvShowId()));
        holder.textViewTvShowName.setText(currentTvShow.getTvShowName());
    }

    public void setTvShows(List<TvShow> tvShows) {
        this.tvShows = tvShows;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return tvShows.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;

    }

    class TvShowCombinedViewHolder extends RecyclerView.ViewHolder {
        private ImageView textViewTvShowImageThumbnail;
        private TextView textViewTvShowName;
        private TextView textViewTvShowId;

        private TvShowCombinedViewHolder(View itemView) {
            super(itemView);
            textViewTvShowImageThumbnail = itemView.findViewById(R.id.text_view_watchlist_image_thumbnail);
            textViewTvShowName = itemView.findViewById(R.id.text_view_watchlist_tv_show_name);
            textViewTvShowId = itemView.findViewById(R.id.text_view_watchlist_tv_show_id);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if(listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(tvShows.get(position));
                    }
                }
            });


        }
    }
    }



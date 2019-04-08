package com.lfm.rossellamorgante.lfm.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lfm.rossellamorgante.lfm.Model.Artist;
import com.lfm.rossellamorgante.lfm.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LFMRecAdapter extends RecyclerView.Adapter <LFMRecAdapter.ViewHolder> {

    private List<Artist> mArtists;
    private LayoutInflater mInflater;

    public LFMRecAdapter(List<Artist> data) {
        this.mArtists = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View item = inflater.inflate(R.layout.list_item, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(item);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Artist a = mArtists.get(position);
        holder.artistName.setText(a.name);

    }

    @Override
    public int getItemCount() {
        return this.mArtists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView artistName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            artistName = (TextView) itemView.findViewById(R.id.artistName);
        }
    }
}

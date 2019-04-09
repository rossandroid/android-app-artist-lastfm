/*
 * LFMRecAdapter
 * This Application implements the MVVM Pattern
 *
 * This Adapter permits:
 * - render on item of RecycleView
 * - Open the ActivityDetail
 *
 * Third-party Libraries:
 * - Picasso
 */
package com.lfm.rossellamorgante.lfm.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lfm.rossellamorgante.lfm.DetailActivity;
import com.lfm.rossellamorgante.lfm.Model.Artist;
import com.lfm.rossellamorgante.lfm.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LFMRecAdapter extends RecyclerView.Adapter <LFMRecAdapter.ViewHolder> {

    private List<Artist> mArtists;
    private ImageView image;

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

        // Get the artist according the position in the list
        final Artist a = mArtists.get(position);
        holder.artistName.setText(a.name);
        try {
            Picasso.get().load(a.image.get(0).text).resize(50, 50) .centerCrop().into(image);
        }catch (IllegalArgumentException ei){
            //in case of Exception, default image is rendered
        }
        // Set a setOnClickListener to start the DetailActivity
        // Artist is passed as serialization
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("artist",  a);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return this.mArtists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView artistName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            artistName = (TextView) itemView.findViewById(R.id.artist_name);
            image = (ImageView) itemView.findViewById(R.id.image_artist);

        }
    }
}

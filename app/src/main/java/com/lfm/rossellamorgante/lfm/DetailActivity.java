/*
 * DetailActivity
 * This Application implements the MVVM Pattern
 *
 * This Activity permits:
 * - Artist's detail
 * - Open Browser for more detail
 *
 * Third-party Libraries:
 * - Picasso
 */
package com.lfm.rossellamorgante.lfm;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.lfm.rossellamorgante.lfm.Model.Artist;
import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Receive the Artist Object
        Intent intent = getIntent();
        final Artist artist = (Artist) intent.getSerializableExtra("artist");

        // Set the values of name and mbid
        ((TextView)findViewById(R.id.artist_name)).setText(artist.name);
        ((TextView)findViewById(R.id.artist_mbid)).setText(artist.mbid);
        ImageView image = ((ImageView)findViewById(R.id.image_artist));

        // Set the Image
        try {
            Picasso.get().load(artist.image.get(1).text).into(image);
        }catch (Exception e){
            // in case of Exception, default image is rendered
        }

        // Replace the default image of Fab
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setImageResource(R.drawable.ic_public_black_24dp);
        // Click on Fab the browser is opened
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(artist.url)));
            }
        });
    }

}

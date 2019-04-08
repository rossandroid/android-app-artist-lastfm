package com.lfm.rossellamorgante.lfm;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lfm.rossellamorgante.lfm.Adapter.LFMRecAdapter;
import com.lfm.rossellamorgante.lfm.Model.Artist;
import com.lfm.rossellamorgante.lfm.Model.Results;
import com.lfm.rossellamorgante.lfm.RepoNet.LFMNetInterface;
import com.lfm.rossellamorgante.lfm.ViewModel.LFMViewModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {

    LFMViewModel viewModel;
    private RecyclerView recyclerView;
    private LFMRecAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Recycle View
        recyclerView = (RecyclerView) findViewById(R.id.artist_list);
        recyclerView.setHasFixedSize(true);

        List<Artist> l = new ArrayList<Artist>();
        Artist a = new Artist();
        a.name=("AAAAA");
        l.add(a);

        mAdapter = new LFMRecAdapter(l);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        /*viewModel = ViewModelProviders.of(this).get(LFMViewModel.class);
        viewModel.getArtists().observe(this,
                new Observer<List<Artist>>() {
                    @Override
                    public void onChanged(@Nullable final List<Artist> _arstist) {
                        mAdapter.notifyDataSetChanged();
                    }
                }
            );*/



    }

}

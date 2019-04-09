package com.lfm.rossellamorgante.lfm;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;

import com.lfm.rossellamorgante.lfm.Adapter.LFMRecAdapter;
import com.lfm.rossellamorgante.lfm.Model.Artist;
import com.lfm.rossellamorgante.lfm.ViewModel.LFMViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {

    LFMViewModel viewModel;
    private RecyclerView recyclerView;
    private LFMRecAdapter mAdapter;
    private List<Artist> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list= new ArrayList<Artist>();
        // Recycle View
        recyclerView = (RecyclerView) findViewById(R.id.artist_list);
        recyclerView.setHasFixedSize(true);


        mAdapter = new LFMRecAdapter(list);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        viewModel = ViewModelProviders.of(this).get(LFMViewModel.class);
        viewModel.getArtists().observe(this,
                new Observer<List<Artist>>() {
                    @Override
                    public void onChanged(@Nullable final List<Artist> _arstist) {
                        if(_arstist!=null)updateRecicleView(_arstist);
                    }
                }
            );

    }

    public void updateRecicleView(List<Artist> _arstist){
        list.addAll(_arstist);
        mAdapter.notifyDataSetChanged();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.searchmenu, menu);
        MenuItem searchViewItem = menu.findItem(R.id.app_bar_search);

        final SearchView searchView = (SearchView) searchViewItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.clearFocus();
                //setSpinner(true,false,"Searching...");
                viewModel.search(query);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                list.clear();
                mAdapter.notifyDataSetChanged();
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

}

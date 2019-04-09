/*
* MainActivity
* This Application implements the MVVM Pattern
*
* This Activity permits:
* - Search for an Artist
* - Show List Artists
* - Go to Artist's detail*/

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
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import com.lfm.rossellamorgante.lfm.Adapter.LFMRecAdapter;
import com.lfm.rossellamorgante.lfm.Model.Artist;
import com.lfm.rossellamorgante.lfm.ViewModel.LFMViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {

    private LFMViewModel viewModel;
    private RecyclerView recyclerView;
    private LFMRecAdapter mAdapter;
    private List<Artist> list;
    private ProgressBar spinner;
    private TextView textSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // This spinner is started during the data dowload
        spinner= findViewById(R.id.spinner);
        // This shows the status of the download (i.e. somethins is gone wrong)
        textSpinner= findViewById(R.id.textSpinner);
        // List of Artist displayed
        list= new ArrayList<Artist>();
        // Create the Recycleview
        recyclerView = (RecyclerView) findViewById(R.id.artist_list);
        recyclerView.setHasFixedSize(true);
        // Create the Adapter for Recycleview
        mAdapter = new LFMRecAdapter(list);
        // Set the Adapter to Recycleview
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Create the ViewModel and set the Bbserver for new Data
        // The Data can be an List of Artist or null
        viewModel = ViewModelProviders.of(this).get(LFMViewModel.class);
        viewModel.getArtists().observe(this,
                new Observer<List<Artist>>() {
                    @Override
                    public void onChanged(@Nullable final List<Artist> _arstist) {
                        if(_arstist!=null){
                            // The artstlist is populate and the spinner is turner off
                            updateRecicleView(_arstist);
                            setSpinner(false, false, "");
                        }else {
                            // otherwise something is gone wrong.
                            setSpinner(true, true, "Something was gone wrong. Try again!");
                        }
                    }
                }
            );

    }

    // Permit to update the list of Artist displayed in Recycleview
    public void updateRecicleView(List<Artist> _arstist){
        list.addAll(_arstist);
        mAdapter.notifyDataSetChanged();

    }

    // The ToolBar shows a search button and a search field.
    // To Clck on Button the search is started
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.searchmenu, menu);
        MenuItem searchViewItem = menu.findItem(R.id.app_bar_search);

        final SearchView searchView = (SearchView) searchViewItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            // The query string is passed to the ViewModel
            // And the spinner is activated
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.clearFocus();
                setSpinner(true,false,"Searching...");
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


    // Method to manage the spinner
    public void setSpinner(boolean show, boolean error, String message){
        if(show){
            recyclerView.setVisibility(View.GONE);
            textSpinner.setVisibility(View.VISIBLE);
            textSpinner.setText(message);

            if(error){
                spinner.setVisibility(View.GONE);
            }else
                spinner.setVisibility(View.VISIBLE);

        }else {
            recyclerView.setVisibility(View.VISIBLE);
            spinner.setVisibility(View.GONE);
            textSpinner.setVisibility(View.GONE);
        }

    }

}

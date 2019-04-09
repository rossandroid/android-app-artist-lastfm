/*
 * LFMViewModel
 * This Application implements the MVVM Pattern
 *
 * This Class extends ViewModel:
 * - create the MutableLiveData
 * - use the LFMNet Class to start the search
 * - update the list of Artists
 */
package com.lfm.rossellamorgante.lfm.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.lfm.rossellamorgante.lfm.Model.Artist;
import com.lfm.rossellamorgante.lfm.RepoNet.LFMNet;

import java.util.List;

public class LFMViewModel extends ViewModel {

    // Mutable Object: when it changes , it rises an event
    public MutableLiveData<List<Artist>> artists;

    public  LFMViewModel(){
         artists=new MutableLiveData<List<Artist>>();
    }

    public MutableLiveData<List<Artist>> getArtists() {
        return artists;
    }

    // Used to LFMNet to update the List<Artist>
    public void setArtist(List<Artist> list){
        artists.setValue(list);
    }
    // Search for an artist.
    // Pass itself to LFMNet to update Viewmodel
    public void search(String query){
        new LFMNet().search(query, this);
    }
}

package com.lfm.rossellamorgante.lfm.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.lfm.rossellamorgante.lfm.Model.Artist;

import java.util.List;

public class LFMViewModel extends ViewModel {

    private MutableLiveData<List<Artist>> artists;

    public void init(){

    }
    public MutableLiveData<List<Artist>> getArtists() {
        return artists;
    }

}

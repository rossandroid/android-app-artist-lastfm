package com.lfm.rossellamorgante.lfm.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.lfm.rossellamorgante.lfm.Model.Artist;
import com.lfm.rossellamorgante.lfm.RepoNet.LFMNet;

import java.util.List;

public class LFMViewModel extends ViewModel {

    public static LFMViewModel instance;
    public MutableLiveData<List<Artist>> artists;

    public  LFMViewModel(){
         artists=new MutableLiveData<List<Artist>>();
    }

    public MutableLiveData<List<Artist>> getArtists() {
        return artists;
    }

    public void setArtist(List<Artist> list){
        artists.setValue(list);
    }

    public void search(String query){
        new LFMNet().search(query, this);
    }
}

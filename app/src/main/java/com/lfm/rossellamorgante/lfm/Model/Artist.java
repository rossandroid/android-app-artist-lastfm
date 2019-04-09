package com.lfm.rossellamorgante.lfm.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Artist  implements Serializable {

    public String name;
    public String mbid;
    public String url;
    public List<Image> image;
    public String streamable;


    public class Image implements Serializable{
        @SerializedName("#text")
        public String text;
        public String size;
    }

}

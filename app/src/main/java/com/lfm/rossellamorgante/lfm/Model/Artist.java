/*
 * Artist
 * This Application implements the MVVM Pattern
 *
 * This Class is the Core Object.
 * It is serialized because this object is passed
 * from MainActivity to DetailActivity via Intent
 */
package com.lfm.rossellamorgante.lfm.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Artist  implements Serializable {

    public String name;
    public String mbid;
    public String url;
    public List<Image> image;


    public class Image implements Serializable{
        //Bind field #text that came from the server with internal one named text.
        @SerializedName("#text")
        public String text;
        public String size;
    }

}

package com.app.loodosapp.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Ahmet Oguz Er on 19.10.2019
 */
public class MovieModel implements Serializable {

    @Expose
    @SerializedName("Response")
    public String Response;
    @Expose
    @SerializedName("Website")
    public String Website;
    @Expose
    @SerializedName("Production")
    public String Production;
    @Expose
    @SerializedName("BoxOffice")
    public String BoxOffice;
    @Expose
    @SerializedName("DVD")
    public String DVD;
    @Expose
    @SerializedName("Type")
    public String Type;
    @Expose
    @SerializedName("imdbID")
    public String imdbID;
    @Expose
    @SerializedName("imdbVotes")
    public String imdbVotes;
    @Expose
    @SerializedName("imdbRating")
    public String imdbRating;
    @Expose
    @SerializedName("Metascore")
    public String Metascore;
    @Expose
    @SerializedName("Ratings")
    public List<Ratings> Ratings;
    @Expose
    @SerializedName("Poster")
    public String Poster;
    @Expose
    @SerializedName("Awards")
    public String Awards;
    @Expose
    @SerializedName("Country")
    public String Country;
    @Expose
    @SerializedName("Language")
    public String Language;
    @Expose
    @SerializedName("Plot")
    public String Plot;
    @Expose
    @SerializedName("Actors")
    public String Actors;
    @Expose
    @SerializedName("Writer")
    public String Writer;
    @Expose
    @SerializedName("Director")
    public String Director;
    @Expose
    @SerializedName("Genre")
    public String Genre;
    @Expose
    @SerializedName("Runtime")
    public String Runtime;
    @Expose
    @SerializedName("Released")
    public String Released;
    @Expose
    @SerializedName("Rated")
    public String Rated;
    @Expose
    @SerializedName("Year")
    public String Year;
    @Expose
    @SerializedName("Title")
    public String Title;

    public static class Ratings {
        @Expose
        @SerializedName("Value")
        public String Value;
        @Expose
        @SerializedName("Source")
        public String Source;
    }
}

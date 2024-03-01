package com.example.moviesearch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class data_model {

    public List<data_model> data;


    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("released_at")
    @Expose
    private String released_at;

    @SerializedName("director")
    @Expose
    private String director;

    @SerializedName("details_url")
    @Expose
    private String details_url;

    @SerializedName("poster_url")
    @Expose
    private String poster_url;

    @SerializedName("running_time")
    @Expose
    private String running_time;

    @SerializedName("text")
    @Expose
    private String text;





    public String getName(){
        return name;
    }
    public String getReleased_at(){
        return released_at;
    }
    public String getDirector(){
        return director;
    }
    public String getDetails_url(){
        return details_url;
    }
    public String getPoster_url(){
        return poster_url;
    }
    public String getRunning_time(){
        return running_time;
    }





}

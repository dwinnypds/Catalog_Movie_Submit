package com.example.asus.catalog_movie_submit;

import org.json.JSONObject;

public class MovieItems {
    private Integer id;
    private String poster_path;
    private String title;
    private String popularity;
    private String overview, releas_date;

    public int getId(){
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle(){
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }
    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPopularity() {
        return popularity;
    }
    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getPoster_path() {
        return poster_path;
    }
    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getReleas_date() {
        return releas_date;
    }
    public void setReleas_date(String releas_date) {
        this.releas_date = releas_date;
    }


    public MovieItems (JSONObject object){
        try{

            id = object.getInt("id");
            poster_path = object.getString("poster_path");
            title = object.getString("title");
            popularity = object.getString("popularity");
            overview = object.getString("overview");
            releas_date = object.getString("release_date");


        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

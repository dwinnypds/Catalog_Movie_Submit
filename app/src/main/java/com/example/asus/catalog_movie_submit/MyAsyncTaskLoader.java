package com.example.asus.catalog_movie_submit;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MyAsyncTaskLoader extends AsyncTaskLoader<ArrayList<MovieItems>> {

    private ArrayList<MovieItems> mData;
    private boolean mHasResult = false;

    private String mKumpulan_movie;
    public String API = "https://api.themoviedb.org/3/movie/popular?api_key=94fe5b9f899ce456efdc69788f7897ef&language=en-US&page=1";
    public String url = "hmm";
    String searchApi = "\n" +
            "https://api.themoviedb.org/3/search/movie?api_key=94fe5b9f899ce456efdc69788f7897ef&language=en-US&query=";
    String end = "&page=1&include_adult=false";

    public MyAsyncTaskLoader(final Context context, String kumpulan_movie) {
        super(context);

        onContentChanged();
        this.mKumpulan_movie = kumpulan_movie;
        if (!mKumpulan_movie.isEmpty()) {
            Log.e("cari", kumpulan_movie);
            url = searchApi + kumpulan_movie + end;
        }else{
            Log.e("cari", "no");
            url=API;
        }
    }

    @Override
    protected void onStartLoading() {
        if (takeContentChanged())
            forceLoad();
        else if (mHasResult)
            deliverResult(mData);
    }

    @Override
    protected void onReset() {
        super.onReset();
        onStopLoading();
        if (mHasResult) {
            onReleasResources(mData);
            mData = null;
            mHasResult = false;
        }
    }

    private static final String API_KEY = "94fe5b9f899ce456efdc69788f7897ef";

    @Override
    public ArrayList<MovieItems> loadInBackground() {
        SyncHttpClient client = new SyncHttpClient();
        final ArrayList<MovieItems> movieItemes = new ArrayList<>();

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                setUseSynchronousMode(true);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responObject = new JSONObject(result);
                    JSONArray list = responObject.getJSONArray("results");
                    Log.e("getData", list.toString());

                    for (int i = 0; i < list.length(); i++) {
                        JSONObject movie = list.getJSONObject(i);
                        MovieItems movieItems = new MovieItems(movie);
                        movieItemes.add(movieItems);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
        return movieItemes;
    }

    protected void onReleasResources(ArrayList<MovieItems> data) {
    }

}

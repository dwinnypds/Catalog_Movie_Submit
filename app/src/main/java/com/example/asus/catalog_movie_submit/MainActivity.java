package com.example.asus.catalog_movie_submit;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements  TextWatcher,
        LoaderManager.LoaderCallbacks<ArrayList<MovieItems>>{
    ListView listView;
    MovieAdapter adapter;
    EditText edt_cariMovie;
    ImageView imageView;
    Button btn_search;

    static final String EXTRAS_MOVIE = "EXTRAS_MOVIE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new MovieAdapter(this);
        adapter.notifyDataSetChanged();
        listView = (ListView) findViewById(R.id.List_View_Film);
        listView.setAdapter(adapter);
        imageView = (ImageView)findViewById(R.id.img_poster);

        edt_cariMovie = (EditText) findViewById(R.id.edt_CariJudul);
       // edt_cariMovie.setFocusable(false);
        btn_search = (Button) findViewById(R.id.btn_search);
        btn_search.setOnClickListener(myListener);

        String movie = edt_cariMovie.getText().toString();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRAS_MOVIE, movie);

        getLoaderManager().initLoader(0, bundle, this);


    }

    @Override
    public Loader<ArrayList<MovieItems>> onCreateLoader(int id, Bundle args) {

        String kumpulan_movie = " ";
        if (args != null){
            kumpulan_movie = args.getString(EXTRAS_MOVIE);
        }
        return new MyAsyncTaskLoader(this, kumpulan_movie);

    }

    @Override
    public void onLoadFinished(Loader<ArrayList<MovieItems>> loader, ArrayList<MovieItems> data) {
        adapter.setData(data);
    }

    View.OnClickListener myListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String movie = edt_cariMovie.getText().toString();

            if (TextUtils.isEmpty(movie)) return;

            Bundle bundle = new Bundle();
            bundle.putString(EXTRAS_MOVIE, movie);
            getLoaderManager().restartLoader(0, bundle, MainActivity.this);
        }
    };



    @Override
    public void onLoaderReset(Loader<ArrayList<MovieItems>> loader) {
        adapter.setData(null);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        /*if (s.toString().equals("")) {

        }*/
    }

    @Override
    public void afterTextChanged(Editable s) {

    }


}

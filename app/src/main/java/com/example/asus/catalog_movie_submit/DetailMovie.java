package com.example.asus.catalog_movie_submit;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class DetailMovie extends AppCompatActivity implements View.OnClickListener{
    private ArrayList<MovieItems> mData = new ArrayList<>();
    Context context;
    Intent intentdata;
    ImageView poster_path;
    TextView judul,popularity, releas_date, overview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        poster_path = findViewById(R.id.img_poster);
        poster_path.setOnClickListener(this);

        judul = findViewById(R.id.txt_judul);
        popularity = findViewById(R.id.txt_popularity);
        releas_date = findViewById(R.id.txt_releas_date);
        overview = findViewById(R.id.txt_sinopsis);

        judul.setText(getIntent().getStringExtra("judul"));
        popularity.setText(getIntent().getStringExtra("rating"));
        releas_date.setText(getIntent().getStringExtra("tayang"));
        overview.setText(getIntent().getStringExtra("sinopsis"));
        Glide
                .with(DetailMovie.this)
                .load("http://image.tmdb.org/t/p/w185/"+getIntent().getStringExtra("poster"))
                .into(poster_path);


    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_poster:
        Intent intent = new Intent(DetailMovie.this, PosterView.class);
                intent.putExtra("dtlPoster", getIntent().getStringExtra("poster"));
                v.getContext().startActivity(intent);
                break;
        }
    }
}

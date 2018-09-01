package com.example.asus.catalog_movie_submit;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends BaseAdapter {
    private ArrayList<MovieItems> mData = new ArrayList<>();
    private LayoutInflater mInflater;
    private Context context;
    int resources;
    int activity_detail_moviee;
    @NonNull
    List<MainActivity> object;

    public MovieAdapter(Context context) {
        this.context = context;

        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }




    public void setData(ArrayList<MovieItems> items) {
        mData = items;
        notifyDataSetChanged();
    }

    public void addItem(final MovieItems item) {
        mData.add(item);
        notifyDataSetChanged();
    }

    public void clearData() {
        mData.clear();
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    //tanda

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public MovieItems getItem(int position) {
        return mData.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.film_view, null);
            //holder.id = (TextView) convertView.findViewById(R.id.txt_id);
            holder.img_poster=(ImageView) convertView.findViewById(R.id.img_poster);
            holder.txt_judul_movie = (TextView) convertView.findViewById(R.id.txt_judul);
            holder.txt_waktu_tayang = (TextView) convertView.findViewById(R.id.txt_releas_date);
            holder.txt_popular= (TextView) convertView.findViewById(R.id.txt_popularity);
            holder.txt_sinopsis_film = (TextView) convertView.findViewById(R.id.txt_sinopsis);

            holder.layoutContainer = convertView.findViewById(R.id.layout_container);

            convertView.setTag(holder);


        }

        else {
            holder = (ViewHolder) convertView.getTag();
        }

        ImageView imageView=(ImageView) convertView.findViewById(R.id.img_poster);
        MovieItems movie = getItem(position);


        holder.txt_judul_movie.setText(mData.get(position).getTitle());
        holder.txt_popular.setText(mData.get(position).getPopularity());
        holder.txt_waktu_tayang.setText(mData.get(position).getReleas_date());
        Glide
                .with(context)
                .load("http://image.tmdb.org/t/p/w185/"+mData.get(position).getPoster_path())
                .into(holder.img_poster);
        //holder.txt_sinopsis_film.setText(mData.get(position).getOverview());

        holder.layoutContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailMovie.class);
                intent.putExtra("judul", mData.get(position).getTitle());
                intent.putExtra("rating", mData.get(position).getPopularity());
                intent.putExtra("tayang", mData.get(position).getReleas_date());
                intent.putExtra("sinopsis", mData.get(position).getOverview());
                intent.putExtra("poster", mData.get(position).getPoster_path());
                context.startActivity(intent);

            }
        });


        return convertView;

    }


    private static class ViewHolder {
        TextView txt_judul_movie;
        TextView txt_sinopsis_film;
        TextView txt_waktu_tayang;
        ImageView img_poster;
        TextView txt_popular;
        LinearLayout layoutContainer;
        //TextView id;
    }


}

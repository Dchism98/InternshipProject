package com.example.internshipproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;
/*
this class adapts a list of movie.results
 */
public class MovieAdapter extends BaseAdapter {
    private List<MovieResponse.Result> movieliist;
    private Context context;
    private LayoutInflater layoutInflater;

    public MovieAdapter(List<MovieResponse.Result> movieliist, Context context) {
        this.movieliist = movieliist;
        this.context = context;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)
        ;}

    @Override
    public int getCount() {
        return movieliist.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.movie_layout, parent, false);
        }
        ImageView imageView = convertView.findViewById(R.id.imageView);

        GlideApp.with(context)
                .load("https://image.tmdb.org/t/p/w185/"+movieliist.get(position).getPosterPath())
                .into(imageView);
        return convertView;
    }
}

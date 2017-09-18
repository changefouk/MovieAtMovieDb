package com.example.administrator.movieatmoviedb.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.movieatmoviedb.activity.MovieDetailActivity;
import com.example.administrator.movieatmoviedb.R;
import com.example.administrator.movieatmoviedb.dao.moviedao.Result;

import java.util.List;

/**
 * Created by Administrator on 14/9/2560.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private static final  String IMAGE_PATH_URL = "http://image.tmdb.org/t/p/w185/";

    private List<Result> movies;
    private int rowLayout;
    private Context context;



    public static class MovieViewHolder extends RecyclerView.ViewHolder {

        TextView mtitle;
        TextView msubtitle;
        TextView mdescription;
        TextView ratingScore;
        ImageView poster;
        View mView;

        public MovieViewHolder(View itemView) {
            super(itemView);
            mtitle = (TextView) itemView.findViewById(R.id.title);
            msubtitle = (TextView) itemView.findViewById(R.id.subtitle);
            mdescription = (TextView) itemView.findViewById(R.id.description);
            ratingScore = (TextView) itemView.findViewById(R.id.rating_score);
            poster = (ImageView) itemView.findViewById(R.id.poster_image);
            mView = itemView;
        }


    }

    public MovieAdapter(List<Result> movies, int rowLayout, Context context) {
        this.movies = movies;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent,
                                              int viewType) {
        View v = LayoutInflater.from(parent.getContext()).
                inflate(rowLayout,parent,false);


        return new MovieViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MovieViewHolder holder, final int position) {
        holder.mtitle.setText(movies.get(position).getmTitle());
        holder.msubtitle.setText(movies.get(position).getmReleaseDate());
        holder.mdescription.setText(movies.get(position).getmOverview());
        String vote = Double.toString(movies.get(position).getmVoteAverage());
        holder.ratingScore.setText(vote);

        Glide.with(context)
                .load(IMAGE_PATH_URL+movies.get(position).getmPosterPath())
                .into(holder.poster);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"select : "+movies.get(position).getmTitle(),Toast.LENGTH_SHORT).show();
                Intent i = new Intent(context,MovieDetailActivity.class);
                i.putExtra("Value",movies.get(position).getmId());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }


}

package com.example.administrator.movieatmoviedb.rest;

import com.example.administrator.movieatmoviedb.dao.moviedao.MovieDao;
import com.example.administrator.movieatmoviedb.dao.moviedetaildao.MovieDetailDao;
import com.example.administrator.movieatmoviedb.dao.movienowplaying.NowplayingDao;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Administrator on 14/9/2560.
 */

public interface ApiInterface {

    @GET("movie/top_rated")
    Call<MovieDao> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/{id}")
    Call<MovieDetailDao> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);

    @GET("movie/now_playing")
    Call<NowplayingDao> getNowPlatingMovie(@Query("api_key")String apiKey);

}

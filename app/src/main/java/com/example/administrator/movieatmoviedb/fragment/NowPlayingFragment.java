package com.example.administrator.movieatmoviedb.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.administrator.movieatmoviedb.R;
import com.example.administrator.movieatmoviedb.adapter.MovieAdapter;
import com.example.administrator.movieatmoviedb.dao.moviedao.Result;
import com.example.administrator.movieatmoviedb.dao.movienowplaying.NowplayingDao;
import com.example.administrator.movieatmoviedb.rest.ApiClient;
import com.example.administrator.movieatmoviedb.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 15/9/2560.
 */

public class NowPlayingFragment extends Fragment {

    RecyclerView recyclerView;
    private static final String TAG = "MainFragment.class";
    private final static String API_KEY = "e223915c735b1f9901c1960b049e544f";

    public NowPlayingFragment(){
        super();
    }

    public static NowPlayingFragment newInstance() {

        Bundle args = new Bundle();

        NowPlayingFragment fragment = new NowPlayingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_nowplaying, container, false);
        initInstance(rootView);
        return rootView;
    }

    private void initInstance(View rootView) {
        recyclerView  = rootView.findViewById(R.id.nowplaying_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ConnectApiService();
    }

    private void ConnectApiService() {

        if (API_KEY.isEmpty()) {
            Toast.makeText(getActivity().getApplicationContext(),
                    "Please obtain your API KEY first from themoviedb.org",
                    Toast.LENGTH_LONG).show();
            return;
        }

        ApiInterface apiInterface = ApiClient.getClient()
                .create(ApiInterface.class);

        Call<NowplayingDao> call = apiInterface.getNowPlatingMovie(API_KEY);
        call.enqueue(new Callback<NowplayingDao>() {
            @Override
            public void onResponse(Call<NowplayingDao> call, Response<NowplayingDao> response) {
                List<Result> movies = response.body().getmResults();

                recyclerView.setAdapter(new MovieAdapter(movies,R.layout.list_item_movie,getContext()));
            }

            @Override
            public void onFailure(Call<NowplayingDao> call, Throwable t) {

            }
        });
    }
}

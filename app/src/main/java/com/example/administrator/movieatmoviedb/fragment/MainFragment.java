package com.example.administrator.movieatmoviedb.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.administrator.movieatmoviedb.R;
import com.example.administrator.movieatmoviedb.adapter.MovieAdapter;
import com.example.administrator.movieatmoviedb.dao.moviedao.MovieDao;
import com.example.administrator.movieatmoviedb.dao.moviedao.Result;
import com.example.administrator.movieatmoviedb.rest.ApiClient;
import com.example.administrator.movieatmoviedb.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    private static final String TAG = "MainFragment.class";
    private final static String API_KEY = "e223915c735b1f9901c1960b049e544f";

    ProgressBar progressBar;
    RecyclerView recyclerView;

    public MainFragment() {
        // Required empty public constructor
        super();
    }

    public static MainFragment newInstance() {

        Bundle args = new Bundle();

        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        initInstance(rootView);
        return rootView;
    }

    private void initInstance(View rootView) {
        progressBar = rootView.findViewById(R.id.loadingProgress);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                connectApiService();


    }

    private void connectApiService() {

        if (API_KEY.isEmpty()) {
            Toast.makeText(getActivity().getApplicationContext(),
                    "Please obtain your API KEY first from themoviedb.org",
                    Toast.LENGTH_LONG).show();
            return;
        }

        ApiInterface apiService = ApiClient.getClient().
                create(ApiInterface.class);

        Call<MovieDao> call = apiService.getTopRatedMovies(API_KEY);
        call.enqueue(new Callback<MovieDao>() {
            @Override
            public void onResponse(Call<MovieDao> call, Response<MovieDao> response) {
                List<Result> movies = response.body().getmResults();
                Log.d(TAG,"Movie Recived : "+movies.size()+ " data");
                recyclerView.setAdapter(new MovieAdapter(movies,R.layout.list_item_movie,getContext()));
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<MovieDao> call, Throwable t) {
                Log.e(TAG, t.toString());
                Toast.makeText(getContext(),"Check Your Internet Connection",Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.GONE);
            }
        });

    }

}

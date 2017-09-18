package com.example.administrator.movieatmoviedb.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.movieatmoviedb.R;

/**
 * Created by Administrator on 15/9/2560.
 */

public class UpComingFragment extends Fragment {

    public UpComingFragment(){
        super();
    }

    public static UpComingFragment newInstance() {

        Bundle args = new Bundle();

        UpComingFragment fragment = new UpComingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_upcoming, container, false);
        initInstance(rootView);
        return rootView;
    }

    private void initInstance(View rootView) {

    }
}

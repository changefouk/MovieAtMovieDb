package com.example.administrator.movieatmoviedb.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.example.administrator.movieatmoviedb.R;

/**
 * Created by Administrator on 15/9/2560.
 */

public class FragmentPagerAdapter extends android.support.v4.app.FragmentPagerAdapter {

    private Context mContext;

    public FragmentPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new UpComingFragment();
        } else if (position == 1) {
            return new NowPlayingFragment();
        } else if (position == 2) {
            return new PopularFragment();
        } else {
            return new MainFragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return mContext.getString(R.string.upcoming_movie);
            case 1:
                return mContext.getString(R.string.nowplaying_movie);
            case 2:
                return mContext.getString(R.string.popular_movie);
            case 3:
                return mContext.getString(R.string.toprate_movie);
            default:
                return null;
        }
    }
}

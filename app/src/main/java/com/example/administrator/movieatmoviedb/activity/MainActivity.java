package com.example.administrator.movieatmoviedb.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.administrator.movieatmoviedb.fragment.FragmentPagerAdapter;
import com.example.administrator.movieatmoviedb.fragment.MainFragment;
import com.example.administrator.movieatmoviedb.R;

public class MainActivity extends AppCompatActivity {

    Toolbar  toolbar;
    ViewPager viewPager;
    TabLayout tabLayout;
    android.app.FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initInstance();

//        if (savedInstanceState == null){
//            getSupportFragmentManager().beginTransaction()
//                    .add(R.id.ContentContainer, MainFragment.newInstance())
//                    .commit();
//        }

    }

    private void initInstance() {
        toolbar = (Toolbar) findViewById(R.id.toolbars);
        setSupportActionBar(toolbar);

        viewPager = findViewById(R.id.viewpager);
        FragmentPagerAdapter fragmentPagerAdapter =
                new FragmentPagerAdapter(this,getSupportFragmentManager());
        viewPager.setAdapter(fragmentPagerAdapter);

        tabLayout = findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
    }
}

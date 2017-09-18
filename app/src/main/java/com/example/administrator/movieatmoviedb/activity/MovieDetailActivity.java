package com.example.administrator.movieatmoviedb.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.movieatmoviedb.R;
import com.example.administrator.movieatmoviedb.dao.moviedetaildao.Genre;
import com.example.administrator.movieatmoviedb.dao.moviedetaildao.MovieDetailDao;
import com.example.administrator.movieatmoviedb.dao.moviedetaildao.ProductionCompany;
import com.example.administrator.movieatmoviedb.dao.moviedetaildao.ProductionCountry;
import com.example.administrator.movieatmoviedb.rest.ApiClient;
import com.example.administrator.movieatmoviedb.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailActivity extends AppCompatActivity {

    private static final String TAG = MovieDetailActivity.class.getSimpleName().toString();
    private final static String API_KEY = "e223915c735b1f9901c1960b049e544f";
    private static final String IMAGE_PATH_URL = "http://image.tmdb.org/t/p/w500/";

    private int idMovie;
    Context context = this;
    Toolbar toolbar;

    private ProgressBar progressBar;

    private ImageView imagePosterMovie;
    private TextView titleMovie;
    private TextView genresMovie;
    private TextView releaseDateMovie;
    private TextView runtimeMovie;
    private TextView productMovie;
    private TextView overViewMovie;
    private TextView voteScoreMovie;
    private TextView countireMovie;


    String product = null;
    String countrieJa = "";
    String genreJa = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        //Recived id movie to show Detail
        Bundle bundle = getIntent().getExtras();
        idMovie = bundle.getInt("Value");
//        Toast.makeText(getApplicationContext(),
//                "Recived Movie Id : "+idMovie,
//                Toast.LENGTH_SHORT).show();

        initInstance();

    }

    private void initInstance() {
        setUi();
        if (API_KEY.isEmpty()) {
            Toast.makeText(getApplicationContext(),
                    "Please obtain your API KEY first from themoviedb.org",
                    Toast.LENGTH_LONG).show();
            return;
        }
        connectApiService();

    }

    private void setUi() {

        toolbar = findViewById(R.id.DetailToolbar);
        setSupportActionBar(toolbar);

        progressBar = findViewById(R.id.proGress);

        imagePosterMovie = (ImageView) findViewById(R.id.ivPoster);
        titleMovie = (TextView) findViewById(R.id.tv_title);
        genresMovie = (TextView) findViewById(R.id.tv_genres);
        releaseDateMovie = (TextView) findViewById(R.id.tv_releaseDate);
        runtimeMovie = (TextView) findViewById(R.id.tv_runtime);
        productMovie = (TextView) findViewById(R.id.tv_company);
        countireMovie = findViewById(R.id.tv_countire);
        overViewMovie = (TextView) findViewById(R.id.tv_overview);
        voteScoreMovie = (TextView) findViewById(R.id.tv_vote);

    }

    private void connectApiService() {

        ApiInterface apiService = ApiClient.getClient().
                create(ApiInterface.class);

        Call<MovieDetailDao> call = apiService.getMovieDetails(idMovie, API_KEY);
        call.enqueue(new Callback<MovieDetailDao>() {
            @Override
            public void onResponse(Call<MovieDetailDao> call, Response<MovieDetailDao> response) {

                Glide.with(context)
                        .load(IMAGE_PATH_URL + response.body().getmPosterPath())
                        .into(imagePosterMovie);

                titleMovie.setText(response.body().getmTitle().toString());

                List<Genre> genreList = response.body().getmGenres();
                if (genreList.size() == 0){
                    genresMovie.setText("Genre : -");
                }else {
                    for (int i = 0;i<genreList.size();i++){
                        String genre = genreJa;
                        String newGenre = genreList.get(i).getmName()+", ";
                        String totalGenre = genre+newGenre;
                        genreJa = totalGenre;
                    }
                    genresMovie.setText("Genre : " + genreJa);
                }

                releaseDateMovie.setText("Release date : " + response.body().getmReleaseDate());
                runtimeMovie.setText("Time Duration : " + response.body().getmRuntime() + " Minute");

                setCompanyName(response);

                setCountries(response);

                overViewMovie.setText("Overview :"+response.body().getmOverview());
                voteScoreMovie.setText("Vote Rate : " + response.body().getmVoteAverage() + " (" +
                        response.body().getmVoteCount() + " Vote)");

                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<MovieDetailDao> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }

    private void setCountries(Response<MovieDetailDao> response) {
        List<ProductionCountry> countries = response.body().getmProductionCountries();
        if (countries.size() == 0){
            countireMovie.setText("Countires : -");
        }else {
            for (int i = 0;i<countries.size();i++){
                String contrie = countrieJa;
                String newCountire = countries.get(i).getName()+"\n";
                String totalCountire = contrie+newCountire;

                countrieJa = totalCountire;
            }
            countireMovie.setText("Countries : " + countrieJa);
        }
    }

    private void setCompanyName(Response<MovieDetailDao> response) {

        // IF COMPANY TO MANY DO THIS

        List<ProductionCompany> companies = response.body().getmProductionCompanies();
        if (companies.size() == 0){
         productMovie.setText("Production : - ");
        }else {
            for (int i =0;i <companies.size();i++) {
                String productthis = product;
                if (productthis == null){
                    productthis = "";
                }
                String productinside = companies.get(i).getmName();
                String merg;

                merg = productthis+productinside+"\n";
                product = merg;
            }
            Log.d("TAG","PRODUCT = " +product);
            productMovie.setText("Production : "+product);
        }
    }
}

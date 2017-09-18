
package com.example.administrator.movieatmoviedb.dao.moviedao;

import java.util.List;

import com.google.gson.annotations.SerializedName;



public class MovieDao {

    @SerializedName("page")
    private int mPage;
    @SerializedName("results")
    private List<Result> mResults;
    @SerializedName("total_pages")
    private int mTotalPages;
    @SerializedName("total_results")
    private int mTotalResults;



    public List<Result> getResults() {
        return mResults;
    }

    public void setResults(List<Result> results) {
        mResults = results;
    }

    public int getmPage() {
        return mPage;
    }

    public void setmPage(int mPage) {
        this.mPage = mPage;
    }

    public List<Result> getmResults() {
        return mResults;
    }

    public void setmResults(List<Result> mResults) {
        this.mResults = mResults;
    }

    public int getmTotalPages() {
        return mTotalPages;
    }

    public void setmTotalPages(int mTotalPages) {
        this.mTotalPages = mTotalPages;
    }

    public int getmTotalResults() {
        return mTotalResults;
    }

    public void setmTotalResults(int mTotalResults) {
        this.mTotalResults = mTotalResults;
    }
}

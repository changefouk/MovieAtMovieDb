
package com.example.administrator.movieatmoviedb.dao.movienowplaying;

import java.util.List;

import com.example.administrator.movieatmoviedb.dao.moviedao.Result;
import com.google.gson.annotations.SerializedName;


public class NowplayingDao {

    @SerializedName("dates")
    private Dates mDates;
    @SerializedName("page")
    private Integer mPage;
    @SerializedName("results")
    private List<Result> mResults;
    @SerializedName("total_pages")
    private Integer mTotalPages;
    @SerializedName("total_results")
    private Integer mTotalResults;

    public Dates getmDates() {
        return mDates;
    }

    public void setmDates(Dates mDates) {
        this.mDates = mDates;
    }

    public Integer getmPage() {
        return mPage;
    }

    public void setmPage(Integer mPage) {
        this.mPage = mPage;
    }


    public Integer getmTotalPages() {
        return mTotalPages;
    }

    public void setmTotalPages(Integer mTotalPages) {
        this.mTotalPages = mTotalPages;
    }

    public Integer getmTotalResults() {
        return mTotalResults;
    }

    public void setmTotalResults(Integer mTotalResults) {
        this.mTotalResults = mTotalResults;
    }

    public List<Result> getmResults() {
        return mResults;
    }

    public void setmResults(List<Result> mResults) {
        this.mResults = mResults;
    }
}

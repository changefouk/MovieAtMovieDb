
package com.example.administrator.movieatmoviedb.dao.moviedetaildao;


import com.google.gson.annotations.SerializedName;


@SuppressWarnings("unused")
public class ProductionCompany {

    @SerializedName("id")
    private Integer mId;
    @SerializedName("name")
    private String mName;

    public Integer getmId() {
        return mId;
    }

    public void setmId(Integer mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }
}

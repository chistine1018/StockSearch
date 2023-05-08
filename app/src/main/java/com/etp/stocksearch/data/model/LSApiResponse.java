package com.etp.stocksearch.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class LSApiResponse {

    @SerializedName("stat")
    private String mStatus;

    @SerializedName("date")
    private String mDate;

    @SerializedName("title")
    private String mTitle;

    @SerializedName("data")
    private List<List<String>> mThreeCorporationList;

    @SerializedName("selectType")
    private String mSelectType;


    public String getmStatus() {
        return mStatus;
    }

    public void setmStatus(String mStatus) {
        this.mStatus = mStatus;
    }

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public List<List<String>> getmThreeCorporationList() {
        return mThreeCorporationList;
    }

    public void setmThreeCorporationList(List<List<String>> mThreeCorporationList) {
        this.mThreeCorporationList = mThreeCorporationList;
    }

    public String getmSelectType() {
        return mSelectType;
    }

    public void setmSelectType(String mSelectType) {
        this.mSelectType = mSelectType;
    }


}

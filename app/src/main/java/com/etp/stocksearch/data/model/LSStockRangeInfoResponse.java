package com.etp.stocksearch.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class LSStockRangeInfoResponse {

    @SerializedName("stat")
    private String mStatus;

    @SerializedName("date")
    private String mDate;

    @SerializedName("title")
    private String mTitle;

    @SerializedName("data")
    private List<List<String>> mStockList = new ArrayList<>();

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String mStatus) {
        this.mStatus = mStatus;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String mDate) {
        this.mDate = mDate;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public List<List<String>> getStockList() {
        return mStockList;
    }

    public void setStockList(List<List<String>> mStockList) {
        this.mStockList = mStockList;
    }
}

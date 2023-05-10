package com.etp.stocksearch.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class LSCorporationResponse {

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

    public List<List<String>> getThreeCorporationList() {
        return mThreeCorporationList;
    }

    public void setThreeCorporationList(List<List<String>> mThreeCorporationList) {
        this.mThreeCorporationList = mThreeCorporationList;
    }

    public String getSelectType() {
        return mSelectType;
    }

    public void setSelectType(String mSelectType) {
        this.mSelectType = mSelectType;
    }


}

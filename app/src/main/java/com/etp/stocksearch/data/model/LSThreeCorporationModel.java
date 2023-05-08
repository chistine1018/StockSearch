package com.etp.stocksearch.data.model;

public class LSThreeCorporationModel {

    private String mStockID = "";

    private String mStockName = "";

    private String mTransVolume = "";

    public String getStockID() {
        return mStockID;
    }

    public void setStockID(String stockID) {
        mStockID = stockID;
    }

    public String getStockName() {
        return mStockName;
    }

    public void setStockName(String stockName) {
        mStockName = stockName;
    }

    public String getTransVolume() {
        return mTransVolume;
    }

    public void setTransVolume(String transVolume) {
        mTransVolume = transVolume;
    }
}

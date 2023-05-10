package com.etp.stocksearch.data.model;

import java.util.ArrayList;
import java.util.List;



public class LSStockDetail {

    private String mStockID = "";

    private String mStockName = "";

    private List<LSCorporationDetail> mCorporationDetailList = new ArrayList<>();

    private List<LSStockRangeInfoDetail> mStockRangeInfoDetailList = new ArrayList<>();

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

    public List<LSCorporationDetail> getCorporationDetailList() {
        return mCorporationDetailList;
    }

    public void setCorporationDetailList(List<LSCorporationDetail> corporationDetailList) {
        mCorporationDetailList = corporationDetailList;
    }

    public List<LSStockRangeInfoDetail> getStockRangeInfoDetailList() {
        return mStockRangeInfoDetailList;
    }

    public void setStockRangeInfoDetailList(List<LSStockRangeInfoDetail> stockRangeInfoDetailList) {
        mStockRangeInfoDetailList = stockRangeInfoDetailList;
    }
}

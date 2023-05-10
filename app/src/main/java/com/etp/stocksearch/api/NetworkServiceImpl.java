package com.etp.stocksearch.api;


import com.etp.stocksearch.custom.application.StockSearchApplication;
import com.etp.stocksearch.data.model.LSCorporationResponse;
import com.etp.stocksearch.data.model.LSOperatingRevenueResponse;
import com.etp.stocksearch.data.model.LSStockRangeInfoResponse;

import java.util.List;

import retrofit2.Call;
import timber.log.Timber;

public class NetworkServiceImpl implements NetworkService {

    @Override
    public Call<LSStockRangeInfoResponse> callGetStockRangeInfo() {
        Timber.d("///" + "NetworkServiceImpl callGetStockRangeInfo");
        return StockSearchApplication.getInstance().getBaseStockApiManager().getmApi().callLSStockRangeInfo("json");
    }

    @Override
    public Call<LSCorporationResponse> callGetCorporation(String date, String selectType) {
        Timber.d("///" + "NetworkServiceImpl callGetCorporation");
        return StockSearchApplication.getInstance().getBaseStockApiManager().getmApi().callLSStockThreeCorporation("json", date, selectType);
    }

    @Override
    public Call<List<LSOperatingRevenueResponse>> callGetOperatingRevenue() {
        Timber.d("///" + "NetworkServiceImpl callGetOperatingRevenue");
        return StockSearchApplication.getInstance().getBaseOperatingApiManager().getmApi().callOperatingInfo();
    }
}

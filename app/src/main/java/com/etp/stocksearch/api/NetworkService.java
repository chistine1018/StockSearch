package com.etp.stocksearch.api;

import com.etp.stocksearch.data.model.LSCorporationResponse;
import com.etp.stocksearch.data.model.LSOperatingRevenueResponse;
import com.etp.stocksearch.data.model.LSStockRangeInfoResponse;

import java.util.List;

import retrofit2.Call;

public interface NetworkService {

    Call<LSStockRangeInfoResponse> callGetStockRangeInfo();

    Call<LSCorporationResponse> callGetCorporation(String date, String selectType);

    Call<List<LSOperatingRevenueResponse>> callGetOperatingRevenue();
}

package com.etp.stocksearch.api;

import com.etp.stocksearch.data.model.LSCorporationResponse;
import com.etp.stocksearch.data.model.LSOperatingRevenueResponse;
import com.etp.stocksearch.data.model.LSStockRangeInfoResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    /**
     * 每日盤後個股 上市三大法人買賣超
     * ex http://www.twse.com.tw/fund/T86?response=json&date=20210108&selectType=ALL
     *
     * @return
     */

    @GET("fund/T86")
    Call<LSCorporationResponse> callLSStockThreeCorporation(@Query("response") String response,
                                                            @Query("date") String date,
                                                            @Query("selectType") String selectType);

    /**
     * 每日盤後所有個股最高最低收盤價格
     * ex http://www.twse.com.tw/exchangeReport/STOCK_DAY_ALL?response=json
     *
     * @return
     */

    @GET("exchangeReport/STOCK_DAY_ALL")
    Call<LSStockRangeInfoResponse> callLSStockRangeInfo(@Query("response") String response);


    /**
     * 上市公司 每月營收
     * ex     https://openapi.twse.com.tw/v1/opendata/t187ap05_L
     *
     * @return
     */
    @GET("opendata/t187ap05_L")
    Call<List<LSOperatingRevenueResponse>> callOperatingInfo();
}

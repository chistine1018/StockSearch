package com.etp.stocksearch.api;

import com.etp.stocksearch.data.model.LSApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    /**
     * 每日盤後個股 上市三大法人買賣超
     * http://www.twse.com.tw/fund/T86?response=json&date=20210108&selectType=ALL
     *
     * @return
     */

    @GET("fund/T86")
    Call<LSApiResponse> callLSStockThreeCorporation(@Query("response") String response,
                                                    @Query("date") String date,
                                                    @Query("selectType") String selectType);
}

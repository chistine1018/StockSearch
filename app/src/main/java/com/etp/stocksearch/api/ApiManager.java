package com.etp.stocksearch.api;

import retrofit2.Retrofit;

public class ApiManager {

    private Api mApi;

    public Api getmApi() {
        return mApi;
    }

    public ApiManager(Retrofit retrofit) {
        this.mApi = retrofit.create(Api.class);
    }
}

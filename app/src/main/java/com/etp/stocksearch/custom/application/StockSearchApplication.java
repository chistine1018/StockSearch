package com.etp.stocksearch.custom.application;

import android.app.Application;

import com.etp.stocksearch.BuildConfig;
import com.etp.stocksearch.api.ApiManager;

import java.lang.ref.WeakReference;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

public class StockSearchApplication extends Application {

    private ApiManager mApimanager;
    private ApiManager mOperatingApiManager;
    private Retrofit mRetrofit;
    private Retrofit mOperatingRetrofit;


    private static String BASE_STOCK_URL = BuildConfig.BASE_STOCK_URL;

    private static WeakReference<StockSearchApplication> Instance;

    public static StockSearchApplication getInstance() {

        if (Instance != null && Instance.get() != null) {
            return Instance.get();
        } else {
            return null;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Instance = new WeakReference<>(this);
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    public Retrofit getBaseStockRetrofit() {

        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_STOCK_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(new OkHttpClient())
                    .build();
        }
        return mRetrofit;
    }

    public Retrofit getBaseOperatingRetrofit() {

        if (mOperatingRetrofit == null) {
            mOperatingRetrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_OPERATING_REVENUE)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(new OkHttpClient())
                    .build();
        }
        return mOperatingRetrofit;
    }

    public ApiManager getBaseStockApiManager() {

        if (mApimanager == null) {
            mApimanager = new ApiManager(getBaseStockRetrofit());
        }
        return mApimanager;
    }

    public ApiManager getBaseOperatingApiManager() {

        if (mOperatingApiManager == null) {
            mOperatingApiManager = new ApiManager(getBaseOperatingRetrofit());
        }
        return mOperatingApiManager;
    }

}

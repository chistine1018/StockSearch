package com.etp.stocksearch.viewmodel;

import android.text.TextUtils;


import com.etp.stocksearch.api.NetworkService;
import com.etp.stocksearch.api.NetworkServiceImpl;
import com.etp.stocksearch.custom.StockProperties;
import com.etp.stocksearch.data.dao.LSOperatingRevenueDao;
import com.etp.stocksearch.data.dao.LSOperatingRevenueImpl;
import com.etp.stocksearch.data.dao.LSStockDao;
import com.etp.stocksearch.data.dao.LSStockDaoImpl;
import com.etp.stocksearch.data.dao.LSStockPerCorporationDao;
import com.etp.stocksearch.data.dao.LSStockPerCorporationImpl;
import com.etp.stocksearch.data.model.LSCorporationDetail;
import com.etp.stocksearch.data.model.LSCorporationResponse;
import com.etp.stocksearch.data.model.LSOperatingRevenueResponse;
import com.etp.stocksearch.data.model.LSStockRangeInfoDetail;
import com.etp.stocksearch.data.model.LSStockRangeInfoResponse;
import com.etp.stocksearch.utils.ApiItemToDetail;
import com.etp.stocksearch.utils.DateUtility;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class SplashViewModel extends BaseViewModel {

    private NetworkService mNetworkService = new NetworkServiceImpl();
    private LSStockDao lsStockDao = new LSStockDaoImpl();
    private LSStockPerCorporationDao lsStockPerCorporationDao = new LSStockPerCorporationImpl();
    private LSOperatingRevenueDao lsOperatingRevenueDao = new LSOperatingRevenueImpl();

    public Input mInput = new Input();
    public Output mOutput = new Output();

    public boolean isSetUp() {
        boolean isSetUp = false;
        try {

            //region 訂閱 呼叫同步Data
            {
                Disposable disposable = mInput.callSyncData.subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {

                        if (aBoolean) {
                            syncData();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Timber.d("///" + "callSyncData disposable error: " + new Gson().toJson(throwable));

                    }
                });
                addDisposable(disposable);
            }
            isSetUp = true;
        } catch (Exception e) {

        }
        return isSetUp;
    }

    public void syncData() {
        mOutput.showWaitDialogOrDismiss.onNext(true);

        Timber.d("///" + "syncData start");
        //region Call 每日收盤個股資訊

        Callback<LSStockRangeInfoResponse> apiCallGetStockPerDay = new Callback<LSStockRangeInfoResponse>() {
            @Override
            public void onResponse(Call<LSStockRangeInfoResponse> call, Response<LSStockRangeInfoResponse> response) {

                Timber.d("///" + "apiCallGetStockPerDay success Request: " + new Gson().toJson(call.request()));

                try {
                    if (response.isSuccessful() && response.body() != null && TextUtils.equals(response.body().getStatus(), StockProperties.ApiStatus.SUCCESS)) {

                        List<LSStockRangeInfoDetail> lsStockRangeInfoDetails = new ArrayList<>();
                        //region 將API 字串陣列 轉換為 資料
                        for (List<String> apiStringList : response.body().getStockList()) {
                            lsStockRangeInfoDetails.add(ApiItemToDetail.StockRangeInfo.toPerStockRangeInfo(apiStringList));
                        }
                        //endregion

                        //region 將各股資訊匯入
                        lsStockDao.insertAndUpdateByDetail(lsStockRangeInfoDetails);
                        Timber.d("///" + "insert StockData success");
                        //endregion

                        //region Call 對應日期之 三大法人購買資訊
                        Callback<LSCorporationResponse> apiCallGetCorporationPerDay = new Callback<LSCorporationResponse>() {
                            @Override
                            public void onResponse(Call<LSCorporationResponse> call, Response<LSCorporationResponse> response) {

                                Timber.d("///" + "apiCallGetCorporationPerDay success Request: " + new Gson().toJson(call.request()));

                                if (response.isSuccessful() && response.body() != null && TextUtils.equals(response.body().getStatus(), StockProperties.ApiStatus.SUCCESS)) {

                                    List<LSCorporationDetail> corporationDetailList = new ArrayList<>();
                                    //region 將API 字串陣列 轉換為 資料
                                    for (List<String> apiStringList : response.body().getThreeCorporationList()) {
                                        corporationDetailList.add(ApiItemToDetail.ThreeCorporation.toPerStockCorporation(apiStringList));
                                    }
                                    //endregion

                                    //region 先查詢對應日期是否已有資料 若有表示已塞入過資料庫 則跳過
                                    if (lsStockPerCorporationDao.getItemListByDate(DateUtility.getCurrentDate()).size() == 0) {
                                        lsStockPerCorporationDao.insertAndUpdateByDetail(corporationDetailList);
                                        Timber.d("///" + "insert CorporationData success");

                                    }
                                    //endregion
                                }

                                mOutput.changeToMainPage.onNext(true);
                                mOutput.showWaitDialogOrDismiss.onNext(false);

                                //region Call 上市公司 每月營業收入
                                Callback<List<LSOperatingRevenueResponse>> apiCallGetOperatingRevenue = new Callback<List<LSOperatingRevenueResponse>>() {
                                    @Override
                                    public void onResponse(Call<List<LSOperatingRevenueResponse>> call, Response<List<LSOperatingRevenueResponse>> response) {
                                        try {

                                            if (response.isSuccessful() && response.body().size() > 0) {
                                                lsOperatingRevenueDao.insertAndUpdateByDetailList(response.body());
                                            }

                                        } catch (Exception e) {
                                            Timber.d("///" + "gson error: " + e);

                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<List<LSOperatingRevenueResponse>> call, Throwable t) {
                                        Timber.d("///" + "apiCallGetOperatingRevenue fail: " + t);
                                        mOutput.changeToMainPage.onNext(true);
                                    }
                                };
                                //endregion
                                mNetworkService.callGetOperatingRevenue().enqueue(apiCallGetOperatingRevenue);
                            }

                            @Override
                            public void onFailure(Call<LSCorporationResponse> call, Throwable t) {
                                mOutput.changeToMainPage.onNext(true);
                            }
                        };
                        //endregion
                        mNetworkService.callGetCorporation(DateUtility.getCurrentDate(), StockProperties.Type.ALL_BUT_NOT_0999).enqueue(apiCallGetCorporationPerDay);

                    }
                } catch (Exception e) {
                    Timber.d("///" + "apiCallGetStockPerDay try catch error: " + new Gson().toJson(e));

                }
            }

            @Override
            public void onFailure(Call<LSStockRangeInfoResponse> call, Throwable t) {
                Timber.d("///" + "apiCallGetCorporationPerDay onFailure: " + new Gson().toJson(t));
                mOutput.showWaitDialogOrDismiss.onNext(false);
                mOutput.changeToMainPage.onNext(true);
            }
        };
        //endregion
        Timber.d("///" + "apiCallGetCorporationPerDay call");
        mNetworkService.callGetStockRangeInfo().enqueue(apiCallGetStockPerDay);
    }


    public class Input {

        public PublishSubject<Boolean> callSyncData = PublishSubject.create();
    }

    public class Output {

        public PublishSubject<Boolean> showWaitDialogOrDismiss = PublishSubject.create();
        public PublishSubject<Boolean> changeToMainPage = PublishSubject.create();
    }
}

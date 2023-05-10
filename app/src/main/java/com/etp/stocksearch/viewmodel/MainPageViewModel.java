package com.etp.stocksearch.viewmodel;


import com.etp.stocksearch.data.dao.LSStockDao;
import com.etp.stocksearch.data.dao.LSStockDaoImpl;
import com.etp.stocksearch.data.dao.LSStockPerCorporationDao;
import com.etp.stocksearch.data.dao.LSStockPerCorporationImpl;
import com.etp.stocksearch.data.dao.LSStockPerDayDao;
import com.etp.stocksearch.data.dao.LSStockPerDayImpl;
import com.etp.stocksearch.data.model.LSCorporationDetail;
import com.etp.stocksearch.data.model.LSStockDetail;
import com.etp.stocksearch.data.model.LSStockRangeInfoDetail;
import com.etp.stocksearch.utils.DbItemToDetail;
import com.google.gson.Gson;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;


import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.BehaviorSubject;


import io.reactivex.subjects.PublishSubject;
import timber.log.Timber;

public class MainPageViewModel extends BaseViewModel {

    public Input mInput = new Input();
    public Output mOutput = new Output();

    private LSStockDao mStockDao = new LSStockDaoImpl();
    private LSStockPerDayDao mStockPerDayDao = new LSStockPerDayImpl();
    private LSStockPerCorporationDao mStockPerCorporationDao = new LSStockPerCorporationImpl();

    public boolean isSetUp() {

        boolean result = false;

        try {

            //region 訂閱 RecyclerView ItemClick 進行跳頁
            {
                Disposable disposable = mInput.recyclerViewItemClick.subscribe(new Consumer<LSStockDetail>() {
                    @Override
                    public void accept(LSStockDetail stockDetail) throws Exception {

                        if (stockDetail != null) {

                            mOutput.changeToStockDetailPage.onNext(stockDetail);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Timber.d("///" + "recyclerViewItemClick disposable error: " + new Gson().toJson(throwable));
                    }
                });
                addDisposable(disposable);
            }
            //endregion

            getStockDB();
            Timber.d("///" + "MainPageViewModel setUp success");
            result = true;
        } catch (Exception e) {
            Timber.d("///" + "MainViewModel error: " + e);
        }

        return result;
    }

    @Override
    public void onCleared() {
        super.onCleared();
        Timber.d("///" + "MainViewModel onCleared");
    }

    /**
     * 從DB中取得相關資料
     */
    private void getStockDB() {

        try {

            List<LSStockDetail> stockDetailList = DbItemToDetail.Stock.toStockDetailList(mStockDao.getAllItem());
            List<LSStockRangeInfoDetail> stockRangeInfoDetailList = DbItemToDetail.StockRange.toStockRangeInfoDetailList(mStockPerDayDao.getAllItem());
            List<LSCorporationDetail> corporationDetailList = DbItemToDetail.Corporation.toCorporationDetailList(mStockPerCorporationDao.getAllItem());

            for (LSStockDetail detail : stockDetailList) {

                detail.setCorporationDetailList(DbItemToDetail.Corporation.toCorporationDetailList(mStockPerCorporationDao.getItemListByStockID(detail.getStockID())));
                detail.setStockRangeInfoDetailList(DbItemToDetail.StockRange.toStockRangeInfoDetailList(mStockPerDayDao.getItemListByStockID(detail.getStockID())));
            }

            Timber.d("///" + "StockEntitySize: " + stockDetailList.size() + " StockPerDayEntitySize: " + stockRangeInfoDetailList.size() + " StockPerCorporationSize: " + corporationDetailList.size());

            Collections.sort(stockDetailList, new Comparator<LSStockDetail>() {
                @Override
                public int compare(LSStockDetail stockEntityFirst, LSStockDetail stockEntitySecond) {

                    int firstCorporationSize = stockEntityFirst.getCorporationDetailList().size();
                    int secondCorporationSize = stockEntitySecond.getCorporationDetailList().size();

                    int firstTotalOver = 0;
                    int secondTotalOver = 0;

                    if (firstCorporationSize > 0) {
                        firstTotalOver = Integer.parseInt(stockEntityFirst.getCorporationDetailList().get(firstCorporationSize - 1).getTotalOver());
                    }

                    if (secondCorporationSize > 0) {
                        secondTotalOver = Integer.parseInt(stockEntitySecond.getCorporationDetailList().get(secondCorporationSize - 1).getTotalOver());
                    }

                    return secondTotalOver - firstTotalOver;
                }
            });

            if (stockDetailList.size() > 0) {
                mOutput.showRecyclerView.onNext(stockDetailList);
            }

        } catch (Exception e) {
            Timber.d("///" + "getStockDB try catch error: " + new Gson().toJson(e));
        }
    }

    public class Input {

        public PublishSubject<LSStockDetail> recyclerViewItemClick = PublishSubject.create();
    }

    public class Output {

        public BehaviorSubject<List<LSStockDetail>> showRecyclerView = BehaviorSubject.create();

        public PublishSubject<LSStockDetail> changeToStockDetailPage = PublishSubject.create();
    }

}

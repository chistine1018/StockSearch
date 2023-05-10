package com.etp.stocksearch.viewmodel;


import com.etp.stocksearch.data.dao.LSOperatingRevenueDao;
import com.etp.stocksearch.data.dao.LSOperatingRevenueImpl;
import com.etp.stocksearch.data.model.LSCorporationDetail;
import com.etp.stocksearch.data.model.LSOperatingRevenueResponse;
import com.etp.stocksearch.data.model.LSStockDetail;
import com.etp.stocksearch.data.model.LSStockRangeInfoDetail;
import com.etp.stocksearch.utils.DbItemToDetail;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import io.reactivex.subjects.BehaviorSubject;
import timber.log.Timber;


public class StockDetailViewModel extends BaseViewModel {

    public Input input = new Input();
    public Output output = new Output();

    private LSStockDetail mStockDetail;
    private LSOperatingRevenueDao mOperatingRevenueDao = new LSOperatingRevenueImpl();

    public boolean isSetUp(String detailJson) {

        boolean isSetUp = false;

        try {

            mStockDetail = new Gson().fromJson(detailJson, new TypeToken<LSStockDetail>() {
            }.getType());
            LSStockRangeInfoDetail infoDetail = mStockDetail.getStockRangeInfoDetailList().get(mStockDetail.getStockRangeInfoDetailList().size() - 1);
            LSOperatingRevenueResponse detail = DbItemToDetail.OperatingRevenue.toOperatingRevenueDetail(mOperatingRevenueDao.findByStockID(infoDetail.getStockID()));

            output.stockRangeInfoSubject.onNext(infoDetail);
            output.corporationListSubject.onNext(mStockDetail.getCorporationDetailList());
            output.operatingRevenueSubject.onNext(detail);
            isSetUp = true;
        } catch (Exception e) {
            Timber.d("///" + "StockDetailViewModel setUp error: " + e);
        }

        return isSetUp;
    }

    public class Input {

    }

    public class Output {

        public BehaviorSubject<LSStockRangeInfoDetail> stockRangeInfoSubject = BehaviorSubject.create();
        public BehaviorSubject<List<LSCorporationDetail>> corporationListSubject = BehaviorSubject.create();
        public BehaviorSubject<LSOperatingRevenueResponse> operatingRevenueSubject = BehaviorSubject.create();
    }
}

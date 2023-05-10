package com.etp.stocksearch.data.dao;


import com.etp.stocksearch.data.enity.LSOperatingRevenueEntity;
import com.etp.stocksearch.data.enity.LSOperatingRevenueEntity_;
import com.etp.stocksearch.data.model.LSOperatingRevenueResponse;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import io.objectbox.Box;
import io.objectbox.BoxStore;
import timber.log.Timber;

public class LSOperatingRevenueImpl implements LSOperatingRevenueDao {

    Database database = new ObjectBoxDatabase();

    @Override
    public boolean insertAndUpdateByDetailList(List<LSOperatingRevenueResponse> insertDetailList) {
        boolean result = false;

        try {

            BoxStore boxStore = (BoxStore) database.getConnection();
            Box<LSOperatingRevenueEntity> operatingRevenueEntityBox = boxStore.boxFor(LSOperatingRevenueEntity.class);

            List<LSOperatingRevenueEntity> putList = new ArrayList<>();

            if (deleteAllItem()) {

                for (LSOperatingRevenueResponse detail : insertDetailList) {

                    LSOperatingRevenueEntity entity = new LSOperatingRevenueEntity();
                    entity.setStockID(detail.getStockID());
                    entity.setStockName(detail.getStockName());
                    entity.setCompanyType(detail.getCompanyType());
                    entity.setReleaseDate(detail.getReleaseDate());
                    entity.setDataDate(detail.getDataDate());
                    entity.setRevenue(detail.getRevenue());
                    entity.setCompareWithLastMonthRevenue(detail.getCompareWithLastMonthRevenue());
                    entity.setCompareWithLastMonthRevenuePercent(detail.getCompareWithLastMonthRevenuePercent());
                    entity.setCompareWithLastYearRevenue(detail.getCompareWithLastYearRevenue());
                    entity.setCompareWithLastYearRevenuePercent(detail.getCompareWithLastYearRevenuePercent());
                    entity.setTotalRevenueDeductThisMonth(detail.getTotalRevenueDeductThisMonth());
                    entity.setTotalRevenueDeductLastYear(detail.getTotalRevenueDeductLastYear());
                    entity.setTotalRevenueCompareWithLastTimePercent(detail.getTotalRevenueCompareWithLastTimePercent());
                    entity.setTag(detail.getTag());
                    putList.add(entity);
                }
            }

            operatingRevenueEntityBox.put(putList);
            result = true;
        } catch (Exception e) {
            Timber.d("///" + "insertAndUpdateByDetailList error: " + new Gson().toJson(e));

        }
        Timber.d("///" + "OperatingRevenue insertAndUpdateByDetailList result: " + result);
        return result;
    }

    @Override
    public boolean deleteAllItem() {
        boolean result = false;
        try {
            BoxStore boxStore = (BoxStore) database.getConnection();
            Box<LSOperatingRevenueEntity> operatingRevenueEntityBox = boxStore.boxFor(LSOperatingRevenueEntity.class);

            operatingRevenueEntityBox.removeAll();
            result = true;
        } catch (Exception e) {
            Timber.d("///" + "OperatingRevenue deleteAllItem error: " + new Gson().toJson(e));
        }

        Timber.d("///" + "OperatingRevenue deleteAllItem result: " + result);
        return result;
    }

    @Override
    public LSOperatingRevenueEntity findByStockID(String stockID) {
        LSOperatingRevenueEntity operatingRevenueEntity = null;

        try {
            BoxStore boxStore = (BoxStore) database.getConnection();
            Box<LSOperatingRevenueEntity> operatingRevenueEntityBox = boxStore.boxFor(LSOperatingRevenueEntity.class);

            operatingRevenueEntity = operatingRevenueEntityBox
                    .query()
                    .equal(LSOperatingRevenueEntity_.stockID, stockID)
                    .build()
                    .findUnique();
        } catch (Exception e) {
            Timber.d("///" + "OperatingRevenue findByStockID error: " + e);
        }

        return operatingRevenueEntity;
    }

    @Override
    public List<LSOperatingRevenueEntity> findAll() {
        List<LSOperatingRevenueEntity> result = null;

        try {

            BoxStore boxStore = (BoxStore) database.getConnection();
            Box<LSOperatingRevenueEntity> operatingRevenueEntityBox = boxStore.boxFor(LSOperatingRevenueEntity.class);

            result = operatingRevenueEntityBox
                    .query()
                    .build()
                    .find();

        } catch (Exception e) {
            Timber.d("///" + "OperatingRevenue findAll error: " + e);
        }

        return result;
    }
}

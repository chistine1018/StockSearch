package com.etp.stocksearch.data.dao;


import com.etp.stocksearch.data.enity.LSStockEntity;
import com.etp.stocksearch.data.enity.LSStockPerDayEntity;
import com.etp.stocksearch.data.enity.LSStockPerDayEntity_;
import com.etp.stocksearch.data.model.LSStockRangeInfoDetail;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import io.objectbox.Box;
import io.objectbox.BoxStore;
import timber.log.Timber;


public class LSStockPerDayImpl implements LSStockPerDayDao {

    Database database = new ObjectBoxDatabase();

    @Override
    public LSStockPerDayEntity setTargetByItem(LSStockEntity stockEntity) {

        LSStockPerDayEntity result = new LSStockPerDayEntity();

        try {

            BoxStore boxStore = (BoxStore) database.getConnection();
            Box<LSStockPerDayEntity> stockPerDayEntityBox = boxStore.boxFor(LSStockPerDayEntity.class);

            result.stockItem.setTarget(stockEntity);
            stockPerDayEntityBox.put(result);

        } catch (Exception e) {
            Timber.d("///" + "setTargetByItem(StockEntity) error: " + new Gson().toJson(e));
        }

        return result;
    }

    @Override
    public LSStockPerDayEntity setTargetByItem(LSStockEntity stockEntity, LSStockPerDayEntity stockPerDayEntity) {

        try {

            BoxStore boxStore = (BoxStore) database.getConnection();
            Box<LSStockPerDayEntity> stockPerDayEntityBox = boxStore.boxFor(LSStockPerDayEntity.class);

            stockPerDayEntity.stockItem.setTarget(stockEntity);
            stockPerDayEntityBox.put(stockPerDayEntity);

        } catch (Exception e) {
            Timber.d("///" + "setTargetByItem(StockEntity, StockPerDayEntity) error: " + new Gson().toJson(e));
        }

        return stockPerDayEntity;
    }

    @Override
    public boolean insertAndUpdateByDB(LSStockPerDayEntity insertDbItem) {

        boolean result = false;

        try {

            BoxStore boxStore = (BoxStore) database.getConnection();
            Box<LSStockPerDayEntity> stockPerDayEntityBox = boxStore.boxFor(LSStockPerDayEntity.class);

            LSStockPerDayEntity stockPerDayEntity = stockPerDayEntityBox
                    .query()
                    .equal(LSStockPerDayEntity_.stockID, insertDbItem.getStockID())
                    .equal(LSStockPerDayEntity_.date, insertDbItem.getDate())
                    .build()
                    .findUnique();

            if (stockPerDayEntity == null) {
                stockPerDayEntity = new LSStockPerDayEntity();
            }

            stockPerDayEntity.setStockID(insertDbItem.getStockID());
            stockPerDayEntity.setDate(insertDbItem.getDate());
            stockPerDayEntity.setOpenPrize(insertDbItem.getOpenPrize());
            stockPerDayEntity.setHighestPrize(insertDbItem.getHighestPrize());
            stockPerDayEntity.setLowestPrize(insertDbItem.getLowestPrize());
            stockPerDayEntity.setClosePrize(insertDbItem.getClosePrize());
            stockPerDayEntity.setRange(insertDbItem.getRange());
            stockPerDayEntity.setDealStock(insertDbItem.getDealStock());
            stockPerDayEntity.setDealCount(insertDbItem.getDealCount());
            stockPerDayEntity.setDealTotalPrize(insertDbItem.getDealTotalPrize());

            result = true;
        } catch (Exception e) {

            Timber.d("///" + "insertAndUpdateSingleStockPerDayByDB error: " + new Gson().toJson(e));
        }

        return result;
    }

    @Override
    public boolean insertAndUpdateByDetail(LSStockRangeInfoDetail insertDetailITem) {
        boolean result = false;

        try {

            BoxStore boxStore = (BoxStore) database.getConnection();
            Box<LSStockPerDayEntity> stockPerDayEntityBox = boxStore.boxFor(LSStockPerDayEntity.class);

            LSStockPerDayEntity stockPerDayEntity = stockPerDayEntityBox
                    .query()
                    .equal(LSStockPerDayEntity_.stockID, insertDetailITem.getStockID())
                    .equal(LSStockPerDayEntity_.date, insertDetailITem.getDate())
                    .build()
                    .findUnique();

            if (stockPerDayEntity == null) {
                stockPerDayEntity = new LSStockPerDayEntity();
            }

            stockPerDayEntity.setStockID(insertDetailITem.getStockID());
            stockPerDayEntity.setDate(insertDetailITem.getDate());
            stockPerDayEntity.setOpenPrize(insertDetailITem.getOpenPrize());
            stockPerDayEntity.setHighestPrize(insertDetailITem.getHighestPrize());
            stockPerDayEntity.setLowestPrize(insertDetailITem.getLowestPrize());
            stockPerDayEntity.setClosePrize(insertDetailITem.getClosePrize());
            stockPerDayEntity.setRange(insertDetailITem.getRange());
            stockPerDayEntity.setDealStock(insertDetailITem.getDealStock());
            stockPerDayEntity.setDealCount(insertDetailITem.getDealCount());
            stockPerDayEntity.setDealTotalPrize(insertDetailITem.getDealPrize());

            result = true;
        } catch (Exception e) {

            Timber.d("///" + "insertAndUpdateSingleStockPerDayByDetail error: " + new Gson().toJson(e));
        }

        return result;
    }

    private LSStockPerDayEntity getInsertAndUpdateStockPerDayByDB(LSStockPerDayEntity dbItem) {

        LSStockPerDayEntity result = new LSStockPerDayEntity();

        try {

            BoxStore boxStore = (BoxStore) database.getConnection();
            Box<LSStockPerDayEntity> stockPerDayEntityBox = boxStore.boxFor(LSStockPerDayEntity.class);

            result = stockPerDayEntityBox
                    .query()
                    .equal(LSStockPerDayEntity_.stockID, dbItem.getStockID())
                    .equal(LSStockPerDayEntity_.date, dbItem.getDate())
                    .build()
                    .findUnique();

            if (result == null) {
                result = new LSStockPerDayEntity();
            }

            result.setStockID(dbItem.getStockID());
            result.setDate(dbItem.getDate());
            result.setOpenPrize(dbItem.getOpenPrize());
            result.setHighestPrize(dbItem.getHighestPrize());
            result.setLowestPrize(dbItem.getLowestPrize());
            result.setClosePrize(dbItem.getClosePrize());
            result.setRange(dbItem.getRange());
            result.setDealStock(dbItem.getDealStock());
            result.setDealCount(dbItem.getDealCount());
            result.setDealTotalPrize(dbItem.getDealTotalPrize());

        } catch (Exception e) {

            Timber.d("///" + "getInsertAndUpdateStockPerDayByDB error: " + new Gson().toJson(e));
        }

        return result;
    }

    private LSStockPerDayEntity getInsertAndUpdateStockPerDayByDetail(LSStockRangeInfoDetail detail) {

        LSStockPerDayEntity result = new LSStockPerDayEntity();

        try {

            BoxStore boxStore = (BoxStore) database.getConnection();
            Box<LSStockPerDayEntity> stockPerDayEntityBox = boxStore.boxFor(LSStockPerDayEntity.class);

            result = stockPerDayEntityBox
                    .query()
                    .equal(LSStockPerDayEntity_.stockID, detail.getStockID())
                    .equal(LSStockPerDayEntity_.date, detail.getDate())
                    .build()
                    .findUnique();

            if (result == null) {
                result = new LSStockPerDayEntity();
            }

            result.setStockID(detail.getStockID());
            result.setDate(detail.getDate());
            result.setOpenPrize(detail.getOpenPrize());
            result.setHighestPrize(detail.getHighestPrize());
            result.setLowestPrize(detail.getLowestPrize());
            result.setClosePrize(detail.getClosePrize());
            result.setRange(detail.getRange());
            result.setDealStock(detail.getDealStock());
            result.setDealCount(detail.getDealCount());
            result.setDealTotalPrize(detail.getDealPrize());

        } catch (Exception e) {

            Timber.d("///" + "getInsertAndUpdateStockPerDayByDetail error: " + new Gson().toJson(e));
        }

        return result;
    }

    @Override
    public boolean insertAndUpdateByDB(List<LSStockPerDayEntity> insertDbItemList) {
        boolean result = false;

        try {

            BoxStore boxStore = (BoxStore) database.getConnection();
            Box<LSStockPerDayEntity> stockPerDayEntityBox = boxStore.boxFor(LSStockPerDayEntity.class);

            List<LSStockPerDayEntity> stockPerDayEntityList = new ArrayList<>();
            for (LSStockPerDayEntity dbItem : insertDbItemList) {
                stockPerDayEntityList.add(getInsertAndUpdateStockPerDayByDB(dbItem));
            }

            stockPerDayEntityBox.put(stockPerDayEntityList);
            result = true;
        } catch (Exception e) {

            Timber.d("///" + "insertAndUpdateAllStockPerDayByDB error: " + new Gson().toJson(e));
        }

        return result;
    }

    @Override
    public boolean insertAndUpdateByDetail(List<LSStockRangeInfoDetail> insertDetailItemList) {
        boolean result = false;

        try {

            BoxStore boxStore = (BoxStore) database.getConnection();
            Box<LSStockPerDayEntity> stockPerDayEntityBox = boxStore.boxFor(LSStockPerDayEntity.class);

            List<LSStockPerDayEntity> stockPerDayEntityList = new ArrayList<>();
            for (LSStockRangeInfoDetail detail : insertDetailItemList) {
                stockPerDayEntityList.add(getInsertAndUpdateStockPerDayByDetail(detail));
            }

            stockPerDayEntityBox.put(stockPerDayEntityList);

            result = true;
        } catch (Exception e) {

            Timber.d("///" + "insertAndUpdateAllStockPerDayByDetail error: " + new Gson().toJson(e));
        }

        return result;
    }

    @Override
    public List<LSStockPerDayEntity> getAllItem() {
        List<LSStockPerDayEntity> result = new ArrayList<>();

        try {

            BoxStore boxStore = (BoxStore) database.getConnection();
            Box<LSStockPerDayEntity> stockPerDayEntityBox = boxStore.boxFor(LSStockPerDayEntity.class);

            result = stockPerDayEntityBox
                    .query()
                    .build()
                    .find();

        } catch (Exception e) {

            Timber.d("///" + "getAllStockPerDay error: " + new Gson().toJson(e));
        }

        return result;
    }

    @Override
    public List<LSStockPerDayEntity> getItemListByStockID(String stockID) {
        List<LSStockPerDayEntity> result = new ArrayList<>();

        try {

            BoxStore boxStore = (BoxStore) database.getConnection();
            Box<LSStockPerDayEntity> stockPerDayEntityBox = boxStore.boxFor(LSStockPerDayEntity.class);

            result = stockPerDayEntityBox
                    .query()
                    .equal(LSStockPerDayEntity_.stockID, stockID)
                    .build()
                    .find();

        } catch (Exception e) {

            Timber.d("///" + "getStockPerDayByStockID error: " + new Gson().toJson(e));
        }

        return result;
    }

    @Override
    public List<LSStockPerDayEntity> getItemListByDate(String date) {
        List<LSStockPerDayEntity> result = new ArrayList<>();

        try {

            BoxStore boxStore = (BoxStore) database.getConnection();
            Box<LSStockPerDayEntity> stockPerDayEntityBox = boxStore.boxFor(LSStockPerDayEntity.class);

            result = stockPerDayEntityBox
                    .query()
                    .equal(LSStockPerDayEntity_.date, date)
                    .build()
                    .find();

        } catch (Exception e) {

            Timber.d("///" + "getStockPerDayByDate error: " + new Gson().toJson(e));
        }

        return result;
    }

    @Override
    public LSStockPerDayEntity getItemByStockIDAndDate(String stockID, String date) {
        LSStockPerDayEntity result = new LSStockPerDayEntity();

        try {

            BoxStore boxStore = (BoxStore) database.getConnection();
            Box<LSStockPerDayEntity> stockPerDayEntityBox = boxStore.boxFor(LSStockPerDayEntity.class);

            result = stockPerDayEntityBox
                    .query()
                    .equal(LSStockPerDayEntity_.stockID, stockID)
                    .equal(LSStockPerDayEntity_.date, date)
                    .build()
                    .findUnique();

        } catch (Exception e) {

            Timber.d("///" + "getStockPerDayByStockIDAndDate error: " + new Gson().toJson(e));
        }

        return result;
    }
}

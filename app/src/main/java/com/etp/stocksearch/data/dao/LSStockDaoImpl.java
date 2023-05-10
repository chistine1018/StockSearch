package com.etp.stocksearch.data.dao;


import com.etp.stocksearch.data.enity.LSStockEntity;
import com.etp.stocksearch.data.enity.LSStockEntity_;
import com.etp.stocksearch.data.enity.LSStockPerDayEntity;
import com.etp.stocksearch.data.enity.LSStockPerDayEntity_;
import com.etp.stocksearch.data.model.LSStockRangeInfoDetail;
import com.etp.stocksearch.utils.DateUtility;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import io.objectbox.Box;
import io.objectbox.BoxStore;
import timber.log.Timber;


public class LSStockDaoImpl implements LSStockDao {

    Database database = new ObjectBoxDatabase();

    private LSStockPerDayDao mStockPerDayDao = new LSStockPerDayImpl();

    @Override
    public boolean insertAndUpdateByDB(LSStockEntity insertDbItem) {
        boolean result = false;

        try {

            BoxStore boxStore = (BoxStore) database.getConnection();
            Box<LSStockEntity> stockEntityBox = boxStore.boxFor(LSStockEntity.class);

            LSStockEntity stockEntity = stockEntityBox
                    .query()
                    .equal(LSStockEntity_.stockID, insertDbItem.getStockID())
                    .build()
                    .findUnique();

            if (stockEntity == null) {
                stockEntity = new LSStockEntity();
            }

            stockEntity.setStockID(insertDbItem.getStockID());
            stockEntity.setStockName(insertDbItem.getStockName());
            stockEntity.setStockMonthAvg(insertDbItem.getStockMonthAvg());
            stockEntity.setType(insertDbItem.getType());

            stockEntityBox.put(stockEntity);
            result = true;
        } catch (Exception e) {

            Timber.d("///" + "insertAndUpdateSingleStockByDB error: " + new Gson().toJson(e));
        }

        return result;
    }

    @Override
    public boolean insertAndUpdateByDetail(LSStockRangeInfoDetail insertDetailITem) {
        boolean result = false;

        try {

            BoxStore boxStore = (BoxStore) database.getConnection();
            Box<LSStockEntity> stockEntityBox = boxStore.boxFor(LSStockEntity.class);

            LSStockEntity stockEntity = stockEntityBox
                    .query()
                    .equal(LSStockEntity_.stockID, insertDetailITem.getStockID())
                    .build()
                    .findUnique();

            if (stockEntity == null) {
                stockEntity = new LSStockEntity();
            }

            stockEntity.setStockID(insertDetailITem.getStockID());
            stockEntity.setStockName(insertDetailITem.getStockName());
            stockEntity.setType(insertDetailITem.getType());
            stockEntity.setStockMonthAvg(insertDetailITem.getMonthAvg());

            stockEntityBox.put(stockEntity);
            result = true;
        } catch (Exception e) {

            Timber.d("///" + "insertAndUpdateSingleStockByDB error: " + new Gson().toJson(e));
        }

        return result;
    }

    private LSStockEntity getInsertAndUpdateDbItemByDB(LSStockEntity insertDbItem) {

        LSStockEntity dbItem = null;

        try {

            BoxStore boxStore = (BoxStore) database.getConnection();
            Box<LSStockEntity> stockEntityBox = boxStore.boxFor(LSStockEntity.class);

            dbItem = stockEntityBox
                    .query()
                    .equal(LSStockEntity_.stockID, insertDbItem.getStockID())
                    .build()
                    .findUnique();

            if (dbItem == null) {
                dbItem = new LSStockEntity();
            }

            dbItem.setStockID(insertDbItem.getStockID());
            dbItem.setStockName(insertDbItem.getStockName());
            dbItem.setStockMonthAvg(insertDbItem.getStockMonthAvg());
            dbItem.setType(insertDbItem.getType());

        } catch (Exception e) {

            Timber.d("///" + "insertAndUpdateSingleStockByDB error: " + new Gson().toJson(e));
        }

        return dbItem;
    }

    private LSStockEntity getInsertAndUpdateDbItemByDetail(LSStockRangeInfoDetail insertDetailItem) {

        LSStockEntity dbItem = null;

        try {

            BoxStore boxStore = (BoxStore) database.getConnection();
            Box<LSStockEntity> stockEntityBox = boxStore.boxFor(LSStockEntity.class);

            dbItem = stockEntityBox
                    .query()
                    .equal(LSStockEntity_.stockID, insertDetailItem.getStockID())
                    .build()
                    .findUnique();

            if (dbItem == null) {
                dbItem = new LSStockEntity();
            }

            dbItem.setStockID(insertDetailItem.getStockID());
            dbItem.setStockName(insertDetailItem.getStockName());
            dbItem.setType(insertDetailItem.getType());
            dbItem.setStockMonthAvg(insertDetailItem.getMonthAvg());

        } catch (Exception e) {

            Timber.d("///" + "insertAndUpdateSingleStockByDB error: " + new Gson().toJson(e));
        }

        return dbItem;
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
    public boolean insertAndUpdateByDB(List<LSStockEntity> insertDbItemList) {

        boolean result = false;

        try {

            BoxStore boxStore = (BoxStore) database.getConnection();
            Box<LSStockEntity> stockEntityBox = boxStore.boxFor(LSStockEntity.class);

            List<LSStockEntity> stockEntityList = new ArrayList<>();
            for (LSStockEntity entity : insertDbItemList) {
                stockEntityList.add(getInsertAndUpdateDbItemByDB(entity));
            }

            stockEntityBox.put(stockEntityList);
            result = true;
        } catch (Exception e) {

            Timber.d("///" + "insertAndUpdateAllStockByDB error: " + new Gson().toJson(e));
        }

        return result;
    }

    @Override
    public boolean insertAndUpdateByDetail(List<LSStockRangeInfoDetail> insertDetailItemList) {
        boolean result = false;

        try {

            BoxStore boxStore = (BoxStore) database.getConnection();
            Box<LSStockEntity> stockEntityBox = boxStore.boxFor(LSStockEntity.class);
            Box<LSStockPerDayEntity> stockPerDayEntityBox = boxStore.boxFor(LSStockPerDayEntity.class);

            List<LSStockEntity> stockEntityList = new ArrayList<>();
            List<LSStockPerDayEntity> stockPerDayEntityList = null;

            if (mStockPerDayDao.getItemListByDate(DateUtility.getCurrentDate()).size() == 0) {
                stockPerDayEntityList = new ArrayList<>();
            }

            for (LSStockRangeInfoDetail detail : insertDetailItemList) {
                stockEntityList.add(getInsertAndUpdateDbItemByDetail(detail));
                if (stockPerDayEntityList != null) {
                    stockPerDayEntityList.add(getInsertAndUpdateStockPerDayByDetail(detail));
                }
            }

            stockEntityBox.put(stockEntityList);
            if (stockPerDayEntityList != null && stockPerDayEntityList.size() > 0) {
                stockPerDayEntityBox.put(stockPerDayEntityList);
            }
            result = true;
        } catch (Exception e) {

            Timber.d("///" + "insertAndUpdateAllStockByDetail error: " + new Gson().toJson(e));
        }

        return result;
    }

    @Override
    public List<LSStockEntity> getAllItem() {

        List<LSStockEntity> result = new ArrayList<>();

        try {

            BoxStore boxStore = (BoxStore) database.getConnection();
            Box<LSStockEntity> stockEntityBox = boxStore.boxFor(LSStockEntity.class);

            result = stockEntityBox
                    .query()
                    .build()
                    .find();

        } catch (Exception e) {

            Timber.d("///" + "getAllStock error: " + new Gson().toJson(e));
        }

        return result;
    }

    @Override
    public List<LSStockEntity> getItemListByType(String type) {
        List<LSStockEntity> result = new ArrayList<>();

        try {

            BoxStore boxStore = (BoxStore) database.getConnection();
            Box<LSStockEntity> stockEntityBox = boxStore.boxFor(LSStockEntity.class);

            result = stockEntityBox
                    .query()
                    .equal(LSStockEntity_.type, type)
                    .build()
                    .find();

        } catch (Exception e) {

            Timber.d("///" + "getStockByType error: " + new Gson().toJson(e));
        }

        return result;
    }

    @Override
    public LSStockEntity getItemByID(String stockID) {
        LSStockEntity result = new LSStockEntity();

        try {

            BoxStore boxStore = (BoxStore) database.getConnection();
            Box<LSStockEntity> stockEntityBox = boxStore.boxFor(LSStockEntity.class);

            result = stockEntityBox
                    .query()
                    .equal(LSStockEntity_.stockID, stockID)
                    .build()
                    .findUnique();

        } catch (Exception e) {

            Timber.d("///" + "getSpecificStockByID error: " + new Gson().toJson(e));
        }

        return result;
    }
}

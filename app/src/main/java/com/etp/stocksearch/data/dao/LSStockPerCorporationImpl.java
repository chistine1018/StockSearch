package com.etp.stocksearch.data.dao;


import com.etp.stocksearch.data.enity.LSStockEntity;
import com.etp.stocksearch.data.enity.LSStockPerCorporationEntity;
import com.etp.stocksearch.data.enity.LSStockPerCorporationEntity_;
import com.etp.stocksearch.data.model.LSCorporationDetail;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import io.objectbox.Box;
import io.objectbox.BoxStore;
import timber.log.Timber;


public class LSStockPerCorporationImpl implements LSStockPerCorporationDao {

    Database database = new ObjectBoxDatabase();

    @Override
    public LSStockPerCorporationEntity setTargetByItem(LSStockEntity stockEntity) {

        LSStockPerCorporationEntity result = new LSStockPerCorporationEntity();

        try {

            BoxStore boxStore = (BoxStore) database.getConnection();
            Box<LSStockPerCorporationEntity> stockPerCorporationEntityBox = boxStore.boxFor(LSStockPerCorporationEntity.class);

            result.stockItem.setTarget(stockEntity);
            stockPerCorporationEntityBox.put(result);

        } catch (Exception e) {
            Timber.d("///" + "setTargetByItem error: " + new Gson().toJson(e));
        }

        return result;
    }

    @Override
    public LSStockPerCorporationEntity setTargetByItem(LSStockEntity stockEntity, LSStockPerCorporationEntity stockPerCorporationEntity) {

        try {

            BoxStore boxStore = (BoxStore) database.getConnection();
            Box<LSStockPerCorporationEntity> stockPerCorporationEntityBox = boxStore.boxFor(LSStockPerCorporationEntity.class);

            stockPerCorporationEntity.stockItem.setTarget(stockEntity);
            stockPerCorporationEntityBox.put(stockPerCorporationEntity);

        } catch (Exception e) {
            Timber.d("///" + "setTargetByItem error: " + new Gson().toJson(e));
        }

        return stockPerCorporationEntity;
    }

    @Override
    public boolean insertAndUpdateByDB(LSStockPerCorporationEntity insertDbItem) {

        boolean result = false;

        try {

            BoxStore boxStore = (BoxStore) database.getConnection();
            Box<LSStockPerCorporationEntity> stockPerCorporationEntityBox = boxStore.boxFor(LSStockPerCorporationEntity.class);

            LSStockPerCorporationEntity stockPerCorporationEntity = stockPerCorporationEntityBox
                    .query()
                    .equal(LSStockPerCorporationEntity_.stockID, insertDbItem.getStockID())
                    .equal(LSStockPerCorporationEntity_.date, insertDbItem.getDate())
                    .build()
                    .findUnique();

            if (stockPerCorporationEntity == null) {
                stockPerCorporationEntity = new LSStockPerCorporationEntity();
            }

            stockPerCorporationEntity.setStockID(insertDbItem.getStockID());
            stockPerCorporationEntity.setDate(insertDbItem.getDate());
            stockPerCorporationEntity.setForeignBuy(insertDbItem.getForeignBuy());
            stockPerCorporationEntity.setForeignSell(insertDbItem.getForeignSell());
            stockPerCorporationEntity.setForeignOver(insertDbItem.getForeignOver());
            stockPerCorporationEntity.setInvestmentBuy(insertDbItem.getInvestmentBuy());
            stockPerCorporationEntity.setInvestmentSell(insertDbItem.getInvestmentSell());
            stockPerCorporationEntity.setInvestmentOver(insertDbItem.getInvestmentOver());
            stockPerCorporationEntity.setSelfBuy(insertDbItem.getSelfBuy());
            stockPerCorporationEntity.setSelfSell(insertDbItem.getSelfSell());
            stockPerCorporationEntity.setSelfOver(insertDbItem.getSelfOver());
            stockPerCorporationEntity.setTotalOver(insertDbItem.getTotalOver());

            stockPerCorporationEntityBox.put(stockPerCorporationEntity);
            result = true;
        } catch (Exception e) {
            Timber.d("///" + "insertAndUpdateByDB error: " + new Gson().toJson(e));
        }

        return result;
    }

    @Override
    public boolean insertAndUpdateByDetail(LSCorporationDetail insertDetailITem) {

        boolean result = false;

        try {

            BoxStore boxStore = (BoxStore) database.getConnection();
            Box<LSStockPerCorporationEntity> stockPerCorporationEntityBox = boxStore.boxFor(LSStockPerCorporationEntity.class);

            LSStockPerCorporationEntity stockPerCorporationEntity = stockPerCorporationEntityBox
                    .query()
                    .equal(LSStockPerCorporationEntity_.stockID, insertDetailITem.getStockID())
                    .equal(LSStockPerCorporationEntity_.date, insertDetailITem.getDate())
                    .build()
                    .findUnique();

            if (stockPerCorporationEntity == null) {
                stockPerCorporationEntity = new LSStockPerCorporationEntity();
            }

            stockPerCorporationEntity.setStockID(insertDetailITem.getStockID());
            stockPerCorporationEntity.setDate(insertDetailITem.getDate());
            stockPerCorporationEntity.setForeignBuy(insertDetailITem.getForeignBuy());
            stockPerCorporationEntity.setForeignSell(insertDetailITem.getForeignSell());
            stockPerCorporationEntity.setForeignOver(insertDetailITem.getForeignOver());
            stockPerCorporationEntity.setInvestmentBuy(insertDetailITem.getInvestmentBuy());
            stockPerCorporationEntity.setInvestmentSell(insertDetailITem.getInvestmentSell());
            stockPerCorporationEntity.setInvestmentOver(insertDetailITem.getInvestmentOver());
            stockPerCorporationEntity.setSelfBuy(insertDetailITem.getSelfBuy());
            stockPerCorporationEntity.setSelfSell(insertDetailITem.getSelfSell());
            stockPerCorporationEntity.setSelfOver(insertDetailITem.getSelfOver());
            stockPerCorporationEntity.setTotalOver(insertDetailITem.getTotalOver());

            stockPerCorporationEntityBox.put(stockPerCorporationEntity);
            result = true;
        } catch (Exception e) {
            Timber.d("///" + "insertAndUpdateByDetail error: " + new Gson().toJson(e));
        }

        return result;
    }

    private LSStockPerCorporationEntity getItemByDbItem(LSStockPerCorporationEntity dbItem) {

        LSStockPerCorporationEntity result = new LSStockPerCorporationEntity();

        try {

            BoxStore boxStore = (BoxStore) database.getConnection();
            Box<LSStockPerCorporationEntity> stockPerCorporationEntityBox = boxStore.boxFor(LSStockPerCorporationEntity.class);

            result = stockPerCorporationEntityBox
                    .query()
                    .equal(LSStockPerCorporationEntity_.stockID, dbItem.getStockID())
                    .equal(LSStockPerCorporationEntity_.date, dbItem.getDate())
                    .build()
                    .findUnique();

            if (result == null) {
                result = new LSStockPerCorporationEntity();
            }

            result.setStockID(dbItem.getStockID());
            result.setDate(dbItem.getDate());
            result.setForeignBuy(dbItem.getForeignBuy());
            result.setForeignSell(dbItem.getForeignSell());
            result.setForeignOver(dbItem.getForeignOver());
            result.setInvestmentBuy(dbItem.getInvestmentBuy());
            result.setInvestmentSell(dbItem.getInvestmentSell());
            result.setInvestmentOver(dbItem.getInvestmentOver());
            result.setSelfBuy(dbItem.getSelfBuy());
            result.setSelfSell(dbItem.getSelfSell());
            result.setSelfOver(dbItem.getSelfOver());
            result.setTotalOver(dbItem.getTotalOver());

        } catch (Exception e) {
            Timber.d("///" + "getItemByDbItem error: " + new Gson().toJson(e));
        }

        return result;
    }

    private LSStockPerCorporationEntity getItemByDetail(LSCorporationDetail detail) {

        LSStockPerCorporationEntity result = new LSStockPerCorporationEntity();

        try {

            BoxStore boxStore = (BoxStore) database.getConnection();
            Box<LSStockPerCorporationEntity> stockPerCorporationEntityBox = boxStore.boxFor(LSStockPerCorporationEntity.class);

            result = stockPerCorporationEntityBox
                    .query()
                    .equal(LSStockPerCorporationEntity_.stockID, detail.getStockID())
                    .equal(LSStockPerCorporationEntity_.date, detail.getDate())
                    .build()
                    .findUnique();

            if (result == null) {
                result = new LSStockPerCorporationEntity();
            }

            result.setStockID(detail.getStockID());
            result.setDate(detail.getDate());
            result.setForeignBuy(detail.getForeignBuy());
            result.setForeignSell(detail.getForeignSell());
            result.setForeignOver(detail.getForeignOver());
            result.setInvestmentBuy(detail.getInvestmentBuy());
            result.setInvestmentSell(detail.getInvestmentSell());
            result.setInvestmentOver(detail.getInvestmentOver());
            result.setSelfBuy(detail.getSelfBuy());
            result.setSelfSell(detail.getSelfSell());
            result.setSelfOver(detail.getSelfOver());
            result.setTotalOver(detail.getTotalOver());

        } catch (Exception e) {
            Timber.d("///" + "getItemByDetail error: " + new Gson().toJson(e));
        }

        return result;
    }

    @Override
    public boolean insertAndUpdateByDB(List<LSStockPerCorporationEntity> insertDbItemList) {

        boolean result = false;

        try {

            BoxStore boxStore = (BoxStore) database.getConnection();
            Box<LSStockPerCorporationEntity> stockPerCorporationEntityBox = boxStore.boxFor(LSStockPerCorporationEntity.class);

            List<LSStockPerCorporationEntity> stockPerCorporationEntityList = new ArrayList<>();
            for (LSStockPerCorporationEntity dbItem : insertDbItemList) {
                stockPerCorporationEntityList.add(getItemByDbItem(dbItem));
            }

            stockPerCorporationEntityBox.put(stockPerCorporationEntityList);
            result = true;

        } catch (Exception e) {
            Timber.d("///" + "insertAndUpdateByDB error: " + new Gson().toJson(e));
        }

        return result;
    }

    @Override
    public boolean insertAndUpdateByDetail(List<LSCorporationDetail> insertDetailItemList) {

        boolean result = false;

        try {

            BoxStore boxStore = (BoxStore) database.getConnection();
            Box<LSStockPerCorporationEntity> stockPerCorporationEntityBox = boxStore.boxFor(LSStockPerCorporationEntity.class);

            List<LSStockPerCorporationEntity> stockPerCorporationEntityList = new ArrayList<>();
            for (LSCorporationDetail detail : insertDetailItemList) {
                stockPerCorporationEntityList.add(getItemByDetail(detail));
            }

            stockPerCorporationEntityBox.put(stockPerCorporationEntityList);
            result = true;

        } catch (Exception e) {
            Timber.d("///" + "insertAndUpdateByDetail error: " + new Gson().toJson(e));
        }

        return result;
    }

    @Override
    public List<LSStockPerCorporationEntity> getAllItem() {

        List<LSStockPerCorporationEntity> result = new ArrayList<>();

        try {

            BoxStore boxStore = (BoxStore) database.getConnection();
            Box<LSStockPerCorporationEntity> stockPerCorporationEntityBox = boxStore.boxFor(LSStockPerCorporationEntity.class);

            result = stockPerCorporationEntityBox
                    .query()
                    .build()
                    .find();

        } catch (Exception e) {
            Timber.d("///" + "getAllItem error: " + new Gson().toJson(e));
        }

        return result;
    }

    @Override
    public List<LSStockPerCorporationEntity> getItemListByStockID(String stockID) {

        List<LSStockPerCorporationEntity> result = new ArrayList<>();

        try {

            BoxStore boxStore = (BoxStore) database.getConnection();
            Box<LSStockPerCorporationEntity> stockPerCorporationEntityBox = boxStore.boxFor(LSStockPerCorporationEntity.class);

            result = stockPerCorporationEntityBox
                    .query()
                    .equal(LSStockPerCorporationEntity_.stockID, stockID)
                    .build()
                    .find();

        } catch (Exception e) {
            Timber.d("///" + "getItemListByStockID error: " + new Gson().toJson(e));
        }

        return result;
    }

    @Override
    public List<LSStockPerCorporationEntity> getItemListByDate(String date) {

        List<LSStockPerCorporationEntity> result = new ArrayList<>();

        try {

            BoxStore boxStore = (BoxStore) database.getConnection();
            Box<LSStockPerCorporationEntity> stockPerCorporationEntityBox = boxStore.boxFor(LSStockPerCorporationEntity.class);

            result = stockPerCorporationEntityBox
                    .query()
                    .equal(LSStockPerCorporationEntity_.date, date)
                    .build()
                    .find();

        } catch (Exception e) {
            Timber.d("///" + "getItemListByDate error: " + new Gson().toJson(e));
        }

        return result;
    }

    @Override
    public LSStockPerCorporationEntity getItemByStockIDAndDate(String stockID, String date) {

        LSStockPerCorporationEntity result = new LSStockPerCorporationEntity();

        try {

            BoxStore boxStore = (BoxStore) database.getConnection();
            Box<LSStockPerCorporationEntity> stockPerCorporationEntityBox = boxStore.boxFor(LSStockPerCorporationEntity.class);

            result = stockPerCorporationEntityBox
                    .query()
                    .equal(LSStockPerCorporationEntity_.stockID, stockID)
                    .equal(LSStockPerCorporationEntity_.date, date)
                    .build()
                    .findUnique();

        } catch (Exception e) {
            Timber.d("///" + "getItemByStockIDAndDate error: " + new Gson().toJson(e));
        }

        return result;
    }
}

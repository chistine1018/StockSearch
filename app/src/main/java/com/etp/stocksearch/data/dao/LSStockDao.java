package com.etp.stocksearch.data.dao;


import com.etp.stocksearch.data.enity.LSStockEntity;
import com.etp.stocksearch.data.model.LSStockRangeInfoDetail;

import java.util.List;

public interface LSStockDao {

    boolean insertAndUpdateByDB(LSStockEntity insertDbItem);

    boolean insertAndUpdateByDetail(LSStockRangeInfoDetail insertDetailITem);

    boolean insertAndUpdateByDB(List<LSStockEntity> insertDbItemList);

    boolean insertAndUpdateByDetail(List<LSStockRangeInfoDetail> insertDetailItemList);

    List<LSStockEntity> getAllItem();

    List<LSStockEntity> getItemListByType(String type);

    LSStockEntity getItemByID(String stockID);
}

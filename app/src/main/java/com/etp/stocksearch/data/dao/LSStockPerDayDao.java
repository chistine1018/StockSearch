package com.etp.stocksearch.data.dao;


import com.etp.stocksearch.data.enity.LSStockEntity;
import com.etp.stocksearch.data.enity.LSStockPerDayEntity;
import com.etp.stocksearch.data.model.LSStockRangeInfoDetail;

import java.util.List;

public interface LSStockPerDayDao {

    LSStockPerDayEntity setTargetByItem(LSStockEntity stockEntity);

    LSStockPerDayEntity setTargetByItem(LSStockEntity stockEntity, LSStockPerDayEntity stockPerDayEntity);

    boolean insertAndUpdateByDB(LSStockPerDayEntity insertDbItem);

    boolean insertAndUpdateByDetail(LSStockRangeInfoDetail insertDetailITem);

    boolean insertAndUpdateByDB(List<LSStockPerDayEntity> insertDbItemList);

    boolean insertAndUpdateByDetail(List<LSStockRangeInfoDetail> insertDetailItemList);

    List<LSStockPerDayEntity> getAllItem();

    List<LSStockPerDayEntity> getItemListByStockID(String stockID);

    List<LSStockPerDayEntity> getItemListByDate(String date);

    LSStockPerDayEntity getItemByStockIDAndDate(String stockID, String date);
}

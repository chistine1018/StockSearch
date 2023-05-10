package com.etp.stocksearch.data.dao;



import com.etp.stocksearch.data.enity.LSStockEntity;
import com.etp.stocksearch.data.enity.LSStockPerCorporationEntity;
import com.etp.stocksearch.data.model.LSCorporationDetail;

import java.util.List;


public interface LSStockPerCorporationDao {

    LSStockPerCorporationEntity setTargetByItem(LSStockEntity stockEntity);

    LSStockPerCorporationEntity setTargetByItem(LSStockEntity stockEntity, LSStockPerCorporationEntity stockPerCorporationEntity);

    boolean insertAndUpdateByDB(LSStockPerCorporationEntity insertDbItem);

    boolean insertAndUpdateByDetail(LSCorporationDetail insertDetailITem);

    boolean insertAndUpdateByDB(List<LSStockPerCorporationEntity> insertDbItemList);

    boolean insertAndUpdateByDetail(List<LSCorporationDetail> insertDetailItemList);

    List<LSStockPerCorporationEntity> getAllItem();

    List<LSStockPerCorporationEntity> getItemListByStockID(String stockID);

    List<LSStockPerCorporationEntity> getItemListByDate(String date);

    LSStockPerCorporationEntity getItemByStockIDAndDate(String stockID, String date);
}

package com.etp.stocksearch.data.dao;


import com.etp.stocksearch.data.enity.LSOperatingRevenueEntity;
import com.etp.stocksearch.data.model.LSOperatingRevenueResponse;

import java.util.List;


public interface LSOperatingRevenueDao {

    boolean insertAndUpdateByDetailList(List<LSOperatingRevenueResponse> insertDetailList);

    boolean deleteAllItem();

    LSOperatingRevenueEntity findByStockID(String stockID);

    List<LSOperatingRevenueEntity> findAll();
}

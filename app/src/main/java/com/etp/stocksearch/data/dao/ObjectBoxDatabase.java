package com.etp.stocksearch.data.dao;

import com.etp.stocksearch.custom.application.StockSearchApplication;
import com.etp.stocksearch.data.enity.MyObjectBox;

import io.objectbox.BoxStore;

public class ObjectBoxDatabase implements Database {
    private static BoxStore boxStore = null;

    public ObjectBoxDatabase() {
        if (boxStore == null) {

            boxStore = MyObjectBox.builder()
                    .androidContext(StockSearchApplication.getInstance().getApplicationContext())
                    .name("stock_search")
                    .build();
        }
    }

    @Override
    public AutoCloseable getConnection() {
        return boxStore;
    }
}

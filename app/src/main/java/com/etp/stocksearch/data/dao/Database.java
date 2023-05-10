package com.etp.stocksearch.data.dao;

public interface Database {
    AutoCloseable getConnection();
}

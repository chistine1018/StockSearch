package com.etp.stocksearch.data.enity;

import java.util.List;

import io.objectbox.annotation.Backlink;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToMany;


@Entity
public class LSStockEntity {

    @Id
    public long id;

    private String stockID;
    private String stockName;
    private String stockMonthAvg;
    private String type;

    @Backlink
    public ToMany<LSStockPerDayEntity> stockPerDayList;
    @Backlink
    public ToMany<LSStockPerCorporationEntity> stockPerCorporationList;

    public String getStockID() {
        return stockID;
    }

    public void setStockID(String stockID) {
        this.stockID = stockID;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getStockMonthAvg() {
        return stockMonthAvg;
    }

    public void setStockMonthAvg(String stockMonthAvg) {
        this.stockMonthAvg = stockMonthAvg;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<LSStockPerDayEntity> getStockPerDayLis() {
        return stockPerDayList.subList(0, stockPerDayList.size());
    }

    public List<LSStockPerCorporationEntity> getStockPerCorporationList() {
        return stockPerCorporationList.subList(0, stockPerCorporationList.size());
    }
}

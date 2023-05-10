package com.etp.stocksearch.utils;


import com.etp.stocksearch.data.enity.LSOperatingRevenueEntity;
import com.etp.stocksearch.data.enity.LSStockEntity;
import com.etp.stocksearch.data.enity.LSStockPerCorporationEntity;
import com.etp.stocksearch.data.enity.LSStockPerDayEntity;
import com.etp.stocksearch.data.model.LSCorporationDetail;
import com.etp.stocksearch.data.model.LSOperatingRevenueResponse;
import com.etp.stocksearch.data.model.LSStockDetail;
import com.etp.stocksearch.data.model.LSStockRangeInfoDetail;

import java.util.ArrayList;
import java.util.List;


public class DbItemToDetail {

    public static class Stock {

        public static LSStockDetail toStockDetail(LSStockEntity dbItem) {

            LSStockDetail stockDetail = new LSStockDetail();
            stockDetail.setStockID(dbItem.getStockID());
            stockDetail.setStockName(dbItem.getStockName());

            return stockDetail;
        }

        public static List<LSStockDetail> toStockDetailList(List<LSStockEntity> dbItemList) {

            List<LSStockDetail> detailList = new ArrayList<>();
            for (LSStockEntity dbItem : dbItemList) {
                detailList.add(toStockDetail(dbItem));
            }

            return detailList;
        }
    }

    public static class Corporation {

        public static LSCorporationDetail toCorporationDetail(LSStockPerCorporationEntity dbItem) {

            LSCorporationDetail detail = new LSCorporationDetail();
            detail.setStockID(dbItem.getStockID());
            detail.setDate(dbItem.getDate());
            detail.setForeignBuy(dbItem.getForeignBuy());
            detail.setForeignSell(dbItem.getForeignSell());
            detail.setForeignOver(dbItem.getForeignOver());
            detail.setInvestmentBuy(dbItem.getInvestmentBuy());
            detail.setInvestmentSell(dbItem.getInvestmentSell());
            detail.setInvestmentOver(dbItem.getInvestmentOver());
            detail.setSelfBuy(dbItem.getSelfBuy());
            detail.setSelfSell(dbItem.getSelfSell());
            detail.setSelfOver(dbItem.getSelfOver());
            detail.setTotalOver(dbItem.getTotalOver());

            return detail;
        }

        public static List<LSCorporationDetail> toCorporationDetailList(List<LSStockPerCorporationEntity> dbItemList) {

            List<LSCorporationDetail> detailList = new ArrayList<>();
            for (LSStockPerCorporationEntity dbItem : dbItemList) {
                detailList.add(toCorporationDetail(dbItem));
            }

            return detailList;
        }
    }

    public static class StockRange {

        public static LSStockRangeInfoDetail toStockRangeInfoDetail(LSStockPerDayEntity dbItem) {

            LSStockRangeInfoDetail detail = new LSStockRangeInfoDetail();
            detail.setStockID(dbItem.getStockID());
            detail.setDate(dbItem.getDate());
            detail.setOpenPrize(dbItem.getOpenPrize());
            detail.setHighestPrize(dbItem.getHighestPrize());
            detail.setLowestPrize(dbItem.getLowestPrize());
            detail.setClosePrize(dbItem.getClosePrize());
            detail.setRange(dbItem.getRange());
            detail.setDealStock(dbItem.getDealStock());
            detail.setDealCount(dbItem.getDealCount());
            detail.setDealPrize(dbItem.getDealTotalPrize());

            return detail;
        }

        public static List<LSStockRangeInfoDetail> toStockRangeInfoDetailList(List<LSStockPerDayEntity> dbItemList) {

            List<LSStockRangeInfoDetail> detailList = new ArrayList<>();
            for (LSStockPerDayEntity dbItem : dbItemList) {
                detailList.add(toStockRangeInfoDetail(dbItem));
            }

            return detailList;
        }
    }

    public static class OperatingRevenue {

        public static LSOperatingRevenueResponse toOperatingRevenueDetail(LSOperatingRevenueEntity dbItem) {

            LSOperatingRevenueResponse detail = new LSOperatingRevenueResponse();
            detail.setStockID(dbItem.getStockID());
            detail.setStockName(dbItem.getStockName());
            detail.setCompanyType(dbItem.getCompanyType());
            detail.setCompareWithLastMonthRevenue(dbItem.getCompareWithLastMonthRevenue());
            detail.setCompareWithLastMonthRevenuePercent(dbItem.getCompareWithLastMonthRevenuePercent());
            detail.setCompareWithLastYearRevenue(dbItem.getCompareWithLastYearRevenue());
            detail.setCompareWithLastYearRevenuePercent(dbItem.getCompareWithLastYearRevenuePercent());
            detail.setDataDate(dbItem.getDataDate());
            detail.setReleaseDate(dbItem.getReleaseDate());
            detail.setRevenue(dbItem.getRevenue());
            detail.setTag(dbItem.getTag());
            detail.setTotalRevenueCompareWithLastTimePercent(dbItem.getTotalRevenueCompareWithLastTimePercent());
            detail.setTotalRevenueDeductLastYear(dbItem.getTotalRevenueDeductLastYear());
            detail.setTotalRevenueDeductThisMonth(dbItem.getTotalRevenueDeductThisMonth());
            return detail;
        }

        public static List<LSOperatingRevenueResponse> toOperatingRevenueDetailList(List<LSOperatingRevenueEntity> dbItemList) {

            List<LSOperatingRevenueResponse> detailList = new ArrayList<>();

            for (LSOperatingRevenueEntity dbItem : dbItemList) {

                detailList.add(toOperatingRevenueDetail(dbItem));
            }

            return detailList;
        }
    }
}

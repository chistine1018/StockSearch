package com.etp.stocksearch.utils;

import com.etp.stocksearch.custom.StockProperties;
import com.etp.stocksearch.data.model.LSStockRangeInfoDetail;
import com.etp.stocksearch.data.model.LSCorporationDetail;

import java.util.List;

public class ApiItemToDetail {

    public static class ThreeCorporation {

        public static LSCorporationDetail toPerStockCorporation(List<String> apiItemList) {

            LSCorporationDetail detail = new LSCorporationDetail();
            if (apiItemList.size() > 18) {
                detail.setStockID(apiItemList.get(0));
                detail.setStockName(apiItemList.get(1).trim());
                detail.setForeignBuy(apiItemList.get(2).replace(StockProperties.Punctuation.COMMA, ""));
                detail.setForeignSell(apiItemList.get(3).replace(StockProperties.Punctuation.COMMA, ""));
                detail.setForeignOver(apiItemList.get(4).replace(StockProperties.Punctuation.COMMA, ""));
                detail.setInvestmentBuy(apiItemList.get(8).replace(StockProperties.Punctuation.COMMA, ""));
                detail.setInvestmentSell(apiItemList.get(9).replace(StockProperties.Punctuation.COMMA, ""));
                detail.setInvestmentOver(apiItemList.get(10).replace(StockProperties.Punctuation.COMMA, ""));
                int selfBuy = Integer.parseInt(apiItemList.get(12).replace(StockProperties.Punctuation.COMMA, "")) +
                        Integer.parseInt(apiItemList.get(15).replace(StockProperties.Punctuation.COMMA, ""));
                int selfSell = Integer.parseInt(apiItemList.get(13).replace(StockProperties.Punctuation.COMMA, "")) +
                        Integer.parseInt(apiItemList.get(16).replace(StockProperties.Punctuation.COMMA, ""));
                detail.setSelfBuy(String.valueOf(selfBuy));
                detail.setSelfSell(String.valueOf(selfSell));
                detail.setSelfOver(apiItemList.get(11).replace(StockProperties.Punctuation.COMMA, ""));
                detail.setTotalOver(apiItemList.get(18).replace(StockProperties.Punctuation.COMMA, ""));
                detail.setDate(DateUtility.getCurrentDate());
            }

            return detail;
        }
    }


    public static class StockRangeInfo {

        public static LSStockRangeInfoDetail toPerStockRangeInfo(List<String> apiItemList) {

            LSStockRangeInfoDetail detail = new LSStockRangeInfoDetail();
            if (apiItemList.size() > 9) {

                detail.setStockID(apiItemList.get(0));
                detail.setStockName(apiItemList.get(1).trim());
                detail.setDealStock(apiItemList.get(2).replace(StockProperties.Punctuation.COMMA, ""));
                detail.setDealPrize(apiItemList.get(3).replace(StockProperties.Punctuation.COMMA, ""));
                detail.setOpenPrize(apiItemList.get(4).replace(StockProperties.Punctuation.COMMA, ""));
                detail.setHighestPrize(apiItemList.get(5).replace(StockProperties.Punctuation.COMMA, ""));
                detail.setLowestPrize(apiItemList.get(6).replace(StockProperties.Punctuation.COMMA, ""));
                detail.setClosePrize(apiItemList.get(7).replace(StockProperties.Punctuation.COMMA, ""));
                String range = apiItemList.get(8).replace(StockProperties.Punctuation.COMMA, "");
                range = range.replace("X", "");
                detail.setRange(range);
                detail.setDealCount(apiItemList.get(9).replace(StockProperties.Punctuation.COMMA, ""));
                detail.setDate(DateUtility.getCurrentDate());
            }

            return detail;
        }
    }
}


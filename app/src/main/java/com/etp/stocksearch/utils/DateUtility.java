package com.etp.stocksearch.utils;

import com.etp.stocksearch.custom.StockProperties;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtility {
    public static String getCurrentDate() {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(StockProperties.DateFormat.yyyyMMdd, Locale.getDefault());
        Date date = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        switch (calendar.get(Calendar.DAY_OF_WEEK)) {
            case Calendar.SATURDAY: {

                date.setTime(date.getTime() - 86400000);
                break;
            }
            case Calendar.SUNDAY: {
                date.setTime(date.getTime() - 172800000);
                break;
            }
        }

        return simpleDateFormat.format(date);
    }
}

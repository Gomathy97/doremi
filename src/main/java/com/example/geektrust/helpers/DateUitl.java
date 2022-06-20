package com.example.geektrust.helpers;

import com.example.geektrust.enums.Constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DateUitl {
    private static final List<Integer> month_30_days = new ArrayList<>(Arrays.asList(4, 6, 9, 11));
    private static final int no_of_months = 12;

    private static final int start_time = 1;

    private static final int days_30 = 30;

    private static final int days_31 = 31;

    private static final int days_29 = 29;

    private static final int days_28 = 28;

    private static final String february = "2";

    public static boolean isValidSubscriptionDate(String date) {
        String[] date_arr = date.split("-");
        int day = Integer.parseInt(date_arr[0]);
        int month = Integer.parseInt(date_arr[1]);
        if(month > no_of_months || month < start_time || day < start_time || day > days_31)    return false;
        boolean is30Days = isMonth30Days(month);
        if(is30Days && day <= days_30 || !is30Days && !date_arr[1].equals(february)) {
            return true;
        }
        if(date_arr[1].equals(february)) {
            if(isLeapYear(Integer.parseInt(date_arr[2])) && day <= days_29) {
                return true;
            } else return day <= days_28;
        }
        return false;
    }

    public static boolean isLeapYear(int year) {
        return year % 4 == 0 && (year % 100 != 0 || (year % 400 == 0));
    }

    public static boolean isMonth30Days(int month) {
        return month_30_days.contains(month);
    }

    public static String getDate(String subscription_start_date, String plan) {
        String[] dates = subscription_start_date.split("-");
        int no_of_months = plan.equals(Constants.PREMIUM) ? 3 : 1;
        int day = Integer.parseInt(dates[0]) - 10;
        int month = Integer.parseInt(dates[1]) + no_of_months;
        int year = Integer.parseInt(dates[2]);
        month = day < 0 ? month - 1 : month;
        year = month > 12 ? year + 1 : year;
        month = month > 12 ? month % 12 : month;
        day = day < 0 ?
                DateUitl.isMonth30Days(month) ? 30 + day :
                        month == 2 ? isLeapYear(year) ? 29 + day : 28 + day : 31 + day : day;
        return (day < 10 ? "0" : "") + day + "-" +
                (month < 10 ? "0" : "") + month + "-" + year;
    }
}

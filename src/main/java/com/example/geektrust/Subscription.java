package com.example.geektrust;

import com.example.geektrust.enums.CategoryType;
import com.example.geektrust.enums.Constants;
import com.example.geektrust.enums.Topup;

import java.util.*;

import static com.example.geektrust.helpers.DateUitl.getDate;

public class Subscription {
    private String subscription_start_date;

    private Map<String, String> categoryTypeVsSubscriptionType = new LinkedHashMap<>();

    private boolean hasTopup;

    private Long amount = 0L;

    private static final String ADD_SUBSCRIPTION_FAILED = "ADD_SUBSCRIPTION_FAILED";

    private static final String ADD_TOPUP_FAILED = "ADD_TOPUP_FAILED";

    private static final String DUPLICATE_CATEGORY = "DUPLICATE_CATEGORY";

    private static final String DUPLICATE_TOPUP = "DUPLICATE_TOPUP";

    private static final String SUBSCRIPTIONS_NOT_FOUND = "SUBSCRIPTIONS_NOT_FOUND";

    private static final String RENEWAL_REMINDER = "RENEWAL_REMINDER";

    private static final String RENEWAL_AMOUNT = "RENEWAL_AMOUNT";

    public Subscription() {}
    public Subscription(String subscription_start_date) {
        this.subscription_start_date = subscription_start_date;
    }

    public void addSubscription(String category, String plan) {
        if(categoryTypeVsSubscriptionType.containsKey(category)) {
            System.out.println(ADD_SUBSCRIPTION_FAILED + " " + DUPLICATE_CATEGORY);
            return;
        }
        categoryTypeVsSubscriptionType.put(category, plan);
        setAmount(category, plan);
    }

    public void addTopup(String plan, String month) {
        if(isSubscriptionEmpty()) {
            System.out.println(ADD_TOPUP_FAILED + " " + SUBSCRIPTIONS_NOT_FOUND);
            return;
        }
        if(hasTopup) {
            System.out.println(ADD_TOPUP_FAILED + " " + DUPLICATE_TOPUP);
            return;
        }
        hasTopup = true;
        amount += plan.equals(Constants.FOUR_DEVICE) ?
                Long.parseLong(month) * Topup.FOUR_DEVICE.getValue() :
                Long.parseLong(month) * Topup.TEN_DEVICE.getValue();
    }

    public void printRenewals() {
        if(isSubscriptionEmpty()) {
            System.out.println(SUBSCRIPTIONS_NOT_FOUND);
            return;
        }
        for (Map.Entry<String, String> entry : categoryTypeVsSubscriptionType.entrySet()) {
            String category = entry.getKey();
            String plan = entry.getValue();
            System.out.println(RENEWAL_REMINDER + " " + category + " " + getDate(subscription_start_date, plan));
        }
        System.out.println(RENEWAL_AMOUNT + " " + amount);
    }

    private boolean isSubscriptionEmpty() {
        return categoryTypeVsSubscriptionType.isEmpty();
    }

    public void setAmount(String category, String plan) {
        if(plan.equals(Constants.FREE)) {
            return;
        }
        long[] costs = new long[2];
        switch (category) {
            case Constants.MUSIC:
                costs = CategoryType.MUSIC.getValue();
                break;
            case Constants.VIDEO:
                costs = CategoryType.VIDEO.getValue();
                break;
            case Constants.PODCAST:
                costs = CategoryType.PODCAST.getValue();
                break;
        }
        amount += Constants.PERSONAL.equals(plan) ? costs[0] : costs[1];

    }
}

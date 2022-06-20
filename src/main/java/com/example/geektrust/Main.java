package com.example.geektrust;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

import static com.example.geektrust.helpers.DateUitl.isValidSubscriptionDate;

public class Main {
    private static final String START_SUBSCRIPTION = "START_SUBSCRIPTION";

    private static final String ADD_SUBSCRIPTION = "ADD_SUBSCRIPTION";

    private static final String ADD_SUBSCRIPTION_FAILED = "ADD_SUBSCRIPTION_FAILED";

    private static final String ADD_TOPUP_FAILED = "ADD_TOPUP_FAILED";

    private static final String ADD_TOPUP = "ADD_TOPUP";

    private static final String PRINT_RENEWAL_DETAILS = "PRINT_RENEWAL_DETAILS";

    private static final String INVALID_DATE = "INVALID_DATE";

    public static void main(String[] args) {
        try {
            Subscription subscription = new Subscription();
            // the file to be opened for reading
            FileInputStream fis = new FileInputStream(args[0]);
            Scanner sc = new Scanner(fis);
            boolean isInvalidDate = false;
            while (sc.hasNextLine()) {
                String text = sc.nextLine();
                String[] input = text.split(" ");
                switch (input[0]) {
                   case START_SUBSCRIPTION:
                       if(isValidSubscriptionDate(input[1])) {
                           subscription = new Subscription(input[1]);
                       } else {
                           System.out.println(INVALID_DATE);
                           isInvalidDate = true;
                       }
                       break;
                   case ADD_SUBSCRIPTION:
                       if(isInvalidDate) {
                           System.out.println(ADD_SUBSCRIPTION_FAILED + " " + INVALID_DATE);
                           break;
                       }
                       subscription.addSubscription(input[1], input[2]);
                       break;
                   case ADD_TOPUP:
                       if(isInvalidDate) {
                           System.out.println(ADD_TOPUP_FAILED + " " + INVALID_DATE);
                           break;
                       }
                       subscription.addTopup(input[1], input[2]);
                       break;
                   case PRINT_RENEWAL_DETAILS:
                       subscription.printRenewals();
                }
            }
            sc.close(); // closes the scanner
        } catch (IOException e) {

        }
    }
}

package com.vasyl.summer.practice.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class RandomHelper {

    public static BigDecimal getRandomQuantity(double a, double b, int scale) {
        java.util.Random rand = new java.util.Random();
        double dx = a + rand.nextDouble() * (b - a);

        return BigDecimal.valueOf(dx).setScale(scale, RoundingMode.HALF_UP).stripTrailingZeros();
    }

    public static Integer getRandomQuantity(int min, int max) {
        return (int) (Math.random() * (max - min)) + min;
    }

    public static boolean getRand() {
        java.util.Random rand = new java.util.Random();

        int a = 1;
        int b = 11;

        double dx = a + rand.nextDouble() * (b - a);

        return dx < 5;
    }

    public static boolean getRand30Percents() {
        java.util.Random rand = new java.util.Random();

        int a = 1;
        int b = 31;

        double dx = a + rand.nextDouble() * (b - a);

        return dx < 10;
    }

    public static String getRandomConfirmLoginCode() {
        int min = 100000;
        int max = 999999;
        int random_int = (int) (Math.random() * (max - min + 1) + min);
        return String.valueOf(random_int);
    }

    public static Long getRandomWebId() {
        long min = 1000000000L;
        long max = 9999999999L;
        return (long) (Math.random() * (max - min + 1) + min);
    }

    // function to generate a random string of length n
    public static String getRandomHash() {

        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(64);

        for (int i = 0; i < 64; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int) (AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }

    // function to generate a random string of length n
    public static String getRandomHash(int n) {

        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int) (AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }

}

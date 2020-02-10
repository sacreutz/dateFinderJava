package com.company;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        Date[][] array = create2dArray();
        findDates(array);

    }

    public static int lilian(Date date) {
        final int SUBDAYS = 578100;
        int numDays = 0;
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int month = localDate.getMonthValue();
        int day = localDate.getDayOfYear();
        int year = localDate.getYear();
        if (month <= 2) {
            numDays = numDays + ((month - 1) * 31);
        } else {
            numDays = numDays + ((month - 1) * 31) - ((4 * (month - 1) + 27) / 10);
        }

        numDays = numDays + day;

        numDays = numDays + ( year / 4) - (year / 100) + (year / 400);

        if (month < 3)
        {
            if ( ( year % 4) == 0)   numDays = numDays - 1;
            if ( ( year % 100) == 0) numDays = numDays + 1;
            if ( ( year % 400) == 0) numDays = numDays - 1;
        }

        numDays = numDays - SUBDAYS;

        return numDays;
    }

    private static void findDates(Date[][] inputArray) {
        long millisecondsSince1970 = 86400000;
        Date oldestDate = new Date(millisecondsSince1970);
        int oldestDateIndex = 0;
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        String startDate = simpleDateFormat.format(oldestDate);
        Date now = new Date();
        Date nearestDate = now;
        int nearestDateIndex = 0;
        long greatestDifference = 0;
        long smallestDifference = 88888888888888888L;

            for (int i = 0; i < inputArray.length; i++) {

                {

                    for (int j = 0; j < inputArray[i].length; j++) {

                        String pattern3 = "yyyy-MM-dd";
                        SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat(pattern3);
                        String currentDate = simpleDateFormat3.format(inputArray[i][j]);
                       // System.out.println(currentDate);

                        long diffNow = (Math.abs(inputArray[i][j].getTime() - now.getTime()));
                        long diff = TimeUnit.DAYS.convert(diffNow, TimeUnit.MILLISECONDS);

                        if (diff > greatestDifference) {
                            greatestDifference = diff;
                            oldestDate = inputArray[i][j];
                            oldestDateIndex = j;
                    }


                       if (diff < smallestDifference) {
                           smallestDifference = diff;
                            nearestDate = inputArray[i][j];
                            nearestDateIndex = i;
                        }
                    }
                }

        }

        String pattern1 = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(pattern);

        String oldestDate1 = simpleDateFormat1.format(oldestDate);
        System.out.println("oldestDate");
        System.out.println(oldestDate1);
        System.out.println("Column: " + oldestDateIndex);
        String nearestDate1 = simpleDateFormat1.format(nearestDate);
        System.out.println("nearestDate");
        System.out.println(nearestDate1);
        System.out.println("Row: " + nearestDateIndex);
    }


    public static Date[][] create2dArray() {
        long millisecondsSince1970 = 86400000;
        Date start = new Date(millisecondsSince1970);
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        String startDate = simpleDateFormat.format(start);
        System.out.println("startDate");
        System.out.println(startDate);
        long milliseconds2 = 1576800000000L + 1036800000L;
        Date end = new Date(milliseconds2);
        String endDate = simpleDateFormat.format(end);
        System.out.println("endDate");
        System.out.println(endDate);


        Date[][] array = new Date[20][20];
        for (Date[] row : array) {
            for (int i = 0; i < row.length; i++) {

                long random = ThreadLocalRandom.current().nextLong(start.getTime(), end.getTime());
                Date date = new Date(random);
                row[i] = date;
            }
        }


        for(Date[] row : array) {
            printRow(row);
        }
        return array;
    };


    public static void printRow(Date[] row) {
        for (Date i : row) {
            String pattern3 = "yyyy-MM-dd";
            SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat(pattern3);
            String currentDate = simpleDateFormat3.format(i);
            System.out.print(currentDate);
            System.out.print("\t");
        }
        System.out.println();
    }





}

package main;

import java.sql.SQLOutput;

public class Calc {
    public static int[][] weekdays = {
            {2,5,5,1,3,6,1,4,0,2,5,0},  //1901
            {3,6,6,2,4,0,2,5,1,3,6,1},
            {4,0,0,3,5,1,3,6,2,4,0,2},
            {5,1,2,5,0,3,5,1,4,6,2,4},
            {0,3,3,6,1,4,6,2,5,0,3,5},
            {1,4,4,0,2,5,0,3,6,1,4,6},
            {2,5,5,1,3,6,1,4,0,2,5,0},
            {3,6,0,3,5,1,3,6,2,4,0,2},
            {5,1,1,4,6,2,4,0,3,5,1,3},
            {6,2,2,5,0,3,5,1,4,6,2,4},
            {0,3,3,6,1,4,6,2,5,0,3,5},
            {1,4,5,1,3,6,1,4,0,2,5,0},
            {3,6,6,2,4,0,2,5,1,3,6,1},
            {4,0,0,3,5,1,3,6,2,4,0,2},
            {5,1,1,4,6,2,4,0,3,5,1,3},
            {6,2,3,6,1,4,6,2,5,0,3,5},
            {1,4,4,0,2,5,0,3,6,1,4,6},
            {2,5,5,1,3,6,1,4,0,2,5,0},
            {3,6,6,2,4,0,2,5,1,3,6,1},
            {4,0,1,4,6,2,4,0,3,5,1,3},
            {6,2,2,5,0,3,5,1,4,6,2,4},
            {0,3,3,6,1,4,6,2,5,0,3,5},
            {1,4,4,0,2,5,0,3,6,1,4,6},
            {2,5,6,2,4,0,2,5,1,3,6,1},
            {4,0,0,3,5,1,3,6,2,4,0,2},
            {5,1,1,4,6,2,4,0,3,5,1,3},
            {6,2,2,5,0,3,5,1,4,6,2,4},
            {0,3,4,0,2,5,0,3,6,1,4,6}   //1928
    };

    public static final String[] WD_LONG = {
            "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"
    };

    public static final String[] WD_SHORT = {
            "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"
    };

    public static final String[] WD_LONG_DE = {
            "Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag", "Samstag", "Sonntag"
    };

    public static final String[] WD_SHORT_DE = {
            "Mo", "Di", "Mi", "Do", "Fr", "Sa", "So"
    };

    public static int getWeekday(int d, int m, int y) {
        if (y < 1901)
            return -1;

        int i = (y - 1901) % 28;
        //System.out.println("i = "+i);

        int res = d + weekdays[i][m-1];
        //System.out.println("res = "+res);

        //x=sunday, ..., 7x=saturday
        //System.out.println("weekday(res%7-2) = "+(res%7-2));
        res = res % 7 - 2;
        if (res < 0)
            res += 7;
        return res;
    }

    public static int getWeekday(String date) {
        int d = Integer.parseInt(date.substring(0,2).replace("0", ""));
        int m = Integer.parseInt(date.substring(2,4).replace("0", ""));
        int y = Integer.parseInt(date.substring(4));
        //System.out.println(d+"."+m+"."+y);

        return getWeekday(d, m, y);
    }

    public static int[] getCurrentDate() {
        long time = System.currentTimeMillis();
        // 01.01.1970 00:00 GMT/UTC
        // day in seconds: 86400
        // year in seconds: 31536000
        // 2 years in seconds: 63072000
        // 4 years (+ 1 day) in seconds: 126230400

        // year calculation
        int year = (int) (1972 + ((((time / 1000) - 63072000) / 126230400) * 4));

        // month calculation
        int years = year - 1972;
        int yearsP = (year - 1972) / 4;
        int lYear = 0;
        if (years - (yearsP * 4) == 0)
            lYear = lYear + 1;
        int month = (int) ((((((((time / 1000) - 63072000)- ((yearsP * 126230400) + (years - yearsP * 4) * 31536000)) + 86400) / 86400) *12) / (365 + lYear)) +1);

        // day calculation
        int day = (int) (((((time / 1000) - 63072000)- ((yearsP * 126230400) + (years - yearsP * 4) * 31536000)) + 86400) / 86400);
        if (month == 2) {
            day = day - 31;
        } else if (month == 3) {
            day = day - 59;
        } else if (month == 4) {
            day = day - 90;
        } else if (month == 5) {
            day = day - 120;
        } else if (month == 6) {
            day = day - 151;
        } else if (month == 7) {
            day = day - 181;
        } else if (month == 8) {
            day = day - 212;
        } else if (month == 9) {
            day = day - 243;
        } else if (month == 10) {
            day = day - 273;
        } else if (month == 11) {
            day = day - 304;
        } else if (month == 12) {
            day = day - 334;
        } if (day >= 60)
            day = day + lYear;

        //System.out.println(day + "." + month + "." + year);
        // System.out.println("Systemtime: " + time); // dev. information
        // System.out.println("Leapyear: " + lYear); // dev. information
        return new int[] {day, month, year};
    }
}

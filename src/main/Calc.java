package main;

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
}

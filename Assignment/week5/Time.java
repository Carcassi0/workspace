package Assignment.week5;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Time {
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;

    public Time(int yy, int mo, int dd, int hh, int mm){
        year = yy; month = mo; day = dd;
        hour = hh; minute = mm;
    }

    public void addMinute(int amt) {
        boolean isLeapYear = false;

        if ((this.year % 4 == 0 && this.year % 100 != 0) || (this.year % 400 == 0)) {
            isLeapYear = true;
        }

        if (amt <= 0 || amt > 300) {
            amt = 0;
        }
        if (amt != 0) {
            this.minute += amt;
            if (this.minute >= 60) {
                this.hour += this.minute / 60;
                this.minute = this.minute % 60;
            }
            if (this.hour >= 24) {
                this.day += this.hour / 24;
                this.hour = this.hour % 24;
            }

            // 윤년이고 2월이고 일수가 29일 이상인 경우
            if (isLeapYear && this.month == 2 && this.day >= 29) {
                this.month += 1;
                this.day = 1;
            }
            // 31일까지 있는 달
            else if ((this.month == 1 || this.month == 3 || this.month == 5 || this.month == 7 || this.month == 8 || this.month == 10) && this.day >= 31) {
                this.month += 1;
                this.day = 1;
            }
            // 30일까지 있는 달
            else if ((this.month == 4 || this.month == 6 || this.month == 9 || this.month == 11) && this.day >= 30) {
                this.month += 1;
                this.day = 1;
            }
            // 연도가 바뀌는 경우
            if (this.month > 12) {
                this.year += 1;
                this.month = 1;
            }
          
        }
    }

    public int timeDifference(Time t){ // (다)
        Calendar targetCal = new GregorianCalendar(t.year, t.month, t.day, t.hour, t.minute);
        Calendar baseCal = new GregorianCalendar(this.year, this.month, this.day, this.hour, this.minute);
        long diffSec = (baseCal.getTimeInMillis()-targetCal.getTimeInMillis())/1000;
        int diffMin = (int)(diffSec/60);

        return  diffMin;
    }

    public String toString(){
        return String.format("%4d/%02d/%02d-%02d:%02d", year, month, day, hour, minute);
    }
}


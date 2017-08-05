package com.student.john.taskmanagerclient.models;




import android.support.annotation.NonNull;

import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.Period;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class CustomDate implements Comparable<CustomDate> {
    private String monthName;
    private int monthNum;
    private int day;
    private int year;
    private int dayOfWeekNum;
    private String dayOfWeekName;
    private int dayOfYear;

    private LocalDate jodaDate;

    private final int FRIDAY = 5;
    private final int SUNDAY = 7;

    public CustomDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getDefault());
        String currentMonth = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
        int currentDay = cal.get(Calendar.DAY_OF_MONTH);
        int currentYear = cal.get(Calendar.YEAR);

    }



    //this constructor is meant to be used for calculating due dates. The key containing when the due
    //date should be relative to the current date is passed in and then calculations are made to
    //generate the correct due date. Time is not included because that is saved in an abstracted way
    //in dueTime
    public CustomDate(String input)
    {
        LocalDate currentDate = new LocalDate();


        switch(input) {
            case Model.DD_TOMORROW:
                jodaDate = currentDate.plus(Period.days(1));
                break;
            case Model.DD_BY_FRIDAY:
                jodaDate = currentDate.plus( Period.days( getDaysUntilFriday(currentDate) ) );
                break;
            case Model.DD_TODAY:
                jodaDate = currentDate;
                break;
            case Model.DD_NEXT_3_DAYS:
                jodaDate = currentDate.plus( Period.days(3) );
                break;
            case Model.DD_WITHIN_WEEK:
                jodaDate = currentDate.plus ( Period.days( getDaysUntilSunday(currentDate) ) );
                break;
            case Model.DD_NEXT_7_DAYS:
                jodaDate = currentDate.plus (Period.days(7));
                break;
            case Model.DD_WITHIN_2_WEEKS:
                jodaDate = currentDate.plus (Period.days(14));
                break;
            case Model.DD_WITHIN_MONTH:
                jodaDate = currentDate.plus ( Period.days( getDaysUntilEndOfMonth(currentDate) ));
                break;
            case Model.DD_WITHIN_30_DAYS:
                jodaDate = currentDate.plus ( Period.days(30));
                break;
            default:
                if (isValidDateString(input))
                {
                    int year = Integer.parseInt(input.substring(0,4));
                    int month = Integer.parseInt(input.substring(5,7));
                    int day = Integer.parseInt(input.substring(8,10));
                    jodaDate = new LocalDate(year, month, day);
                }
                else
                {
                    jodaDate = null;
                }

                return;


        }

        setFields(jodaDate);

    }

    private void setFields(LocalDate dueDate)
    {
        day = dueDate.getDayOfMonth();
        monthName = dueDate.monthOfYear().getAsShortText();
        monthNum = dueDate.getMonthOfYear();
        year = dueDate.getYear();
        dayOfYear = dueDate.getDayOfYear();
        dayOfWeekNum = dueDate.getDayOfWeek();
        dayOfWeekName = dueDate.dayOfWeek().getAsShortText();
    }

    private int getDaysUntilFriday(LocalDate currentDay)
    {
        int difference = FRIDAY - currentDay.getDayOfWeek();
        if (difference == 0) difference = 7;
        else if (difference < 0) difference += 7;

        return difference;
    }

    private int getDaysUntilSunday(LocalDate currentDay)
    {
        int difference = SUNDAY - currentDay.getDayOfWeek();
        if (difference == 0) difference = 7;

        return difference;
    }

    private int getDaysUntilEndOfMonth(LocalDate currentDay)
    {
        return Days.daysBetween(currentDay, currentDay.dayOfMonth().withMaximumValue()).getDays();
    }

    public String getCurrentDescription()
    {
        LocalDate currentDate = new LocalDate();
        int difference = Days.daysBetween( currentDate, jodaDate ).getDays();
        String description;

        if (difference == 0)
        {
            description = Model.DD_TODAY;
        }
        else if (difference == 1)
        {
            description = Model.DD_TOMORROW;
        }
        else if (dayOfWeekNum == FRIDAY)
        {
            description = Model.DD_BY_FRIDAY;
        }
        else if (difference <= 3)
        {
            description = Model.DD_NEXT_3_DAYS;
        }
        else if (dayOfWeekNum == SUNDAY)
        {
            description = Model.DD_WITHIN_WEEK;
        }
        else if (difference <= 7)
        {
            description = Model.DD_NEXT_7_DAYS;
        }
        else if (difference <= 14)
        {
            description = Model.DD_WITHIN_2_WEEKS;
        }
        else if (day == currentDate.dayOfMonth().withMaximumValue().getDayOfMonth())
        {
            description = Model.DD_WITHIN_MONTH;
        }
        else if (difference <= 30)
        {
            description = Model.DD_WITHIN_30_DAYS;
        }
        else
        {
            description = Model.SELECT_OPTION;
        }

        return description;
    }

    public String getMonthName() {
        return monthName;
    }

    public int getMonthNum() {
        return monthNum;
    }

    public int getDay() {
        return day;
    }

    public int getYear() {
        return year;
    }

    public int getDayOfWeekNum() {
        return dayOfWeekNum;
    }

    public String getDayOfWeekName() {
        return dayOfWeekName;
    }

    public LocalDate getJodaDate()
    {
        return this.jodaDate;
    }

    private boolean isValidDateString(String input)
    {

        try {
            DateTimeFormatter dtf = DateTimeFormat.forPattern("YYYY-MM-dd");
            dtf.parseDateTime(input);
            return true;
        } catch (IllegalArgumentException iae) {
            return false;
        }
    }

    public String getDateAsString()
    {
        if (jodaDate == null) return null;
        DateTimeFormatter dtf = DateTimeFormat.forPattern("YYYY-MM-dd");
        return jodaDate.toString(dtf);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;

        if (obj.getClass() != this.getClass()) return false;

        CustomDate date1 = (CustomDate) obj;

        if (this.getDateAsString().equals(date1.getDateAsString())) return true;
        else return false;
    }

    @Override
    public int compareTo(@NonNull CustomDate o) {
        return this.jodaDate.compareTo(o.getJodaDate());

    }
}

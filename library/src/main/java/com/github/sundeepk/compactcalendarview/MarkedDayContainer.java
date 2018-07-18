package com.github.sundeepk.compactcalendarview;

import com.github.sundeepk.compactcalendarview.domain.MarkedDay;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MarkedDayContainer {

    private Map<String, MarkedDay> markedDayMap = new HashMap<>();
    private Calendar markedDayCalendar;

    public MarkedDayContainer(Calendar markedDayCalendar) {
        this.markedDayCalendar = markedDayCalendar;
    }

    void addSpecialDay(MarkedDay markedDay) {
        markedDayCalendar.setTimeInMillis(markedDay.getTimeInMillis());
        String key = getKeyForCalendarEvent(markedDayCalendar);
        //Set<MarkedDay> markedDayOfMonth = markedDayMap.get(key);
//        if (markedDayOfMonth == null) { markedDayOfMonth = new HashSet<>(); }
//        markedDayOfMonth.add(markedDay);
        markedDayMap.put(key, markedDay);
    }

    MarkedDay getMarkedDay(int day, int month, int year) {
        markedDayCalendar.set(year, month, day);
        String key = getKeyForCalendarEvent(markedDayCalendar);
        return markedDayMap.get(key);
    }

    private String getKeyForCalendarEvent(Calendar calendar) {
        return calendar.get(Calendar.YEAR) + "_" + calendar.get(Calendar.MONTH) + "_" + calendar.get(Calendar.DAY_OF_MONTH);
    }
}

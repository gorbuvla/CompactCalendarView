package com.github.sundeepk.compactcalendarview;

import android.util.SparseArray;

import com.github.sundeepk.compactcalendarview.domain.Event;
import com.github.sundeepk.compactcalendarview.domain.MarkedDay;

import org.w3c.dom.ls.LSException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MarkedDayContainer {

    private Map<String, SparseArray<MarkedDay>> markedDayMap = new HashMap<>();
    private Calendar markedDayCalendar;

    MarkedDayContainer(Calendar markedDayCalendar) {
        this.markedDayCalendar = markedDayCalendar;
    }

    void addMarkedDay(MarkedDay markedDay) {
        markedDayCalendar.setTimeInMillis(markedDay.getTimeInMillis());
        String key = getKeyForCalendarMonth(markedDayCalendar);
        SparseArray<MarkedDay> days = markedDayMap.get(key);
        if (days == null) { days = new SparseArray<>(); }
        days.put(markedDayCalendar.get(Calendar.DAY_OF_MONTH), markedDay);
        markedDayMap.put(key, days);
    }

    void addMarkedDays(List<MarkedDay> markedDays) {
        for (MarkedDay day: markedDays) {
            addMarkedDay(day);
        }
    }

    MarkedDay getMarkedDay(int day, int month, int year) {
        markedDayCalendar.set(year, month, day);
        String key = getKeyForCalendarMonth(markedDayCalendar);
        SparseArray<MarkedDay> days = markedDayMap.get(key);
        if (days != null) {
            return days.get(day);
        }
        return null;
    }

    SparseArray<MarkedDay> getMarkedDaysForMonthAndYear(int month, int year) {
        return markedDayMap.get(year + "_" + month);
    }

    List<MarkedDay> getMarkedDayForMonth(long timeInMillis) {
        markedDayCalendar.setTimeInMillis(timeInMillis);
        String keyForCalendar = getKeyForCalendarMonth(markedDayCalendar);
        SparseArray<MarkedDay> days = markedDayMap.get(keyForCalendar);
        List<MarkedDay> allMarkedDays = new ArrayList<>();
        for (int i = 0; i < days.size(); i++) {
            allMarkedDays.add(days.valueAt(i));
        }
        return allMarkedDays;
    }

    MarkedDay getMarkedDay(long timeInMillis) {
        markedDayCalendar.setTimeInMillis(timeInMillis);
        String key = getKeyForCalendarMonth(markedDayCalendar);
        SparseArray<MarkedDay> days = markedDayMap.get(key);
        return days != null ? days.get(markedDayCalendar.get(Calendar.DAY_OF_MONTH)) : null;
    }

    void removeMarkedDayByTime(long timeInMillis) {
        markedDayCalendar.setTimeInMillis(timeInMillis);
        String key = getKeyForCalendarMonth(markedDayCalendar);
        SparseArray<MarkedDay> days = markedDayMap.get(key);
        if (days != null) {
            days.remove(markedDayCalendar.get(Calendar.DAY_OF_MONTH));
        }
    }

    void removeMarkedDay(MarkedDay day) {
        markedDayCalendar.setTimeInMillis(day.getTimeInMillis());
        String key = getKeyForCalendarMonth(markedDayCalendar);
        SparseArray<MarkedDay> markedDaysForMonth = markedDayMap.get(key);
        if (markedDaysForMonth != null) {
            markedDaysForMonth.remove(markedDayCalendar.get(Calendar.DAY_OF_MONTH));
        }
    }

    void removeMarkedDays(List<MarkedDay> days) {
        for (MarkedDay day: days) {
            removeMarkedDay(day);
        }
    }


    void removeAllMarkedDays() {
        markedDayMap.clear();
    }

    private String getKeyForCalendarMonth(Calendar calendar) {
        return calendar.get(Calendar.YEAR) + "_" + calendar.get(Calendar.MONTH);
    }
}
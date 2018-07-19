package com.github.sundeepk.compactcalendarview.domain;

import android.support.annotation.Nullable;

/**
 * DTO representing a "marked" day that should be colored
 * in provided color. Ex: holidays, special events, etc.
 */
public class MarkedDay {

    private int color;
    private long timeInMillis;
    private Object data;

    public MarkedDay(int color, long timeInMillis) {
        this.color = color;
        this.timeInMillis = timeInMillis;
    }

    public MarkedDay(int color, long timeInMillis, Object data) {
        this.color = color;
        this.timeInMillis = timeInMillis;
        this.data = data;
    }

    public int getColor() {
        return color;
    }

    public long getTimeInMillis() {
        return timeInMillis;
    }

    @Nullable
    public Object getData() {
        return data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MarkedDay day = (MarkedDay) o;

        if (color != day.color) return false;
        if (timeInMillis != day.timeInMillis) return false;
        if (data != null ? !data.equals(day.data) : day.data != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = color;
        result = 31 * result + (int) (timeInMillis ^ (timeInMillis >>> 32));
        result = 31 * result + (data != null ? data.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Event{" +
                "color=" + color +
                ", timeInMillis=" + timeInMillis +
                ", data=" + data +
                '}';
    }

}
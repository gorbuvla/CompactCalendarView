package com.github.sundeepk.compactcalendarview.domain;


import java.util.Objects;

public class MarkedDay {

    private int color;
    private long timeInMillis;

    public MarkedDay(int color, long timeInMillis) {
        this.color = color;
        this.timeInMillis = timeInMillis;
    }

    public int getColor() {
        return color;
    }

    public long getTimeInMillis() {
        return timeInMillis;
    }
}
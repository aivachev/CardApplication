package com.example.andrew.cardapplication.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Andrew on 09.10.2017.
 */

public class DateTime {
    @SerializedName("time")
    private String time;
    @SerializedName("milliseconds_since_epoch")
    private String milliseconds;
    @SerializedName("date")
    private String date;

    public String getTime() {
        return time;
    }

    public String getMilliseconds() {
        return milliseconds;
    }

    public String getDate() {
        return date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setMilliseconds(String milliseconds) {
        this.milliseconds = milliseconds;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

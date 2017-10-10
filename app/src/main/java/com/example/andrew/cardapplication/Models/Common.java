package com.example.andrew.cardapplication.Models;

/**
 * Created by Andrew on 09.10.2017.
 */

public class Common {
    private IP ip;
    private DateTime dateTime;
    private Headers headers;

    public Common(IP ip, DateTime dateTime, Headers headers) {
        this.ip = ip;
        this.dateTime = dateTime;
        this.headers = headers;
    }

    public Common() {
    }

    public IP getIp() {
        return ip;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public Headers getHeaders() {
        return headers;
    }

    public void setIp(IP ip) {
        this.ip = ip;
    }

    public void setDateTime(DateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setHeaders(Headers headers) {
        this.headers = headers;
    }
}

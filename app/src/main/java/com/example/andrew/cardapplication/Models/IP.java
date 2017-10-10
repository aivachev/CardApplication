package com.example.andrew.cardapplication.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Andrew on 09.10.2017.
 */

public class IP {
    @SerializedName("ip")
    private String ip;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}

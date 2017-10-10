package com.example.andrew.cardapplication.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Andrew on 09.10.2017.
 */

public class Headers {
    @SerializedName("X-Cloud-Trace-Context")
    private String cloud;
    @SerializedName("User-Agent")
    private String agent;
    @SerializedName("Host")
    private String host;

    public String getHost() {
        return host;
    }
    public String getAgent() {
        return agent;
    }
    public String getCloud() {
        return cloud;
    }

    public void setHost(String host) {
        this.host = host;
    }
    public void setAgent(String agent) {
        this.agent = agent;
    }
    public void setCloud(String cloud) {
        this.cloud = cloud;
    }
}

package com.example.andrew.cardapplication.AsyncTasks;

import android.content.Context;

import com.example.andrew.cardapplication.Models.Common;
import com.example.andrew.cardapplication.Models.DateTime;
import com.example.andrew.cardapplication.Models.Headers;
import com.example.andrew.cardapplication.Models.IP;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by Andrew on 09.10.2017.
 */

public class AsyncTask extends android.os.AsyncTask<String, Void, Common> {

    private final static String IP_ADDR = "http://ip.jsontest.com/";
    private final static String HEADERS_ADDR = "http://headers.jsontest.com/";
    private final static String DATE_ADDR = "http://date.jsontest.com";
    private Context context;

    public AsyncTask(Context context) {
        this.context = context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    protected Common doInBackground(String... strings) {
        Common res = new Common();
        try {
            res = new Common(getIPjson(), getDateTimeJson(), getHeadersJson());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    //получение информации об ip
    private IP getIPjson() throws IOException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        URL url1 = new URL(IP_ADDR);
        InputStreamReader reader = new InputStreamReader(url1.openStream());
        IP dto = gson.fromJson(reader, IP.class);
        return dto;
    }

    //получение информации о headers
    private Headers getHeadersJson() throws IOException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        URL url1 = new URL(HEADERS_ADDR);
        InputStreamReader reader = new InputStreamReader(url1.openStream());
        Headers dto = gson.fromJson(reader, Headers.class);
        return dto;
    }

    //получение информации о date time
    private DateTime getDateTimeJson() throws IOException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        URL url1 = new URL(DATE_ADDR);
        InputStreamReader reader = new InputStreamReader(url1.openStream());
        DateTime dto = gson.fromJson(reader, DateTime.class);
        return dto;
    }
}

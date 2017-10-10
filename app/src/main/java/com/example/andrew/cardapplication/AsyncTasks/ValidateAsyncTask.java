package com.example.andrew.cardapplication.AsyncTasks;

import android.content.Context;

import com.example.andrew.cardapplication.Models.JsonValidate;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by Andrew on 10.10.2017.
 */

public class ValidateAsyncTask extends android.os.AsyncTask<String, String, JsonValidate> {
    private final static String VALIDATE_ADDR = "http://validate.jsontest.com/?json=";
    private Context context;

    public ValidateAsyncTask(Context context) {
        this.context = context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    protected JsonValidate doInBackground(String... strings) {
        JsonValidate res = new JsonValidate();
        try {
            res = getValidateJson(strings[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    private JsonValidate getValidateJson(String str) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        URL url1 = new URL(VALIDATE_ADDR + str);
        InputStreamReader reader = new InputStreamReader(url1.openStream());
        JsonValidate dto = gson.fromJson(reader, JsonValidate.class);
        return dto;
    }
}

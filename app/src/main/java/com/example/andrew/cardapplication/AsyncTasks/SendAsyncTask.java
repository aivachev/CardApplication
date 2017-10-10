package com.example.andrew.cardapplication.AsyncTasks;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by Andrew on 10.10.2017.
 */

public class SendAsyncTask extends android.os.AsyncTask<String, String, Void> {
    private final static String ECHO_ADDR = "http://echo.jsontest.com/";
    private Context context;

    public SendAsyncTask(Context context) {
        this.context = context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    protected Void doInBackground(String... strings) {
        try {
            getIPjson(strings[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void getIPjson(String str) throws IOException {
        URL url1 = new URL(ECHO_ADDR + str);
        InputStreamReader reader = new InputStreamReader(url1.openStream());
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(reader);
        String prettyJsonString = gson.toJson(je);
        Log.d("GSON", "IP: " + prettyJsonString);

        publishProgress(prettyJsonString);
    }

    protected void onProgressUpdate(String... progress) {
        Toast.makeText(context, progress[0], Toast.LENGTH_SHORT).show();
    }
}

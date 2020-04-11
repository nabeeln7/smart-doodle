package com.iott.smartdoodle;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class LifxPowerOnAPI extends AsyncTask<Void, Void, Void> {
    private static final String sTOKEN = "c488be466ed32c59c993704b241982f050a499a5a16568951ae57723e09d02fc";

    @Override
    protected Void doInBackground(Void... voids) {
        String urlString = "https://api.lifx.com/v1/lights/all/state"; // URL to call
        String urlParameters  = "power=on";

        try {
            URL url = new URL(urlString);
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setRequestMethod("PUT");
            urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            urlConnection.setRequestProperty("authorization", "Bearer " + sTOKEN);

            OutputStreamWriter out = new OutputStreamWriter(
                    urlConnection.getOutputStream());
            out.write(urlParameters);
            out.close();

            String json_response = "";
            InputStreamReader in = new InputStreamReader(urlConnection.getInputStream());
            BufferedReader br = new BufferedReader(in);
            String text = "";
            while ((text = br.readLine()) != null) {
                json_response += text;
            }
            Log.d("http", json_response);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
}

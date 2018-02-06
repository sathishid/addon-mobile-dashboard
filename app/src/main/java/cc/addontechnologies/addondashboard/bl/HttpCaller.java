package cc.addontechnologies.addondashboard.bl;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import cc.addontechnologies.addondashboard.models.Report;

/**
 * Created by sathishbabur on 1/31/2018.
 */

public class HttpCaller extends AsyncTask<HttpRequest, String, HttpResponse> {


    private static final String UTF_8 = "UTF-8";


    @Override

    protected HttpResponse doInBackground(HttpRequest... params) {

        HttpURLConnection urlConnection = null;

        HttpRequest httpCall = params[0];
        HttpResponse httpResponse = new HttpResponse();

        StringBuilder response = new StringBuilder();

        try {

            String dataParams = getDataString(httpCall.getParams(), httpCall.getMethodtype());

            URL url = new URL(httpCall.getUrl());

            urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setRequestMethod(httpCall.getMethodName());

            urlConnection.setReadTimeout(10000 /* milliseconds */);

            urlConnection.setConnectTimeout(15000 /* milliseconds */);

            if (httpCall.getMethodtype() == HttpRequest.POST) {

                OutputStream os = urlConnection.getOutputStream();

                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, UTF_8));

                writer.append(dataParams);

                writer.flush();

                writer.close();

                os.close();

            }

            int responseCode = urlConnection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {

                String line;

                BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

                while ((line = br.readLine()) != null) {
                    response.append(line);
                }
                httpResponse.setSuccess();
                httpResponse.setReports(jsonToObject(response.toString()));
            } else {
                httpResponse.setMesssage(urlConnection.getResponseMessage());
            }
        } catch (JsonParseException exception) {
            Log.i("JSON", exception.getMessage());
            httpResponse.setMesssage(exception.getMessage());
        } catch (UnsupportedEncodingException e) {
            httpResponse.setMesssage(e.getMessage());
            e.printStackTrace();
        } catch (MalformedURLException e) {
            httpResponse.setMesssage(e.getMessage());
            e.printStackTrace();

        } catch (IOException e) {
            httpResponse.setMesssage(e.getMessage());
            e.printStackTrace();
        } catch (Exception exception) {
            httpResponse.setMesssage(exception.getMessage());
            exception.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }

        return httpResponse;

    }

    private Report[] jsonToObject(String json) {
        //"2015-02-02T00:00:00" "YYYY-MM-dd'T'HH:mm:ss.SS'Z'"
        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class,new DateAdapter()).create();

        List<Report> reports = gson.fromJson(json, new TypeToken<List<Report>>() {
        }.getType());

        return reports.toArray(new Report[]{});
    }

    @Override

    protected void onPostExecute(HttpResponse response) {

        super.onPostExecute(response);

        onResponse(response);

    }


    public void onResponse(HttpResponse response) {


    }


    private String getDataString(HashMap<String, String> params, int methodType)
            throws UnsupportedEncodingException {

        return null;

    }

}
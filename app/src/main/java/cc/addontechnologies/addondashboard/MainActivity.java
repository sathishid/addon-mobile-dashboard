package cc.addontechnologies.addondashboard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import cc.addontechnologies.addondashboard.bl.AppConstant;
import cc.addontechnologies.addondashboard.bl.ChartUtil;
import cc.addontechnologies.addondashboard.bl.HttpCaller;
import cc.addontechnologies.addondashboard.bl.HttpRequest;
import cc.addontechnologies.addondashboard.bl.HttpResponse;
import cc.addontechnologies.addondashboard.models.Report;

public class MainActivity extends AppCompatActivity {

    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy",
            Locale.getDefault());
    Chart chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chart=findViewById(R.id.barChart);
    }


    public void renderChart(View view) {
        try {
            final HttpRequest httpRequest = new HttpRequest();

            httpRequest.setMethodtype(HttpRequest.GET);

            httpRequest.setUrl(AppConstant.getAPIUrl());

            HashMap<String, String> params = new HashMap<>();

            params.put("name", "James Bond");

            httpRequest.setParams(params);

            new HttpCaller() {

                @Override

                public void onResponse(HttpResponse response) {

                    super.onResponse(response);

                    if (response.getStatus() == HttpResponse.ERROR) {
                        Toast.makeText(MainActivity.this, response.getMesssage(), Toast.LENGTH_SHORT).show();
                    } else {
                        new ChartUtil(chart,response.getReports()).populateChart().invalidate();
                    }
                }
            }.execute(httpRequest);
        } catch (Exception exception) {
            Toast.makeText(MainActivity.this, exception.getMessage(), Toast.LENGTH_SHORT).show();
            Log.e("URL Malfunction :", exception.getMessage());
        }
    }



}

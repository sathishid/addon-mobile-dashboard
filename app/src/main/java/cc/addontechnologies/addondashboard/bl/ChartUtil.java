package cc.addontechnologies.addondashboard.bl;

import android.graphics.Color;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

import cc.addontechnologies.addondashboard.R;
import cc.addontechnologies.addondashboard.models.Report;

/**
 * Created by sathishbabur on 1/31/2018.
 */
enum ChartType {
    LineChart,
    BarChart
}

public class ChartUtil {

    Chart chart;
    Report[] reports;
    ChartType chartType;

    public ChartUtil(Chart chart, Report[] reports) {
        this.chart = chart;
        this.reports = reports;
        if (chart instanceof BarChart)
            this.chartType = ChartType.BarChart;
        else if (chart instanceof LineChart)
            this.chartType = ChartType.LineChart;
        else
            throw new IllegalArgumentException("Not Supported Chart type");
    }

    private List<Entry> getLineEntries() {
        List<Entry> entries = new ArrayList<>(reports.length);
        for (Report report : reports) {
            entries.add(new Entry(report.getId(), (float) report.getTotalAmount()));
        }
        return entries;
    }

    private List<BarEntry> getBarEnties() {
        List<BarEntry> entries = new ArrayList<>(reports.length);
        int i=0;
        for (Report report : reports) {
            entries.add(new BarEntry(i, (float) report.getTotalAmount()));
            i++;
        }
        return entries;
    }

    private Chart populateLineChart() {
        LineDataSet lineDataSet = new LineDataSet(getLineEntries(), "Dates");
        lineDataSet.setColor(R.color.colorAccent);
        lineDataSet.setValueTextColor(R.color.colorPrimaryDark);

        LineData lineData = new LineData(lineDataSet);

        chart.setData(lineData);
        chart.getXAxis().setDrawLabels(false);
        chart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        chart.getLegend().setEnabled(false);

        return chart;
    }

    private Chart populateBarChart() {
        BarDataSet barDataSet = new BarDataSet(getBarEnties(), "Dates");
        BarData barData = new BarData(barDataSet);
        BarChart barChart = (BarChart) chart;
        barChart.setData(barData);
        XAxis xAxis=barChart.getXAxis();
        xAxis.setDrawAxisLine(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setValueFormatter(new XAxisValueDateFormatter(reports));
        //xAxis.setLabelRotationAngle(90f);

        barDataSet.setValueTextSize(0);

        barDataSet.setColor(Color.parseColor("#FF0066CC"));

        YAxis yAxis=barChart.getAxisRight();
        yAxis.setDrawAxisLine(false);
        yAxis.setDrawLabels(false);

        barChart.getLegend().setEnabled(false);
        barChart.getDescription().setText("");
        return chart;
    }

    public Chart populateChart() {
        switch (chartType) {
            case BarChart:
                return populateBarChart();
            case LineChart:
                return populateLineChart();
            default:
                throw new UnsupportedOperationException("Unsupported chart type.");
        }
    }
}

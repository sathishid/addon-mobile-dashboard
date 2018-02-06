package cc.addontechnologies.addondashboard.bl;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.text.SimpleDateFormat;
import java.util.Date;

import cc.addontechnologies.addondashboard.models.Report;

/**
 * Created by sathishbabur on 2/1/2018.
 */

public class XAxisValueDateFormatter implements IAxisValueFormatter {


    private SimpleDateFormat mFormat = new SimpleDateFormat("dd MMM");
    Report[] reports;
    public XAxisValueDateFormatter(Report[] reports)
    {
        this.reports=reports;
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        return  mFormat.format(reports[(int)value].getDate());
    }

}


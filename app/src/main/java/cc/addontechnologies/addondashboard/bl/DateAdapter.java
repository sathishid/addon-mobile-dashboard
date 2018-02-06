package cc.addontechnologies.addondashboard.bl;

import android.util.Log;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.net.Inet4Address;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by sathishbabur on 1/31/2018.
 */

public class DateAdapter extends TypeAdapter<Date> {
    @Override
    public Date read(JsonReader in) throws IOException {
        if (in.peek() == JsonToken.NULL) {
            in.nextNull();
            return null;
        }
        Date date=null;
        try {
            String dateAsStr = in.nextString();
            String[] strDate = dateAsStr.split("T")[0].split("-");
            int year=Integer.parseInt(strDate[0]);
            int month=Integer.parseInt(strDate[1])-1;
            int day= Integer.parseInt(strDate[2]);
            Calendar calendar=Calendar.getInstance();
            calendar.set(year,month,day);
            date= calendar.getTime();
        }
        catch(Exception e)
        {
            Log.e("DateAdapter",e.getMessage());
        }
        return date;
    }

    @Override
    public void write(JsonWriter out, Date value) throws IOException {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}

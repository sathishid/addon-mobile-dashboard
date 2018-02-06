package cc.addontechnologies.addondashboard.bl;

import java.lang.reflect.Array;

import cc.addontechnologies.addondashboard.models.Report;

/**
 * Created by sathishbabur on 1/31/2018.
 */

public class HttpResponse {
    public static final int ERROR=1;
    public static final int Success=2;

    private int status=ERROR;
    private String messsage;
    private Report[] reports;

    public int getStatus() {
        return status;
    }
    public void setSuccess()
    {
        setStatus(Success);
    }
    public void setError()
    {
        setStatus(ERROR);
    }
    public void setStatus(int status) {
        this.status = status;
    }

    public String getMesssage() {
        return messsage;
    }

    public void setMesssage(String messsage) {
        this.messsage = messsage;
    }

    public Report[] getReports() {
        return reports;
    }

    public void setReports(Report[] reports) {
        this.reports = reports;
    }
}

package cc.addontechnologies.addondashboard.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by sathishbabur on 1/30/2018.
 */

public class Report {

    @SerializedName("Id")
    private int id;
    @SerializedName("RecordId")
    private int recordId;
    @SerializedName("Date")
    private Date date;
    @SerializedName("BusinessId")
    private String businessId;
    @SerializedName("SystemId")
    private String systemId;
    @SerializedName("TotalAmount")
    private double totalAmount;

    public Report() {

    }

    public Report(int id, Date date, double totalAmount) {
        this.id = id;
        this.date = null;
        this.totalAmount = totalAmount;
        businessId = "1";
        systemId = "1";
    }

    private Date toDate(String date) {
        String[] splitDate = date.split("/");
        int year = Integer.parseInt(splitDate[2]);
        int month = Integer.parseInt(splitDate[1]);
        int day = Integer.parseInt(splitDate[0]);
        return new Date(year, month, day);
    }

    public Report(int id, String date, double totalAmount) {
        this(id, new Date(2015, 1, 1), totalAmount);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmound) {
        this.totalAmount = totalAmound;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }
}

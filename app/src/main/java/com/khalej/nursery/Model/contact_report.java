package com.khalej.nursery.Model;

import com.google.gson.annotations.SerializedName;

public class contact_report {
    @SerializedName("id")
    int id;

    @SerializedName("details")
    String details;
    @SerializedName("month")
    int month;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

   public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

}

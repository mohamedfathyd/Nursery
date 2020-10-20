package com.khalej.nursery.Model;

import com.google.gson.annotations.SerializedName;

public class contact_absence {
    @SerializedName("id")
    int id;

    @SerializedName("name")
    String name;

    @SerializedName("details")
    String details;
    @SerializedName("month")
    int month;

    @SerializedName("num_days")
    int num_days;
    @SerializedName("sud_id")
    int sud_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getNum_days() {
        return num_days;
    }

    public void setNum_days(int num_days) {
        this.num_days = num_days;
    }

    public int getSud_id() {
        return sud_id;
    }

    public void setSud_id(int sud_id) {
        this.sud_id = sud_id;
    }
}

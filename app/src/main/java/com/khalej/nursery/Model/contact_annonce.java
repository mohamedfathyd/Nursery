package com.khalej.nursery.Model;
import com.google.gson.annotations.SerializedName;

public class contact_annonce {
    @SerializedName("id")
    int id;

    @SerializedName("image")
    String Image;
    @SerializedName("name")
    String name;
    @SerializedName("details")
    String details;
    @SerializedName("date")
    String date;
    @SerializedName("time")
    String time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

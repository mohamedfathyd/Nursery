package com.khalej.nursery.Model;
import com.google.gson.annotations.SerializedName;


public class contact_home_ {
    @SerializedName("id")
    int id;
    @SerializedName("name")
    String name;
    @SerializedName("img")
    String Img;
    @SerializedName("price")
    String price;
    @SerializedName("details")
    String Description;
    @SerializedName("error")
    String error;
    @SerializedName("date")
    String date;
    @SerializedName("time")
    String time;

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

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getImg() {
        return Img;
    }

    public void setImg(String img) {
        Img = img;
    }
}

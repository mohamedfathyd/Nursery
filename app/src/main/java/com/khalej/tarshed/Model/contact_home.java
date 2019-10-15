package com.khalej.tarshed.Model;
import com.google.gson.annotations.SerializedName;


public class contact_home{
    @SerializedName("id")
    int id;
    @SerializedName("name")
    String name;
    @SerializedName("img")
    String Img;
    @SerializedName("price")
    String price;
    @SerializedName("description")
    String Description;

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

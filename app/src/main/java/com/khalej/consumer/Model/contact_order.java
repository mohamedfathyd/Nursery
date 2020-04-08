package com.khalej.consumer.Model;

import com.google.gson.annotations.SerializedName;

public class contact_order {
    @SerializedName("id")
    int id;

    @SerializedName("name")
    String name;

    @SerializedName("phone")
    String phone;
    @SerializedName("address")
    String address;

    @SerializedName("charge")
    String charge;
    @SerializedName("sud_id")
    int sud_id;

    public int getSud_id() {
        return sud_id;
    }

    public void setSud_id(int sud_id) {
        this.sud_id = sud_id;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }



    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

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


}

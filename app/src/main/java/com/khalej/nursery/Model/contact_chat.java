package com.khalej.nursery.Model;

import com.google.gson.annotations.SerializedName;

public class contact_chat {
    @SerializedName("id")
    int id;

    @SerializedName("text")
    String text;

    @SerializedName("sender")
    int sender;
    @SerializedName("sud_id")
    int sud_id;

    public int getSud_id() {
        return sud_id;
    }

    public void setSud_id(int sud_id) {
        this.sud_id = sud_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getSender() {
        return sender;
    }

    public void setSender(int sender) {
        this.sender = sender;
    }
}

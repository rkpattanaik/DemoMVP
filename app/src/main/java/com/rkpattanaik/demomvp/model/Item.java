
package com.rkpattanaik.demomvp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Item {

    @SerializedName("srl")
    @Expose
    private int srl;
    @SerializedName("content")
    @Expose
    private List<Content> content = null;

    public int getSrl() {
        return srl;
    }

    public void setSrl(int srl) {
        this.srl = srl;
    }

    public List<Content> getContent() {
        return content;
    }

    public void setContent(List<Content> content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Item{" +
                "srl=" + srl +
                ", content=" + content +
                '}';
    }
}

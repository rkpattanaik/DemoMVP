
package com.rkpattanaik.demomvp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Content {

    @SerializedName("t")
    @Expose
    private int t;
    @SerializedName("v")
    @Expose
    private String v;

    public int getT() {
        return t;
    }

    public void setT(int t) {
        this.t = t;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    @Override
    public String toString() {
        return "Content{" +
                "t=" + t +
                ", v='" + v + '\'' +
                '}';
    }
}

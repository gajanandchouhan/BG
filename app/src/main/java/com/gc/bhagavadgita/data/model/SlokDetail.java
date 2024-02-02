package com.gc.bhagavadgita.data.model;

import com.google.gson.annotations.SerializedName;

public class SlokDetail {
    @SerializedName("ht")
    String meaning;
    @SerializedName("hc")
    String transalation;

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getTransalation() {
        return transalation;
    }

    public void setTransalation(String transalation) {
        this.transalation = transalation;
    }
}

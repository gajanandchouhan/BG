package com.gc.bhagavadgita.data.model;

import androidx.annotation.Keep;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
@Keep
public class VersesListResponse implements Serializable{
    @SerializedName("chapter")
    private String chapter_number;
    private String meaning;
    @SerializedName("slok")
    private String text;
    @SerializedName("rams")
    private SlokDetail slokDetail;
    @SerializedName("verse")
    private String verse_number;

    public String getChapter_number() {
        return chapter_number;
    }

    public void setChapter_number(String chapter_number) {
        this.chapter_number = chapter_number;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public String getVerse_number() {
        return verse_number;
    }

    public void setVerse_number(String verse_number) {
        this.verse_number = verse_number;
    }

    public SlokDetail getSlokDetail() {
        return slokDetail;
    }

    public void setSlokDetail(SlokDetail slokDetail) {
        this.slokDetail = slokDetail;
    }
}

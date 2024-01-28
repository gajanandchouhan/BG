package com.gc.bhagavadgita.data.model;

import androidx.annotation.Keep;

import java.io.Serializable;
import java.util.HashMap;
@Keep
public class ChapterListResponse implements Serializable {

    private int chapter_number;
    private String chapter_summary;
    private String name;
    private HashMap<String, String> meaning;
    private String name_translation;
    private String verses_count;

    public int getChapter_number() {
        return chapter_number;
    }

    public void setChapter_number(int chapter_number) {
        this.chapter_number = chapter_number;
    }

    public String getChapter_summary() {
        return chapter_summary;
    }

    public void setChapter_summary(String chapter_summary) {
        this.chapter_summary = chapter_summary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<String,String> getName_meaning() {
        return meaning;
    }

    public void setName_meaning(HashMap name_meaning) {
        this.meaning = name_meaning;
    }

    public String getName_translation() {
        return name_translation;
    }

    public void setName_translation(String name_translation) {
        this.name_translation = name_translation;
    }

    public String getVerses_count() {
        return verses_count;
    }

    public void setVerses_count(String verses_count) {
        this.verses_count = verses_count;
    }
}

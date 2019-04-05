package com.gc.bhagavadgita.data.model;

import java.io.Serializable;

public class VersesListResponse implements Serializable{
    private String chapter_number;
    private String meaning;
    private String text;
    private String transliteration;
    private String verse_number;
    private String word_meanings;

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

    public String getTransliteration() {
        return transliteration;
    }

    public void setTransliteration(String transliteration) {
        this.transliteration = transliteration;
    }

    public String getVerse_number() {
        return verse_number;
    }

    public void setVerse_number(String verse_number) {
        this.verse_number = verse_number;
    }

    public String getWord_meanings() {
        return word_meanings;
    }

    public void setWord_meanings(String word_meanings) {
        this.word_meanings = word_meanings;
    }
}


package com.example.kidapp.model;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Comment {

    @SerializedName("author")
    private Author mAuthor;
    @SerializedName("content")
    private String mContent;
    @SerializedName("date")
    private String mDate;
    @SerializedName("id")
    private Long mId;
    @SerializedName("title")
    private String mTitle;

    public Author getAuthor() {
        return mAuthor;
    }

    public void setAuthor(Author author) {
        mAuthor = author;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

}

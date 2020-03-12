
package com.example.kidapp.model;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Banner {

    @SerializedName("id")
    private Long mId;
    @SerializedName("image")
    private String mImage;
    @SerializedName("link_type")
    private Long mLinkType;
    @SerializedName("link_value")
    private String mLinkValue;

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        mImage = image;
    }

    public Long getLinkType() {
        return mLinkType;
    }

    public void setLinkType(Long linkType) {
        mLinkType = linkType;
    }

    public String getLinkValue() {
        return mLinkValue;
    }

    public void setLinkValue(String linkValue) {
        mLinkValue = linkValue;
    }

}

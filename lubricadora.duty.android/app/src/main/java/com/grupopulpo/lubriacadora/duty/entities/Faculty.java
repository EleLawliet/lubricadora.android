
package com.grupopulpo.lubriacadora.duty.entities;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Faculty {

    @SerializedName("code")
    private int mCode;
    @SerializedName("description")
    private String mDescription;
    @SerializedName("image")
    private String mImage;
    @SerializedName("name")
    private String mName;
    @SerializedName("phone")
    private String mPhone;

    public int getCode() {
        return mCode;
    }

    public void setCode(int code) {
        mCode = code;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        mImage = image;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String phone) {
        mPhone = phone;
    }

    public Faculty(int mCode, String mDescription, String mImage, String mName, String mPhone) {
        this.mCode = mCode;
        this.mDescription = mDescription;
        this.mImage = mImage;
        this.mName = mName;
        this.mPhone = mPhone;
    }
}

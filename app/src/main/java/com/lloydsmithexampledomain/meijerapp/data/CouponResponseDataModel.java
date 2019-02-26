/*
 *
 * Created by Lloyd Smith on 2/25/19 10:04 PM.
 * Copyright (c) 2019. All rights reserved.
 * Last modified 2/25/19 7:36 AM.
 */

package com.lloydsmithexampledomain.meijerapp.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CouponResponseDataModel implements Serializable {

    @SerializedName("meijerOfferId")
    private int id;
    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private String description;
    @SerializedName("couponExpirationGroupTag")
    private String expirationTag;
    @SerializedName("imageURL")
    private String imageURL;
    @SerializedName("availableCouponCount")
    private Integer availableCouponCount;

    private boolean isClipped = false;

    public boolean isClipped() {
        return isClipped;
    }

    public void setClipped(boolean clipped) {
        isClipped = clipped;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getExpirationTag() {
        return expirationTag;
    }

    public String getImageURL() {
        return imageURL;
    }

    public Integer getAvailableCouponCount() {
        if (availableCouponCount == null) {
            return 0;
        } else {
            return availableCouponCount;
        }
    }
}

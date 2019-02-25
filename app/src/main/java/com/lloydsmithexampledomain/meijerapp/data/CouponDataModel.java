/*
 *
 * Created by Lloyd Smith on 2/24/19 7:34 PM.
 * Copyright (c) 2019. All rights reserved.
 * Last modified 2/24/19 7:29 PM.
 */

package com.lloydsmithexampledomain.meijerapp.data;

import android.graphics.Bitmap;

public class CouponDataModel {

    private int id;
    private String title;
    private String description;
    private String expirationTag;
    private String imageURL;
    private boolean isClipped = false;
    private Bitmap image = null;

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isClipped() {
        return isClipped;
    }

    public void setClipped(boolean clipped) {
        isClipped = clipped;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExpirationTag() {
        return expirationTag;
    }

    public void setExpirationTag(String expirationTag) {
        this.expirationTag = expirationTag;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}

/*
 *
 * Created by Lloyd Smith on 2/24/19 7:34 PM.
 * Copyright (c) 2019. All rights reserved.
 * Last modified 2/24/19 7:29 PM.
 */

package com.lloydsmithexampledomain.meijerapp.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CouponListDataModel implements Serializable {

    @SerializedName("listOfCoupons")
    private List<CouponResponseDataModel> couponList;

    public List<CouponResponseDataModel> getCouponList() {
        if (couponList == null) {
            return new ArrayList<>();
        }
        return couponList;
    }
}

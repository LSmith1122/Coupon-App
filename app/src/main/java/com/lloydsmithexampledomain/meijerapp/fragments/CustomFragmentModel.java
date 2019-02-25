/*
 *
 * Created by Lloyd Smith on 2/24/19 7:34 PM.
 * Copyright (c) 2019. All rights reserved.
 * Last modified 2/24/19 7:29 PM.
 */

package com.lloydsmithexampledomain.meijerapp.fragments;

import android.support.v4.app.Fragment;

import com.lloydsmithexampledomain.meijerapp.data.CouponDataModel;

import java.util.List;

public class CustomFragmentModel extends Fragment {

    public List<CouponDataModel> getCouponDataModelList() {
        return null;
    }

    public void setCouponDataModelList(List<CouponDataModel> couponDataModelList) {

    }

    public interface CouponUpdateListener {
        void onCouponListUpdate(CouponDataModel couponDataModel);
    }
}

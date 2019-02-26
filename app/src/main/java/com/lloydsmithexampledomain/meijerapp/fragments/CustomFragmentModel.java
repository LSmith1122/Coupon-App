/*
 *
 * Created by Lloyd Smith on 2/25/19 10:04 PM.
 * Copyright (c) 2019. All rights reserved.
 * Last modified 2/25/19 7:36 AM.
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

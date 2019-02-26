/*
 *
 * Created by Lloyd Smith on 2/25/19 10:04 PM.
 * Copyright (c) 2019. All rights reserved.
 * Last modified 2/25/19 7:36 AM.
 */

package com.lloydsmithexampledomain.meijerapp.interfaces;

import com.lloydsmithexampledomain.meijerapp.data.CouponDataModel;

import java.util.List;

public interface CouponFragmentInterface {
    void refreshList(List<CouponDataModel> couponDataModelList);
}

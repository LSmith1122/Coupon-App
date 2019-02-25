/*
 *
 * Created by Lloyd Smith on 2/24/19 7:34 PM.
 * Copyright (c) 2019. All rights reserved.
 * Last modified 2/24/19 7:29 PM.
 */

package com.lloydsmithexampledomain.meijerapp.interfaces;

import com.lloydsmithexampledomain.meijerapp.data.CouponDataModel;

import java.util.List;

public interface CouponFragmentInterface {
    void refreshList(List<CouponDataModel> couponDataModelList);
}

/*
 *
 * Created by Lloyd Smith on 2/25/19 10:04 PM.
 * Copyright (c) 2019. All rights reserved.
 * Last modified 2/25/19 7:36 AM.
 */

package com.lloydsmithexampledomain.meijerapp.interfaces;

import com.lloydsmithexampledomain.meijerapp.data.CouponListDataModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NetworkModelInterface {

    @GET("api/Products?code=34lgBae%2FxIEnqksQpkn3w9F0JTKCafuiCr0ejLNLvBzlOlOZBa1CMA%3D%3D")
    Call<CouponListDataModel> getCoupons();
}

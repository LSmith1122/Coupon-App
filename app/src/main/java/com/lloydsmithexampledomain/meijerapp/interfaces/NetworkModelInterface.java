/*
 *
 * Created by Lloyd Smith on 2/24/19 7:34 PM.
 * Copyright (c) 2019. All rights reserved.
 * Last modified 2/24/19 7:29 PM.
 */

package com.lloydsmithexampledomain.meijerapp.interfaces;

import com.lloydsmithexampledomain.meijerapp.data.CouponListDataModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NetworkModelInterface {

    @GET("api/Products?code=34lgBae%2FxIEnqksQpkn3w9F0JTKCafuiCr0ejLNLvBzlOlOZBa1CMA%3D%3D")
    Call<CouponListDataModel> getCoupons();
}

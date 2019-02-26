/*
 *
 * Created by Lloyd Smith on 2/25/19 10:04 PM.
 * Copyright (c) 2019. All rights reserved.
 * Last modified 2/25/19 7:36 AM.
 */

package com.lloydsmithexampledomain.meijerapp.presenters;

import com.lloydsmithexampledomain.meijerapp.data.CouponListDataModel;
import com.lloydsmithexampledomain.meijerapp.data.CouponResponseDataModel;
import com.lloydsmithexampledomain.meijerapp.helpers.Constants;
import com.lloydsmithexampledomain.meijerapp.interfaces.NetworkModelInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivityPresenter {

    private static MainActivityPresenter thisPresenter;
    private MainActivityPresenter.View view;

    public MainActivityPresenter(MainActivityPresenter.View context) {
        view = context;
    }

    public void getCoupons() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.URL.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        NetworkModelInterface networkInterface = retrofit.create(NetworkModelInterface.class);
        Call<CouponListDataModel> call = networkInterface.getCoupons();
        call.enqueue(new Callback<CouponListDataModel>() {
            @Override
            public void onResponse(Call<CouponListDataModel> call, Response<CouponListDataModel> response) {
                view.onSuccess(response.body().getCouponList());
            }

            @Override
            public void onFailure(Call<CouponListDataModel> call, Throwable t) {
                view.onError();
            }
        });
    }

    public interface View {
        void onSuccess(List<CouponResponseDataModel> couponResponseList);

        void onError();
    }
}

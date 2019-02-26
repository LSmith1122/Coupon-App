/*
 *
 * Created by Lloyd Smith on 2/25/19 10:04 PM.
 * Copyright (c) 2019. All rights reserved.
 * Last modified 2/25/19 7:36 AM.
 */

package com.lloydsmithexampledomain.meijerapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lloydsmithexampledomain.meijerapp.R;
import com.lloydsmithexampledomain.meijerapp.adapters.CouponRecyclerAdapter;
import com.lloydsmithexampledomain.meijerapp.data.CouponDataModel;
import com.lloydsmithexampledomain.meijerapp.helpers.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ClippedCouponFragment extends CustomFragmentModel
        implements CouponRecyclerAdapter.CouponInteractionListener {

    private static final String SPACE_STRING = " ";
    private static final int SCROLL_DOWN = 1;
    @BindView(R.id.coupon_recyclerview)
    protected RecyclerView recyclerView;
    @BindView(R.id.coupon_amount_text)
    protected TextView couponAmountText;
    private List<CouponDataModel> couponDataModelList = new ArrayList<>();
    private CouponRecyclerAdapter adapter;
    private CouponUpdateListener listener;

    public ClippedCouponFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof CouponUpdateListener) {
            listener = (CouponUpdateListener) context;
        } else {
            throw new IllegalStateException();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_available_coupon, container, false);
        ButterKnife.bind(this, rootView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        setCouponAmount();

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!recyclerView.canScrollVertically(SCROLL_DOWN)) {
                    adapter.increaseItemLimit();
                }
            }
        });

        return rootView;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public List<CouponDataModel> getCouponDataModelList() {
        return couponDataModelList;
    }

    @Override
    public void setCouponDataModelList(List<CouponDataModel> newList) {
        this.couponDataModelList.clear();
        this.couponDataModelList.addAll(newList);
        if (adapter == null) {
            adapter = new CouponRecyclerAdapter(getContext(), this, couponDataModelList);
            recyclerView.setAdapter(adapter);
        }
        setupOrRefreshAdapter();
    }

    private void setupOrRefreshAdapter() {
        recyclerView.swapAdapter(createNewAdapterFromExisting(), false);
        setCouponAmount();
    }

    private CouponRecyclerAdapter createNewAdapterFromExisting() {
        CouponRecyclerAdapter currentAdapter = ((CouponRecyclerAdapter) adapter);
        int itemLimit = Math.max(currentAdapter.getItemLimit(), Constants.Coupons.ITEM_LIMIT_MIN);
        return new CouponRecyclerAdapter(getContext(), this, couponDataModelList, itemLimit);
    }

    private void setCouponAmount() {
        final String totalAmountText = couponDataModelList.size() + SPACE_STRING + getActivity().getString(R.string.total).toUpperCase();
        couponAmountText.setText(totalAmountText);
    }

    @Override
    public void onCouponClipToggleInteraction(CouponDataModel couponDataModel, boolean clipState) {
        toggleClipState(couponDataModel, clipState);
    }

    private void toggleClipState(CouponDataModel couponDataModel, boolean clipped) {
        couponDataModel.setClipped(clipped);
        listener.onCouponListUpdate(couponDataModel);
    }
}

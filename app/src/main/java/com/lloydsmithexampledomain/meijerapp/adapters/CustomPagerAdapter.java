/*
 *
 * Created by Lloyd Smith on 2/25/19 10:04 PM.
 * Copyright (c) 2019. All rights reserved.
 * Last modified 2/25/19 7:36 AM.
 */

package com.lloydsmithexampledomain.meijerapp.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.lloydsmithexampledomain.meijerapp.R;
import com.lloydsmithexampledomain.meijerapp.fragments.AvailableCouponFragment;
import com.lloydsmithexampledomain.meijerapp.fragments.ClippedCouponFragment;
import com.lloydsmithexampledomain.meijerapp.helpers.Constants;

public class CustomPagerAdapter extends FragmentPagerAdapter {

    private Context context;
    private AvailableCouponFragment availableCouponFragment;
    private ClippedCouponFragment clippedCouponFragment;

    public CustomPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case Constants.PagerTabs.POSITION_AVAILABLE_SECTION:
                if (availableCouponFragment == null) {
                    availableCouponFragment = new AvailableCouponFragment();
                }
                return availableCouponFragment;
            case Constants.PagerTabs.POSITION_CLIPPED_SECTION:
                if (clippedCouponFragment == null) {
                    clippedCouponFragment = new ClippedCouponFragment();
                }
                return clippedCouponFragment;
            default:
                Log.i("Adapter", "Nothing");
                return null;
        }
    }

    @Override
    public int getCount() {
        return context.getResources().getStringArray(R.array.tab_titles).length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return context.getResources().getStringArray(R.array.tab_titles)[position];
    }
}

/*
 *
 * Created by Lloyd Smith on 2/25/19 10:04 PM.
 * Copyright (c) 2019. All rights reserved.
 * Last modified 2/25/19 9:58 PM.
 */

package com.lloydsmithexampledomain.meijerapp.activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.lloydsmithexampledomain.meijerapp.R;
import com.lloydsmithexampledomain.meijerapp.adapters.CustomPagerAdapter;
import com.lloydsmithexampledomain.meijerapp.data.CouponDataModel;
import com.lloydsmithexampledomain.meijerapp.data.CouponResponseDataModel;
import com.lloydsmithexampledomain.meijerapp.fragments.AvailableCouponFragment;
import com.lloydsmithexampledomain.meijerapp.fragments.ClippedCouponFragment;
import com.lloydsmithexampledomain.meijerapp.fragments.CustomFragmentModel;
import com.lloydsmithexampledomain.meijerapp.helpers.Constants;
import com.lloydsmithexampledomain.meijerapp.presenters.MainActivityPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements MainActivityPresenter.View,
        CustomFragmentModel.CouponUpdateListener {

    private static final String TAG = "Main";
    @BindView(R.id.drawerlayout)
    protected DrawerLayout drawerLayout;
    @BindView(R.id.navigation_view)
    protected NavigationView navigationView;
    @BindView(R.id.view_pager)
    protected ViewPager viewPager;
    @BindView(R.id.tablayout)
    protected TabLayout tabLayout;
    @BindView(R.id.progressbar)
    protected ProgressBar progressBar;
    private MainActivityPresenter presenter;
    private FragmentManager fragmentManager;
    private CustomPagerAdapter pagerAdapter;
    private List<CouponDataModel> couponList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        ButterKnife.bind(this);
        progressBar.setVisibility(View.VISIBLE);

        presenter = new MainActivityPresenter(this);
        fragmentManager = getSupportFragmentManager();

        setupActionbar();
        setupTabLayout();

        if (presenter != null) {
            presenter.getCoupons();
        }
    }

    private void setupActionbar() {
        ActionBar actionbar = getSupportActionBar();
        if (actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(true);
            actionbar.setHomeAsUpIndicator(R.drawable.ic_hamburger_menu);
            actionbar.setElevation(0f);
            actionbar.setDisplayShowTitleEnabled(true);
        }

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menuitem_available:
                        tabLayout.getTabAt(Constants.PagerTabs.POSITION_AVAILABLE_SECTION).select();
                        break;
                    case R.id.menuitem_clipped:
                        tabLayout.getTabAt(Constants.PagerTabs.POSITION_CLIPPED_SECTION).select();
                        break;
                }
                item.setChecked(true);
                drawerLayout.closeDrawers();
                return true;
            }
        });
        navigationView.getMenu().findItem(R.id.menuitem_available).setChecked(true);
    }

    private void setupTabLayout() {
        pagerAdapter = new CustomPagerAdapter(fragmentManager, this);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setTabTextColors(
                getResources().getColor(R.color.tabUnselected, null),
                getResources().getColor(R.color.tabSelected, null));
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onSuccess(List<CouponResponseDataModel> couponResponseList) {
        couponList = getCouponDataModelList(couponResponseList);
        progressBar.setVisibility(View.GONE);
        setDataToLists();
    }

    private List<CouponDataModel> getCouponDataModelList(List<CouponResponseDataModel> couponResponseList) {
        ArrayList<CouponDataModel> list = new ArrayList<>();
        if (couponResponseList.size() > 0) {
            for (int i = 0; i < couponResponseList.size(); i++) {
                CouponDataModel dataModel = new CouponDataModel();
                dataModel.setTitle(couponResponseList.get(i).getTitle());
                dataModel.setDescription(couponResponseList.get(i).getDescription());
                dataModel.setExpirationTag(couponResponseList.get(i).getExpirationTag());
                dataModel.setImageURL(couponResponseList.get(i).getImageURL());
                dataModel.setId(couponResponseList.get(i).getId());
                list.add(dataModel);
            }
        }
        return list;
    }

    private void setDataToLists() {
        ArrayList<CouponDataModel> availableList = new ArrayList<>();
        ArrayList<CouponDataModel> clippedList = new ArrayList<>();
        final CouponDataModel loaderModel = new CouponDataModel();

        for (CouponDataModel coupon : couponList) {
            if (!coupon.isClipped()) {
                availableList.add(coupon);
            } else {
                clippedList.add(coupon);
            }
        }

//        if (availableList.size() > Constants.Coupons.ITEM_LIMIT_MIN) {
//            availableList.add(loaderModel);
//        } else if (clippedList.size() > Constants.Coupons.ITEM_LIMIT_MIN) {
//            clippedList.add(loaderModel);
//        }

        for (Fragment fragmentModel : fragmentManager.getFragments()) {
            if (fragmentModel instanceof AvailableCouponFragment) {
                ((CustomFragmentModel) fragmentModel).setCouponDataModelList(availableList);
            } else if (fragmentModel instanceof ClippedCouponFragment) {
                ((CustomFragmentModel) fragmentModel).setCouponDataModelList(clippedList);
            }
        }
    }

    @Override
    public void onError() {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setMessage(getString(R.string.unexpected_error_occurred));
        alertDialog.setCancelable(true);
        alertDialog.setButton(
                Dialog.BUTTON_NEUTRAL,
                getString(R.string.close),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finishAffinity();
                        dialogInterface.dismiss();
                    }
                });
        progressBar.setVisibility(View.GONE);
        alertDialog.show();
    }

    @Override
    public void onCouponListUpdate(CouponDataModel couponDataModel) {
        placeCouponToOtherList(couponDataModel);
    }

    public void placeCouponToOtherList(CouponDataModel couponDataModel) {
        for (int i = 0; i < couponList.size(); i++) {
            if (couponList.get(i).getId() == couponDataModel.getId()) {
                couponList.set(i, couponDataModel);
                break;
            }
        }
        setDataToLists();
    }
}

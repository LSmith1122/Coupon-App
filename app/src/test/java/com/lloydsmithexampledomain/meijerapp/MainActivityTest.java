/*
 *
 * Created by Lloyd Smith on 2/25/19 10:04 PM.
 * Copyright (c) 2019. All rights reserved.
 * Last modified 2/25/19 10:01 PM.
 */

package com.lloydsmithexampledomain.meijerapp;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.lloydsmithexampledomain.meijerapp.activities.MainActivity;
import com.lloydsmithexampledomain.meijerapp.data.CouponResponseDataModel;
import com.lloydsmithexampledomain.meijerapp.fragments.CustomFragmentModel;
import com.lloydsmithexampledomain.meijerapp.presenters.MainActivityPresenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.fakes.RoboMenuItem;

import java.util.ArrayList;
import java.util.List;

@Config(constants = BuildConfig.class)
@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {

    private Activity activity;

    @Before
    public void setup() {
        Intent intent = new Intent(RuntimeEnvironment.application, MainActivity.class);
        activity = Robolectric.buildActivity(MainActivity.class, intent).create().start().resume().get();
    }

    @Test
    public void testViewBinding_OnCreate() {
        // Arrange


        // Act


        // Assert
        assert (activity.findViewById(R.id.drawerlayout) != null);
        assert (activity.findViewById(R.id.navigation_view) != null);
        assert (activity.findViewById(R.id.view_pager) != null);
        assert (activity.findViewById(R.id.tablayout) != null);
        assert (activity.findViewById(R.id.progressbar) != null);
    }

    @Test
    public void testNavigationView_HasCorrectMenuItems() {
        // Arrange
        NavigationView navigationView = activity.findViewById(R.id.navigation_view);
        Menu menu = navigationView.getMenu();

        // Act

        // Assert
        assert (menu.findItem(R.id.menuitem_available) != null);
        assert (menu.findItem(R.id.menuitem_clipped) != null);
    }

    @Test
    public void testMainActivity_HasNecessaryInterfaces() {
        // Arrange


        // Act


        // Assert
        assert (activity instanceof MainActivityPresenter.View);
        assert (activity instanceof CustomFragmentModel.CouponUpdateListener);
    }

    @Test
    public void testViewPager_IsAvailable() {
        // Arrange
        ViewPager viewPager = activity.findViewById(R.id.view_pager);

        // Act


        // Assert
        assert (viewPager != null);
    }

    @Test
    public void testPagerAdapter_IsAvailable() {
        // Arrange
        ViewPager viewPager = activity.findViewById(R.id.view_pager);
        PagerAdapter pagerAdapter = viewPager.getAdapter();

        // Act


        // Assert
        assert (pagerAdapter != null);
    }

    @Test
    public void testPagerAdapter_HasViews() {
        // Arrange
        ViewPager viewPager = activity.findViewById(R.id.view_pager);
        PagerAdapter pagerAdapter = viewPager.getAdapter();

        // Act


        // Assert
        assert (pagerAdapter.getCount() > 0);
    }

    @Test
    public void testTabLayout_IsAvailable() {
        // Arrange
        TabLayout tabLayout = activity.findViewById(R.id.tablayout);

        // Act

        // Assert
        assert (tabLayout != null);
    }

    @Test
    public void testProgressBar_IsAvailable() {
        // Arrange
        ProgressBar progressBar = activity.findViewById(R.id.progressbar);

        // Act

        // Assert
        assert (progressBar != null);
    }

    @Test
    public void testDrawerLayout_IsOpen_OnHomeButtonSelected() {
        // Arrange
        boolean expected = true;
        MenuItem menuItem = new RoboMenuItem(android.R.id.home);
        DrawerLayout drawerLayout = activity.findViewById(R.id.drawerlayout);

        // Act
        boolean result = activity.onOptionsItemSelected(menuItem);

        // Assert
        assert (drawerLayout.isDrawerOpen(GravityCompat.START));
        assert (result == expected);
    }

    @Test
    public void testDrawerLayout_IsNotOpen_OnHomeButtonSelected() {
        // Arrange
        boolean expected = false;
        MenuItem menuItem = new RoboMenuItem();
        DrawerLayout drawerLayout = activity.findViewById(R.id.drawerlayout);

        // Act
        boolean result = activity.onOptionsItemSelected(menuItem);

        // Assert
        assert (!drawerLayout.isDrawerOpen(GravityCompat.START));
        assert (result == expected);
    }

    @Test
    public void testProgressBar_VisibilityIsGONE_OnSuccess() {
        // Arrange
        int expected = View.GONE;
        ProgressBar progressBar = activity.findViewById(R.id.progressbar);
        List<CouponResponseDataModel> list = new ArrayList<>();
        CouponResponseDataModel model = new CouponResponseDataModel();
        list.add(model);

        // Act
        ((MainActivityPresenter.View) activity).onSuccess(list);

        // Assert;
        assert (progressBar.getVisibility() == expected);
    }

}

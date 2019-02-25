/*
 *
 * Created by Lloyd Smith on 2/24/19 7:34 PM.
 * Copyright (c) 2019. All rights reserved.
 * Last modified 2/24/19 7:29 PM.
 */

package com.lloydsmithexampledomain.meijerapp.helpers.utility;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.animation.Animation;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.TranslateAnimation;

import com.lloydsmithexampledomain.meijerapp.helpers.Constants;

public class AppUtil {

    public static Animation getAnimation(Activity activity, @NonNull String direction) {
        AnticipateOvershootInterpolator interpolator = new AnticipateOvershootInterpolator(1.6f);

        int speed = 700;
        int directionType = 1;

        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int screenWidth = displayMetrics.widthPixels;

        if (direction.equals(Constants.Coupons.CLIPPED)) {
            directionType = directionType;
        } else {
            directionType = -directionType;
        }

        screenWidth = screenWidth * directionType;

        TranslateAnimation translationAnimation = new TranslateAnimation(0, screenWidth, 0, 0);
        translationAnimation.setDuration(speed);
        translationAnimation.setFillAfter(true);
        translationAnimation.setInterpolator(interpolator);

        return translationAnimation;
    }

}

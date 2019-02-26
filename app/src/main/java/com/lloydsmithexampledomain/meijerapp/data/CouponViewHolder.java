/*
 *
 * Created by Lloyd Smith on 2/25/19 10:04 PM.
 * Copyright (c) 2019. All rights reserved.
 * Last modified 2/25/19 7:36 AM.
 */

package com.lloydsmithexampledomain.meijerapp.data;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.lloydsmithexampledomain.meijerapp.R;

public class CouponViewHolder extends RecyclerView.ViewHolder {

    public ImageView imageView;
    public TextView titleView;
    public TextView descriptionView;
    public TextView expirationView;
    public TextView clipText;
    public FrameLayout clipActionView;

    public CouponViewHolder(View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.coupon_image);
        titleView = itemView.findViewById(R.id.coupon_title);
        descriptionView = itemView.findViewById(R.id.coupon_description);
        expirationView = itemView.findViewById(R.id.coupon_expiration);
        clipActionView = itemView.findViewById(R.id.button_clip_action);
        clipText = itemView.findViewById(R.id.button_clip_text);
    }
}

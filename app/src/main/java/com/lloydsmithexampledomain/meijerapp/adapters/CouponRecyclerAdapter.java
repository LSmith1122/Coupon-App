/*
 *
 * Created by Lloyd Smith on 2/25/19 10:04 PM.
 * Copyright (c) 2019. All rights reserved.
 * Last modified 2/25/19 7:36 AM.
 */

package com.lloydsmithexampledomain.meijerapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.lloydsmithexampledomain.meijerapp.R;
import com.lloydsmithexampledomain.meijerapp.data.CouponDataModel;
import com.lloydsmithexampledomain.meijerapp.data.CouponViewHolder;
import com.lloydsmithexampledomain.meijerapp.fragments.AvailableCouponFragment;
import com.lloydsmithexampledomain.meijerapp.fragments.ClippedCouponFragment;
import com.lloydsmithexampledomain.meijerapp.fragments.CustomFragmentModel;
import com.lloydsmithexampledomain.meijerapp.helpers.Constants;
import com.lloydsmithexampledomain.meijerapp.helpers.utility.AppUtil;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;
import java.util.Objects;

public class CouponRecyclerAdapter extends RecyclerView.Adapter<CouponViewHolder> {

    private Integer ITEM_LIMIT;
    private Context context;
    private List<CouponDataModel> couponList;
    private CouponInteractionListener listener;

    public CouponRecyclerAdapter(Context context, Fragment fragment, List<CouponDataModel> couponList) {
        this.context = context;
        this.couponList = couponList;
        ITEM_LIMIT = Constants.Coupons.ITEM_LIMIT_MIN;

        if (fragment instanceof CustomFragmentModel) {
            listener = (CouponInteractionListener) fragment;
        } else {
            throw new IllegalStateException();
        }
    }

    public CouponRecyclerAdapter(Context context, Fragment fragment, List<CouponDataModel> couponList, int itemLimit) {
        this.context = context;
        this.couponList = couponList;
        ITEM_LIMIT = itemLimit;

        if (fragment instanceof CustomFragmentModel) {
            listener = (CouponInteractionListener) fragment;
        } else {
            throw new IllegalStateException();
        }
    }

    public int getItemLimit() {
        return ITEM_LIMIT;
    }

    @Override
    public CouponViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View rootView;
        if (viewType == Constants.ViewHolder.VIEW_TYPE_COUPON) {
            rootView = layoutInflater.inflate(R.layout.layout_default_viewholder, parent, false);
        } else {
            rootView = layoutInflater.inflate(R.layout.layout_loading_viewholder, parent, false);
        }

        return new CouponViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(CouponViewHolder viewHolder, int position) {
        CouponDataModel currentModel = couponList.get(position);
        setupViews(viewHolder, currentModel);
    }

    @Override
    public int getItemViewType(int position) {
        Fragment fragment = getVisibleFragment();
        final Integer lastPosition = getItemCount() - 1;

        if (fragment instanceof AvailableCouponFragment
                || fragment instanceof ClippedCouponFragment) {
//            if (position != lastPosition) {
//                return Constants.ViewHolder.VIEW_TYPE_COUPON;
//            } else {
//                if (position == 0) {
//                    return Constants.ViewHolder.VIEW_TYPE_COUPON;
//                } else {
//                    return Constants.ViewHolder.VIEW_TYPE_LOADER;
//                }
//            }
            return Constants.ViewHolder.VIEW_TYPE_COUPON;
        }
        throw new IllegalStateException("Invalid Fragment Type Instance!");
    }

    @Override
    public int getItemCount() {
        return Math.min(couponList.size(), ITEM_LIMIT);
    }

    private Fragment getVisibleFragment() {
        FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        for (Fragment fragment : fragments) {
            if (fragment != null && fragment.isVisible()) {
                return fragment;
            }
        }
        return null;
    }

    private void setupViews(final CouponViewHolder viewHolder, final CouponDataModel couponDataModel) {
//        if (viewHolder.getAdapterPosition() < Math.min((ITEM_LIMIT - 1), (couponList.size() - 1))) {
        if (viewHolder.getItemViewType() == Constants.ViewHolder.VIEW_TYPE_COUPON) {
            viewHolder.titleView.setText(couponDataModel.getTitle());
            viewHolder.descriptionView.setText(couponDataModel.getDescription());
            viewHolder.expirationView.setText(couponDataModel.getExpirationTag());
            retrieveImageResourceForView(viewHolder.imageView, couponDataModel);

            if (couponDataModel.isClipped()) {
                viewHolder.clipText.setText(context.getString(R.string.button_unclip));
            } else {
                viewHolder.clipText.setText(context.getString(R.string.button_clip));
            }

            viewHolder.clipActionView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String action = (!couponDataModel.isClipped()) ? Constants.Coupons.CLIPPED : Constants.Coupons.UNCLIPPED;
                    Animation animation = AppUtil.getAnimation(((Activity) context), action);
                    animation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            removeCurrentItem(couponDataModel);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });

                    viewHolder.itemView.startAnimation(animation);
                }
            });
        }
    }

    private void retrieveImageResourceForView(final ImageView imageView, final CouponDataModel couponDataModel) {
        if (couponDataModel.getImage() == null) {
            Target target = new Target() {
                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                    couponDataModel.setImage(bitmap);
                    imageView.setImageBitmap(bitmap);
                }

                @Override
                public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                    imageView.setImageDrawable(errorDrawable);
                }

                @Override
                public void onPrepareLoad(Drawable placeHolderDrawable) {
                    imageView.setImageDrawable(placeHolderDrawable);
                }
            };
            Picasso.get().load(couponDataModel.getImageURL())
                    .placeholder(Objects.requireNonNull(context.getDrawable(R.drawable.placeholder_image)))
                    .error(Objects.requireNonNull(context.getDrawable(R.drawable.missing_image)))
                    .into(target);
        } else {
            imageView.setImageBitmap(couponDataModel.getImage());
        }
    }

    private void removeCurrentItem(CouponDataModel couponDataModel) {
        listener.onCouponClipToggleInteraction(couponDataModel, !couponDataModel.isClipped());
        notifyItemRangeChanged(0, getItemCount());
    }

    public void increaseItemLimit() {
        ITEM_LIMIT += Constants.Coupons.ITEM_LIMIT_INCREMENT;
        if (ITEM_LIMIT > couponList.size()) {
            ITEM_LIMIT = couponList.size();
        }
    }

    public interface CouponInteractionListener {
        void onCouponClipToggleInteraction(CouponDataModel couponDataModel, boolean clipState);
    }
}

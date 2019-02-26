/*
 *
 * Created by Lloyd Smith on 2/25/19 10:04 PM.
 * Copyright (c) 2019. All rights reserved.
 * Last modified 2/25/19 7:36 AM.
 */

package com.lloydsmithexampledomain.meijerapp.helpers;

public class Constants {

    private final String ILLEGAL_STATE_EXCEPTION = "This class must not be instantiated.";

    private Constants() {
        throwIllegalStateException();
    }

    private void throwIllegalStateException() {
        throw new IllegalStateException(ILLEGAL_STATE_EXCEPTION);
    }

    public class ViewHolder {

        public static final int VIEW_TYPE_COUPON = 0;
        public static final int VIEW_TYPE_LOADER = 1;

        private ViewHolder() {
            throwIllegalStateException();
        }
    }

    public class URL {

        public static final String BASE_URL = "https://meijerkraig.azurewebsites.net/";

        private URL() {
            throwIllegalStateException();
        }
    }

    public class PagerTabs {

        public static final int POSITION_AVAILABLE_SECTION = 0;
        public static final int POSITION_CLIPPED_SECTION = 1;

        private PagerTabs() {
            throwIllegalStateException();
        }
    }

    public class Coupons {

        public static final int ITEM_LIMIT_MIN = 30;
        public static final int ITEM_LIMIT_INCREMENT = 30;
        public static final String UNCLIPPED = "UNCLIPPED";
        public static final String CLIPPED = "CLIPPED";

        private Coupons() {
            throwIllegalStateException();
        }
    }
}

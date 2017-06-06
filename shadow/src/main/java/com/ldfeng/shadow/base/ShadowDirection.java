package com.ldfeng.shadow.base;

import android.support.annotation.IntDef;

/**
 * Created by ldf on 17/6/6.
 */
@IntDef(flag = true, value = {ShadowDirection.LEFT, ShadowDirection.TOP,
        ShadowDirection.RIGHT, ShadowDirection.BOTTOM,
        ShadowDirection.LEFT_TOP, ShadowDirection.TOP_RIGHT,
        ShadowDirection.RIGHT_BOTTOM, ShadowDirection.BOTTOM_LEFT,
        ShadowDirection.BOTTOM_LEFT_TOP, ShadowDirection.LEFT_TOP_RIGHT,
        ShadowDirection.TOP_RIGHT_BOTTOM, ShadowDirection.RIGHT_BOTTOM_LEFT,
        ShadowDirection.ALL})
public @interface ShadowDirection {
    int LEFT = 1;

    int TOP = 1 << 1;

    int RIGHT = 1 << 2;

    int BOTTOM = 1 << 3;

    int LEFT_TOP = 1 << 4;

    int TOP_RIGHT = 1 << 5;

    int RIGHT_BOTTOM = 1 << 6;

    int BOTTOM_LEFT = 1 << 7;

    int BOTTOM_LEFT_TOP = 1 << 8;

    int LEFT_TOP_RIGHT = 1 << 9;

    int TOP_RIGHT_BOTTOM = 1 << 10;

    int RIGHT_BOTTOM_LEFT = 1 << 11;

    int ALL = 1 << 12;
}

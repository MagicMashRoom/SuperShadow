package com.ldfeng.shadow.componet;

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
    int NONE = -1;

    int LEFT = 0;

    int TOP = 1;

    int RIGHT = 2;

    int BOTTOM = 3;

    int LEFT_TOP = 4;

    int LEFT_RIGHT= 5;

    int TOP_RIGHT = 6;

    int TOP_BOTTOM = 7;

    int RIGHT_BOTTOM = 8;

    int BOTTOM_LEFT = 9;

    int LEFT_TOP_RIGHT = 10;

    int TOP_RIGHT_BOTTOM = 11;

    int RIGHT_BOTTOM_LEFT = 12;

    int BOTTOM_LEFT_TOP = 13;

    int ALL = 14;
}

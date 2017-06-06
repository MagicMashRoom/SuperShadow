package com.ldfeng.shadow.base;

import android.view.View;

/**
 * Created by ldf on 17/6/6.
 */

public interface IShadowRenderer {
    void makeShadow(View view);

    void removeShadow();

    void hideShadow();

    void showShadow();
}

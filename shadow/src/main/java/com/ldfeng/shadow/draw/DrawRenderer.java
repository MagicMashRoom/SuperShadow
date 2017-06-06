package com.ldfeng.shadow.draw;

import android.graphics.drawable.Drawable;
import android.view.View;

import com.ldfeng.shadow.base.IShadowRenderer;
import com.ldfeng.shadow.base.ShadowAttr;

/**
 * Created by ldf on 17/6/6.
 */

public class DrawRenderer implements IShadowRenderer{

    private ShadowAttr attr;

    private View view;

    private Drawable orignalDrawable, shadowDrawable;

    public DrawRenderer(ShadowAttr attr) {
        this.attr = attr;
    }

    @Override
    public void makeShadow(View view) {

    }

    @Override
    public void removeShadow() {

    }

    @Override
    public void hideShadow() {

    }

    @Override
    public void showShadow() {

    }
}

package com.ldfeng.shadow.draw;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;

import com.ldfeng.shadow.base.CrazyShadowAttr;
import com.ldfeng.shadow.base.ShadowHandler;

public class ShadowDrawer implements ShadowHandler {

    private CrazyShadowAttr attr;

    private RoundShadowDrawable shadowDrawable;

    private RelativeLayout mockSubView;
    private View shadow;

    RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.WRAP_CONTENT ,
            RelativeLayout.LayoutParams.WRAP_CONTENT);

    public ShadowDrawer(CrazyShadowAttr attr) {
        this.attr = attr;
    }

    @Override
    public RelativeLayout makeShadow(Context context) {
        mockSubView = new RelativeLayout(context);
        shadowDrawable = new RoundShadowDrawable(attr);
        layoutParams.height = calcShadowViewHeight();
        layoutParams.width = calcShadowViewWidth();
        shadow = new View(context);
        shadow.setLayoutParams(layoutParams);
        shadow.setBackground(shadowDrawable);
        mockSubView.addView(shadow);
        mockSubView.setLayoutParams(layoutParams);
        return mockSubView;
    }

    private int calcShadowViewHeight() {
        return attr.getHeight()
                + (int)attr.getShadowRadius() * 2 * (int) RoundShadowDrawable.SHADOW_MULTIPLIER
                + RoundShadowDrawable.SHADOW_GAP * 2 + (int)shadowDrawable.getShadowSize()
                + (int) Math.ceil(shadowDrawable.calcVerticalPadding()) * 2;
    }

    private int calcShadowViewWidth() {
        return attr.getWidth()
                +  (int)attr.getShadowRadius() * 2 * (int) RoundShadowDrawable.SHADOW_MULTIPLIER
                + RoundShadowDrawable.SHADOW_GAP * 2
                + (int) Math.ceil(shadowDrawable.calcHorizontalPadding()) * 2;
    }


    @Override
    public void removeShadow() {
        if (shadow != null)
            shadow.setVisibility(View.GONE);
    }

    @Override
    public void hideShadow() {
        if (shadow != null)
            shadow.setVisibility(View.GONE);
    }

    @Override
    public void showShadow() {
        if (mockSubView  == null || shadow == null || shadowDrawable == null) return ;
        shadow.setVisibility(View.VISIBLE);
    }
}

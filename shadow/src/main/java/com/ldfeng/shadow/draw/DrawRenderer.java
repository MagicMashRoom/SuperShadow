package com.ldfeng.shadow.draw;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;

import com.ldfeng.shadow.base.IShadowRenderer;
import com.ldfeng.shadow.base.ShadowAttr;

/**
 * Created by ldf on 17/6/6.
 * email : 2286767746@qq.com
 * https://github.com/MagicMashRoom
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
        this.view = view;
        orignalDrawable = view.getBackground();
        shadowDrawable = new RoundShadowDrawable(view, attr.getColors(),
                attr.getCorner(), attr.getShadowSize());
        view.setBackgroundDrawable(shadowDrawable);
    }

    @Override
    public void removeShadow() {
        if (view != null && view.getBackground() instanceof RoundShadowDrawable)
            view.setBackgroundDrawable(orignalDrawable);
    }

    @Override
    public void hideShadow() {
        if (view != null && view.getBackground() instanceof RoundShadowDrawable)
            view.setBackgroundDrawable(orignalDrawable);
    }

    @Override
    public void showShadow() {
        if (view  == null || shadowDrawable == null) return ;
        view.setBackgroundDrawable(shadowDrawable);
    }
}

package com.ldfeng.shadow.draw;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;

import com.ldfeng.shadow.base.CrazyShadowAttr;

/**
 * Created by hitomi on 2016/10/17.
 */
public class RoundShadowDrawable extends Drawable {
    private final static double COS_45 = Math.cos(Math.toRadians(45));
    public final static float SHADOW_MULTIPLIER = 1.5f;
    public static final int SHADOW_GAP = 10;

    private RectF bounds;
    private Paint contentPaint;
    private Paint cornerShadowPaint;
    private Paint edgeShadowPaint;
    private float cornerRadius;
    private Path cornerShadowPath;
    private float maxShadowSize;
    private float rawMaxShadowSize;
    private float shadowSize;
    private float rawShadowSize;
    private int[] shadowColors;
    private boolean boundsHasChanged = true;
    private boolean addPaddingForCorners = true;
    private CrazyShadowAttr attr;

    public RoundShadowDrawable(CrazyShadowAttr attr) {
        this.attr = attr;
        this.shadowColors = attr.getColors();
        contentPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        contentPaint.setColor(Color.TRANSPARENT);
        cornerShadowPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        cornerShadowPaint.setStyle(Paint.Style.FILL);
        edgeShadowPaint = new Paint(cornerShadowPaint);
        edgeShadowPaint.setAntiAlias(false);
        cornerRadius = (int) (attr.getCorner() + 0.5f);
        bounds = new RectF();
        setShadowSize(attr.getShadowRadius() , attr.getShadowRadius());
    }

    private int convertToEvenInteger(float value) {
        int i = (int) (value + .5f);
        if (i % 2 == 1) {
            return i - 1;
        }
        return i;
    }

    @Override
    public void setAlpha(int alpha) {
        contentPaint.setAlpha(alpha);
        cornerShadowPaint.setAlpha(alpha);
        edgeShadowPaint.setAlpha(alpha);
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        boundsHasChanged = true;
    }

    void setShadowSize(float shadowSize, float maxShadowSize) {
        if (shadowSize < 0f) {
            throw new IllegalArgumentException("Invalid shadow size " + shadowSize +
                    ". Must be >= 0");
        }
        if (maxShadowSize < 0f) {
            throw new IllegalArgumentException("Invalid max shadow size " + maxShadowSize +
                    ". Must be >= 0");
        }
        shadowSize = convertToEvenInteger(shadowSize);
        maxShadowSize = convertToEvenInteger(maxShadowSize);
        if (shadowSize > maxShadowSize) {
            shadowSize = maxShadowSize;
        }
        if (rawShadowSize == shadowSize && rawMaxShadowSize == maxShadowSize) {
            return;
        }
        rawShadowSize = shadowSize;
        rawMaxShadowSize = maxShadowSize;
        this.shadowSize = (int) (shadowSize * SHADOW_MULTIPLIER + SHADOW_GAP + .5f);
        this.maxShadowSize = maxShadowSize + SHADOW_GAP;
        boundsHasChanged = true;
        invalidateSelf();
    }

    @Override
    public boolean getPadding(Rect padding) {
        int verticalPadding = (int) Math.ceil(calcVerticalPadding());
        int horizontalPadding = (int) Math.ceil(calcHorizontalPadding());
        padding.set(horizontalPadding, verticalPadding, horizontalPadding, verticalPadding);
        return true;
    }

    public float calcVerticalPadding() {
        if (addPaddingForCorners) {
            return (float) (rawMaxShadowSize * SHADOW_MULTIPLIER + (1 - COS_45) * cornerRadius);
        } else {
            return maxShadowSize * SHADOW_MULTIPLIER;
        }
    }

    public float calcHorizontalPadding() {
        if (addPaddingForCorners) {
            return (float) (rawMaxShadowSize + (1 - COS_45) * cornerRadius);
        } else {
            return maxShadowSize;
        }
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
        contentPaint.setColorFilter(cf);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    @Override
    public void draw(Canvas canvas) {
        if (boundsHasChanged) {
            buildComponents(getBounds());
            boundsHasChanged = false;
        }
        canvas.translate(0, rawShadowSize / 2);
        drawShadow(canvas);
        canvas.translate(0, -rawShadowSize / 2);
        canvas.drawRoundRect(bounds, cornerRadius, cornerRadius, contentPaint);
    }

    private void drawShadow(Canvas canvas) {
        final float edgeShadowTop = -cornerRadius - shadowSize;
        final float inset = cornerRadius + SHADOW_GAP + rawShadowSize / 2;
        final boolean drawHorizontalEdges = bounds.width() - 2 * inset > 0;
        final boolean drawVerticalEdges = bounds.height() - 2 * inset > 0;

        // LT
        int saved = canvas.save();
        canvas.translate(bounds.left + 2 * inset, bounds.top + inset);
        canvas.drawPath(cornerShadowPath, cornerShadowPaint);
        if (drawHorizontalEdges) {
            canvas.drawRect(0, edgeShadowTop,
                    bounds.width() - 4 * inset, -cornerRadius,
                    edgeShadowPaint);
        }
        canvas.restoreToCount(saved);
        // RB
        saved = canvas.save();
        canvas.translate(bounds.right - 2 * inset, bounds.bottom - inset);
        canvas.rotate(180f);
        canvas.drawPath(cornerShadowPath, cornerShadowPaint);
        if (drawHorizontalEdges) {
            canvas.drawRect(0, edgeShadowTop,
                    bounds.width() - 4 * inset, -cornerRadius,
                    edgeShadowPaint);
        }
        canvas.restoreToCount(saved);
        // LB
        saved = canvas.save();
        canvas.translate(bounds.left + 2 * inset, bounds.bottom - inset);
        canvas.rotate(270f);
        canvas.drawPath(cornerShadowPath, cornerShadowPaint);
        if (drawVerticalEdges) {
            canvas.drawRect(0, edgeShadowTop,
                    bounds.height() - 2 * inset, -cornerRadius, edgeShadowPaint);
        }
        canvas.restoreToCount(saved);
        // RT
        saved = canvas.save();
        canvas.translate(bounds.right - 2 * inset, bounds.top + inset);
        canvas.rotate(90f);
        canvas.drawPath(cornerShadowPath, cornerShadowPaint);
        if (drawVerticalEdges) {
            canvas.drawRect(0, edgeShadowTop,
                    bounds.height() - 2 * inset, -cornerRadius, edgeShadowPaint);
        }
        canvas.restoreToCount(saved);
    }

    private void buildShadowCorners() {
        RectF innerBounds = new RectF(-cornerRadius, -cornerRadius, cornerRadius, cornerRadius);
        RectF outerBounds = new RectF(innerBounds);
        outerBounds.inset(-shadowSize, -shadowSize);

        if (cornerShadowPath == null) {
            cornerShadowPath = new Path();
        } else {
            cornerShadowPath.reset();
        }
        cornerShadowPath.setFillType(Path.FillType.EVEN_ODD);
        cornerShadowPath.moveTo(-cornerRadius, 0);
        cornerShadowPath.rLineTo(-shadowSize, 0);
        // outer arc
        cornerShadowPath.arcTo(outerBounds, 180f, 90f, false);
        // inner arc
        cornerShadowPath.arcTo(innerBounds, 270f, -90f, false);
        cornerShadowPath.close();
        float startRatio = cornerRadius / (cornerRadius + shadowSize);
        cornerShadowPaint.setShader(new RadialGradient(0, 0, cornerRadius + shadowSize,
                shadowColors,
                new float[]{0f, startRatio, 1f}
                , Shader.TileMode.CLAMP));

        // we offset the content shadowSize/2 pixels up to make it more realistic.
        // this is why edge shadow shader has some extra space
        // When drawing bottom edge shadow, we use that extra space.
        edgeShadowPaint.setShader(new LinearGradient(0, -cornerRadius + shadowSize, 0,
                -cornerRadius - shadowSize,
                shadowColors,
                new float[]{0f, .5f, 1f}, Shader.TileMode.CLAMP));
        edgeShadowPaint.setAntiAlias(false);
    }

    private void buildComponents(Rect bounds) {
        final float verticalOffset = rawMaxShadowSize * SHADOW_MULTIPLIER;
        this.bounds.set(bounds.left , bounds.top + verticalOffset + SHADOW_GAP,
                bounds.right , bounds.bottom - verticalOffset - SHADOW_GAP);
        buildShadowCorners();
    }

    float getCornerRadius() {
        return cornerRadius;
    }

    void setCornerRadius(float radius) {
        if (radius < 0f) {
            throw new IllegalArgumentException("Invalid radius " + radius + ". Must be >= 0");
        }
        radius = (int) (radius + .5f);
        if (cornerRadius == radius) {
            return;
        }
        cornerRadius = radius;
        boundsHasChanged = true;
        invalidateSelf();
    }

    void getMaxShadowAndCornerPadding(Rect into) {
        getPadding(into);
    }

    float getShadowSize() {
        return rawShadowSize;
    }

    void setShadowSize(float size) {
        setShadowSize(size, rawMaxShadowSize);
    }

    float getMaxShadowSize() {
        return rawMaxShadowSize;
    }

    void setMaxShadowSize(float size) {
        setShadowSize(rawShadowSize, size);
    }

    float getMinWidth() {
        final float content = 2 *
                Math.max(rawMaxShadowSize, cornerRadius + SHADOW_GAP + rawMaxShadowSize / 2);
        return content + (rawMaxShadowSize + SHADOW_GAP) * 2;
    }

    float getMinHeight() {
        final float content = 2 * Math.max(rawMaxShadowSize, cornerRadius + SHADOW_GAP
                + rawMaxShadowSize * SHADOW_MULTIPLIER / 2);
        return content + (rawMaxShadowSize * SHADOW_MULTIPLIER + SHADOW_GAP) * 2;
    }

    public void setAddPaddingForCorners(boolean addPaddingForCorners) {
        this.addPaddingForCorners = addPaddingForCorners;
        invalidateSelf();
    }
}

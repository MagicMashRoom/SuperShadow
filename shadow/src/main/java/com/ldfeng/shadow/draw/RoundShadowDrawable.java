package com.ldfeng.shadow.draw;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

/**
 * Created by ldf on 17/6/6.
 * email : 2286767746@qq.com
 * https://github.com/MagicMashRoom
 */

public class RoundShadowDrawable extends Drawable {
    // used to calculate content padding
    private final static double COS_45 = Math.cos(Math.toRadians(45));

    public final static float SHADOW_MULTIPLIER = 1.5f;

    private final float minShadowSize; // extra shadow to avoid gaps between card and shadow
    private final RectF bounds;
    private Paint bitmapPaint;
    private Paint shadowCornerPaint;
    private Paint shadowEdgePaint;
    private float cornerRadius;
    private float shadowRadius;
    private float shadowSize;
    private Path cornerShadowPath;
    private Drawable originalDrawable;

    private int[] shadowColors;
    private boolean dirty = true;
    private boolean addPaddingForCorners = true;

    public RoundShadowDrawable(View view, int[] shadowColors,
                               float radius, float shadowSize) {
        minShadowSize = 10;
        this.shadowColors = shadowColors;
        this.originalDrawable = view.getBackground();
        shadowCornerPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        shadowCornerPaint.setStyle(Paint.Style.FILL);
        cornerRadius = (int) (radius + .5f);
        bounds = new RectF();
        shadowEdgePaint = new Paint(shadowCornerPaint);
        shadowEdgePaint.setAntiAlias(false);
        configShadowSize(radius , shadowSize);
    }

    private Paint initBitmapPaint(Drawable drawable) {
        Paint bitmapPaint = new Paint();
        bitmapPaint.setAntiAlias(true);
        if (drawable == null) {
            return null;
        }
        Bitmap bmp = drawableToBitmap(drawable);
        float scaleWidth  = (bounds.width() - shadowRadius) * 1.0f / bmp.getWidth();
        float scaleHeight  = (bounds.height() - shadowRadius) * 1.0f / bmp.getHeight();
        Matrix matrix = new Matrix();
        matrix.setScale(scaleWidth, scaleHeight);
        BitmapShader bitmapShader = new BitmapShader(bmp, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        bitmapShader.setLocalMatrix(matrix);
        bitmapPaint.setShader(bitmapShader);
        return bitmapPaint;
    }

    private Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bd = (BitmapDrawable) drawable;
            return bd.getBitmap();
        } else {
            int w = (int)(bounds.width());
            int h = (int)(bounds.height());

            Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, w, h);
            drawable.draw(canvas);
            return bitmap;
        }
    }
    /**
     * Casts the value to an even integer.
     */
    private int toEven(float value) {
        int i = (int) (value + .5f);
        if (i % 2 == 1) {
            return i - 1;
        }
        return i;
    }

    public void setAddPaddingForCorners(boolean addPaddingForCorners) {
        this.addPaddingForCorners = addPaddingForCorners;
        invalidateSelf();
    }

    @Override
    public void setAlpha(int alpha) {
        bitmapPaint.setAlpha(alpha);
        shadowCornerPaint.setAlpha(alpha);
        shadowEdgePaint.setAlpha(alpha);
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        dirty = true;
    }

    void configShadowSize(float cornerRadius , float shadowSize) {
        if (shadowSize < 0f) {
            throw new IllegalArgumentException("Invalid shadow size " + shadowSize +
                    ". Must be >= 0");
        }

        if(shadowSize < minShadowSize) {
            shadowSize = minShadowSize;
        }
        this.shadowSize =  shadowSize * SHADOW_MULTIPLIER;
        shadowRadius = toEven(this.shadowSize) + cornerRadius;
        dirty = true;
        invalidateSelf();
    }

    @Override
    public boolean getPadding(Rect padding) {
        int offset = (int) Math.ceil(calculatePadding(addPaddingForCorners));
        padding.set(offset, offset, offset, offset);
        return true;
    }

    float calculatePadding(boolean addPaddingForCorners) {
        if (addPaddingForCorners) {
            return (float) (shadowSize + (1 - COS_45) * cornerRadius);
        } else {
            return shadowSize;
        }
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
        bitmapPaint.setColorFilter(cf);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    @Override
    public void draw(Canvas canvas) {
        if (dirty) {
            buildComponents(getBounds());
            dirty = false;
        }

        drawShadow(canvas);
        bounds.inset(shadowRadius - cornerRadius, shadowRadius - cornerRadius);
        canvas.drawRoundRect(bounds, cornerRadius , cornerRadius , bitmapPaint);
        bounds.inset(-shadowRadius + cornerRadius, -shadowRadius + cornerRadius);
    }

    private void buildComponents(Rect bounds) {

        if(bounds.height() == (int) Math.ceil(calculatePadding(addPaddingForCorners)) * 2
                ||bounds.width() == (int) Math.ceil(calculatePadding(addPaddingForCorners)) * 2) {
            MeasureSpec measureSpec = MeasureSpec.WRAP_CONTENT;
            throw new IllegalArgumentException("Invalid MeasureSpec " + measureSpec +
                    ". Must be MeasureSpec.MATCH_PARENT");
        }

        this.bounds.set(bounds.left , bounds.top ,
                bounds.right , bounds.bottom );
        bitmapPaint = initBitmapPaint(originalDrawable);
        buildShadowCorners();
    }

    private void buildShadowCorners() {
        RectF cornerRecf = new RectF(shadowRadius - cornerRadius, shadowRadius - cornerRadius,
                shadowRadius + cornerRadius, shadowRadius + cornerRadius);
        RectF shadowCornerRecf = new RectF(0, 0, 2 * shadowRadius, 2 * shadowRadius);

        if (cornerShadowPath == null) {
            cornerShadowPath = new Path();
        } else {
            cornerShadowPath.reset();
        }
        cornerShadowPath.setFillType(Path.FillType.EVEN_ODD);
        cornerShadowPath.moveTo(shadowRadius - cornerRadius, shadowRadius);
        // 内半圆，如果cornerRadius 为零时，将绘制不出阴影的内圆角

        cornerShadowPath.arcTo(cornerRecf, 180f, 90f, false);
        cornerShadowPath.lineTo(shadowRadius, 0);
        // 外半圆，始终都会绘制shadow的圆角
        cornerShadowPath.arcTo(shadowCornerRecf, 270f, -90f, false);
        cornerShadowPath.lineTo(shadowRadius - cornerRadius , shadowRadius);

        cornerShadowPath.close();

        float startPosition = cornerRadius / shadowRadius;

        shadowCornerPaint.setShader(new RadialGradient(shadowRadius  , shadowRadius  ,
                shadowRadius , shadowColors,
                new float[]{0, startPosition , 1f}
                , Shader.TileMode.CLAMP));

        shadowEdgePaint.setShader(new LinearGradient(shadowRadius, shadowRadius,
                shadowRadius, 0,
                shadowColors,
                new float[]{0, startPosition , 1f}, Shader.TileMode.CLAMP));
        shadowEdgePaint.setAntiAlias(false);
    }

    private void drawShadow(Canvas canvas) {
        final boolean drawHorizontalEdges = bounds.width() - 2 * shadowRadius > 0;
        final boolean drawVerticalEdges = bounds.height() - 2 * shadowRadius > 0;
        // LT
        int saved = canvas.save();
        canvas.drawPath(cornerShadowPath, shadowCornerPaint);
        if (drawHorizontalEdges) {
            canvas.drawRect(shadowRadius, 0,
                    bounds.width() - shadowRadius, shadowRadius - cornerRadius,
                    shadowEdgePaint);
        }
        canvas.restoreToCount(saved);
        // RB
        saved = canvas.save();
        canvas.translate(bounds.right, bounds.bottom);
        canvas.rotate(180f);
        canvas.drawPath(cornerShadowPath, shadowCornerPaint);
        if (drawHorizontalEdges) {
            canvas.drawRect(shadowRadius, 0,
                    bounds.width() - shadowRadius, shadowRadius - cornerRadius,
                    shadowEdgePaint);
        }
        canvas.restoreToCount(saved);
        // LB
        saved = canvas.save();
        canvas.translate(bounds.left, bounds.bottom);
        canvas.rotate(270f);
        canvas.drawPath(cornerShadowPath, shadowCornerPaint);
        if (drawVerticalEdges) {
            canvas.drawRect(shadowRadius, 0,
                    bounds.height() - shadowRadius, shadowRadius - cornerRadius,
                    shadowEdgePaint);
        }
        canvas.restoreToCount(saved);
        // RT
        saved = canvas.save();
        canvas.translate(bounds.right, bounds.top);
        canvas.rotate(90f);
        canvas.drawPath(cornerShadowPath, shadowCornerPaint);
        if (drawVerticalEdges) {
            canvas.drawRect(shadowRadius, 0,
                    bounds.height() - shadowRadius, shadowRadius - cornerRadius,
                    shadowEdgePaint);
        }
        canvas.restoreToCount(saved);
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
        dirty = true;
        invalidateSelf();
    }

    void getMaxShadowAndCornerPadding(Rect into) {
        getPadding(into);
    }

    float getShadowRadius() {
        return shadowRadius;
    }

    void setShadowSize(float size) {
        configShadowSize(cornerRadius, size);
    }

    float getMinWidth() {
        final float content = 2 *
                Math.max(shadowRadius, cornerRadius + minShadowSize + shadowRadius / 2);
        return content + (shadowRadius + minShadowSize) * 2;
    }

    float getMinHeight() {
        final float content = 2 * Math.max(shadowRadius, cornerRadius + minShadowSize
                + shadowRadius * SHADOW_MULTIPLIER / 2);
        return content + (shadowRadius * SHADOW_MULTIPLIER + minShadowSize) * 2;
    }

    private enum MeasureSpec {
        MATCH_PARENT , WRAP_CONTENT
    }
}

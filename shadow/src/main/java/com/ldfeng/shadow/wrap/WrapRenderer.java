package com.ldfeng.shadow.wrap;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;

import com.ldfeng.shadow.componet.IShadowRenderer;
import com.ldfeng.shadow.componet.ShadowAttr;
import com.ldfeng.shadow.componet.ShadowDirection;
import com.ldfeng.shadow.componet.view.CornerShadowView;
import com.ldfeng.shadow.componet.view.EdgeShadowView;

/**
 * Created by ldf on 17/6/6.
 * email : 2286767746@qq.com
 * https://github.com/MagicMashRoom
 */

public class WrapRenderer implements IShadowRenderer{

    private Context context;

    private View contentView;

    private RelativeLayout shadowLayout;

    private Drawable orignalDrawable;

    private OnMeasureListener measureListener;

    private ShadowAttr attr;

    private boolean init;

    public WrapRenderer(Context context, ShadowAttr attr) {
        this.context = context;
        this.attr = attr;
        measureListener = new OnMeasureListener();
    }

    private void prepareLayout() {
        ViewGroup parent = (ViewGroup) contentView.getParent();
        int orignalIndex = parent.indexOfChild(contentView);
        parent.removeView(contentView);

        shadowLayout = new RelativeLayout(context);
        ViewGroup.LayoutParams contentViewLp = contentView.getLayoutParams();
        contentViewLp.width = contentView.getWidth();
        contentViewLp.height = contentView.getHeight();
        shadowLayout.setLayoutParams(contentViewLp);
        parent.addView(shadowLayout, orignalIndex);
        shadowLayout.addView(contentView, getContentViewLayoutParams());
    }

    @NonNull
    private RelativeLayout.LayoutParams getContentViewLayoutParams() {
        int width, height;
        RelativeLayout.LayoutParams rlp;
        int direction = attr.getDirection();
        if (direction == ShadowDirection.LEFT || direction == ShadowDirection.RIGHT) {
            width = (int) (contentView.getWidth() - attr.getShadowSize());
            height = contentView.getHeight();
            rlp = new RelativeLayout.LayoutParams(width, height);
            if (direction == ShadowDirection.LEFT)
                rlp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        } else if (direction == ShadowDirection.TOP || direction == ShadowDirection.BOTTOM) {
            width = contentView.getWidth();
            height = (int) (contentView.getHeight() - attr.getShadowSize());
            rlp = new RelativeLayout.LayoutParams(width, height);
            if (direction == ShadowDirection.TOP)
                rlp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        } else if (direction == ShadowDirection.LEFT_TOP || direction == ShadowDirection.TOP_RIGHT) {
            width = (int) (contentView.getWidth() - attr.getShadowSize());
            height = (int) (contentView.getHeight() - attr.getShadowSize());
            rlp = new RelativeLayout.LayoutParams(width, height);
            if (direction == ShadowDirection.LEFT_TOP) {
                rlp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            } else {
                rlp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            }
            rlp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        } else if (direction == ShadowDirection.BOTTOM_LEFT || direction == ShadowDirection.RIGHT_BOTTOM) {
            width = (int) (contentView.getWidth() - attr.getShadowSize());
            height = (int) (contentView.getHeight() - attr.getShadowSize());
            rlp = new RelativeLayout.LayoutParams(width, height);
            if (direction == ShadowDirection.BOTTOM_LEFT)
                rlp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        } else if (direction == ShadowDirection.BOTTOM_LEFT_TOP || direction == ShadowDirection.TOP_RIGHT_BOTTOM) {
            width = (int) (contentView.getWidth() - attr.getShadowSize());
            height = (int) (contentView.getHeight() - attr.getShadowSize() * 2);
            rlp = new RelativeLayout.LayoutParams(width, height);
            if (direction == ShadowDirection.BOTTOM_LEFT_TOP) {
                rlp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            }
            rlp.addRule(RelativeLayout.CENTER_VERTICAL);
        } else if (direction == ShadowDirection.LEFT_TOP_RIGHT || direction == ShadowDirection.RIGHT_BOTTOM_LEFT) {
            width = (int) (contentView.getWidth() - attr.getShadowSize() * 2);
            height = (int) (contentView.getHeight() - attr.getShadowSize());
            rlp = new RelativeLayout.LayoutParams(width, height);
            if (direction == ShadowDirection.LEFT_TOP_RIGHT) {
                rlp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            }
            rlp.addRule(RelativeLayout.CENTER_HORIZONTAL);
        } else { // All
            width = (int) (contentView.getWidth() - attr.getShadowSize() * 2);
            height = (int) (contentView.getHeight() - attr.getShadowSize() * 2);
            rlp = new RelativeLayout.LayoutParams(width, height);
            rlp.addRule(RelativeLayout.CENTER_IN_PARENT);
        }
        return rlp;
    }

    private void addShadow() {
        addEdgeShadow();
        addCornerShadow();
    }

    private void addEdgeShadow() {
        EdgeShadowView.Builder edgeShadowBuilder = new EdgeShadowView.Builder()
                .setContext(context)
                .setShadowColors(attr.getColors())
                .setCornerRadius(attr.getCorner())
                .setShadowRadius(attr.getShadowSize());

        if (attr.containLeft())
            decorateLeft(edgeShadowBuilder);

        if (attr.containTop())
            decorateTop(edgeShadowBuilder);

        if (attr.containRight())
            decorateRight(edgeShadowBuilder);

        if (attr.containBottom())
            decorateBottom(edgeShadowBuilder);
    }

    private void decorateLeft(EdgeShadowView.Builder edgeShadowBuilder) {
        RelativeLayout.LayoutParams leftRlp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        float shadowSize;
        if (attr.getDirection() == ShadowDirection.LEFT) {
            shadowSize = contentView.getHeight();
        } else if (attr.getDirection() == ShadowDirection.ALL
                || attr.getDirection() == ShadowDirection.BOTTOM_LEFT_TOP) {
            shadowSize = contentView.getHeight() - 2 * (attr.getShadowSize() + attr.getCorner());
            leftRlp.addRule(RelativeLayout.CENTER_VERTICAL);
        } else {
            shadowSize = contentView.getHeight() - attr.getShadowSize() - attr.getCorner();
            if (attr.getDirection() == ShadowDirection.LEFT_TOP || attr.getDirection() == ShadowDirection.LEFT_TOP_RIGHT) {
                leftRlp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            }
        }

        if (shadowSize <= 0) return ;

        EdgeShadowView leftEdgeShadow = edgeShadowBuilder
                .setShadowSize(shadowSize)
                .setDirection(ShadowDirection.LEFT)
                .create();
        shadowLayout.addView(leftEdgeShadow, leftRlp);
    }

    private void decorateTop(EdgeShadowView.Builder edgeShadowBuilder) {
        RelativeLayout.LayoutParams topRlp = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        float shadowSize;
        if (attr.getDirection() == ShadowDirection.TOP) {
            shadowSize = contentView.getWidth();
        } else if (attr.getDirection() == ShadowDirection.ALL
                || attr.getDirection() == ShadowDirection.LEFT_TOP_RIGHT) {
            shadowSize = contentView.getWidth() - 2 * (attr.getShadowSize() + attr.getCorner());
            topRlp.addRule(RelativeLayout.CENTER_HORIZONTAL);
        } else {
            shadowSize = contentView.getWidth() - attr.getShadowSize() - attr.getCorner();
            if (attr.getDirection() == ShadowDirection.LEFT_TOP || attr.getDirection() == ShadowDirection.BOTTOM_LEFT_TOP) {
                topRlp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            }
        }

        if (shadowSize <= 0) return ;

        EdgeShadowView topEdgeShadow = edgeShadowBuilder
                .setShadowSize(shadowSize)
                .setDirection(ShadowDirection.TOP)
                .create();
        shadowLayout.addView(topEdgeShadow, topRlp);
    }

    private void decorateRight(EdgeShadowView.Builder edgeShadowBuilder) {
        RelativeLayout.LayoutParams rightRlp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rightRlp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        float shadowSize;
        if (attr.getDirection() == ShadowDirection.RIGHT) {
            shadowSize = contentView.getHeight();
        } else if (attr.getDirection() == ShadowDirection.ALL
                || attr.getDirection() == ShadowDirection.TOP_RIGHT_BOTTOM) {
            shadowSize = contentView.getHeight() - 2 * (attr.getShadowSize() + attr.getCorner());
            rightRlp.addRule(RelativeLayout.CENTER_VERTICAL);
        } else {
            shadowSize = contentView.getHeight() - attr.getShadowSize() - attr.getCorner();
            if (attr.getDirection() == ShadowDirection.TOP_RIGHT || attr.getDirection() == ShadowDirection.LEFT_TOP_RIGHT) {
                rightRlp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            }
        }

        if (shadowSize <= 0) return ;

        EdgeShadowView rightEdgeShadow = edgeShadowBuilder
                .setShadowSize(shadowSize)
                .setDirection(ShadowDirection.RIGHT)
                .create();
        shadowLayout.addView(rightEdgeShadow, rightRlp);
    }

    private void decorateBottom(EdgeShadowView.Builder edgeShadowBuilder) {
        RelativeLayout.LayoutParams bottomRlp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        bottomRlp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        float shadowSize;
        if (attr.getDirection() == ShadowDirection.BOTTOM) {
            shadowSize = contentView.getWidth();
        } else if (attr.getDirection() == ShadowDirection.ALL
                || attr.getDirection() == ShadowDirection.RIGHT_BOTTOM_LEFT) {
            shadowSize = contentView.getWidth() - 2 * (attr.getShadowSize() + attr.getCorner());
            bottomRlp.addRule(RelativeLayout.CENTER_HORIZONTAL);
        } else {
            shadowSize = contentView.getWidth() - attr.getShadowSize() - attr.getCorner();
            if (attr.getDirection() == ShadowDirection.BOTTOM_LEFT || attr.getDirection() == ShadowDirection.BOTTOM_LEFT_TOP) {
                bottomRlp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            }
        }

        if (shadowSize <= 0) return ;

        EdgeShadowView bottomEdgeShadow = edgeShadowBuilder
                .setShadowSize(shadowSize)
                .setDirection(ShadowDirection.BOTTOM)
                .create();
        shadowLayout.addView(bottomEdgeShadow, bottomRlp);
    }

    private void addCornerShadow() {
        CornerShadowView.Builder cornerShadowBuilder = new CornerShadowView.Builder()
                .setContext(context)
                .setShadowColors(attr.getColors())
                .setShadowSize(attr.getShadowSize())
                .setCornerRadius(attr.getCorner());

        if (attr.containLeft() && attr.containTop())
            decorateLeftTop(cornerShadowBuilder);

        if (attr.containRight() && attr.containTop())
            decorateRightTop(cornerShadowBuilder);

        if (attr.containRight() && attr.containBottom())
            decorateRightBottom(cornerShadowBuilder);

        if (attr.containLeft() && attr.containBottom())
            decorateLeftBottom(cornerShadowBuilder);
    }

    private void decorateLeftTop(CornerShadowView.Builder cornerShadowBuilder) {
        CornerShadowView leftTopCornerShadow = cornerShadowBuilder
                .setDirection(ShadowDirection.LEFT_TOP)
                .create();
        RelativeLayout.LayoutParams leftTopRlp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT
                , ViewGroup.LayoutParams.WRAP_CONTENT);
        leftTopRlp.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        leftTopRlp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        shadowLayout.addView(leftTopCornerShadow, leftTopRlp);
    }

    private void decorateRightTop(CornerShadowView.Builder cornerShadowbuilder) {
        CornerShadowView rightTopCornerShadow = cornerShadowbuilder
                .setDirection(ShadowDirection.TOP_RIGHT)
                .create();
        RelativeLayout.LayoutParams rightTopRlp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT
                , ViewGroup.LayoutParams.WRAP_CONTENT);
        rightTopRlp.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        rightTopRlp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        shadowLayout.addView(rightTopCornerShadow, rightTopRlp);
    }

    private void decorateRightBottom(CornerShadowView.Builder cornerShadowbuilder) {
        CornerShadowView RightBottomCornerShadow = cornerShadowbuilder
                .setDirection(ShadowDirection.RIGHT_BOTTOM)
                .create();
        RelativeLayout.LayoutParams rightBottomRlp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT
                , ViewGroup.LayoutParams.WRAP_CONTENT);
        rightBottomRlp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        rightBottomRlp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        shadowLayout.addView(RightBottomCornerShadow, rightBottomRlp);
    }

    private void decorateLeftBottom(CornerShadowView.Builder cornerShadowbuilder) {
        CornerShadowView leftBottomCornerShadow = cornerShadowbuilder
                .setDirection(ShadowDirection.BOTTOM_LEFT)
                .create();
        RelativeLayout.LayoutParams leftBottomRlp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT
                , ViewGroup.LayoutParams.WRAP_CONTENT);
        leftBottomRlp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        leftBottomRlp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        shadowLayout.addView(leftBottomCornerShadow, leftBottomRlp);
    }

    @Override
    public void makeShadow(View view) {
        contentView = view;
        init = true;
        if (attr.getBackground() != 0) {
            orignalDrawable = contentView.getBackground();
            contentView.setBackgroundColor(attr.getBackground());
        }
        contentView.getViewTreeObserver().addOnGlobalLayoutListener(measureListener);
    }

    @Override
    public void removeShadow() {
        shadowLayout.removeView(contentView);

        ViewGroup parent = (ViewGroup) shadowLayout.getParent();
        int orignalIndex = parent.indexOfChild(shadowLayout);
        parent.removeView(shadowLayout);

        contentView.setLayoutParams(shadowLayout.getLayoutParams());
        parent.addView(contentView, orignalIndex);

        if (attr.getBackground() != 0) {
            contentView.setBackgroundDrawable(orignalDrawable);
        }
    }

    @Override
    public void hideShadow() {
        setShadowViewAlpha(0);
        ViewGroup.LayoutParams contentViewLp = contentView.getLayoutParams();
        contentViewLp.width = shadowLayout.getLayoutParams().width;
        contentViewLp.height = shadowLayout.getLayoutParams().height;
        contentView.setLayoutParams(contentViewLp);
        if (attr.getBackground() != 0) {
            contentView.setBackgroundDrawable(orignalDrawable);
        }
    }

    @Override
    public void showShadow() {
        setShadowViewAlpha(1);
        contentView.setLayoutParams(getContentViewLayoutParams());
        if (attr.getBackground() != 0 && contentView != null) {
            contentView.setBackgroundColor(attr.getBackground());
        }
    }

    private void setShadowViewAlpha(int alpha) {
        int childCount = shadowLayout.getChildCount();
        View child;
        for (int i = 0; i < childCount; i++) {
            child = shadowLayout.getChildAt(i);
            if (child instanceof EdgeShadowView || child instanceof CornerShadowView) {
                child.setAlpha(alpha);
            }
        }
    }

    private class OnMeasureListener implements ViewTreeObserver.OnGlobalLayoutListener {

        @Override
        public void onGlobalLayout() {
            if (init) {
                prepareLayout();
                addShadow();
                init = false;
                contentView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        }
    }
}

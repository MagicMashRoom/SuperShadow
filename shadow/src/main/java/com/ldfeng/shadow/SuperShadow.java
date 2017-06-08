package com.ldfeng.shadow;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;

import com.ldfeng.shadow.base.IShadowRenderer;
import com.ldfeng.shadow.base.ShadowAttr;
import com.ldfeng.shadow.base.ShadowDirection;
import com.ldfeng.shadow.draw.DrawRenderer;
import com.ldfeng.shadow.wrap.WrapRenderer;

/**
 * Created by ldf on 17/6/6.
 * email : 2286767746@qq.com
 * https://github.com/MagicMashRoom
 */

public class SuperShadow {
    /**
     * 以 {@link android.view.View#setBackground(Drawable)} 的形式为你的
     * View 添加阴影背景，同时可以设置圆角 [{@link com.ldfeng.shadow.draw.DrawRenderer}]
     */
    public static final String DRAW_RENDERER = "drawer";

    /**
     * 以包装的形式为你的 View 添加阴影 [{@link com.ldfeng.shadow.wrap.WrapRenderer}]
     */
    public static final String WRAP_RENDERER = "wrapper";

    private Context context;

    private IShadowRenderer renderer;

    private boolean makeShadow;

    public SuperShadow(Context context) {
        this.context = context;
    }

    private void createShadowHandler(ShadowAttr attr) {
        if (attr.getImpl().equals(DRAW_RENDERER)) {
            renderer = new DrawRenderer(attr);
        } else if (attr.getImpl().equals(WRAP_RENDERER)) {
            renderer = new WrapRenderer(context, attr);
        }
    }

    /**
     * 为 View 添加阴影效果
     * @param view
     */
    public void make(View view) {
        if (!makeShadow) {
            renderer.makeShadow(view);
            makeShadow = true;
        }
    }

    /**
     * 将添加的阴影效果移除
     */
    public void remove() {
        if (makeShadow) {
            renderer.removeShadow();
            makeShadow = false;
        }
    }

    /**
     * 显示阴影效果
     */
    public void show() {
        renderer.showShadow();
    }

    /**
     * 隐藏阴影效果
     */
    public void hide() {
        renderer.hideShadow();
    }

    public static class Builder {

        private Context context;

        /**
         * {@link #DRAW_RENDERER} <br/>
         * {@link #WRAP_RENDERER} <br/>
         */
        private String impl;

        /**
         * 阴影的基本色
         */
        private int baseShadowColor;

        /**
         * 背景色
         */
        private int background;

        /**
         * 表示阴影的一个颜色由深到浅且长度为3的数组
         */
        private int[] colors;

        /**
         * 对 {@link #DRAW_RENDERER} 形式表示为背景的圆角角度.<br/>
         * 对 {@link #WRAP_RENDERER}
         * 表示为阴影顶点的内侧弧度。以适配被设置的 View 是圆角的情况
         */
        private float corner;

        /**
         * 阴影半径
         */
        private float shadowSize;

        /**
         * 阴影设置在 View 的方向
         */
        @ShadowDirection
        private int direction;

        public Builder setContext(Context context) {
            this.context = context;
            return this;
        }

        /**
         *  以何种方式添加阴影:<br/>
         * {@link #DRAW_RENDERER} <br/>
         * {@link #WRAP_RENDERER} <br/>
         * @param impl
         * @return Builder
         */
        public Builder setImpl(String impl) {
            this.impl = impl;
            return this;
        }

        /**
         * 阴影的基本颜色，即最深的颜色, {@link ShadowAttr#setBaseShadowColor(int)}
         * 方法会自动计算出绘制阴影时需要的 {@link #colors}
         * @param baseColor
         * @return Builder
         */
        public Builder setBaseShadowColor(int baseColor) {
            this.baseShadowColor = baseColor;
            return this;
        }

        /**
         * 背景色
         * @param background
         * @return Builder
         */
        public Builder setBackground(int background) {
            this.background = background;
            return this;
        }

        /**
         * 绘制阴影时需要的一个颜色由深到浅且长度为3的数组
         * @param colors
         * @return Builder
         */
        public Builder setColors(int[] colors) {
            this.colors = colors;
            return this;
        }

        /**
         * 对 {@link #DRAW_RENDERER} 形式表示为背景的圆角角度.<br/>
         * 对 {@link #WRAP_RENDERER}
         * 表示为阴影顶点的内侧弧度。以适配被设置的 View 是圆角的情况
         * @param corner [unit : pixels]
         * @return Builder
         */
        public Builder setCorner(float corner) {
            this.corner = corner;
            return this;
        }

        /**
         * 设置阴影大小
         * @param shadowSize [unit : pixels]
         * @return Builder
         */
        public Builder setShadowSize(float shadowSize) {
            this.shadowSize = shadowSize;
            return this;
        }

        /**
         * 设置阴影的方向，具体查看 {@link ShadowAttr}
         * @param direction
         * @return Builder
         */
        public Builder setDirection(int direction) {
            this.direction = direction;
            return this;
        }

        private SuperShadow create() {
            if (colors == null && baseShadowColor == 0){
                colors = new int[]{0x63000000, 0x32000000, 0x00000000};
            }
            ShadowAttr attr = new ShadowAttr();
            attr.setImpl(impl);
            attr.setBaseShadowColor(baseShadowColor);
            attr.setBackground(background);
            attr.setColors(colors);
            attr.setCorner(corner);
            attr.setShadowSize(shadowSize);
            attr.setDirection(direction);
            SuperShadow superShadow = new SuperShadow(context);
            superShadow.createShadowHandler(attr);
            return superShadow;
        }

        /**
         * 绘制阴影的启动方法，你需要保证参数已经正确设置完毕
         * @param view 被添加阴影的 View
         */
        public SuperShadow action(View view) {
            SuperShadow superShadow = create();
            superShadow.make(view);
            return superShadow;
        }
    }
}

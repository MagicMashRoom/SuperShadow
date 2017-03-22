package com.ldfeng.shadow;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.ldfeng.shadow.base.CrazyShadowAttr;
import com.ldfeng.shadow.base.CrazyShadowDirection;
import com.ldfeng.shadow.base.ShadowHandler;
import com.ldfeng.shadow.draw.ShadowDrawer;
import com.ldfeng.shadow.floating.ShadowFloating;
import com.ldfeng.shadow.wrap.ShadowWrapper;

/**
 * Created by hitomi on 2017/03/19.
 *
 * email : 2286767746@qq.com
 *
 * https://github.com/MagicMashRoom
 */
public class CrazyShadow {

    /**
     * 以 {@link android.view.View#setBackground(Drawable)} 的形式为你的
     * View 添加阴影背景，同时可以设置圆角 [{@link com.ldfeng.shadow.draw.ShadowDrawer}]
     */
    public static final String IMPL_DRAW = "drawer";

    /**
     * 以包装的形式为你的 View 添加阴影 [{@link com.ldfeng.shadow.wrap.ShadowWrapper}]
     */
    public static final String IMPL_WRAP = "wrapper";

    /**
     * 以浮动修饰的形式为你的 View 添加阴影 [{@link ShadowFloating}]
     */
    public static final String IMPL_FLOAT = "floating";

    private Context context;

    private ShadowHandler shadowHandler;

    private boolean makeShadow = false;

    private static RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.WRAP_CONTENT ,
            RelativeLayout.LayoutParams.WRAP_CONTENT);

    private CrazyShadow(Context context) {
        this.context = context;
    }

    private void createShadowHandler(CrazyShadowAttr attr) {
        if (attr.getImpl().equals(IMPL_DRAW)) {
            shadowHandler = new ShadowDrawer(attr);
        } else if (attr.getImpl().equals(IMPL_WRAP)) {
            shadowHandler = new ShadowWrapper(context, attr);
        } else {
            shadowHandler = new ShadowFloating(context, attr);
        }
    }

    public RelativeLayout make() {
        if (!makeShadow) {
            RelativeLayout frameLayout = shadowHandler.makeShadow(context);
            makeShadow = true;
            return frameLayout;
        }
        return null;
    }

    /**
     * 将添加的阴影效果移除
     */
    public void remove() {
        if (makeShadow) {
            shadowHandler.removeShadow();
            makeShadow = false;
        }
    }

    /**
     * 显示阴影效果
     */
    public void show() {
        shadowHandler.showShadow();
    }

    /**
     * 隐藏阴影效果
     */
    public void hide() {
        shadowHandler.hideShadow();
    }

    public static class Builder {

        private Context context;

        /**
         * {@link #IMPL_DRAW} <br/>
         * {@link #IMPL_WRAP} <br/>
         * {@link #IMPL_FLOAT} <br/>
         */
        private String impl;

        /**
         * {@link #IMPL_DRAW} <br/>
         * {@link #IMPL_WRAP} <br/>
         * {@link #IMPL_FLOAT} <br/>
         */

        private int width;

        public Builder setWidth(int width) {
            this.width = width;
            return this;
        }

        /**
         * {@link #IMPL_DRAW} <br/>
         * {@link #IMPL_WRAP} <br/>
         * {@link #IMPL_FLOAT} <br/>
         */
        private int height;

        public Builder setHeight(int height) {
            this.height = height;
            return this;
        }

        /**
         * 阴影的基本色
         */
        private int baseShadowColor;

        private int[] shadowColors;

        private float corner;

        /**
         * 阴影半径
         */
        private float shadowRadius;

        /**
         * 阴影设置在 View 的方向
         */
        @CrazyShadowDirection
        private int direction;

        private View child;

        public Builder setContext(Context context) {
            this.context = context;
            return this;
        }

        /**
         *  以何种方式添加阴影:<br/>
         * {@link #IMPL_DRAW} <br/>
         * {@link #IMPL_WRAP} <br/>
         * {@link #IMPL_FLOAT} <br/>
         * @param impl
         * @return Builder
         */
        public Builder setImpl(String impl) {
            this.impl = impl;
            return this;
        }

        /**
         * 阴影的基本颜色，即最深的颜色, {@link CrazyShadowAttr#setBaseShadowColor(int)}
         * 方法会自动计算出绘制阴影时需要的 {@link #shadowColors}
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

        /**
         * 绘制阴影时需要的一个颜色由深到浅且长度为3的数组
         * @param shadowColors
         * @return Builder
         */
        public Builder setShadowColors(int[] shadowColors) {
            this.shadowColors = shadowColors;
            return this;
        }

        /**
         * 对 {@link #IMPL_DRAW} 形式表示为背景的圆角角度.<br/>
         * 对 {@link #IMPL_WRAP} 与 {@link #IMPL_FLOAT}
         * 表示为阴影顶点的内侧弧度。以适配被设置的 View 是圆角的情况
         * @param corner [unit : pixels]
         * @return Builder
         */
        public Builder setCorner(float corner) {
            this.corner = corner;
            return this;
        }

        /**
         * 设置阴影半径
         * @param shadowRadius [unit : pixels]
         * @return Builder
         */
        public Builder setShadowRadius(float shadowRadius) {
            this.shadowRadius = shadowRadius;
            return this;
        }

        /**
         * 设置阴影的方向，具体查看 {@link CrazyShadowAttr}
         * @param direction
         * @return Builder
         */
        public Builder setDirection(int direction) {
            this.direction = direction;
            return this;
        }

        public Builder setChild(View child) {
            this.child = child;
            return this;
        }

        private CrazyShadow create() {
            if (shadowColors == null && baseShadowColor == 0)
                //分别为开始颜色，中间颜色，结束颜色
                shadowColors = new int[]{0x63000000, 0x32000000, 0x00000000};
            CrazyShadowAttr attr = new CrazyShadowAttr();
            attr.setImpl(impl);
            attr.setBaseShadowColor(baseShadowColor);
            attr.setColors(shadowColors);
            attr.setCorner(corner);
            attr.setShadowRadius(shadowRadius);
            attr.setDirection(direction);
            attr.setWidth(width);
            attr.setHeight(height);
            CrazyShadow crazyShadow = new CrazyShadow(context);
            crazyShadow.createShadowHandler(attr);
            return crazyShadow;
        }

        public CrazyShadow action(final ViewGroup parent) {
            ViewGroup.LayoutParams rlpchild;
            ViewGroup.LayoutParams rlpRelativeLayout;
            CrazyShadow crazyShadow = create();
            RelativeLayout relativeLayout = crazyShadow.make();
            rlpRelativeLayout = relativeLayout.getLayoutParams();
            int childIndex = parent.indexOfChild(child);
            rlpchild = child.getLayoutParams();
            rlpchild.height = rlpRelativeLayout.height;
            rlpchild.width = rlpRelativeLayout.width;
            rlp.addRule(RelativeLayout.CENTER_IN_PARENT);
            child.setLayoutParams(rlp);
            if(childIndex >= 0){
                parent.removeView(child);
                relativeLayout.addView(child);
                relativeLayout.setLayoutParams(rlpchild);
                parent.addView(relativeLayout , childIndex);
            }

            return crazyShadow;
        }
    }
}

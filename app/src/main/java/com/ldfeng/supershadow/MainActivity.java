package com.ldfeng.supershadow;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.ldfeng.shadow.SuperShadow;
import com.ldfeng.shadow.componet.Direction;
import com.ldfeng.shadow.componet.ShadowDirection;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Context context;

    private View viewDrawShadow;

    private ImageView drawShadowViewExample;

    private View drawShadowViewGroupExample;

    private View wrapShadowDirectionExample,
            wrapShadowDirectionExample1,
            wrapShadowDirectionExample2,
            wrapShadowDirectionExample3;

    private SuperShadow superDrawShadowOnView;

    private SuperShadow superDrawShadowOnViewGroup;

    private boolean titleFlag = true, draw0Flag = true;

    private boolean wrap0Flag = true,
            wrap1Flag = true,
            wrap2Flag = true,
            wrap3Flag = true,
            shadow0Flag = true,
            shadow1Flag = true;

    private int shadowDirection = ShadowDirection.NONE;
    private SuperShadow superWrapShadowLeftExample;
    private SuperShadow superWrapShadowLeftTopExample;
    private SuperShadow superWrapShadowLeftTopRightExample;
    private SuperShadow superWrapShadowAllExample;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        initView();
        initShadow();
        initListener();
    }

    private void initListener() {
        viewDrawShadow.setOnClickListener(this);
        drawShadowViewGroupExample.setOnClickListener(this);
        wrapShadowDirectionExample.setOnClickListener(this);
        wrapShadowDirectionExample1.setOnClickListener(this);
        wrapShadowDirectionExample2.setOnClickListener(this);
        wrapShadowDirectionExample3.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.relay_draw_shadow_view:
                if (titleFlag) {
                    superDrawShadowOnView.hide();
                } else {
                    superDrawShadowOnView.show();
                }
                titleFlag = !titleFlag;
                break;
            case R.id.relay_draw_shadow_viewgroup:
                if (draw0Flag) {
                    superDrawShadowOnViewGroup.hide();
                } else {
                    superDrawShadowOnViewGroup.show();
                }
                draw0Flag = !draw0Flag;
                break;

            case R.id.change_wrap_shadow_direction_example:
                if (wrap0Flag) {
                    superWrapShadowLeftExample.hide();
                } else {
                    superWrapShadowLeftExample.show();
                }
                wrap0Flag = !wrap0Flag;
                break;
            case R.id.change_wrap_shadow_direction_example1:
                if (wrap1Flag) {
                    superWrapShadowLeftTopExample.hide();
                } else {
                    superWrapShadowLeftTopExample.show();
                }
                wrap1Flag = !wrap1Flag;
                break;
            case R.id.change_wrap_shadow_direction_example2:
                if (wrap2Flag) {
                    superWrapShadowLeftTopRightExample.hide();
                } else {
                    superWrapShadowLeftTopRightExample.show();
                }
                wrap2Flag = !wrap2Flag;
                break;
            case R.id.change_wrap_shadow_direction_example3:
                if (wrap3Flag) {
                    superWrapShadowAllExample.hide();
                } else {
                    superWrapShadowAllExample.show();
                }
                wrap3Flag = !wrap3Flag;
                break;
            case R.id.change_shadow_color_example:
                if (shadow0Flag) {
                } else {
                }
                shadow0Flag = !shadow0Flag;
                break;
        }
    }

    private void initShadow() {
        superDrawShadowOnView = new SuperShadow.Builder()
                .setContext(this)
                .setDirection(ShadowDirection.ALL)
                .setShadowSize(dip2Px(4))
                .setCorner(dip2Px(2))
                .setBaseShadowColor(Color.parseColor("#ff4444"))
                .setImpl(SuperShadow.DRAW)
                .action(drawShadowViewExample);
        superDrawShadowOnViewGroup = new SuperShadow.Builder()
                .setContext(this)
                .setDirection(ShadowDirection.ALL)
                .setShadowSize(dip2Px(4))
                .setCorner(dip2Px(2))
                .setBaseShadowColor(Color.parseColor("#ff4444"))
                .setImpl(SuperShadow.DRAW)
                .action(drawShadowViewGroupExample);
        superWrapShadowLeftExample = new SuperShadow.Builder()
                .setContext(this)
                .setDirection(ShadowDirection.LEFT)
                .setShadowSize(dip2Px(12))
                .setCorner(dip2Px(4))
                .setBaseShadowColor(Color.parseColor("#99cc00"))
                .setImpl(SuperShadow.WRAP)
                .action(wrapShadowDirectionExample);
//        superWrapShadowLeftTopExample = new SuperShadow.Builder()
//                .setContext(this)
//                .setDirection(ShadowDirection.LEFT_TOP)
//                .setShadowSize(dip2Px(12))
//                .setCorner(dip2Px(4))
//                .setBaseShadowColor(Color.parseColor("#99cc00"))
//                .setImpl(SuperShadow.WRAP)
//                .action(wrapShadowDirectionExample1);
//
//        superWrapShadowLeftTopRightExample = new SuperShadow.Builder()
//                .setContext(this)
//                .setDirection(ShadowDirection.LEFT_TOP_RIGHT)
//                .setShadowSize(dip2Px(12))
//                .setCorner(dip2Px(4))
//                .setBaseShadowColor(Color.parseColor("#99cc00"))
//                .setImpl(SuperShadow.WRAP)
//                .action(wrapShadowDirectionExample2);
//
//        superWrapShadowAllExample = new SuperShadow.Builder()
//                .setContext(this)
//                .setDirection(ShadowDirection.LEFT_TOP_RIGHT)
//                .setShadowSize(dip2Px(12))
//                .setCorner(dip2Px(4))
//                .setBaseShadowColor(Color.parseColor("#99cc00"))
//                .setImpl(SuperShadow.WRAP)
//                .action(wrapShadowDirectionExample3);

    }

    private void initView() {
        viewDrawShadow = findViewById(R.id.relay_draw_shadow_view);
        drawShadowViewExample = (ImageView) findViewById(R.id.relay_title_tv);
        drawShadowViewGroupExample = findViewById(R.id.relay_draw_shadow_viewgroup);
        wrapShadowDirectionExample = findViewById(R.id.change_wrap_shadow_direction_example);
        wrapShadowDirectionExample1 = findViewById(R.id.change_wrap_shadow_direction_example1);
        wrapShadowDirectionExample2 = findViewById(R.id.change_wrap_shadow_direction_example2);
        wrapShadowDirectionExample3 = findViewById(R.id.change_wrap_shadow_direction_example3);

    }


    public int dip2Px(float dpValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}

package com.ldfeng.supershadow;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.ldfeng.shadow.SuperShadow;
import com.ldfeng.shadow.componet.Direction;
import com.ldfeng.shadow.componet.ShadowDirection;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Context context;

    private ImageView drawShadowViewExample;

    private View drawShadowViewGroupExample;

    private View wrapShadowLeftExample;
    private View wrapShadowLeftTopExample;
    private View wrapShadowLeftTopRightExample;
    private View wrapShadowAllExample;

    private SuperShadow superDrawShadowOnView;
    private SuperShadow superDrawShadowOnViewGroup;

    private SuperShadow superWrapShadowDirectionExample;
    private SuperShadow superWrapShadowDirectionExample1;
    private SuperShadow superWrapShadowDirectionExample2;
    private SuperShadow superWrapShadowDirectionExample3;

    private boolean draw0Flag = true , draw1Flag = true;
    ;
    private boolean wrap0Flag = true, wrap1Flag = true, wrap2Flag = true, wrap3Flag = true;

    private int shadowDirection = ShadowDirection.NONE;

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
        drawShadowViewExample.setOnClickListener(this);
        drawShadowViewGroupExample.setOnClickListener(this);
        wrapShadowLeftExample.setOnClickListener(this);
        wrapShadowLeftTopExample.setOnClickListener(this);
        wrapShadowLeftTopRightExample.setOnClickListener(this);
        wrapShadowAllExample.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.relay_draw_shadow_view:
                if (draw0Flag) {
                    superDrawShadowOnView.hide();
                } else {
                    superDrawShadowOnView.show();
                }
                draw0Flag = !draw0Flag;
                break;
            case R.id.relay_draw_shadow_viewgroup:
                if (draw1Flag) {
                    superDrawShadowOnViewGroup.hide();
                } else {
                    superDrawShadowOnViewGroup.show();
                }
                draw1Flag = !draw1Flag;
                break;
            case R.id.wrap_shadow_direction_example:
                if(superWrapShadowDirectionExample == null) {
                    superWrapShadowDirectionExample = new SuperShadow.Builder()
                            .setContext(this)
                            .setDirection(ShadowDirection.LEFT)
                            .setShadowSize(dip2Px(8))
                            .setCorner(dip2Px(4))
                            .setBaseShadowColor(Color.parseColor("#99cc00"))
                            .setImpl(SuperShadow.WRAP)
                            .action(wrapShadowLeftExample);
                }
                if (wrap0Flag) {
                    superWrapShadowDirectionExample.hide();
                } else {
                    superWrapShadowDirectionExample.show();
                }
                wrap0Flag = !wrap0Flag;
                break;
            case R.id.wrap_shadow_direction_example1:
                if(superWrapShadowDirectionExample1 == null) {
                    superWrapShadowDirectionExample1 = new SuperShadow.Builder()
                            .setContext(this)
                            .setDirection(ShadowDirection.LEFT_TOP)
                            .setShadowSize(dip2Px(8))
                            .setCorner(dip2Px(4))
                            .setBaseShadowColor(Color.parseColor("#99cc00"))
                            .setImpl(SuperShadow.WRAP)
                            .action(wrapShadowLeftTopExample);
                }
                if (wrap1Flag) {
                    superWrapShadowDirectionExample1.hide();
                } else {
                    superWrapShadowDirectionExample1.show();
                }
                wrap1Flag = !wrap1Flag;
                break;
            case R.id.wrap_shadow_direction_example2:
                if(superWrapShadowDirectionExample2 == null) {
                    superWrapShadowDirectionExample2 = new SuperShadow.Builder()
                            .setContext(this)
                            .setDirection(ShadowDirection.LEFT_TOP_RIGHT)
                            .setShadowSize(dip2Px(8))
                            .setCorner(dip2Px(4))
                            .setBaseShadowColor(Color.parseColor("#99cc00"))
                            .setImpl(SuperShadow.WRAP)
                            .action(wrapShadowLeftTopRightExample);
                }
                if (wrap2Flag) {
                    superWrapShadowDirectionExample2.hide();
                } else {
                    superWrapShadowDirectionExample2.show();
                }
                wrap2Flag = !wrap2Flag;
                break;
            case R.id.wrap_shadow_direction_example3:
                if(superWrapShadowDirectionExample3 == null) {
                    superWrapShadowDirectionExample3 = new SuperShadow.Builder()
                            .setContext(this)
                            .setDirection(ShadowDirection.ALL)
                            .setShadowSize(dip2Px(8))
                            .setCorner(dip2Px(4))
                            .setBaseShadowColor(Color.parseColor("#99cc00"))
                            .setImpl(SuperShadow.WRAP)
                            .action(wrapShadowAllExample);
                }
                if (wrap3Flag) {
                    superWrapShadowDirectionExample3.hide();
                } else {
                    superWrapShadowDirectionExample3.show();
                }
                wrap3Flag = !wrap3Flag;
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
    }

    private void initView() {
        drawShadowViewExample = (ImageView) findViewById(R.id.relay_draw_shadow_view);
        drawShadowViewGroupExample = findViewById(R.id.relay_draw_shadow_viewgroup);


        wrapShadowLeftExample = findViewById(R.id.wrap_shadow_direction_example);
        wrapShadowLeftTopExample = findViewById(R.id.wrap_shadow_direction_example1);
        wrapShadowLeftTopRightExample = findViewById(R.id.wrap_shadow_direction_example2);
        wrapShadowAllExample = findViewById(R.id.wrap_shadow_direction_example3);
    }


    public int dip2Px(float dpValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}

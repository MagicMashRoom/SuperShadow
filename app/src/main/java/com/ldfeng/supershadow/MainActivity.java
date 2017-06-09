package com.ldfeng.supershadow;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.ldfeng.shadow.SuperShadow;
import com.ldfeng.shadow.base.ShadowDirection;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Context context;

    private View titleView;

    private ImageView titleViewTv;

    private View drawView, drawRoundView;

    private View wrapView0, wrapView1;

    private View shadowView0, shadowView1;

    private SuperShadow titleCrazyShadow;

    private SuperShadow drawCrazyShadow0, drawCrazyShadow1;


    private SuperShadow wrapCrazyShadow0, wrapCrazyShadow1;

    private SuperShadow shadowCrazyShadow0, shadowCrazyShadow1;

    private boolean titleFlag = true, draw0Flag = true, draw1Flag = true;

    private boolean wrap0Flag = true, wrap1Flag = true, shadow0Flag = true, shadow1Flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setStatusBar();
        initView();
        initShadow();
        initListener();
    }

    private void setStatusBar() {
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.colorAccent));
    }

    private void initListener() {
        titleView.setOnClickListener(this);
        drawView.setOnClickListener(this);
        drawRoundView.setOnClickListener(this);
        wrapView0.setOnClickListener(this);
        wrapView1.setOnClickListener(this);
        shadowView0.setOnClickListener(this);
        shadowView1.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.relay_title:
                if (titleFlag) {
                    titleCrazyShadow.hide();
                } else {
                    titleCrazyShadow.show();
                }
                titleFlag = !titleFlag;
                break;
            case R.id.relay_draw0:
                if (draw0Flag) {
                    drawCrazyShadow0.hide();
                } else {
                    drawCrazyShadow0.show();
                }
                draw0Flag = !draw0Flag;
                break;
            case R.id.relay_draw1:
                if (draw1Flag) {
                    drawCrazyShadow1.hide();
                } else {
                    drawCrazyShadow1.show();
                }
                draw1Flag = !draw1Flag;
                break;

            case R.id.relay_wrap0:
                if (wrap0Flag) {
                    wrapCrazyShadow0.hide();
                } else {
                    wrapCrazyShadow0.show();
                }
                wrap0Flag = !wrap0Flag;
                break;
            case R.id.relay_wrap1:
                if (wrap1Flag) {
                    wrapCrazyShadow1.hide();
                } else {
                    wrapCrazyShadow1.show();
                }
                wrap1Flag = !wrap1Flag;
                break;
            case R.id.relay_shadow0:
                if (shadow0Flag) {
                    shadowCrazyShadow0.hide();
                } else {
                    shadowCrazyShadow0.show();
                }
                shadow0Flag = !shadow0Flag;
                break;
            case R.id.relay_shadow1:
                if (shadow1Flag) {
                    shadowCrazyShadow1.hide();
                } else {
                    shadowCrazyShadow1.show();
                }
                shadow1Flag = !shadow1Flag;
                break;
        }
    }

    private void initShadow() {
        titleCrazyShadow = new SuperShadow.Builder()
                .setContext(this)
                .setDirection(ShadowDirection.ALL)
                .setShadowSize(dip2Px(4))
                .setCorner(dip2Px(2))
                .setBaseShadowColor(Color.parseColor("#ff4444"))
                .setImpl(SuperShadow.DRAW_RENDERER)
                .action(titleViewTv);
//
//        drawCrazyShadow0 = new SuperShadow.Builder()
//                .setContext(this)
//                .setDirection(ShadowDirection.ALL)
//                .setShadowSize(dip2Px(12))
//                .setBaseShadowColor(Color.RED)
//                .setImpl(SuperShadow.DRAW_RENDERER)
//                .action(drawView);
//
//        drawCrazyShadow1 = new SuperShadow.Builder()
//                .setContext(this)
//                .setDirection(ShadowDirection.ALL)
//                .setShadowSize(dip2Px(3))
//                .setCorner(dip2Px(5))
//                .setBackground(Color.parseColor("#96a993"))
//                .setImpl(SuperShadow.DRAW_RENDERER)
//                .action(drawRoundView);
//
//        wrapCrazyShadow0 = new SuperShadow.Builder()
//                .setContext(this)
//                .setDirection(ShadowDirection.ALL)
//                .setShadowSize(dip2Px(3))
//                .setImpl(SuperShadow.WRAP_RENDERER)
//                .action(wrapView0);
//
//        wrapCrazyShadow1 = new SuperShadow.Builder()
//                .setContext(this)
//                .setDirection(ShadowDirection.ALL)
//                .setShadowSize(dip2Px(5))
//                .setCorner(dip2Px(8))
//                .setImpl(SuperShadow.WRAP_RENDERER)
//                .action(wrapView1);
//
//        shadowCrazyShadow0 = new SuperShadow.Builder()
//                .setContext(this)
//                .setDirection(ShadowDirection.ALL)
//                .setShadowSize(dip2Px(4))
//                .setBackground(Color.parseColor("#a0a0a0"))
//                .setBaseShadowColor(Color.parseColor("#a0a0a0"))
//                .setImpl(SuperShadow.WRAP_RENDERER)
//                .action(findViewById(R.id.relay_shadow0));
//
        shadowCrazyShadow1 = new SuperShadow.Builder()
                .setContext(this)
                .setDirection(ShadowDirection.TOP)
                .setShadowSize(dip2Px(12))
                .setCorner(dip2Px(4))
                .setBaseShadowColor(Color.parseColor("#99cc00"))
                .setImpl(SuperShadow.WRAP_RENDERER)
                .action(findViewById(R.id.relay_shadow1));

    }

    private void initView() {
        titleView = findViewById(R.id.relay_title);
        titleViewTv = (ImageView) findViewById(R.id.relay_title_tv);
        drawView = findViewById(R.id.relay_draw0);
        drawRoundView = findViewById(R.id.relay_draw1);
        wrapView0 = findViewById(R.id.relay_wrap0);
        wrapView1 = findViewById(R.id.relay_wrap1);
        shadowView0 = findViewById(R.id.relay_shadow0);
        shadowView1 = findViewById(R.id.relay_shadow1);
    }


    public int dip2Px(float dpValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}

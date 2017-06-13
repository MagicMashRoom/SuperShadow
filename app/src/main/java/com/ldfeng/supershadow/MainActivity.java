package com.ldfeng.supershadow;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.ldfeng.shadow.SuperShadow;
import com.ldfeng.shadow.componet.Direction;
import com.ldfeng.shadow.componet.ShadowDirection;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Context context;

    private View titleView;

    private ImageView titleViewTv;

    private View drawView, drawRoundView;

    private View wrapView0, wrapView1;

    private View shadowView0, shadowView1;

    private SuperShadow superShadowTitle;

    private SuperShadow drawCrazyShadow0, drawCrazyShadow1;


    private SuperShadow wrapCrazyShadow0, wrapCrazyShadow1;

    private SuperShadow shadowCrazyShadow0, superShadowExample;

    private boolean titleFlag = true, draw0Flag = true, draw1Flag = true;

    private boolean wrap0Flag = true, wrap1Flag = true, shadow0Flag = true, shadow1Flag = true;

    private CheckBox left;

    private CheckBox top;

    private CheckBox right;

    private CheckBox bottom;

    private ArrayList<Direction> directions = new ArrayList<>();

    private int shadowDirection = ShadowDirection.NONE;

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
        left.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    directions.add(Direction.LEFT);
                } else {
                    directions.remove(Direction.LEFT);
                }
                changeDirection();
            }
        });

        top.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    directions.add(Direction.TOP);
                } else {
                    directions.remove(Direction.TOP);
                }
                changeDirection();
            }
        });

        right.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    directions.add(Direction.RIGHT);
                } else {
                    directions.remove(Direction.RIGHT);
                }
                changeDirection();
            }
        });

        bottom.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    directions.add(Direction.BOTTOM);
                } else {
                    directions.remove(Direction.BOTTOM);
                }
                changeDirection();
            }
        });
    }

    private void changeDirection() {
        switch (directions.size()) {
            case 0:
                superShadowExample.hide();
                break;
            case 1:
                if(directions.contains(Direction.LEFT)) {
                    superShadowExample = new SuperShadow.Builder()
                            .setContext(this)
                            .setDirection(ShadowDirection.LEFT)
                            .setShadowSize(dip2Px(12))
                            .setCorner(dip2Px(4))
                            .setBaseShadowColor(Color.parseColor("#99cc00"))
                            .setImpl(SuperShadow.WRAP)
                            .action(shadowView1);
                }
                if(directions.contains(Direction.TOP)) {
                    superShadowExample = new SuperShadow.Builder()
                            .setContext(this)
                            .setDirection(ShadowDirection.TOP)
                            .setShadowSize(dip2Px(12))
                            .setCorner(dip2Px(4))
                            .setBaseShadowColor(Color.parseColor("#99cc00"))
                            .setImpl(SuperShadow.WRAP)
                            .action(shadowView1);
                }
                if(directions.contains(Direction.RIGHT)) {
                    superShadowExample = new SuperShadow.Builder()
                            .setContext(this)
                            .setDirection(ShadowDirection.RIGHT)
                            .setShadowSize(dip2Px(12))
                            .setCorner(dip2Px(4))
                            .setBaseShadowColor(Color.parseColor("#99cc00"))
                            .setImpl(SuperShadow.WRAP)
                            .action(shadowView1);
                }
                if(directions.contains(Direction.BOTTOM)) {
                    superShadowExample = new SuperShadow.Builder()
                            .setContext(this)
                            .setDirection(ShadowDirection.BOTTOM)
                            .setShadowSize(dip2Px(12))
                            .setCorner(dip2Px(4))
                            .setBaseShadowColor(Color.parseColor("#99cc00"))
                            .setImpl(SuperShadow.WRAP)
                            .action(shadowView1);
                }
                superShadowExample.show();
                break;
            case 2:
                if(directions.contains(Direction.LEFT)) {
                    if(directions.contains(Direction.TOP)) {
                        superShadowExample = new SuperShadow.Builder()
                                .setContext(this)
                                .setDirection(ShadowDirection.LEFT_TOP)
                                .setShadowSize(dip2Px(12))
                                .setCorner(dip2Px(4))
                                .setBaseShadowColor(Color.parseColor("#99cc00"))
                                .setImpl(SuperShadow.WRAP)
                                .action(shadowView1);
                    } else {
                        superShadowExample = new SuperShadow.Builder()
                                .setContext(this)
                                .setDirection(ShadowDirection.LEFT_RIGHT)
                                .setShadowSize(dip2Px(12))
                                .setCorner(dip2Px(4))
                                .setBaseShadowColor(Color.parseColor("#99cc00"))
                                .setImpl(SuperShadow.WRAP)
                                .action(shadowView1);
                    }
                }

                if(directions.contains(Direction.TOP)) {
                    if(directions.contains(Direction.RIGHT)) {
                        superShadowExample = new SuperShadow.Builder()
                                .setContext(this)
                                .setDirection(ShadowDirection.TOP_RIGHT)
                                .setShadowSize(dip2Px(12))
                                .setCorner(dip2Px(4))
                                .setBaseShadowColor(Color.parseColor("#99cc00"))
                                .setImpl(SuperShadow.WRAP)
                                .action(shadowView1);
                    } else {
                        superShadowExample = new SuperShadow.Builder()
                                .setContext(this)
                                .setDirection(ShadowDirection.TOP_BOTTOM)
                                .setShadowSize(dip2Px(12))
                                .setCorner(dip2Px(4))
                                .setBaseShadowColor(Color.parseColor("#99cc00"))
                                .setImpl(SuperShadow.WRAP)
                                .action(shadowView1);
                    }
                }

                if(directions.contains(Direction.RIGHT)) {
                    if(directions.contains(Direction.BOTTOM)) {
                        superShadowExample = new SuperShadow.Builder()
                                .setContext(this)
                                .setDirection(ShadowDirection.RIGHT_BOTTOM)
                                .setShadowSize(dip2Px(12))
                                .setCorner(dip2Px(4))
                                .setBaseShadowColor(Color.parseColor("#99cc00"))
                                .setImpl(SuperShadow.WRAP)
                                .action(shadowView1);
                    }
                }

                if(directions.contains(Direction.BOTTOM)) {
                    if(directions.contains(Direction.LEFT)) {
                        superShadowExample = new SuperShadow.Builder()
                                .setContext(this)
                                .setDirection(ShadowDirection.BOTTOM_LEFT)
                                .setShadowSize(dip2Px(12))
                                .setCorner(dip2Px(4))
                                .setBaseShadowColor(Color.parseColor("#99cc00"))
                                .setImpl(SuperShadow.WRAP)
                                .action(shadowView1);
                    }
                }
                superShadowExample.show();

                break;
            case 3:
                if(!directions.contains(Direction.LEFT)) {
                    superShadowExample = new SuperShadow.Builder()
                            .setContext(this)
                            .setDirection(ShadowDirection.TOP_RIGHT_BOTTOM)
                            .setShadowSize(dip2Px(12))
                            .setCorner(dip2Px(4))
                            .setBaseShadowColor(Color.parseColor("#99cc00"))
                            .setImpl(SuperShadow.WRAP)
                            .action(shadowView1);
                }
                if(!directions.contains(Direction.TOP)) {
                    superShadowExample = new SuperShadow.Builder()
                            .setContext(this)
                            .setDirection(ShadowDirection.RIGHT_BOTTOM_LEFT)
                            .setShadowSize(dip2Px(12))
                            .setCorner(dip2Px(4))
                            .setBaseShadowColor(Color.parseColor("#99cc00"))
                            .setImpl(SuperShadow.WRAP)
                            .action(shadowView1);
                }
                if(!directions.contains(Direction.RIGHT)) {
                    superShadowExample = new SuperShadow.Builder()
                            .setContext(this)
                            .setDirection(ShadowDirection.BOTTOM_LEFT_TOP)
                            .setShadowSize(dip2Px(12))
                            .setCorner(dip2Px(4))
                            .setBaseShadowColor(Color.parseColor("#99cc00"))
                            .setImpl(SuperShadow.WRAP)
                            .action(shadowView1);
                }
                if(!directions.contains(Direction.BOTTOM)) {
                    superShadowExample = new SuperShadow.Builder()
                            .setContext(this)
                            .setDirection(ShadowDirection.LEFT_TOP_RIGHT)
                            .setShadowSize(dip2Px(12))
                            .setCorner(dip2Px(4))
                            .setBaseShadowColor(Color.parseColor("#99cc00"))
                            .setImpl(SuperShadow.WRAP)
                            .action(shadowView1);
                }
                superShadowExample.show();

                break;
            case 4:
                superShadowExample = new SuperShadow.Builder()
                        .setContext(this)
                        .setDirection(ShadowDirection.ALL)
                        .setShadowSize(dip2Px(12))
                        .setCorner(dip2Px(4))
                        .setBaseShadowColor(Color.parseColor("#99cc00"))
                        .setImpl(SuperShadow.WRAP)
                        .action(shadowView1);
                superShadowExample.show();

                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.relay_title:
                if (titleFlag) {
                    superShadowTitle.hide();
                } else {
                    superShadowTitle.show();
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
                    superShadowExample.hide();
                } else {
                    superShadowExample.show();
                }
                shadow1Flag = !shadow1Flag;
                break;

        }
    }

    private void initShadow() {
        superShadowTitle = new SuperShadow.Builder()
                .setContext(this)
                .setDirection(ShadowDirection.ALL)
                .setShadowSize(dip2Px(4))
                .setCorner(dip2Px(2))
                .setBaseShadowColor(Color.parseColor("#ff4444"))
                .setImpl(SuperShadow.DRAW)
                .action(titleViewTv);

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
        left = (CheckBox) findViewById(R.id.left_direction);
        top = (CheckBox) findViewById(R.id.top_direction);
        right = (CheckBox) findViewById(R.id.right_direction);
        bottom = (CheckBox) findViewById(R.id.bottom_direction);
    }


    public int dip2Px(float dpValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}

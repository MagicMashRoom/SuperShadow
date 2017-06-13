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

    private View viewDrawShadow;

    private ImageView titleViewTv;

    private View viewGroupDrawShadow;

    private View wrapShadowExample, wrapView1;

    private View changeShadowColor, changeShadowDirection;

    private SuperShadow superShadowOnView;

    private SuperShadow superShadowOnViewGroup;

    private SuperShadow superShadowExample;

    private boolean titleFlag = true, draw0Flag = true;

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
        context = this;
        initView();
        initShadow();
        initListener();
    }

    private void initListener() {
        viewDrawShadow.setOnClickListener(this);
        viewGroupDrawShadow.setOnClickListener(this);
        wrapShadowExample.setOnClickListener(this);
        wrapView1.setOnClickListener(this);
        changeShadowColor.setOnClickListener(this);
        changeShadowDirection.setOnClickListener(this);
        left.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(superShadowExample != null) {
                    superShadowExample.remove();
                    if(isChecked) {
                        directions.add(Direction.LEFT);
                    } else {
                        directions.remove(Direction.LEFT);
                    }
                    changeDirection();
                } else {
                    superShadowExample = new SuperShadow.Builder()
                            .setContext(context)
                            .setDirection(ShadowDirection.LEFT)
                            .setShadowSize(dip2Px(12))
                            .setCorner(dip2Px(4))
                            .setBaseShadowColor(Color.parseColor("#99cc00"))
                            .setImpl(SuperShadow.WRAP)
                            .action(changeShadowDirection);
                }
            }
        });

        top.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(superShadowExample != null) {
                    superShadowExample.remove();
                    if (isChecked) {
                        directions.add(Direction.TOP);
                    } else {
                        directions.remove(Direction.TOP);
                    }
                    changeDirection();
                } else {
                    superShadowExample = new SuperShadow.Builder()
                            .setContext(context)
                            .setDirection(ShadowDirection.TOP)
                            .setShadowSize(dip2Px(12))
                            .setCorner(dip2Px(4))
                            .setBaseShadowColor(Color.parseColor("#99cc00"))
                            .setImpl(SuperShadow.WRAP)
                            .action(changeShadowDirection);
                }
            }
        });

        right.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(superShadowExample != null) {
                    superShadowExample.remove();
                    if (isChecked) {
                        directions.add(Direction.RIGHT);
                    } else {
                        directions.remove(Direction.RIGHT);
                    }
                    changeDirection();
                } else {
                    superShadowExample = new SuperShadow.Builder()
                            .setContext(context)
                            .setDirection(ShadowDirection.RIGHT)
                            .setShadowSize(dip2Px(12))
                            .setCorner(dip2Px(4))
                            .setBaseShadowColor(Color.parseColor("#99cc00"))
                            .setImpl(SuperShadow.WRAP)
                            .action(changeShadowDirection);
                }
            }
        });

        bottom.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(superShadowExample != null) {
                    superShadowExample.remove();
                    if (isChecked) {
                        directions.add(Direction.BOTTOM);
                    } else {
                        directions.remove(Direction.BOTTOM);
                    }
                    changeDirection();
                } else {
                    superShadowExample = new SuperShadow.Builder()
                            .setContext(context)
                            .setDirection(ShadowDirection.BOTTOM)
                            .setShadowSize(dip2Px(12))
                            .setCorner(dip2Px(4))
                            .setBaseShadowColor(Color.parseColor("#99cc00"))
                            .setImpl(SuperShadow.WRAP)
                            .action(changeShadowDirection);
                }
            }
        });
    }

    private void changeDirection() {
        switch (directions.size()) {
            case 0:
                superShadowExample.remove();
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
                            .action(changeShadowDirection);
                }
                if(directions.contains(Direction.TOP)) {
                    superShadowExample = new SuperShadow.Builder()
                            .setContext(this)
                            .setDirection(ShadowDirection.TOP)
                            .setShadowSize(dip2Px(12))
                            .setCorner(dip2Px(4))
                            .setBaseShadowColor(Color.parseColor("#99cc00"))
                            .setImpl(SuperShadow.WRAP)
                            .action(changeShadowDirection);
                }
                if(directions.contains(Direction.RIGHT)) {
                    superShadowExample = new SuperShadow.Builder()
                            .setContext(this)
                            .setDirection(ShadowDirection.RIGHT)
                            .setShadowSize(dip2Px(12))
                            .setCorner(dip2Px(4))
                            .setBaseShadowColor(Color.parseColor("#99cc00"))
                            .setImpl(SuperShadow.WRAP)
                            .action(changeShadowDirection);
                }
                if(directions.contains(Direction.BOTTOM)) {
                    superShadowExample = new SuperShadow.Builder()
                            .setContext(this)
                            .setDirection(ShadowDirection.BOTTOM)
                            .setShadowSize(dip2Px(12))
                            .setCorner(dip2Px(4))
                            .setBaseShadowColor(Color.parseColor("#99cc00"))
                            .setImpl(SuperShadow.WRAP)
                            .action(changeShadowDirection);
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
                                .action(changeShadowDirection);
                    } else {
                        superShadowExample = new SuperShadow.Builder()
                                .setContext(this)
                                .setDirection(ShadowDirection.LEFT_RIGHT)
                                .setShadowSize(dip2Px(12))
                                .setCorner(dip2Px(4))
                                .setBaseShadowColor(Color.parseColor("#99cc00"))
                                .setImpl(SuperShadow.WRAP)
                                .action(changeShadowDirection);
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
                                .action(changeShadowDirection);
                    } else {
                        superShadowExample = new SuperShadow.Builder()
                                .setContext(this)
                                .setDirection(ShadowDirection.TOP_BOTTOM)
                                .setShadowSize(dip2Px(12))
                                .setCorner(dip2Px(4))
                                .setBaseShadowColor(Color.parseColor("#99cc00"))
                                .setImpl(SuperShadow.WRAP)
                                .action(changeShadowDirection);
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
                                .action(changeShadowDirection);
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
                                .action(changeShadowDirection);
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
                            .action(changeShadowDirection);
                }
                if(!directions.contains(Direction.TOP)) {
                    superShadowExample = new SuperShadow.Builder()
                            .setContext(this)
                            .setDirection(ShadowDirection.RIGHT_BOTTOM_LEFT)
                            .setShadowSize(dip2Px(12))
                            .setCorner(dip2Px(4))
                            .setBaseShadowColor(Color.parseColor("#99cc00"))
                            .setImpl(SuperShadow.WRAP)
                            .action(changeShadowDirection);
                }
                if(!directions.contains(Direction.RIGHT)) {
                    superShadowExample = new SuperShadow.Builder()
                            .setContext(this)
                            .setDirection(ShadowDirection.BOTTOM_LEFT_TOP)
                            .setShadowSize(dip2Px(12))
                            .setCorner(dip2Px(4))
                            .setBaseShadowColor(Color.parseColor("#99cc00"))
                            .setImpl(SuperShadow.WRAP)
                            .action(changeShadowDirection);
                }
                if(!directions.contains(Direction.BOTTOM)) {
                    superShadowExample = new SuperShadow.Builder()
                            .setContext(this)
                            .setDirection(ShadowDirection.LEFT_TOP_RIGHT)
                            .setShadowSize(dip2Px(12))
                            .setCorner(dip2Px(4))
                            .setBaseShadowColor(Color.parseColor("#99cc00"))
                            .setImpl(SuperShadow.WRAP)
                            .action(changeShadowDirection);
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
                        .action(changeShadowDirection);
                superShadowExample.show();

                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.relay_draw_shadow_view:
                if (titleFlag) {
                    superShadowOnView.hide();
                } else {
                    superShadowOnView.show();
                }
                titleFlag = !titleFlag;
                break;
            case R.id.relay_draw_shadow_viewgroup:
                if (draw0Flag) {
                } else {
                }
                draw0Flag = !draw0Flag;
                break;

            case R.id.relay_wrap_shadow_example:
                if (wrap0Flag) {
                } else {
                }
                wrap0Flag = !wrap0Flag;
                break;
            case R.id.relay_wrap1:
                if (wrap1Flag) {
                } else {
                }
                wrap1Flag = !wrap1Flag;
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
        superShadowOnView = new SuperShadow.Builder()
                .setContext(this)
                .setDirection(ShadowDirection.ALL)
                .setShadowSize(dip2Px(4))
                .setCorner(dip2Px(2))
                .setBaseShadowColor(Color.parseColor("#ff4444"))
                .setImpl(SuperShadow.DRAW)
                .action(titleViewTv);
    }

    private void initView() {
        viewDrawShadow = findViewById(R.id.relay_draw_shadow_view);
        titleViewTv = (ImageView) findViewById(R.id.relay_title_tv);
        viewGroupDrawShadow = findViewById(R.id.relay_draw_shadow_viewgroup);
        wrapShadowExample = findViewById(R.id.relay_wrap_shadow_example);
        wrapView1 = findViewById(R.id.relay_wrap1);
        changeShadowColor = findViewById(R.id.change_shadow_color_example);
        changeShadowDirection = findViewById(R.id.change_wrap_shadow_direction_example);
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

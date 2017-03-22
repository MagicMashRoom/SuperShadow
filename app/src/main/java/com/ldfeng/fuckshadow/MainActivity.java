package com.ldfeng.fuckshadow;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ldfeng.shadow.CrazyShadow;
import com.ldfeng.shadow.base.CrazyShadowDirection;

public class MainActivity extends AppCompatActivity{

    private ViewGroup drawView0;

    private LinearLayout relayDraw0Content;

    private RelativeLayout addShadowBtn;

    private CrazyShadow drawCrazyShadow0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        drawView0 = (ViewGroup) findViewById(R.id.relay_draw0);
        relayDraw0Content = (LinearLayout) findViewById(R.id.relay_draw0_content);
        addShadowBtn = (RelativeLayout) findViewById(R.id.relay_shadow1);
        addShadowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawCrazyShadow0 = new CrazyShadow.Builder()
                        .setContext(MainActivity.this)
                        .setDirection(CrazyShadowDirection.ALL)
                        .setCorner(dip2Px(0))
                        .setShadowRadius(dip2Px(3))
                        .setBaseShadowColor(Color.RED)
                        .setImpl(CrazyShadow.IMPL_DRAW)
                        .setChild(relayDraw0Content)
                        .setWidth(relayDraw0Content.getWidth())
                        .setHeight(relayDraw0Content.getHeight())
                        .action(drawView0);
            }
        });
    }


    public int dip2Px(float dpValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        Log.e("ldf","onWindowFocusChanged");
        super.onWindowFocusChanged(hasFocus);
    }
}

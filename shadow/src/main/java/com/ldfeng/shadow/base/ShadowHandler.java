package com.ldfeng.shadow.base;

import android.content.Context;
import android.widget.RelativeLayout;

/**
 * Created by hitomi on 2016/10/19.
 */
public interface ShadowHandler {

    RelativeLayout makeShadow(Context context);

    void removeShadow();

    void hideShadow();

    void showShadow();

}

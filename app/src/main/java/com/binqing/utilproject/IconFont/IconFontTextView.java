package com.binqing.utilproject.IconFont;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

public class IconFontTextView extends android.support.v7.widget.AppCompatTextView {
    public IconFontTextView(Context context) {
        super(context);
        setIconFontType(context);
    }

    public IconFontTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setIconFontType(context);
    }

    public IconFontTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setIconFontType(context);
    }

    private void setIconFontType(Context context) {
        Typeface iconfont = Typeface.createFromAsset(context.getAssets(), "iconfont/iconfont.ttf");
        this.setTypeface(iconfont);
    }
}

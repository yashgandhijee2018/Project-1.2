package com.demo.incampus;

import android.content.Context;
import android.util.AttributeSet;

public class CustImg extends androidx.appcompat.widget.AppCompatImageView {
    public CustImg(Context context) {
        super(context);
    }

    public CustImg(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustImg(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = width * getDrawable().getIntrinsicHeight() / getDrawable().getIntrinsicWidth();
        setMeasuredDimension(width, height);
    }
}

package jx.com.day5_weaterfall.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

import jx.com.day5_weaterfall.R;

@SuppressLint("AppCompatCustomView")
public class HeadlineView extends TextView {
    public HeadlineView(Context context) {
        super(context);
    }

    public HeadlineView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.HeadlineView);
        int color = typedArray.getColor(R.styleable.HeadlineView_textColor, Color.BLACK);
        setTextColor(color);
        //回收
        typedArray.recycle();
    }
}

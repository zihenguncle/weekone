package jx.com.day5_weaterfall.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

public class LabelView extends LinearLayout {

    //最高的孩子
    private int mMaxChildHeight;

    //边距
    private int marginheight = 20;
    private int marginwidth = 20;

    public LabelView(Context context) {
        super(context);
    }

    public LabelView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //得到父控件的宽高
        int WidthSize = MeasureSpec.getSize(widthMeasureSpec);
        int HeightSize = MeasureSpec.getSize(heightMeasureSpec);

        //得到孩子的大小(切记勿忘)
        measureChildren(widthMeasureSpec,heightMeasureSpec);

        //得到最高的孩子
        findMaxHeight();

        //初始化
        int left = 0,top = 0;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            if(left != 0){
                if((left + view.getMeasuredWidth())>WidthSize){
                    top+=mMaxChildHeight+marginheight;
                    left = 0;
                }
            }
            left += view.getMeasuredWidth()+marginwidth;
        }
        setMeasuredDimension(WidthSize,(top+mMaxChildHeight)>HeightSize?HeightSize:top+mMaxChildHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        findMaxHeight();
        int left = 0,top = 0;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            if(left != 0){
                if((view.getMeasuredWidth()+left)>getWidth()){
                    top += mMaxChildHeight+marginheight;
                    left = 0;
                }
            }
            view.layout(left,top,left + view.getMeasuredWidth(),top + mMaxChildHeight);
            left += view.getMeasuredWidth() + marginwidth;
        }
    }

    private void findMaxHeight(){
        mMaxChildHeight = 0;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            if(view.getMeasuredHeight()>mMaxChildHeight){
                mMaxChildHeight = view.getMeasuredHeight();
            }
        }
    }
}

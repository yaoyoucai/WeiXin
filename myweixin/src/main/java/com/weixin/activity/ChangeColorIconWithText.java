package com.weixin.activity;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by yh on 2016/1/15.
 */
public class ChangeColorIconWithText extends View {
    private int mColor = 0xFF45C01A;
    private Bitmap mIconBitMap;
    private String mText = "微信";
    //将整型值转变为标准尺寸
    private int mTextSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 12, getResources().getDisplayMetrics());

    private Canvas mCanvas;
    private Bitmap mBitmap;
    private Paint mPaint;
    private int mAlapha;
    private Rect mIconRect;
    private Rect mTextBound;
    private Paint mTextPaint;
    public ChangeColorIconWithText(Context context) {
        this(context, null);
    }

    public ChangeColorIconWithText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ChangeColorIconWithText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ChangeColorIconWithText);
        int count = typedArray.getIndexCount();
        for (int i = 0; i < count; i++) {
            int attr = typedArray.getIndex(i);
            switch (attr) {
                case R.styleable.ChangeColorIconWithText_color1:
                    mColor=typedArray.getColor(attr,0xFF45C01A);
                    break;
                case R.styleable.ChangeColorIconWithText_text1:
                    mText=typedArray.getString(attr);
                    break;
                case R.styleable.ChangeColorIconWithText_text_size1:
                    mTextSize=(int)typedArray.getDimension(attr, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 12, getResources().getDisplayMetrics()));
                    break;
                case R.styleable.ChangeColorIconWithText_icon1:
                    BitmapDrawable drawable= (BitmapDrawable) typedArray.getDrawable(attr);
                    mIconBitMap=drawable.getBitmap();
                    break;
            }
            typedArray.recycle();
            mTextBound=new Rect();
            mTextPaint=new Paint();
            mTextPaint.setTextSize(mTextSize);
            mTextPaint.setColor(0Xff555555);
            mTextPaint.getTextBounds(mText,0,mText.length(),mTextBound);
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int iconWidth=Math.min(getMeasuredWidth()-getPaddingLeft()-getPaddingRight(),getMeasuredHeight()-getPaddingTop()-getPaddingBottom()-mTextBound.height());
        int left=getMeasuredWidth()/2-iconWidth/2;
        int top=getMeasuredHeight()/2-(mTextBound.height()+iconWidth)/2;
        mIconRect=new Rect(left,top,left+iconWidth,top+iconWidth);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(mIconBitMap,null,mIconRect,null);
        super.draw(canvas);
    }
}

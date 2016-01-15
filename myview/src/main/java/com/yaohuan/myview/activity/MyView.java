package com.yaohuan.myview.activity;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by yh on 2016/1/15.
 */
public class MyView extends View {
    private Paint paint;
    public MyView(Context context) {
        super(context);
     }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.BLUE);
    }

    public MyView(Context context,AttributeSet attrs){
        super(context, attrs);
        paint=new Paint();
        //TypedArray是一个用来存放由context.obtainStyledAttributes获得的属性的数组

        //在使用完成后，一定要调用recycle方法

        //属性的名称是styleable中的名称+“_”+属性名称

        //TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.MyView);

       TypedArray array= context.obtainStyledAttributes(attrs, R.styleable.MyView);
        int textColor=array.getColor(R.styleable.MyView_textColor, 0XFF00FF00);
        float textSize=array.getDimension(R.styleable.MyView_textSize,36);
        paint.setColor(textColor);
        paint.setTextSize(textSize);
        array.recycle(); //一定要调用，否则这次的设定会对下次的使用造成影响
    }
}

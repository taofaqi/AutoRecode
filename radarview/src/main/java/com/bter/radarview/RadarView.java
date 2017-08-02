package com.bter.radarview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.SweepGradient;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by taofaqi on 2017/8/2.
 */

public class RadarView extends View {


    private static final int MSG_WHAT = 1 << 1;
    private static final long DELAY_TIME = 20;
    private int staretColor = 0x0000ff00;
    private int endColor = 0x0000ff00;
    private int mRadarCircleCount = 4;
    private int mRadarLineColor = Color.WHITE;
    private int mRadarBgColor = Color.BLACK;
    private Paint mRadarCirclePaint;
    private Paint mRadarBgPaint;
    private SweepGradient radarShader;
    private Matrix matrix;

    //设置默认宽高，雷达一般都是圆形，所以我们下面取宽高会去Math.min(宽,高)
    private final int DEFAULT_WIDTH = 200;
    private final int DEFAULT_HEIGHT = 200;
    //旋转的角度
    private int rotateAngel = 0;
    private int mRadarRadius;


    public RadarView(Context context) {
        this(context, null);
    }

    public RadarView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RadarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
        initPaint();

        radarShader = new SweepGradient(0, 0, staretColor, endColor);
        matrix = new Matrix();
    }

    /*
    * 读取自定义View的自定义属性*/
    private void init(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RadarView);
            staretColor = typedArray.getColor(R.styleable.RadarView_startColor, staretColor);
            endColor = typedArray.getColor(R.styleable.RadarView_endColor, endColor);
            mRadarBgColor = typedArray.getColor(R.styleable.RadarView_bgColor, mRadarBgColor);
            mRadarLineColor = typedArray.getColor(R.styleable.RadarView_LineColor, mRadarLineColor);
            mRadarCircleCount = typedArray.getInteger(R.styleable.RadarView_circleCount, mRadarCircleCount);
            /*注意使用完毕之后要回收*/
            typedArray.recycle();
        }
    }

    /*
    * 自定义画笔*/
    private void initPaint() {
        mRadarCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRadarCirclePaint.setColor(mRadarLineColor);
        mRadarCirclePaint.setStyle(Paint.Style.STROKE);    //设置空心画笔  只画圆边
        mRadarCirclePaint.setStrokeWidth(2);

        mRadarBgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRadarBgPaint.setColor(mRadarBgColor);
        mRadarBgPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = measureSize(1, DEFAULT_WIDTH, widthMeasureSpec);
        int height = measureSize(0, DEFAULT_HEIGHT, heightMeasureSpec);
        int measureSize = Math.max(width, height);          //取最大的 宽|高
        setMeasuredDimension(measureSize, measureSize);
    }

    /**
     * 绘制measure
     *
     * @param specType    1为宽  其他为高
     * @param contentSize 默认值
     */
    private int measureSize(int specType, int contentSize, int measureSpec) {
        int result;
        //获取测量的模式和Size
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY)
            result = Math.max(contentSize, specSize);
        else {
            result = contentSize;
            if (specType == 1)
                result += getPaddingLeft() + getPaddingRight();   // 根据传入方式计算宽
            else
                result += getPaddingTop() + getPaddingBottom();   // 根据传入方式计算高
        }
        return result;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mRadarRadius = Math.min(w / 2, h / 2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mRadarRadius, mRadarRadius);//将画板移动到屏幕的中心点
        mRadarBgPaint.setShader(null);
        canvas.drawCircle(0, 0, mRadarRadius, mRadarBgPaint);  //绘制底色(默认为黑色)，可以使雷达的线看起来更清晰

        for (int i = 1; i <= mRadarCircleCount; i++) {     //根据用户设定的圆个数进行绘制
            canvas.drawCircle(0, 0, (float) (i * 1.0 / mRadarCircleCount * mRadarRadius), mRadarCirclePaint);  //画圆圈
        }

        canvas.drawLine(-mRadarRadius, 0, mRadarRadius, 0, mRadarCirclePaint);  //绘制雷达基线 x轴
        canvas.drawLine(0, mRadarRadius, 0, -mRadarRadius, mRadarCirclePaint);  //绘制雷达基线 y轴

        //设置颜色渐变从透明到不透明
        mRadarBgPaint.setShader(radarShader);
        canvas.concat(matrix);
        canvas.drawCircle(0, 0, mRadarRadius, mRadarBgPaint);
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            rotateAngel += 3;
            postInvalidate();

            matrix.reset();
            matrix.preRotate(rotateAngel, 0, 0);  //旋转
            mHandler.sendEmptyMessageDelayed(MSG_WHAT, DELAY_TIME);
        }
    };


    public void startScan() {
        mHandler.removeMessages(MSG_WHAT);
        mHandler.sendEmptyMessage(MSG_WHAT);
    }

    public void stopScan() {
        mHandler.removeMessages(MSG_WHAT);
    }
}

package com.ip.rishi.deadline.CustomUIClasses;

import android.view.View;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

import com.ip.rishi.deadline.R;

/**
 * Created by Apple on 02/03/16.
 */
public class TimerView extends View {
    public static final String TAG = "TimerView";

    private Context mContext;
    private int mBackgroundColour, mBackgroundWidth, mPrimaryColour, mPrimaryWidth, mRegularTextSize, mPercentTextSize;
    private Paint mArcPaintBackground, mArcPaintPrimary, mTargetMarkPaint, mRegularText, mPercentText, mTargetText, mTitleText;
    private float mPadding, mProgressPercent = 30, mTarget = 30;
    private long mProgressValue = 0;
    private RectF mDrawingRect;
    private static double M_PI_2 = Math.PI / 2;
    //MeterColour
    private int meterColour;
    //TargetColour
    private int targetColour;

    public TimerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();

    }

    private void init() {
        Resources resources = mContext.getResources();
        float density = resources.getDisplayMetrics().density;
        meterColour = resources.getColor(R.color.accent);

        targetColour = resources.getColor(R.color.meterGauge);
        mBackgroundColour = resources.getColor(R.color.meterBackground);
        mBackgroundWidth = (int) (5 * density);
        mPrimaryColour = meterColour;
        mPrimaryWidth = (int) (5 * density);
        mRegularTextSize = (int) (mBackgroundWidth * 10);
        mPercentTextSize = (int) (mBackgroundWidth * 3);
        mArcPaintBackground = new Paint() {
            {
                setDither(true);
                setStyle(Style.STROKE);
                setStrokeCap(Cap.SQUARE);
                setStrokeJoin(Join.BEVEL);
                setAntiAlias(true);
            }
        };
        mArcPaintBackground.setColor(mBackgroundColour);
        mArcPaintBackground.setStrokeWidth(mBackgroundWidth);
        mArcPaintPrimary = new Paint() {
            {
                setDither(true);
                setStyle(Style.STROKE);
                setStrokeCap(Cap.ROUND);
                setStrokeJoin(Join.ROUND);
                setAntiAlias(true);
            }
        };
        mArcPaintPrimary.setColor(mPrimaryColour);
        mArcPaintPrimary.setStrokeWidth(mPrimaryWidth);
        float maxWidth = mBackgroundWidth;
        mPadding = maxWidth / 2;
        Typeface typeface = Typeface.create("Helvetica", Typeface.NORMAL);
        mRegularText = new Paint();
        mRegularText.setColor(resources.getColor(R.color.meterGauge));
        mRegularText.setTextSize(mRegularTextSize);
        mRegularText.setTextAlign(Paint.Align.CENTER);
        mRegularText.setTypeface(typeface);

    }

    public void setProgress(float progress)
    {
        mProgressPercent = progress;
        mTarget = progress;
        invalidate();
        requestLayout();
    }
    public void setProgressValue(long value)
    {
        mProgressValue = value/1000;
        invalidate();
        requestLayout();
    }
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        canvas.drawArc(mDrawingRect, 270, 260, false, mArcPaintBackground);
        float progress = mProgressPercent;
        if (mProgressPercent > 100)
        {
            progress = 100f;
        }
        canvas.drawArc(mDrawingRect, 270, 360 * (progress/100f), false, mArcPaintPrimary);
        float radius;
        if (mDrawingRect.width() <= mDrawingRect.height())
        {
            radius = mDrawingRect.width()/2;
        }
        else
        {
            radius = mDrawingRect.height()/2;
        }
        float target = mTarget;
        long hours=mProgressValue/3600;
        long minutes=(mProgressValue%3600)/60;
        long seconds= (long) ((mProgressValue%3600)%60);
        String valueString = hours+":"+minutes+":"+seconds;
        canvas.drawText(valueString, mDrawingRect.centerX(), mDrawingRect.centerY() * 1.15f, mRegularText);
    }

    protected void onMeasure(int widthMeasure, int heightMeasure)
    {
        super.onMeasure(widthMeasure, heightMeasure);
        int size = 0;
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        int widthWithoutPadding = width - getPaddingLeft() - getPaddingRight();
        int heightWithoutPadding = height - getPaddingTop() - getPaddingBottom();
        if (widthWithoutPadding > heightWithoutPadding)
        {
            size = widthWithoutPadding;
        }
        else
        {
            size = heightWithoutPadding;
        }
        setMeasuredDimension(size + getPaddingLeft() + getPaddingRight(), size + getPaddingTop() + getPaddingBottom());

    }
    protected void onSizeChanged(int width, int height, int oldWidth, int oldHeight)
    {
        super.onSizeChanged(width, height, oldWidth, oldHeight);
        mDrawingRect = new RectF(mPadding + getPaddingLeft(), mPadding + getPaddingTop(), width - mPadding - getPaddingRight(), height - mPadding - getPaddingBottom());
    }

}

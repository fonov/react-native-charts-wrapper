package com.github.wuxudong.rncharts.markers;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.widget.RelativeLayout;

import com.github.mikephil.charting.components.IMarker;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;

public class RNPointMarkerView extends RelativeLayout implements IMarker {

    private MPPointF mOffset = new MPPointF();
    private MPPointF mOffset2 = new MPPointF();
    private float radius;
    private float borderWidth;
    private float shadowBlur;
    private int shadowColor;
    private int backgroundColor;
    private int borderColor;

    public RNPointMarkerView(Context context, float radius, float shadowBlur, float borderWidth, int shadowColor, int backgroundColor, int borderColor) {
        super(context);

        this.radius = radius;
        this.borderWidth = borderWidth;
        this.shadowBlur = shadowBlur;
        this.shadowColor = shadowColor;
        this.backgroundColor = backgroundColor;
        this.borderColor = borderColor;
    }

    @Override
    public void draw(Canvas canvas, float posX, float posY) {
        int saveId = canvas.save();

        // translate to the correct position and draw
        canvas.translate(posX, posY);

        Paint drawPaint = new Paint();
        drawPaint.setColor(this.borderColor);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeWidth(this.borderWidth);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);
        drawPaint.setShadowLayer(this.shadowBlur, 0, 0, this.shadowColor);

        Paint drawInnerPaint = new Paint();
        drawInnerPaint.setColor(this.backgroundColor);
        drawInnerPaint.setStyle(Paint.Style.FILL);

        canvas.drawCircle(0, 0, this.radius, drawPaint);
        canvas.drawCircle(0, 0, this.radius, drawInnerPaint);

        draw(canvas);

        canvas.restoreToCount(saveId);
    }

    @Override
    public MPPointF getOffset() {
        return mOffset;
    }

    @Override
    public MPPointF getOffsetForDrawingAtPoint(float posX, float posY) {
        mOffset.x = posX;
        mOffset.y = posY;

        return mOffset2;
    }

    @Override
    public void refreshContent(Entry e, Highlight highlight) {

    }
}



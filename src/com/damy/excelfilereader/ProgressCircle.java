package com.damy.excelfilereader;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.View;
import java.util.ArrayList;
import java.util.Arrays;

@SuppressLint("DrawAllocation")
public class ProgressCircle extends View {

    int _count;
    int _clr_red, _clr_green, _clr_blue;
    int _pointer;

    String _text;

    ArrayList<Integer> alpha_array = new ArrayList<Integer>(Arrays.asList(70, 70, 60, 60, 40, 30, 20, 10));

    public ProgressCircle(Context context)
    {
        super(context);
        _pointer = 0;
        _count = 8;

        _clr_red = 255;
        _clr_green = 255;
        _clr_blue = 255;

        _text = "";
    }
    
    public ProgressCircle(Context context, AttributeSet arg0)
    {
    	super(context, arg0);
    	_pointer = 0;
        _count = 8;

        _clr_red = 255;
        _clr_green = 255;
        _clr_blue = 255;

        _text = "";
    	return;
    }
    
    public ProgressCircle(Context context, AttributeSet arg0, int arg1)
    {
    	super(context, arg0, arg1);
    	_pointer = 0;
        _count = 8;

        _clr_red = 255;
        _clr_green = 255;
        _clr_blue = 255;

        _text = "";
    	return;
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        canvas.drawColor(Color.TRANSPARENT);

        canvas.save();

        if (_count == 0)
            return;

        if (_pointer > _count)
            _pointer = _pointer % _count;

        RectF bounds;
        if (this.getWidth() > this.getHeight())
            bounds = new RectF(this.getWidth() / 2 - this.getHeight() / 2, 0, this.getHeight(), this.getHeight());
        else
            bounds = new RectF(0, this.getHeight() / 2 - this.getWidth() / 2, this.getWidth(), this.getWidth());

        float fAngle = 360.f / _count;
        float fMarginAngle = fAngle / 5;

        float fStartAngle, fSweepAngle;

        Paint cur_paint = new Paint(), text_paint = new Paint();
        cur_paint.setAntiAlias(true);

        text_paint.setColor(Color.WHITE);
        text_paint.setTextAlign(Paint.Align.CENTER);
        text_paint.setTextSize(18);

        float coreX = bounds.left + bounds.width() / 2;
        float coreY = bounds.top + bounds.height() / 2;
        float out_radius = bounds.width() / 2;
        float in_radius = out_radius / 4 * 3;

        Path largePath = new Path(), smallPath = new Path();
        largePath.addCircle(coreX, coreY, out_radius, Path.Direction.CW);
        smallPath.addCircle(coreX, coreY, in_radius, Path.Direction.CW);

        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        canvas.clipPath(largePath);
        canvas.clipPath(smallPath, Region.Op.XOR);

        int text_xPos = 0, text_yPos = 0;

        ArrayList<Integer> arrCurAlphas = new ArrayList<Integer>();
        for (int i = _pointer; i < _count + _pointer; i++)
        {
            arrCurAlphas.add(alpha_array.get(i % _count));
        }

        for (int i = 0; i < _count; i++)
        {
            cur_paint.setARGB(arrCurAlphas.get(i), _clr_red, _clr_green, _clr_blue);
            fStartAngle = i * fAngle + fMarginAngle / 2;
            fSweepAngle = fAngle - fMarginAngle;

            canvas.drawArc(new RectF(coreX - out_radius, coreY - out_radius, coreX + out_radius, coreY + out_radius), fStartAngle, fSweepAngle, true, cur_paint);
        }

        canvas.restore();

        text_xPos = (int)coreX;
        text_yPos = (int) (coreY - ((text_paint.descent() + text_paint.ascent()) / 2)) ;
        canvas.drawText(_text, text_xPos, text_yPos, text_paint);

    }

    public void setParams(int count, String text, int clr_red, int clr_green, int clr_blue)
    {
        _count = count;
        _text = text;
        _clr_red = clr_red;
        _clr_green = clr_green;
        _clr_blue = clr_blue;
    }

    public void setPointer(int pointer)
    {
        _pointer = pointer;
    }

    public void incPointer()
    {
        _pointer++;
        if (_pointer == _count)
            _pointer = 0;
        invalidate();
    }
}

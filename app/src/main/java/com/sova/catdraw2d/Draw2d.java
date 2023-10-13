package com.sova.catdraw2d;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;

public class Draw2d extends View {
    private Paint mPaint = new Paint();
    private Rect mRect = new Rect();
    private Bitmap mBitmap;
    private Bitmap myBitmap;
    float touchX = 0;
    float touchY = 0;

    public Draw2d(Context context) {
        super(context);

        // Выводим значок из ресурсов
        Resources res = this.getResources();
        myBitmap = BitmapFactory.decodeResource(res, R.drawable.cat_step);
        mBitmap = Bitmap.createScaledBitmap(myBitmap,
                myBitmap.getWidth() / 5,
                myBitmap.getHeight() / 5,
                true);
        // mBitmap.setWidth(mBitmap.getWidth()/5);
        //mBitmap.setHeight(mBitmap.getHeight()/5);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = canvas.getWidth();
        int height = canvas.getHeight();

        // стиль Заливка
        mPaint.setStyle(Paint.Style.FILL);

        // закрашиваем холст белым цветом
        mPaint.setColor(Color.WHITE);
        canvas.drawPaint(mPaint);

        // Рисуем жёлтый круг
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.YELLOW);
        canvas.drawCircle(width - 100, 100, 100, mPaint);

        // Для рисования зелёного прямоугольника
        mPaint.setColor(Color.GREEN);
        canvas.drawRect(0, canvas.getHeight() - 500, width, height, mPaint);

        // Рисуем текст
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(48);
        canvas.drawText("Лужайка только для йоги", 30, height - 75, mPaint);

        // Текст под углом
        int x = width - 350;
        int y = 350;

        mPaint.setColor(Color.MAGENTA);
        mPaint.setTextSize(32);
        String str2rotate = "Лучик солнца!";

        canvas.save();
// Создаём ограничивающий прямоугольник для наклонного текста
// поворачиваем холст по центру текста
        canvas.rotate(-45, x + mRect.exactCenterX(), y + mRect.exactCenterY());

// Рисуем текст
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawText(str2rotate, x, y, mPaint);

// восстанавливаем холст
        canvas.restore();

        if (touchY >= canvas.getHeight() - 750) {
            myBitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.cat_gym);
            mBitmap = Bitmap.createScaledBitmap(myBitmap,
                    myBitmap.getWidth() / 5,
                    myBitmap.getHeight() / 5,
                    true);
        }
        else {
            myBitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.cat_step);
            mBitmap = Bitmap.createScaledBitmap(myBitmap,
                    myBitmap.getWidth() / 5,
                    myBitmap.getHeight() / 5,
                    true);
        }
        // Выводим изображение
        //   canvas.drawBitmap(mBitmap, width - mBitmap.getWidth()+250, height - mBitmap.getHeight() - 10, mPaint);
        canvas.drawBitmap(mBitmap, touchX, touchY, null);
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            touchX = event.getX() - mBitmap.getWidth() / 2;
            touchY = event.getY() - mBitmap.getHeight() / 2;
            invalidate();
        }

        return true;
    }
}

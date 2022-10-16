package com.sdk.bigtextshower.customview;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Build;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

import com.sdk.bigtextshower.R;

public class MyLedView extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    private SurfaceHolder surfaceHolder;
    private Paint mPaint;
    private boolean loop = true;
    private float x = 0;
    private float txtWidth;
    private int screenWidth;
    private int screenHeight;
    private boolean isDrawBg = false;
    private Handler handler;

    private String txt = "Hello ❤️";
    private float textSize = 220f;
    private String backgroundColor = "#FF000000";
    private int font = R.font.baloo;
    private String textColor = "#E91E63";
    private int textSpeed = 200;

    public MyLedView(Context context) {
        super(context);
        init();
    }

    public MyLedView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyLedView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public void init() {
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        setZOrderOnTop(true);
        surfaceHolder.setFormat(PixelFormat.TRANSPARENT);
        setBackgroundColor(Color.parseColor(backgroundColor));
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        WindowManager manager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        screenWidth = outMetrics.widthPixels;
        screenHeight = outMetrics.heightPixels;
    }

    public void surfaceCreated(SurfaceHolder holder) {
        x = getWidth();
        new Thread(this).start();
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) { }

    public void surfaceDestroyed(SurfaceHolder holder) {
        loop = false;
    }

    public void run() {
        while (loop) {
            synchronized (surfaceHolder) {
                draw();
            }
            try{
                Thread.sleep(10);
            }catch(InterruptedException ex){
                Log.e("TextSurfaceView",ex.getMessage()+"\n"+ex);
            }
        }
    }



    private Bitmap drawText() {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.parseColor(textColor));
        paint.setTextSize(textSize);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setShadowLayer(8, 0, 0, Color.rgb(255, 255, 0));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            paint.setTypeface(getContext().getResources().getFont(font));
        }
        Paint.FontMetrics fm = paint.getFontMetrics();
        float height = (float) Math.ceil(fm.descent - fm.top);
        float width = paint.measureText(txt);
        float scale = (screenHeight - 100) / height;
        Bitmap bitmap = Bitmap.createBitmap((int) Math.floor(width), (int) Math.floor(height), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        int xPos = (canvas.getWidth() / 2);
        int yPos = (int) ((canvas.getHeight() / 2) - ((paint.descent() + paint.ascent()) / 2)) ;
        canvas.drawText(txt, xPos, yPos, paint);
        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    private void draw() {
        int width = getWidth();
        int height = getHeight();
        Canvas canvas = surfaceHolder.lockCanvas();
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);

        Bitmap txt = drawText();
        txtWidth = txt.getWidth();

        canvas.drawBitmap(txt, x, 50, mPaint);
        canvas.saveLayer(0, 0, width, height, null, Canvas.ALL_SAVE_FLAG);

        mPaint.setARGB(255, 255, 255, 255);

        int lines = (int) Math.floor(width / 10f);
        int column = (int) Math.floor(width / 10f);
        for (int i = 0; i < lines; i++) {
            int y = i == 0 ? 4 : 4 + i * 10;
            canvas.drawCircle(4, y, 4, mPaint);
            for (int n = 1; n < column; n++) {
                int x = 4 + n * 10;
                canvas.drawCircle(x, y, 4, mPaint);
            }
        }

        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));
        mPaint.setARGB(255, 0, 0, 0);
        canvas.drawRect(0, 0, width, width, mPaint);

        surfaceHolder.unlockCanvasAndPost(canvas);

        if (x < -txtWidth) {
            x = width;
        } else {
            x -= textSpeed;
        }
    }

    public void setText(String text) {
        this.txt = text;
    }

    public void setFont(int font) {
        this.font = font;
    }

    public void setTextColor(String color) {
        this.textColor = color;
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
    }
    public void setBackgroundColor(String color) {
        this.backgroundColor = color;
    }
    public void setTextSpeed(int speed) {
        this.textSpeed = speed;
    }
}
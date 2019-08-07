package com.example.www.sanam;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private Canvas mCanvas;
    private Paint mPaint = new Paint();
    private Paint mPaintText = new Paint(Paint.UNDERLINE_TEXT_FLAG);
    private Bitmap mBitmap;
    private ImageView imageView;
    private Rect mRect = new Rect();
    private Rect mBounds = new Rect();

    private static final int OFFSET = 120;
    private int mOffset = OFFSET;
    private static final int MULTIPLIER = 100;

    private int mColorBackGROUND;
    private int mColorRectangle;
    private int mColorAccent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mColorBackGROUND = ResourcesCompat.getColor(getResources(),R.color.colorBackground,null);
        mColorRectangle = ResourcesCompat.getColor(getResources(),R.color.colorRectangle,null);
        mColorAccent = ResourcesCompat.getColor(getResources(),R.color.colorAccent,null);

        mPaint.setColor(mColorBackGROUND);
        mPaintText.setColor(ResourcesCompat.getColor(getResources(),R.color.colorPrimaryDark,null));
        mPaintText.setTextSize(70);

        imageView = (ImageView)findViewById(R.id.myImageView);
    }

    public void drawSomething(View view){
        int vWidth = view.getWidth();
        int hHeight = view.getHeight();
        int halfWidth = vWidth/2;
        int halfHeight = hHeight/2;

        if(mOffset == OFFSET){

            mBitmap = Bitmap.createBitmap(vWidth,hHeight,Bitmap.Config.ARGB_8888);
            imageView.setImageBitmap(mBitmap);
            mCanvas = new Canvas(mBitmap);
            mCanvas.drawColor(mColorBackGROUND);
            mCanvas.drawText(getString(R.string.keep_tapping),100,100,mPaintText);
            mOffset+=OFFSET;

        }else{
            if(mOffset<halfWidth && mOffset<halfHeight){
              mPaint.setColor(mColorRectangle-MULTIPLIER*mOffset);
              mRect.set(mOffset,mOffset,vWidth-mOffset,hHeight-mOffset);
              mCanvas.drawRect(mRect,mPaint);
              mOffset+=OFFSET;
            }else{
                mPaint.setColor(mColorAccent);
                mCanvas.drawCircle(halfWidth,halfHeight,halfWidth/3,mPaint);
                String text = getString(R.string.done);
                mPaintText.getTextBounds(text,0,text.length(),mBounds);
                int x = halfWidth-mBounds.centerX();
                int y = halfHeight - mBounds.centerY();
                mCanvas.drawText(text,x,y,mPaintText);
            }
        }
        view.invalidate();
    }
}

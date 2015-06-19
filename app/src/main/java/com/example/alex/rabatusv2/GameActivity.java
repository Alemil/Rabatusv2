package com.example.alex.rabatusv2;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created by Mathias Linde on 18-06-2015.
 */
public class GameActivity extends Activity{

    // The Main view
    private RelativeLayout mFrame;

    // Striker image's bitmap
    private Bitmap mBitmap;

    // Display dimensions
    private int mDisplayWidth, mDisplayHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);

        mFrame = (RelativeLayout) findViewById(R.id.frame);
           mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.omnom_striker);
        StrikerView strikerView = new StrikerView(getApplicationContext());
        mFrame.addView(strikerView);
        strikerView.start();

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {

            // Get the size of the display so this View knows where borders are
            mDisplayWidth = mFrame.getWidth();
            mDisplayHeight = mFrame.getHeight();

        }
    }

    public class StrikerView extends View {

        private static final int BITMAP_SIZE = 64;
        private static final int REFRESH_RATE = 40;
        private final Paint mPainter = new Paint();

        private float mPosX, mPosY;
        private Bitmap mScaledBitmap;
        private int mScaledBitmapWidth, mScaledBitmapHeight;
        private ScheduledFuture<?> mMoverFuture;

        public StrikerView(Context context) {
            super(context);

            //Remember that the phone is in horizontal mode.
            //This means that Display height is horizontal.
            //The bitmap uses standard height = vertical

            //Need a different size for different screen sizes, test until at good ratio is found
            mScaledBitmapWidth = mDisplayHeight/8;
            mScaledBitmapHeight = mDisplayWidth/8;

            mScaledBitmap = Bitmap.createScaledBitmap(mBitmap, mScaledBitmapWidth,
                    mScaledBitmapHeight, false);

            mPosX = 0;
            mPosY = mDisplayHeight/2-mScaledBitmapWidth/2;

        }

        public void drawCanvas(Canvas canvas) {

            canvas.save();

            canvas.drawBitmap(mScaledBitmap, mPosX, mPosY, mPainter);

            canvas.restore();
        }

        private void start() {

            // Creates a WorkerThread
            ScheduledExecutorService executor = Executors
                    .newScheduledThreadPool(1);

            // Execute the run() in Worker Thread every REFRESH_RATE
            // milliseconds
            // Save reference to this job in mMoverFuture
            mMoverFuture = executor.scheduleWithFixedDelay(new Runnable() {
                @Override
                public void run() {

                    changeXYPos();
                    postInvalidate();
                }

            }, 0, REFRESH_RATE, TimeUnit.MILLISECONDS);
        }

        public void changeXYPos() {
            //Do sensor shit
        }
    }
}

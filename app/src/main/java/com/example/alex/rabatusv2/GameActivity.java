package com.example.alex.rabatusv2;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created by Mathias Linde on 18-06-2015.
 */
public class GameActivity extends Activity implements SensorEventListener {

    // The Main view
    private RelativeLayout mFrame;

    // Striker image's bitmap
    private Bitmap mBitmap;

    // Display dimensions
    private int mDisplayWidth, mDisplayHeight;

    private static final int UPDATE_THRESHOLD = 500;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private long mLastUpdate;
    private GestureDetector mGestureDetector;
    private int gameStarted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(new GameView(getApplicationContext()));
        setContentView(R.layout.game_activity);

        mFrame = (RelativeLayout) findViewById(R.id.frame);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.omnom_striker);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        if (null == (mAccelerometer = mSensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER))) finish();

    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mAccelerometer,
                SensorManager.SENSOR_DELAY_UI);

        mLastUpdate = System.currentTimeMillis();

        setupGestureDetector();

    }

    @Override
    protected void onPause() {
        mSensorManager.unregisterListener(this);
        super.onPause();
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

    // Set up GestureDetector
    private void setupGestureDetector() {

        mGestureDetector = new GestureDetector(this,

                new GestureDetector.SimpleOnGestureListener() {

                    @Override
                    public boolean onSingleTapConfirmed(MotionEvent event) {
                        if(gameStarted == 0) {
                            gameStarted = 1;
                            StrikerView strikerView = new StrikerView(getApplicationContext());
                            mFrame.addView(strikerView);
                            strikerView.start();
                            return true;
                        }
                        return false;
                    }
                });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return mGestureDetector.onTouchEvent(event);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public class StrikerView extends View {

        private static final int BITMAP_SIZE = 64;
        private static final int REFRESH_RATE = 1000;
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

            mScaledBitmapWidth = mDisplayHeight/10;
            mScaledBitmapHeight = mDisplayWidth/10;

            mScaledBitmap = Bitmap.createScaledBitmap(mBitmap,mScaledBitmapWidth,
                    mScaledBitmapHeight, false);

            mPosX = 100;
            mPosY = mDisplayHeight/2-mScaledBitmapWidth/2;

        }

        public void onDraw(Canvas canvas) {
            Log.v("tryRun","Entered onDraw");
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
                    Log.v("tryRun","Entered run");
                    changeXYPos();
                    postInvalidate();
                }

            }, 0, REFRESH_RATE, TimeUnit.MILLISECONDS);
        }

        public void changeXYPos() {
            if(!borderHit()) {
                mPosY+=10;
            }
        }

        public boolean borderHit() {
            if(1+mPosY+mScaledBitmapWidth >= mDisplayHeight && false) {
                mPosY = mDisplayHeight-mScaledBitmapHeight;
                return true;
            }
            if(1-mPosY <= 0 && false) {
                mPosY = 0;
                return true;
            }
            Log.v("tryRun", String.valueOf(mDisplayHeight));
            return false;
        }

    }
}

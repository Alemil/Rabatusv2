package com.example.alex.rabatusv2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created by Mathias Linde on 18-06-2015.
 */
public class GameActivity extends Activity {

    private static final int REFRESH_RATE = 20;
    private static final int STRIKER_COLLISION = 1;
    private static final int BOTTOM_COLLISION = 2;
    private static final int NO_COLLISION = 0;
    private static final String DEBUG_TAG = "yolo";

    private GestureDetectorCompat mDetector;

    // The Main view
    private RelativeLayout mFrame;

    // Striker image's bitmap
    private Bitmap mBitmapStriker;
    private Bitmap mBitmapBlock;

    // Display dimensions
    private int mDisplayWidth, mDisplayHeight;

    private boolean gameStarted = false;
    private boolean isStillDown = false;
    private boolean paused = false;

    private float pressXPos;
    private TextView textView;
    private StrikerView strikerView;

    private int points = 0;
    private int lives = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);

        final SharedPreferences sp = getSharedPreferences("setting", MODE_PRIVATE);
        mFrame = (RelativeLayout) findViewById(R.id.frame);
        mFrame.setBackgroundColor(sp.getInt("bgcolor", 0xFF00FF));

        textView = (TextView) mFrame.getChildAt(0);

        mBitmapStriker = BitmapFactory.decodeResource(getResources(), R.drawable.omnom_striker);
        mBitmapBlock = BitmapFactory.decodeResource(getResources(), R.drawable.logo_image);

        mDetector = new GestureDetectorCompat(this, new MyGestureListener());

        Button pauseButton = (Button) findViewById(R.id.pause_button);

        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPausedDialog();
            }
        });
    }

    @Override
    public void onBackPressed() {

    }

    public void getPausedDialog() {
        paused = true;
        AlertDialog.Builder menu = new AlertDialog.Builder(GameActivity.this);
        menu.setTitle("Paused Menu");
        menu.setCancelable(false);

        menu.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                paused = false;

                dialog.dismiss();
            }
        });

        menu.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finish();
            }
        });
        menu.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(paused) getPausedDialog();
    }

    @Override
    protected void onPause() {
        paused = true;
        super.onPause();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            // Get the size of the display so this layout knows where borders are
            mDisplayWidth = mFrame.getWidth();
            mDisplayHeight = mFrame.getHeight();

        }
    }

    private void updateText() {
        textView.setText("Score: " + points + "  Lives " + lives);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){

        this.mDetector.onTouchEvent(event);

        int action = MotionEventCompat.getActionMasked(event);

        pressXPos = event.getX();

        switch(action) {
            case (MotionEvent.ACTION_DOWN) :
                Log.d(DEBUG_TAG,"Action was DOWN");
                if(gameStarted) isStillDown = true;
                return true;
            case (MotionEvent.ACTION_MOVE) :
                Log.d(DEBUG_TAG,"Action was MOVE");
                return true;
            case (MotionEvent.ACTION_UP) :
                Log.d(DEBUG_TAG,"Action was UP");
                isStillDown = false;
                return true;
            case (MotionEvent.ACTION_CANCEL) :
                Log.d(DEBUG_TAG,"Action was CANCEL");
                return true;
            case (MotionEvent.ACTION_OUTSIDE) :
                Log.d(DEBUG_TAG,"Movement occurred outside bounds " +
                        "of current screen element");
                return true;
            default :
                return super.onTouchEvent(event);
        }
    }

    public class StrikerView extends View {

        private final Paint mPainter = new Paint();

        private float mPosX, mPosY;
        private Bitmap mScaledBitmap;
        private int mScaledBitmapWidth, mScaledBitmapHeight;
        private ScheduledFuture<?> mMoverFuture;
        private FallingBlockView fBV;

        public StrikerView(Context context) {
            super(context);

            //Need a different size for different screen sizes, test until at good ratio is found

            mScaledBitmapWidth = mDisplayWidth / 10;
            mScaledBitmapHeight = mDisplayHeight / 10;

            mScaledBitmap = Bitmap.createScaledBitmap(mBitmapStriker, mScaledBitmapWidth,
                    mScaledBitmapHeight, false);

            mPosX = mDisplayWidth / 2 - mScaledBitmapWidth / 2;
            mPosY = mDisplayHeight - mScaledBitmapHeight;

            fBV = new FallingBlockView(getApplicationContext());

        }

        public void onDraw(Canvas canvas) {
            canvas.save();

            canvas.drawBitmap(mScaledBitmap, mPosX, mPosY, mPainter);
            canvas.drawBitmap(fBV.mScaledBitmap,fBV.mPosX,fBV.mPosY,mPainter);

            canvas.restore();
        }

        private void start() {

            // Creates a WorkerThread
            ScheduledExecutorService executor = Executors
                    .newScheduledThreadPool(1);

            // Execute the run() in Worker Thread every REFRESH_RATE
            // milliseconds
            // Save reference to this job in mMoverFuture
            executor.scheduleWithFixedDelay(new Runnable() {
                @Override
                public void run() {
                    //Log.v("tryRun", "Entered run");
                    if (!paused) {
                        tryLongPress();

                        if (collision() == NO_COLLISION) {
                            //Log.v("bla","NO COL");
                            fBV.mPosY += 8;
                        } else if (collision() == STRIKER_COLLISION) {
                            Log.v("bla", "STRIKER COL");

                            mFrame.post(new Runnable() {
                                @Override
                                public void run() {

                                    mFrame.removeView(fBV);
                                    fBV = new FallingBlockView(getApplicationContext());
                                    mFrame.addView(fBV);
                                    points++;
                                    updateText();
                                    if(points == 10) {
                                        finishedGame();
                                    }
                                }
                            });

                        } else {
                            mFrame.post(new Runnable() {
                                @Override
                                public void run() {

                                    mFrame.removeView(fBV);
                                    fBV = new FallingBlockView(getApplicationContext());
                                    mFrame.addView(fBV);
                                    lives--;
                                    updateText();
                                    if(lives < 1) {
                                        finish();
                                    }
                                }
                            });

                        }
                        postInvalidate();
                    }

                }

            }, 0, REFRESH_RATE, TimeUnit.MILLISECONDS);
        }

        public void tryLongPress() {
            if(isStillDown) {
                Log.v("Moved","I should move");
                if (pressXPos < mDisplayWidth / 2) {
                    strikerView.changeXPos(-30);
                } else {
                    strikerView.changeXPos(30);
                }
            }
        }

        public void changeXPos(int n) {
            Log.v("Moved","I moved!");
            if (!borderHit(n)) {
                mPosX += n;
            }
        }

        public boolean borderHit(int bla) {
            if (mPosX + mScaledBitmapWidth + bla >= mDisplayWidth) {
                mPosX = mDisplayWidth - mScaledBitmapWidth;
                return true;
            }
            if (mPosX + bla <= 0) {
                mPosX = 0;
                return true;
            }
            return false;
        }

        private int collision() {
            if(rightCorner() || leftCorner()) {
                return STRIKER_COLLISION;
            }
            if(fBV.mPosY >= mDisplayHeight-5) {
                return BOTTOM_COLLISION;
            }
            return NO_COLLISION;
        }

        public boolean leftCorner() {
            return (fBV.mPosX > mPosX
                    && fBV.mPosX < mPosX + mScaledBitmapWidth
                    && fBV.mPosY+fBV.mScaledBitmapWidth > mPosY);
        }

        public boolean rightCorner() {
            return ((fBV.mPosX+fBV.mScaledBitmapWidth > mPosX)
                    && (fBV.mPosX+fBV.mScaledBitmapWidth < mPosX+mScaledBitmapWidth)
                    && (fBV.mPosY+fBV.mScaledBitmapWidth > mPosY));
        }

    }

    public class FallingBlockView extends View {

        private ScheduledFuture<?> mMoverFuture;
        private final Paint mPainter = new Paint();

        private float mPosX, mPosY;
        private Bitmap mScaledBitmap;
        private int mScaledBitmapWidth;

        public FallingBlockView(Context context) {
            super(context);
            Random r = new Random();
            mScaledBitmapWidth = mDisplayWidth / 16;

            mScaledBitmap = Bitmap.createScaledBitmap(mBitmapBlock, mScaledBitmapWidth,
                    mScaledBitmapWidth, false);

            mPosX = r.nextInt(mDisplayWidth+1-mScaledBitmapWidth);
            mPosY = -mScaledBitmapWidth-50;

        }

        private void stop() {
            //Log.v("bla","Entered stop");

            if (null != mMoverFuture && !mMoverFuture.isDone()) {
                mMoverFuture.cancel(true);
            }

            // This work will be performed on the UI Thread
            mFrame.post(new Runnable() {
                @Override
                public void run() {

                    createNewBlock();
                    mFrame.removeView(FallingBlockView.this);

                }
            });
        }

        private void createNewBlock() {
            FallingBlockView fallingBlockView = new FallingBlockView(getApplicationContext());
            mFrame.addView(fallingBlockView);
        }

        public void onDraw(Canvas canvas) {
            canvas.save();

            canvas.drawBitmap(mScaledBitmap, mPosX, mPosY, mPainter);

            canvas.restore();
        }

    }

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final String DEBUG_TAG = "Gestures";

        @Override
        public boolean onSingleTapConfirmed(MotionEvent event) {

            if (!gameStarted) {
                gameStarted = true;
                strikerView = new StrikerView(getApplicationContext());
                updateText();
                mFrame.addView(strikerView);
                mFrame.addView(strikerView.fBV);
                strikerView.start();
            }
            return true;
        }

    }

    private void finishedGame() {
        paused = true;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Congratulations! You've been awarded with a discount code! \n Please press continue to acquire it");
        builder.setCancelable(false);
        builder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent discountIntent = new Intent(GameActivity.this, DiscountListActivity.class);

                discountIntent.putExtra("SenderID", "From_GameActivity");
                finish();
                startActivity(discountIntent);

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }




}

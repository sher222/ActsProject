package com.rfproductions.acts_project;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.transition.TransitionManager;
import android.support.v4.view.GestureDetectorCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;

import static android.animation.ValueAnimator.INFINITE;
import static java.lang.Math.abs;
import static java.lang.Math.random;
import static java.security.AccessController.getContext;


public class WrongActivity extends Activity {
    public monster[] names;
    public ConstraintLayout display;
    public ImageView smileyPlayer;
    private GestureDetectorCompat mDetector;

    static public float playerX;
    static public float playerY;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        mDetector = new GestureDetectorCompat(this, new MyGestureListener());
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        super.onCreate(savedInstanceState);

        setTitle("hello");
        setContentView(R.layout.activity_wrong);

        display = (ConstraintLayout) findViewById(R.id.wrong_layout);


         smileyPlayer = new ImageView (this);
        smileyPlayer.setImageResource(R.drawable.test_char);

        int screenWidth = 1700;
        int screenHeight = 900;
        Log.d("width", String.valueOf(screenWidth));
        Log.d("height", String.valueOf(screenHeight));
        smileyPlayer.setX(screenWidth/2);
        smileyPlayer.setY(screenHeight);
        Log.d("position",String.valueOf(smileyPlayer.getX()));
        Log.d("position",String.valueOf(smileyPlayer.getY()));

        playerX=smileyPlayer.getX();
        playerY=smileyPlayer.getY();
        display.addView(smileyPlayer);

        Random rand=new Random();
        names=new monster[10];
        for (int i = 0; i < 10; i++) {
            names[i]=new monster();
            names[i].create(WrongActivity.this, 170*i);
            //Log.d("size",String.valueOf(width/10*i));
            int delay=rand.nextInt(20000);
            names[i].animate(delay);
        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }



    class MyGestureListener extends GestureDetector.SimpleOnGestureListener { // Listener for scroll


        private float movement = 50;


        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2,
                               float velocityX, float velocityY) {
            Log.d("onFling", String.valueOf(playerX)+","+String.valueOf(playerY));

            String DEBUG_TAG = "movement";
            if (event1.getRawX() < event2.getRawX() &&
                    event2.getRawX() - event1.getRawX() >
                            abs(event1.getRawY() - event2.getRawY())) {
                playerX=playerX+movement;
                smileyPlayer.setX(playerX);
                Log.d(DEBUG_TAG, "Left-to-right");

            } else if (event2.getRawX() < event1.getRawX() &&
                    event1.getRawX() - event2.getRawX() >
                            abs(event1.getRawY() - event2.getRawY())) {
                playerX=playerX-movement;
                smileyPlayer.setX(playerX);
                //horizontal.start();
                Log.d(DEBUG_TAG, "Right-to-left");
            } else if (event1.getRawY() < event2.getRawY() &&
                    event2.getRawY() - event1.getRawY() >
                            abs(event1.getRawX() - event2.getRawX())) {
                playerY=playerY+movement;
                smileyPlayer.setY(playerY);
                //vertical.start();
                Log.d(DEBUG_TAG, "Top-to-bottom");
            } else if (event2.getRawY() < event1.getRawY() &&
                    event1.getRawY() - event2.getRawY() >
                            abs(event1.getRawX() - event2.getRawX())) {
                playerY=playerY-movement;
                smileyPlayer.setY(playerY);
                //vertical.start();
                Log.d(DEBUG_TAG, "Bottom-to-top");
            }
            Log.d("pos", String.valueOf(playerX)+" "+String.valueOf(playerY));
            if (playerY<50){
                Intent goBack=new Intent(WrongActivity.this, LevelActivity.class);
                Intent intent=getIntent();
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                int number=Integer.parseInt(intent.getStringExtra("number"));
                goBack.putExtra("number", number+"");
                startActivity(goBack);
            }
            return true;


        }

    }

    class monster{

        public float currentX;
        public ImageView monsterClone;
        public void create(Context mContext, float x){
            currentX=x;
            monsterClone=new ImageView(mContext);
            monsterClone.setImageResource(R.drawable.fireball);

            //Log.d("monster", String.valueOf(i));
            // ConstraintLayout temp=new ConstraintLayout(this);

            //temp.setLayoutParams(new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.MATCH_PARENT));
            monsterClone.setY(-400);
            monsterClone.setX(x);

            display.addView(monsterClone);
        }
        //private float maxError=smileyPlayer.getWidth()/2+monsterClone.getWidth()/2;
        public float currentY;
        public void animate(final int delay){
            final ValueAnimator monsterMove=ValueAnimator.ofFloat(0f, 1000f);
            monsterMove.setDuration(20000);
            monsterMove.setRepeatCount(INFINITE);
            monsterMove.setStartDelay(delay);
            monsterMove.start();
            monsterMove.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    monsterClone.setTranslationY((float)valueAnimator.getAnimatedValue());
                    currentY=(float)valueAnimator.getAnimatedValue();

//                    Log.d("pos", "m:"+String.valueOf(monsterClone.getX())+","+String.valueOf(monsterClone.getY())+" p:"+String.valueOf(playerX)+" "+String.valueOf(playerY));
                        if (abs(currentX-playerX)<=180){
                            Log.d("crash", "x values are the same");
                            if (abs(currentY-playerY)<=180){
                                Log.d("died", "dead");

                                Intent intent=getIntent();
                                int number=Integer.parseInt(intent.getStringExtra("number"));
                                //change this so it shows a u died screen
                                Intent send=new Intent(WrongActivity.this, DiedActivity.class);
                                send.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(send);
                                finish();
                            }
                        }
                    }

                    //Log.d("animation", "y "+String.valueOf(currentY));

            });
            monsterMove.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {

                }

                @Override
                public void onAnimationEnd(Animator animator) {

                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {
                    Random hi=new Random();
                    int delay=hi.nextInt(2000000);
                    animator.setStartDelay(delay);
                }
            });
        }

        //returns y as a decimal from 0-1 the way constraint set does for the player
        public float getY(){
            return currentY;

        }

        public float getX(){
            return currentX;
        }

    }
}
package com.rfproductions.acts_project;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.transition.TransitionManager;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class LevelActivity extends AppCompatActivity {

    /* EDIT THIS! */
    private int number = 0; // Question number
    private String qText = "Hello world!"; // Question (e.g. 'Who was Jesus?')
    private String correct = "Yes!"; // Correct answer (e.g. 'The Son of God')
    private String[] wrong = {"Wrong", "Wrong", "Fake news"}; // Wrong answers
    // (e.g. {'Me', 'The sky', 'Our President'})

    /* Parameters */
    private int random = (int) (Math.random() * (wrong.length + 1)); // Random number between 0 and
    // length of wrong string (for correct insertion)
    private float[] vertB = {0.35f, 0.35f, 0.95f, 0.95f}; // Vertical constraints of four answers
    private float[] horizB = {0, 0.95f, 0, 0.95f}; // Horizontal constraints of four answers
    private ArrayList<String> bList = new ArrayList<>(); // Button list
    private GestureDetectorCompat mDetector; // Detect movement
    private ConstraintLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); // Force landscape
        this.setTitle("Level "+number); // Set top title
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);

        /* Views */
        layout = (ConstraintLayout) findViewById(R.id.level_layout);
        TextView numberV = (TextView) findViewById(R.id.number);
        TextView qTextV = (TextView) findViewById(R.id.qText);
        ImageView playerV = (ImageView) findViewById(R.id.player);

        /* Set view text */
        numberV.setText("Question "+number);
        qTextV.setText(qText);

        /* Add wrong answers */
        for (String s : wrong) {
            bList.add(s);
        }
        bList.add(random, correct); // Insert correct answer


        int i = 100;
        for (String s : bList) {
            ImageButton newB = new ImageButton(this); // Create new ImageButton
            newB.setBackgroundResource(R.drawable.green); // Set button picture to green.jpg
            newB.setId(i); // Set ID: so layout can identify button

            mDetector = new GestureDetectorCompat(this, new MyGestureListener());

            /* Set button height, width */
            Display display = getWindowManager().getDefaultDisplay();
            int width = (int) (display.getHeight() * 0.1); // 10% of screen height
            int height = (int) (display.getHeight() * 0.1);
            newB.setLayoutParams(new ConstraintLayout.LayoutParams(width, height));
            layout.addView(newB);

            /* Make constraints */
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(layout);

            constraintSet.connect(newB.getId(),
                    ConstraintSet.TOP, layout.getId(), ConstraintSet.TOP, 8);
            constraintSet.connect(newB.getId(),
                    ConstraintSet.BOTTOM, layout.getId(), ConstraintSet.BOTTOM, 8);
            constraintSet.connect(newB.getId(),
                    ConstraintSet.LEFT, layout.getId(), ConstraintSet.LEFT, 8);
            constraintSet.connect(newB.getId(),
                    ConstraintSet.RIGHT, layout.getId(), ConstraintSet.RIGHT, 8);
            constraintSet.setVerticalBias(newB.getId(), vertB[i - 100]);
            constraintSet.setHorizontalBias(newB.getId(), horizB[i - 100]);
            constraintSet.applyTo(layout);
            i++;

            /* Create toast (notification) when clicked */
            final String fs = s; // Why final? onClick cannot get a non-final string
            newB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = getApplicationContext();
                    CharSequence text = fs;
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            });
        }

        /* Display display = getWindowManager().getDefaultDisplay();
        int height = (int) (display.getHeight() * 0.08); // 8% of screen height
        playerV.setLayoutParams(new ConstraintLayout.LayoutParams(height, height)); */

    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        this.mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener { // Listener for scroll
        private static final String DEBUG_TAG = "Gestures";

        @Override
        public boolean onDown(MotionEvent event) {
            Log.d(DEBUG_TAG,"onDown");
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent event1, MotionEvent event2,
                               float velocityX, float velocityY) {
            /* Log.d(DEBUG_TAG, event1.getRawX()+"");
            Log.d(DEBUG_TAG, event2.getRawX()+""); */

            if (event1.getRawX() < event2.getRawX() &&
                    event2.getRawX() - event1.getRawX() >
                    Math.abs(event1.getRawY() - event2.getRawY())) {
                // TransitionManager.beginDelayedTransition(layout);
                Log.d(DEBUG_TAG, "Left-to-right");
            } else if (event2.getRawX() < event1.getRawX() &&
                    event1.getRawX() - event2.getRawX() >
                            Math.abs(event1.getRawY() - event2.getRawY())) {
                Log.d(DEBUG_TAG, "Right-to-left");
            } else if (event1.getRawY() < event2.getRawY() &&
                    event2.getRawY() - event1.getRawY() >
                            Math.abs(event1.getRawX() - event2.getRawX())) {
                Log.d(DEBUG_TAG, "Top-to-bottom");
            } else if (event2.getRawY() < event1.getRawY() &&
                    event1.getRawY() - event2.getRawY() >
                            Math.abs(event1.getRawX() - event2.getRawX())) {
                Log.d(DEBUG_TAG, "Bottom-to-top");
            }

            return true;
        }
    }
}

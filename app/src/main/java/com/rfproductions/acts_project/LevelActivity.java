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
import android.text.Layout;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

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
    private float[] horizB = {0.05f, 0.95f, 0.05f, 0.95f}; // Horizontal constraints of four answers
    private ArrayList<String> bList = new ArrayList<>(); // Button list
    private GestureDetectorCompat mDetector; // Detect movement
    private ConstraintLayout layout;
    private ConstraintLayout[] pops = new ConstraintLayout[wrong.length + 1];
    private float playerVert = 0.67f;
    private float playerHoriz = 0.5f;
    private float movement = 0.05f;

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
            int width = (int) (display.getWidth() * 0.05); // 5% of screen height
            int height = (int) (display.getWidth() * 0.05);
            newB.setLayoutParams(new ConstraintLayout.LayoutParams(width, height));
            layout.addView(newB);

            /* Create popup window */
            final int fi = i;
            pops[i-100] = new ConstraintLayout(this);
            pops[i-100].setId(i+50);
            pops[i-100].setLayoutParams(new ConstraintLayout.LayoutParams(0, 0));

            TextView answer = new TextView(this);
            answer.setId(i+100);
            answer.setText(s);

            ImageButton close = new ImageButton(this);
            close.setId(i+150);
            close.setImageResource(R.mipmap.x);

            close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pops[fi-100].setLayoutParams(new ConstraintLayout.LayoutParams(0, 0));
                    createConstraints(pops[fi-100], layout, horizB[fi-100], vertB[fi-100]);
                }
            });

            createConstraints(answer, pops[i-100], 0.5f, 0.5f);
            createConstraints(close, pops[i-100], 0, 1);

            /* Create popup when clicked */
            newB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("Answer: ", pops[fi-100].toString());
                    Display display = getWindowManager().getDefaultDisplay();
                    pops[fi-100].setLayoutParams(new ConstraintLayout.LayoutParams(
                            (int) (display.getWidth() * 0.8),
                            (int) (display.getHeight() * 0.5)));
                    createConstraints(pops[fi-100], layout, 0.5f, 0.5f);
                }
            });

            /* Make constraints */
            createConstraints(newB, layout, horizB[i-100], vertB[i-100]);
            createConstraints(pops[i-100], layout, horizB[i-100], vertB[i-100]);
            i++;

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
        public boolean onFling(MotionEvent event1, MotionEvent event2,
                               float velocityX, float velocityY) {

            if (event1.getRawX() < event2.getRawX() &&
                    event2.getRawX() - event1.getRawX() >
                    Math.abs(event1.getRawY() - event2.getRawY())) {
                TransitionManager.beginDelayedTransition(layout);
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(layout);
                constraintSet.setHorizontalBias(R.id.player, playerHoriz + movement);
                playerHoriz += movement;
                constraintSet.applyTo(layout);
                Log.d(DEBUG_TAG, "Left-to-right");
            } else if (event2.getRawX() < event1.getRawX() &&
                    event1.getRawX() - event2.getRawX() >
                            Math.abs(event1.getRawY() - event2.getRawY())) {
                TransitionManager.beginDelayedTransition(layout);
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(layout);
                constraintSet.setHorizontalBias(R.id.player, playerHoriz - movement);
                playerHoriz -= movement;
                constraintSet.applyTo(layout);
                Log.d(DEBUG_TAG, "Right-to-left");
            } else if (event1.getRawY() < event2.getRawY() &&
                    event2.getRawY() - event1.getRawY() >
                            Math.abs(event1.getRawX() - event2.getRawX())) {
                TransitionManager.beginDelayedTransition(layout);
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(layout);
                constraintSet.setVerticalBias(R.id.player, playerVert + movement);
                playerVert += movement;
                constraintSet.applyTo(layout);
                Log.d(DEBUG_TAG, "Top-to-bottom");
            } else if (event2.getRawY() < event1.getRawY() &&
                    event1.getRawY() - event2.getRawY() >
                            Math.abs(event1.getRawX() - event2.getRawX())) {
                TransitionManager.beginDelayedTransition(layout);
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(layout);
                constraintSet.setVerticalBias(R.id.player, playerVert - movement);
                playerVert -= movement;
                constraintSet.applyTo(layout);
                Log.d(DEBUG_TAG, "Bottom-to-top");
            }

            for (int i = 0; i < bList.size(); i++) {
                if (Math.abs(playerVert - vertB[i]) <= 0.1 &&
                        Math.abs(playerHoriz - horizB[i]) <= 0.1) {
                    if (bList.get(i).equals(correct)) {
                        Intent intent = new Intent(LevelActivity.this, CorrectActivity.class);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(LevelActivity.this, WrongActivity.class);
                        startActivity(intent);
                    }
                } else {
                    Log.d(DEBUG_TAG, "Player: " + playerHoriz + ", " + playerVert);
                    Log.d(DEBUG_TAG, "Button"+ i + ": " + horizB[i] + ", " + vertB[i]);
                }
            }

            return true;
        }
    }

    public void createConstraints(View v, ConstraintLayout l, float horiz, float vert) {

        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(l);

        constraintSet.connect(v.getId(),
                ConstraintSet.TOP, l.getId(), ConstraintSet.TOP, 8);
        constraintSet.connect(v.getId(),
                ConstraintSet.BOTTOM, l.getId(), ConstraintSet.BOTTOM, 8);
        constraintSet.connect(v.getId(),
                ConstraintSet.LEFT, l.getId(), ConstraintSet.LEFT, 8);
        constraintSet.connect(v.getId(),
                ConstraintSet.RIGHT, l.getId(), ConstraintSet.RIGHT, 8);
        constraintSet.setVerticalBias(v.getId(), vert);
        constraintSet.setHorizontalBias(v.getId(), horiz);
        constraintSet.applyTo(l);

    }
}

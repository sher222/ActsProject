package com.rfproductions.acts_project;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
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
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.logging.Level;

public class LevelActivity extends AppCompatActivity {

    /* EDIT THIS! */
    private int number = 0; // Question number
    private String[] qText = {"example",
            "What did Jesus promise to his disciples before ascending to heaven?",
            "Who was chosen to replace Judas as an apostle?",
            "What happened as the disciples received the Holy Spirit?",
            "Who arrested Peter and John as they preached after healing the lame man?",
            "Who did Peter and John heal at the Beautiful Gate?",
            "How did Ananias and Sapphira lie to the Holy Spirit?",
            "How did the imprisoned apostles appear in the temple?",
            "How did Stephen prove his point to the council?",
            "What was the name of the sorcerer that wanted to buy the power of the Holy Spirit?",
            "Which book of the bible was the Ethiopian eunuch reading?",
            "What was Tabitha's Greek name?",
            "Where is Simon Peter's house located?",
            "Why did Peter let the Gentiles into his home?",
            "Who imprisoned Peter and killed James?",
            "What did the disciples do before they went to Iconium?",
            "In Lystra, what did the people do as a result of a miracle?",
            "Were the believers happy that the Gentiles had come to know Jesus?",
            "Why did the Jews start a riot and persecute Paul and Silas?",
            "Why didn't the disciples at Ephesus receive the Holy Spirit?",
            "Why did Paul bid the Ephesians elder farewell?",}; // Question (e.g. 'Who was Jesus?')
    private String[] correct = {"Yes!", "The coming of the Holy Spirit",
            "Matthias", "They spoke in tongues to the people","The Roman guards",
            "A lame man", "They sold some property deceptively", "They were saved by an angel",
            "He used the history of Israel to prove his point", "Simon", "Isaiah", "Dorcas", "Joppa, near the sea",
            "He was commanded by God to do so", "King Herod", "Shake the dust off their feet",
            "All of the others", "Yes", "They didn't believe in what Paul and Silas said",
            "They received John's baptism", "They knew that none of them would ever see him again"
    }; // Correct answer (e.g. 'The Son of God')
    private ArrayList<String[]> wrong = new ArrayList<>(); // Wrong answers
    // (e.g. {'Me'The sky', 'Our President'})

    /* Parameters */
    private float[] vertB = {0.05f, 0.05f, 0.95f, 0.95f}; // Vertical constraints of four answers
    private float[] horizB = {0.05f, 0.95f, 0.05f, 0.95f}; // Horizontal constraints of four answers
    private ArrayList<String> bList = new ArrayList<>(); // Button list
    private GestureDetectorCompat mDetector; // Detect movement
    private ConstraintLayout layout;
    private ConstraintLayout[] pops;
    private float playerVert = 0.67f;
    private float playerHoriz = 0.5f;
    private float movement = 100f;
    private ArrayList<Wall> walls = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); // Force landscape
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);

        wrong.add(new String[]{"Wrong", "Not right", "No"});
        wrong.add(new String[]{"The defeat of the Roman Empire", "Happy days of rest", "He did not promise anything"});
        wrong.add(new String[]{"Paul", "Barnabas", "Judas was not replaced"});
        wrong.add(new String[]{"Their heads were slightly burnt", "They did not realize until later", "They thought it was the work of the devil and went mad"});
        wrong.add(new String[]{"The priests", "The captain of the temple guard", "The Sadducees"});
        wrong.add(new String[]{"A demon-possessed man", "A man blind from birth", "A man sick to the point of death"});
        wrong.add(new String[]{"They gave the Holy Spirit fake news", "They were frightened when asked about a sin",
            "They told the Holy Spirit they were not a part of the church"});
        wrong.add(new String[]{"They cleverly escaped with wisdom from the Holy Spirit",
                "They were brought out by their arresters to be insulted",
            "They were always preaching in the temple"});
        wrong.add(new String[]{"He flamed them until they gave up from embarrasment"});
        wrong.add(new String[]{"Saul", "Phillip", "Sirius"});
        wrong.add(new String[]{"Jeremiah", "Acts", "Deuteronomy"});
        wrong.add(new String[]{"Sapphira", "Hera", "Aeneas"});
        wrong.add(new String[]{"Joppa, in the mountains", "Damascus, near the sea", "Damascus, in the mountains"});
        wrong.add(new String[]{"He liked them very much", "He drank too much wine and mistakenly did so",
                "He was a Gentile and did not have to follow Jewish law"});
        wrong.add(new String[]{"The Jewish leaders", "Peter's jealous followers", "The Lord"});
        wrong.add(new String[]{"Feast with the Jews", "Baptized the Jewish leaders", "Nothing"});
        wrong.add(new String[]{"Sacrifice of the disciples", "Stone Paul", "Treat the disciples like gods"});
        wrong.add(new String[]{"No"});
        wrong.add(new String[]{"They were jealous", "They were just plain haters"});
        wrong.add(new String[]{"They weren't true Christians", "They didn't pray", "They weren't baptized"});
        wrong.add(new String[]{"He was going to commit suicide", "He felt like it"});
        try {
            Intent numIntent = getIntent();
            int num = Integer.parseInt(numIntent.getStringExtra("number"));
            number = num;
        } catch (Exception e) {
            number = 1;
        }
        if (number >= correct.length) {
            Intent intent = new Intent(LevelActivity.this, WinActivity.class);
            startActivity(intent);
        }

        this.setTitle("Question "+ number); // Set top title

        /* Views */
        pops = new ConstraintLayout[wrong.get(number).length + 1];
        layout = (ConstraintLayout) findViewById(R.id.level_layout);
        TextView numberV = (TextView) findViewById(R.id.number);
        TextView qTextV = (TextView) findViewById(R.id.qText);

        /* Set view text */
        numberV.setText("Question "+number);
        qTextV.setText(qText[number]);

        /* Add wrong answers */
        for (String s : wrong.get(number)) {
            bList.add(s);
        }
        bList.add(correct[number]); // Insert correct answer

        Collections.shuffle(bList);

        int count = 200;
        //getWalls();
        for (Wall w : walls) {
            ImageView wView = new ImageView(this);
            wView.setId(count);
            wView.setBackgroundColor(Color.BLACK);
            ConstraintLayout.LayoutParams lp = new ConstraintLayout.LayoutParams(w.x2 - w.x1,
                    w.y2 - w.y1);
            lp.setMargins(w.x1, w.y1, w.x2, w.y2);
            wView.setLayoutParams(lp);
            layout.addView(wView);
            createConstraints(wView, layout, 0, 0.5f);
            count++;
        }

        int i = 100;
        for (String s : bList) {
            Button newB = new Button(this); // Create new Button
            //newB.setBackgroundResource(R.drawable.green); // Set button picture to green.jpg
            newB.setId(i); // Set ID: so layout can identify button
            newB.setBackgroundColor(Color.rgb(34,139,34));
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
            pops[i-100].setVisibility(ConstraintLayout.INVISIBLE);
            pops[i-100].setBackgroundColor(Color.LTGRAY);
            layout.addView(pops[i-100]);

            TextView answer = new TextView(this);
            answer.setId(i+100);
            answer.setTextSize(20f);
            answer.setText(s);
            pops[i-100].addView(answer);

            ImageButton close = new ImageButton(this);
            close.setId(i+150);
            close.setImageResource(R.drawable.x_small);
            close.setBackgroundColor(Color.LTGRAY);
            pops[i-100].addView(close);

            close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pops[fi-100].setVisibility(ConstraintLayout.INVISIBLE);
                }
            });

            createConstraints(answer, pops[i-100], 0.5f, 0.5f);
            createConstraints(close, pops[i-100], 0.98f, 0f);

            final String fs = s;
            /* Create popup when clicked */
            newB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("Answer", fs);
                    pops[fi-100].setVisibility(ConstraintLayout.VISIBLE);
                }
            });

            /* Make constraints */
            createConstraints(newB, layout, horizB[i-100], vertB[i-100]);
            pops[fi-100].setMinWidth((int) (display.getWidth() * 0.7f));
            pops[fi-100].setMaxWidth((int) (display.getWidth() * 0.7f));
            pops[fi-100].setMinHeight((int) (display.getHeight() * 0.7f));
            pops[fi-100].setMaxHeight((int) (display.getHeight() * 0.7f));
            createConstraints(pops[fi-100], layout, 0.5f, 0.5f);
            i++;

        }

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
            Display display = getWindowManager().getDefaultDisplay();
            if (event1.getRawX() < event2.getRawX() &&
                    event2.getRawX() - event1.getRawX() >
                    Math.abs(event1.getRawY() - event2.getRawY())) {
                TransitionManager.beginDelayedTransition(layout);
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(layout);
                constraintSet.setHorizontalBias(R.id.player, playerHoriz + movement / display.getWidth());
                playerHoriz += movement / display.getWidth();
                constraintSet.applyTo(layout);
                Log.d(DEBUG_TAG, "Left-to-right");
            } else if (event2.getRawX() < event1.getRawX() &&
                    event1.getRawX() - event2.getRawX() >
                            Math.abs(event1.getRawY() - event2.getRawY())) {
                TransitionManager.beginDelayedTransition(layout);
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(layout);
                constraintSet.setHorizontalBias(R.id.player, playerHoriz - movement / display.getWidth());
                playerHoriz -= movement / display.getWidth();
                constraintSet.applyTo(layout);
                Log.d(DEBUG_TAG, "Right-to-left");
            } else if (event1.getRawY() < event2.getRawY() &&
                    event2.getRawY() - event1.getRawY() >
                            Math.abs(event1.getRawX() - event2.getRawX())) {
                TransitionManager.beginDelayedTransition(layout);
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(layout);
                constraintSet.setVerticalBias(R.id.player, playerVert + movement / display.getHeight());
                playerVert += movement / display.getHeight();
                constraintSet.applyTo(layout);
                Log.d(DEBUG_TAG, "Top-to-bottom");
            } else if (event2.getRawY() < event1.getRawY() &&
                    event1.getRawY() - event2.getRawY() >
                            Math.abs(event1.getRawX() - event2.getRawX())) {
                TransitionManager.beginDelayedTransition(layout);
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(layout);
                constraintSet.setVerticalBias(R.id.player, playerVert - movement / display.getHeight());
                playerVert -= movement / display.getHeight();
                constraintSet.applyTo(layout);
                Log.d(DEBUG_TAG, "Bottom-to-top");
            }

            for (int i = 0; i < bList.size(); i++) {
                if (Math.abs(playerVert - vertB[i]) <= 0.1 &&
                        Math.abs(playerHoriz - horizB[i]) <= 0.1) {
                    if (bList.get(i).equals(correct[number])) {
                        Intent intent = new Intent(LevelActivity.this, CorrectActivity.class);
                        intent.putExtra("number", number+"");
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(LevelActivity.this, WrongActivity.class);
                        intent.putExtra("number", number+"");
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

    public void getWalls() {

        Display d = getWindowManager().getDefaultDisplay();
        int height = d.getHeight() - 56;
        int width = d.getWidth();

//        walls.add(new Wall(0, height/2, width, height/2 + 5));
//        walls.add(new Wall(width/2, 0, width/2 + 5, height));

        int currRun = 0;
        int now = 0;

        for (int i = height/30; i < height - 35; i += 30) {
            walls.add(new Wall(0, i+5, width, i));
        }
        for (int i = 0; i < walls.size(); i++) {
            Wall wall = walls.get(i);
            if (wall.x1 + 5 == wall.x2) {
                break;
            }
            for (int j = wall.x1 + 30; j < width - 35; j += 30) {
                Log.d("walls", wall.y1 + ", " + j);
                if ((int) Math.random() == 0) {
                    int carve = (int) (Math.random() * (now - currRun));
                    walls.add(new Wall(j, wall.y1, j+5, wall.y1+35));
                    walls.add(i+1, new Wall(wall.x1, wall.y1, carve * 30, wall.y2));
                    walls.add(i+2, new Wall(carve * 30 + 30, wall.y1, wall.x2, wall.y2));
                    walls.remove(i);
                }
                now++;
            }
            currRun = 0;
        }

    }

}
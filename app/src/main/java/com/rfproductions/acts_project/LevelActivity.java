package com.rfproductions.acts_project;

import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class LevelActivity extends AppCompatActivity {

    /* EDIT THIS! */
    private int number = 0; // Question number
    private String qText = "Hello world!"; // Question (e.g. 'Who was Jesus?')
    private String correct = "Yes!"; // Correct answer (e.g. 'The Son of God')
    private String[] wrong = new String[]{"Wrong", "Wrong", "Fake news"}; // Wrong answers
    // (e.g. {'Me', 'The sky', 'Our President'})

    private int random = (int) (Math.random() * (wrong.length + 1));
    private ArrayList<String> bList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);

        ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.level_layout);
        TextView numberV = (TextView) findViewById(R.id.number);
        TextView qTextV = (TextView) findViewById(R.id.qText);

        numberV.setText("Question "+number);
        qTextV.setText(qText);

        for (String s : wrong) {
            bList.add(s);
        }
        bList.add(random, correct);

        int i = 100;

        for (String s : bList) {
            Button newB = new Button(this);
            newB.setText(s);
            newB.setId(i);
            newB.setLayoutParams(new ConstraintLayout.LayoutParams(
                    ConstraintLayout.LayoutParams.WRAP_CONTENT,
                    ConstraintLayout.LayoutParams.WRAP_CONTENT));
            layout.addView(newB);

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
            constraintSet.setVerticalBias(newB.getId(), (float) ((Math.random() * 65 + 35) / 100));
            constraintSet.setHorizontalBias(newB.getId(), (float) (Math.random()));
            constraintSet.applyTo(layout);
            i++;
        }

    }
}

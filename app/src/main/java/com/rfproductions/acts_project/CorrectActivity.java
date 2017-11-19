package com.rfproductions.acts_project;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CorrectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); // Force landscape
        this.setTitle("Correct");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_correct);
    }
}

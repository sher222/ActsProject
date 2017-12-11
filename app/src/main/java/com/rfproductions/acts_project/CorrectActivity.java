package com.rfproductions.acts_project;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class CorrectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); // Force landscape
        this.setTitle("Correct");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_correct);

        Intent get = getIntent();
        Log.d("intent", get.getStringExtra("number"));
        int curr = Integer.parseInt(get.getStringExtra("number"));

        final int fc = curr;
        Button b = (Button) findViewById(R.id.button3);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CorrectActivity.this, LevelActivity.class);
                intent.putExtra("number", fc+1+"");
                startActivity(intent);
            }
        });

    }
}

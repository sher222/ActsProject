package com.rfproductions.acts_project;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    private Button play;
    private Button comics;
    private Button map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        this.setTitle("Home");
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); // Force landscape
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        play = (Button) findViewById(R.id.button2);
        comics=(Button) findViewById(R.id.comics);
        map = (Button) findViewById(R.id.button);

        comics.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent(HomeActivity.this, displayComics.class);
                                        startActivity(intent);
                                    }
                                }

        );

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, LevelActivity.class);
                startActivity(intent);
            }
        }



        );

        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, MapActivity.class);
                startActivity(intent);
            }
        });

    }
}

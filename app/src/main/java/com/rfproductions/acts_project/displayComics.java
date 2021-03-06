package com.rfproductions.acts_project;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;

import static com.rfproductions.acts_project.displayComics.currentCh;
import static com.rfproductions.acts_project.displayComics.id;


public class displayComics extends AppCompatActivity {

    ConstraintLayout layout;

    //ADD MORE COMICS HERE
    //READ README FOR MORE INFO
    static public int[] imageName={
        R.drawable.acts_ch1_comic, R.drawable.acts_ch2_comic, R.drawable.acts_ch3_comics,
            R.drawable.acts_ch4_comics, R.drawable.acts_ch5_comics, R.drawable.acts_ch6_comics,
            R.drawable.acts_ch7, R.drawable.acts_ch8, R.drawable.acts_ch9, R.drawable.acts_ch10_comic,
            R.drawable.acts_ch11, R.drawable.acts_ch12, R.drawable.acts_ch13, R.drawable.acts_ch14,
            R.drawable.acts_ch15, R.drawable.acts_ch16, R.drawable.acts_ch17, R.drawable.acts_ch18, R.drawable.acts_ch_19,
            R.drawable.ch20, R.drawable.ch21, R.drawable.ch22, R.drawable.ch23, R.drawable.ch24, R.drawable.ch25,
            R.drawable.ch26, R.drawable.ch27, R.drawable.ch28
    };

    static public ImageView currentComic;
    static public int currentCh=1;
    static public int id;

private GestureDetectorCompat mDetector;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        mDetector = new GestureDetectorCompat(this, new comicsListener());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comics1);

        layout= (ConstraintLayout) findViewById(R.id.comics1_layout);

        currentComic=(ImageView) findViewById(R.id.currentComic);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event){
        this.mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

}
class comicsListener extends GestureDetector.SimpleOnGestureListener { // Listener for scroll



   @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY){
       //scroll up
       if (e1.getRawY()<e2.getRawY()){
           currentCh--;
           Log.d("swipe", "up");
           //prevents array out of bounds error
           if (currentCh<=0){
               currentCh++;
               return true;
           }
       }
       //scroll down
       if(e2.getRawY()<e1.getRawY()){
           currentCh++;
           Log.d("swipe", "down");
           //prevents array out of bounds error
           if (currentCh>displayComics.imageName.length){
               currentCh--;
               return true;
           }
       }



       Log.d("id", "id "+id+" "+currentCh);

       displayComics.currentComic.setImageResource(displayComics.imageName[currentCh-1]);

       return true;
   }


}
package com.rfproductions.acts_project;

import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by riley on 12/5/2017.
 */

public class Wall {

    // Top left, bottom right
    int x1;
    int y1;
    int x2;
    int y2;

    Wall(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public boolean isTouch(View v) {
        if (v.getRight() <= x1 || x2 <= v.getLeft()
                || v.getBottom() <= y1 || y2 <= v.getTop()) {
            return false;
        }
        return true;
    }

}

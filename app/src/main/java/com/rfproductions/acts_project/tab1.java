package com.rfproductions.acts_project;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by sheryl on 11/22/17.
 */

public class tab1 extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View myview=inflater.inflate(R.layout.tab1_frag, container, false);
        return myview;
    }


}

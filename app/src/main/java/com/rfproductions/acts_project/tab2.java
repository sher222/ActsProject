package com.rfproductions.acts_project;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by sheryl on 11/22/17.
 */

public class tab2 extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View myview=inflater.inflate(R.layout.tab2_frag, container, false);
        return myview;
    }


}

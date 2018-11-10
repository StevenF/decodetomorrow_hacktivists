package com.frio.steven.decodetomorrow.NavigationDrawer;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.frio.steven.decodetomorrow.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NavFragmentThree extends Fragment {


    public NavFragmentThree() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.nav_fragment_three, container, false);
    }

}

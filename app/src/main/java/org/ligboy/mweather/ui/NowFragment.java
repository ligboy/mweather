package org.ligboy.mweather.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.ligboy.mweather.R;

/**
 *
 */
public class NowFragment extends Fragment {

    public NowFragment() {
        // Required empty public constructor
    }

    public static NowFragment newInstance() {
        NowFragment fragment = new NowFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_now, container, false);
    }

}

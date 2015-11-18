package com.bartoszlipinski.xmltag.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Bartosz Lipinski
 * 16.11.2015
 */
public class SampleFragment extends Fragment {

    public static SampleFragment newInstance() {
        SampleFragment fragment = new SampleFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sample, container, false);
        return view;
    }
}

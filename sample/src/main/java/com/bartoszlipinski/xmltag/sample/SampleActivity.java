package com.bartoszlipinski.xmltag.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bartoszlipinski.xmltag.XmlTagInjector;

/**
 * Created by Bartosz Lipinski
 * 16.11.2015
 */
public class SampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        XmlTagInjector.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
    }
}

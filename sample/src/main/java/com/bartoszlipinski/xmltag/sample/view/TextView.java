package com.bartoszlipinski.xmltag.sample.view;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;

import com.bartoszlipinski.xmltag.annotations.XmlTag;
import com.bartoszlipinski.xmltag.sample.R;

@XmlTag
public class TextView extends android.widget.TextView {
    public TextView(Context context) {
        super(context);
        setupView(context);
    }

    public TextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupView(context);
    }

    public TextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setupView(context);
    }

    private void setupView(Context context) {
        setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
    }
}

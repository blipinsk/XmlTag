package com.bartoszlipinski.xmltag.sample.view;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.bartoszlipinski.xmltag.annotations.XmlTag;

@XmlTag("TestTagForAButton")
public class SampleButton extends AppCompatButton {
    public SampleButton(Context context) {
        super(context);
        setupView(context);
    }

    public SampleButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupView(context);
    }

    public SampleButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setupView(context);
    }

    private void setupView(Context context) {
        setText("I'm just a custom View...");
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getContext() instanceof Activity) {
                    ((Activity) getContext()).finish();
                }
            }
        });
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        getLayoutParams().height = ViewGroup.LayoutParams.MATCH_PARENT;
        requestLayout();
    }
}

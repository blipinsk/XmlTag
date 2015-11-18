package com.bartoszlipinski.xmltag.sample.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.View;

import com.bartoszlipinski.xmltag.annotations.XmlTag;
import com.bartoszlipinski.xmltag.sample.SampleFragmentActivity;

@XmlTag
public class SampleView extends AppCompatButton {
    public SampleView(Context context) {
        super(context);
        setupView(context);
    }

    public SampleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupView(context);
    }

    public SampleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setupView(context);
    }

    private void setupView(Context context) {
        setText("Go to the next Activity");
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getContext() instanceof Activity) {
                    startActivity((Activity) getContext());
                }
            }
        });
    }

    private void startActivity(Activity activity) {
        Intent intent = new Intent(activity, SampleFragmentActivity.class);
        activity.startActivity(intent);
    }
}

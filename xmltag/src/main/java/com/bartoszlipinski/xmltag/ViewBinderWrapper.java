package com.bartoszlipinski.xmltag;

import butterknife.ButterKnife;

/**
 * Created by Bartosz Lipinski
 * 30.12.2015
 */
public class ViewBinderWrapper<T> {
    private ButterKnife.ViewBinder<T> mBinder;

    public ViewBinderWrapper(ButterKnife.ViewBinder<T> butterKnifeBinder) {
        mBinder = butterKnifeBinder;
    }

    public ButterKnife.ViewBinder<T> get() {
        return mBinder;
    }
}

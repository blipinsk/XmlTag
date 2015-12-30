package com.bartoszlipinski.xmltag;

import butterknife.ButterKnife;

/**
 * Created by Bartosz Lipinski
 * 30.12.2015
 */
public abstract class XmlTagBinder<T> implements ButterKnife.ViewBinder<T>  {
    protected ViewBinderWrapper<T> mBinderWrapper;

    public XmlTagBinder(ViewBinderWrapper<T> binderWrapper) {
        mBinderWrapper = binderWrapper;
    }

    @Override
    public void bind(final ButterKnife.Finder finder, final T target, Object source) {
        mBinderWrapper.get().bind(finder, target, source);
    }

    @Override
    public void unbind(T target) {
        mBinderWrapper.get().unbind(target);
    }
}

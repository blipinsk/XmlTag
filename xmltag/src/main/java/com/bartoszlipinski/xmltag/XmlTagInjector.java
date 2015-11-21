/**
 * Copyright 2015 Bartosz Lipinski
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bartoszlipinski.xmltag;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.bartoszlipinski.xmltag.internal.Registrar;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class XmlTagInjector {
    private static final boolean DEBUG = true;

    public static final String PACKAGE_NAME = "com.bartoszlipinski.xmltag";
    public static final String REGISTRAR_NAME = "XmlTagRegistrar";
    public static final String GET_INSTANCE = "getInstance";

    private static XmlTagInjector sInstance = null;

    private Registrar mRegistrar;

    public synchronized static XmlTagInjector getInstance() {
        if (sInstance == null) {
            sInstance = new XmlTagInjector();
        }
        return sInstance;
    }

    private XmlTagInjector() {
        initRegistrar();
    }

    private void initRegistrar() {
        try {
            Class<?> registrarClass = Class.forName(PACKAGE_NAME + "." + REGISTRAR_NAME);
            final Method getInstance = registrarClass.getDeclaredMethod(GET_INSTANCE);
            mRegistrar = (Registrar) getInstance.invoke(null);
        } catch (Exception e) {
            //something went wrong... :/
            if (DEBUG) {
                Log.d(getClass().getSimpleName(), e.getMessage());
            }
        }

        if (mRegistrar == null) {
            //either there was an exception while creating registrar or there's no XmlTag annotations used
            mRegistrar = new Registrar.Dummy();
        }
    }

    public static void inject(Activity activity) {
        getInstance().innerInject(activity);
    }

    private void innerInject(Activity activity) {
        final LayoutInflater layoutInflater = LayoutInflater.from(activity);
        layoutInflater.setFactory(new XmlTaggerFactory(layoutInflater.getFactory()));
    }

    private class XmlTaggerFactory implements LayoutInflater.Factory {
        private final LayoutInflater.Factory mFactory;

        private XmlTaggerFactory(LayoutInflater.Factory factory) {
            mFactory = factory;
        }

        @Override
        public View onCreateView(String tag, Context context, AttributeSet attrs) {
            View instance = null;
            if (mRegistrar.hasRegisteredClassNameFor(tag)) {
                try {
                    Class<?> taggedClass = Class.forName(mRegistrar.getRegisteredClassNameFor(tag));
                    Constructor<?> taggedConstructor = taggedClass.getConstructor(Context.class, AttributeSet.class);
                    instance = (View) taggedConstructor.newInstance(context, attrs);
                } catch (Exception e) {
                    //something went wrong... :/
                    if (DEBUG) {
                        Log.d(getClass().getSimpleName(), e.getMessage());
                    }
                }
            }

            if (instance == null && mFactory != null) {
                instance = mFactory.onCreateView(tag, context, attrs);
            }
            return instance;
        }
    }
}

/*
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
package com.bartoszlipinski.xmltag.compiler.utils;

import java.lang.ref.WeakReference;

import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.tools.Diagnostic;

public class Logger {
    private static Logger instance = null;

    private final WeakReference<Messager> messager;

    public synchronized static void initialize(ProcessingEnvironment pe) {
        getInstance(pe);
    }

    public synchronized static Logger getInstance() {
        return getInstance(null);
    }

    private synchronized static Logger getInstance(ProcessingEnvironment pe) {
        if (instance == null) {
            instance = new Logger(pe);
        }
        return instance;
    }

    public synchronized static void destroyInstance() {
        //TODO: perform necessary actions (on destroy)
        instance = null;
    }

    private Logger(ProcessingEnvironment pe) {
        messager = new WeakReference<>(pe.getMessager());
    }

    public void log(String message) {
        if (messager.get() != null) {
            messager.get().printMessage(Diagnostic.Kind.NOTE, message);
        }
    }

    public void error(String message) {
        if (messager.get() != null) {
            messager.get().printMessage(Diagnostic.Kind.ERROR, message);
        }
    }

    public void warning(String message) {
        if (messager.get() != null) {
            messager.get().printMessage(Diagnostic.Kind.WARNING, message);
        }
    }
}

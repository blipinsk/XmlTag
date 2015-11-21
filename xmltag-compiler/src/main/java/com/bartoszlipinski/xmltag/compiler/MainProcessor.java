/**
 * Copyright 2015 Bartosz Lipinski
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bartoszlipinski.xmltag.compiler;

import com.bartoszlipinski.xmltag.compiler.generator.BaseGenerator;
import com.bartoszlipinski.xmltag.compiler.generator.XmlTagGenerator;
import com.bartoszlipinski.xmltag.compiler.utils.*;
import com.google.auto.service.AutoService;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.tools.Diagnostic;

import static javax.lang.model.SourceVersion.latestSupported;

@AutoService(Processor.class)
public class MainProcessor extends AbstractProcessor {

    public static final Class<? extends BaseGenerator>[] sGenerators = new Class[]{XmlTagGenerator.class};

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        Logger.initialize(processingEnv);
        AnnotatedClass.initialize(processingEnv);
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        LinkedHashSet<String> list = new LinkedHashSet<String>();
        for (Class<? extends BaseGenerator> c : sGenerators) {
            try {
                BaseGenerator baseGenerator = c.newInstance();
                for (Class a : baseGenerator.getAnnotations()) {
                    list.add(a.getCanonicalName());
                    Logger.getInstance().log(a.getCanonicalName());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new LinkedHashSet<String>(list);
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return latestSupported();
    }

    @Override
    public boolean process(Set annotations, RoundEnvironment roundEnv) {
        for (Class<? extends BaseGenerator> c : sGenerators) {
            try {
                BaseGenerator baseGenerator = c.newInstance();
                baseGenerator.generate(roundEnv, processingEnv);
            } catch (Exception e) {
                processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, e.getMessage());
            }
        }
        return true;
    }
}
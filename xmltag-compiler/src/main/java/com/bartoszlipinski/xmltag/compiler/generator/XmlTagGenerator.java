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
package com.bartoszlipinski.xmltag.compiler.generator;

import com.bartoszlipinski.xmltag.annotations.XmlTag;
import com.bartoszlipinski.xmltag.compiler.code.CodeGenerator;
import com.bartoszlipinski.xmltag.compiler.code.RegistrarCodeGenerator;
import com.bartoszlipinski.xmltag.compiler.code.SubClassCodeGenerator;
import com.bartoszlipinski.xmltag.compiler.utils.AnnotatedClass;
import com.bartoszlipinski.xmltag.compiler.utils.Logger;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.SkippingImportJavaFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

public class XmlTagGenerator extends BaseGenerator {

    @Override
    public Class[] getAnnotations() {
        return new Class[]{XmlTag.class};
    }

    @Override
    public void generate(RoundEnvironment roundEnv, ProcessingEnvironment processingEnv) {
        List<AnnotatedClass> annotated = new ArrayList<>();
        for (Element element : roundEnv.getElementsAnnotatedWith(XmlTag.class)) {
            annotated.add(AnnotatedClass.with((TypeElement) element));
        }

        if (annotated.size() == 0) {
            return;
        }
        findTagDuplicates(annotated);
        try {
            SkippingImportJavaFile skippingImportJavaFile;
            for (AnnotatedClass a : annotated) {
                skippingImportJavaFile = SkippingImportJavaFile
                        .builder(a.mSubClassPackageName, SubClassCodeGenerator.generate(a).build())
                        .skipImport(a.mPackageName + "." + a.mShortName)
                        .build();
                skippingImportJavaFile.writeTo(processingEnv.getFiler());
            }

            JavaFile javaFile = JavaFile
                    .builder(CodeGenerator.PACKAGE_NAME,
                            RegistrarCodeGenerator.generate(annotated).build())
                    .build();
            javaFile.writeTo(processingEnv.getFiler());
        } catch (IOException e) {
            Logger.getInstance().error(e.getMessage());
        }
    }

    private void findTagDuplicates(List<AnnotatedClass> annotated) {
        String foundDuplicate = null;
        outerLoop:
        for (int i = 0; i < annotated.size(); ++i) {
            for (int j = i + 1; j < annotated.size(); ++j) {
                if (annotated.get(i).mTag.equals(annotated.get(j).mTag)) {
                    foundDuplicate = annotated.get(i).mTag;
                    break outerLoop;
                }
            }
        }
        if (foundDuplicate != null) {
            Logger.getInstance().error("There's more than one \""+foundDuplicate+"\" tag.");
        }
    }
}
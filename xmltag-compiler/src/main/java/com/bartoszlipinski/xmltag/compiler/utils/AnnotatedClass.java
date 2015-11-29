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
package com.bartoszlipinski.xmltag.compiler.utils;

import com.bartoszlipinski.xmltag.annotations.XmlTag;
import com.bartoszlipinski.xmltag.compiler.code.CodeGenerator;

import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.ElementFilter;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

public class AnnotatedClass {
    private static final String CLASS_VIEW = "android.view.View";
    public static final String CLASS_CONTEXT = "android.content.Context";
    public static final String CLASS_ATTRIBUTE_SET = "android.util.AttributeSet";

    public String mPackageName;
    public String mShortName;
    public String mSubClassPackageName;
    public String mTag;

    private static Types sTypeUtils;
    private static Elements sElementUtils;

    public static void initialize(ProcessingEnvironment processingEnv) {
        sTypeUtils = processingEnv.getTypeUtils();
        sElementUtils = processingEnv.getElementUtils();
    }

    public AnnotatedClass(TypeElement annotatedElement) {
        mShortName = annotatedElement.getSimpleName().toString();
        mPackageName = sElementUtils.getPackageOf(annotatedElement).toString();
        mSubClassPackageName = CodeGenerator.ANDROID_VIEW_PACKAGE_NAME;
        final XmlTag annotation = annotatedElement.getAnnotation(XmlTag.class);
        if (annotation.value().length() != 0) {
            mTag = annotation.value();
        } else {
            mTag = annotatedElement.getSimpleName().toString();
        }
    }

    public static AnnotatedClass with(TypeElement annotatedElement) {
        validate(annotatedElement);
        return new AnnotatedClass(annotatedElement);
    }

    private static void validate(TypeElement annotatedElement) {
        validateClass(annotatedElement);
        validateConstructor(annotatedElement);
        validateTag(annotatedElement);
    }

    private static void validateClass(TypeElement annotatedElement) {
        boolean foundValidSuperClass = false;
        TypeElement typeElement = annotatedElement;
        while (!typeElement.toString().equals(Object.class.getName())) {
            typeElement = (TypeElement) sTypeUtils.asElement(typeElement.getSuperclass());
            if (typeElement.toString().equals(CLASS_VIEW)) {
                foundValidSuperClass = true;
                break;
            }
        }
        if (!foundValidSuperClass) {
            Logger.getInstance().error(annotatedElement.getQualifiedName() + ": A class annotated with @XmlTag must be a child (either direct or indirect) of android.view.View");
        }

        final Set<Modifier> modifiers = annotatedElement.getModifiers();
        if (modifiers.contains(Modifier.FINAL)) {
            Logger.getInstance().error(annotatedElement.getQualifiedName() + ": A class annotated with @XmlTag cannot be final.");
        }
    }

    private static void validateConstructor(TypeElement annotatedElement) {
        boolean foundValidConstructor = false;
        final List<ExecutableElement> constructors = ElementFilter.constructorsIn(annotatedElement.getEnclosedElements());
        for (ExecutableElement constructor : constructors) {
            final List<? extends VariableElement> parameters = constructor.getParameters();
            if (parameters.size() == 2) {
                if (parameters.get(0).asType().toString().equals(CLASS_CONTEXT) &&
                        parameters.get(1).asType().toString().equals(CLASS_ATTRIBUTE_SET)) {
                    foundValidConstructor = true;
                    break;
                }
            }
        }

        if (!foundValidConstructor) {
            Logger.getInstance().error(annotatedElement.getQualifiedName() + ": A class annotated with @XmlTag must have a constructor with Context and AttributeSet");
        }
    }

    private static void validateTag(TypeElement annotatedElement) {
        final XmlTag annotation = annotatedElement.getAnnotation(XmlTag.class);
        String tag;
        if (annotation.value().length() != 0) {
            tag = annotation.value();
        } else {
            tag = annotatedElement.getSimpleName().toString();
        }
        Pattern pattern = Pattern.compile("\\s");
        Matcher matcher = pattern.matcher(tag);
        if (matcher.find()) {
            Logger.getInstance().error(annotatedElement.getQualifiedName() + ": Tag cannot contain any whitespaces.");
        }
        final String conflict = NameConflictFinder.findConflictWith(tag);
        if (conflict != null) {
            Logger.getInstance().error(annotatedElement.getQualifiedName() + ": Names of classes from android.view, android.widget, " +
                    "android.webkit and android.app cannot be used as tags. Tag " + tag + " is conflicting with " + conflict);
        }
    }
}

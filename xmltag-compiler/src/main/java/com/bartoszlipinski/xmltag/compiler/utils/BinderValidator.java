package com.bartoszlipinski.xmltag.compiler.utils;

import java.util.Set;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Types;

/**
 * Created by Bartosz Lipinski
 * 30.12.2015
 */
public class BinderValidator {

    private static final String CLASS_APPLICATION = "android.app.Application";

    private static Types sTypeUtils;

    public static void initialize(ProcessingEnvironment processingEnv) {
        sTypeUtils = processingEnv.getTypeUtils();
    }

    public static boolean analyze(Set<? extends Element> elementsAnnotatedWith) {
        if (elementsAnnotatedWith.size() > 1) {
            Logger.getInstance().error("There can be only one element annotated with @GenerateXmlTagBinder");
        } else if (elementsAnnotatedWith.size() == 1) {
            validateClass((TypeElement) elementsAnnotatedWith.iterator().next());
            return true;
        }
        return false;
    }

    private static void validateClass(TypeElement annotatedElement) {
        boolean foundValidSuperClass = false;
        TypeElement typeElement = annotatedElement;
        while (!typeElement.toString().equals(Object.class.getName())) {
            typeElement = (TypeElement) sTypeUtils.asElement(typeElement.getSuperclass());
            if (typeElement.toString().equals(CLASS_APPLICATION)) {
                foundValidSuperClass = true;
                break;
            }
        }
        if (!foundValidSuperClass) {
            Logger.getInstance().error(annotatedElement.getQualifiedName() + ": A class annotated with @GenerateXmlTagBinder must be a child of " + CLASS_APPLICATION);
        }
    }
}

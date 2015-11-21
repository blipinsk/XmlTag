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
package com.bartoszlipinski.xmltag.compiler.code;

import com.bartoszlipinski.xmltag.compiler.utils.AnnotatedClass;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import javax.lang.model.element.Modifier;

/**
 * Created by Bartosz Lipinski
 * 17.11.2015
 */
public class SubClassCodeGenerator extends CodeGenerator {

    public static final ClassName CLASS_CONTEXT = ClassName.get("android.content", "Context");
    public static final ClassName CLASS_ATTRIBUTE_SET = ClassName.get("android.util", "AttributeSet");
    public static final String CONTEXT = "context";
    public static final String ATTRS = "attrs";

    public static TypeSpec.Builder generate(AnnotatedClass annotated) {
        TypeSpec.Builder builder = TypeSpec.classBuilder(annotated.mTag)
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .superclass(ClassName.get("", annotated.mPackageName + "." + annotated.mShortName))
                .addMethod(MethodSpec
                        .constructorBuilder()
                        .addModifiers(Modifier.PUBLIC)
                        .addParameter(CLASS_CONTEXT, CONTEXT)
                        .addParameter(CLASS_ATTRIBUTE_SET, ATTRS)
                        .addStatement("super($L, $L)", CONTEXT, ATTRS)
                        .build());
        return builder;
    }
}

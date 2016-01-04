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
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.TypeVariableName;

import javax.lang.model.element.Modifier;


/**
 * Created by Bartosz Lipinski
 * 28.12.2015
 */
public class BinderCodeGenerator extends CodeGenerator {
    private static final ClassName VIEW_BINDER = ClassName.get("butterknife.ButterKnife", "ViewBinder");
    private static final ParameterizedTypeName VIEW_BINDER_TYPE_NAME = ParameterizedTypeName.get(VIEW_BINDER, TypeVariableName.get("T"));
    private static final String XML_TAG_BINDER_SUFFIX = "$$XmlTagBinder";
    private static final String BINDER_PARAMETER = "binder";

    public static TypeSpec.Builder generate(AnnotatedClass annotated) {
        ClassName taggedClassName = ClassName.get(annotated.mPackageName, annotated.mShortName);

        TypeSpec.Builder builder = TypeSpec.classBuilder(annotated.mShortName + XML_TAG_BINDER_SUFFIX)
                .addModifiers(Modifier.FINAL, Modifier.PUBLIC)
                .addTypeVariable(TypeVariableName.get("T", taggedClassName))
                .superclass(ClassName.get("com.bartoszlipinski.xmltag", "XmlTagBinder"))
                .addMethod(generateConstructor());
        return builder;
    }

    private static MethodSpec generateConstructor() {
        MethodSpec.Builder result = MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PUBLIC)
                .addParameter(VIEW_BINDER_TYPE_NAME, BINDER_PARAMETER)
                .addStatement("super(binder)");
        return result.build();
    }
}
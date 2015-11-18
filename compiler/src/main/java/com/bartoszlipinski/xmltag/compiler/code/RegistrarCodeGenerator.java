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
package com.bartoszlipinski.xmltag.compiler.code;

import com.bartoszlipinski.xmltag.compiler.utils.AnnotatedClass;
import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeSpec;

import java.util.List;

import javax.lang.model.element.Modifier;

public class RegistrarCodeGenerator extends CodeGenerator {
    public static final String XML_TAG_REGISTRAR = "XmlTagRegistrar";
    public static final String ARRAY_MAP = "ArrayMap";

    public static final ClassName CLASS_REGISTRAR_INTERFACE = ClassName.get(PACKAGE_INTERNAL_NAME, "Registrar");
    public static final ClassName CLASS_XML_TAG_REGISTRAR = ClassName.get(PACKAGE_NAME, XML_TAG_REGISTRAR);
    public static final ClassName CLASS_ARRAY_MAP = ClassName.get("android.support.v4.util", ARRAY_MAP);
    public static final ClassName CLASS_STRING = ClassName.get("java.lang", "String");

    public static final String S_INSTANCE = "sInstance";
    public static final String INITIALIZE = "initialize";
    public static final String GET_INSTANCE = "getInstance";
    public static final String M_MAP = "mMap";

    public static final String HAS_REGISTERED_CLASS_NAME_FOR = "hasRegisteredClassNameFor";
    public static final String GET_REGISTERED_CLASS_NAME_FOR = "getRegisteredClassNameFor";
    public static final String REGISTER = "register";
    public static final String CLASS_NAME = "className";
    public static final String TAG = "tag";

    public static TypeSpec.Builder generate(List<AnnotatedClass> annotated) {
        MethodSpec.Builder initialize = MethodSpec.methodBuilder(INITIALIZE)
                .addModifiers(Modifier.FINAL, Modifier.PRIVATE);
        for (AnnotatedClass a : annotated) {
            initialize.addStatement("$L($S, $S)", REGISTER, a.mSubClassPackageName + "." + a.mTag, a.mTag);
        }

        TypeSpec.Builder builder = TypeSpec.classBuilder(XML_TAG_REGISTRAR)
                .addSuperinterface(CLASS_REGISTRAR_INTERFACE)
                .addModifiers(Modifier.FINAL)
                .addField(FieldSpec
                        .builder(CLASS_XML_TAG_REGISTRAR, S_INSTANCE, Modifier.PRIVATE, Modifier.STATIC)
                        .build())
                .addField(FieldSpec
                        .builder(ParameterizedTypeName.get(CLASS_ARRAY_MAP, CLASS_STRING, CLASS_STRING), M_MAP, Modifier.FINAL, Modifier.PRIVATE)
                        .initializer("new $L<>()", ARRAY_MAP)
                        .build())
                .addMethod(MethodSpec.methodBuilder(GET_INSTANCE)
                        .addModifiers(Modifier.PUBLIC, Modifier.SYNCHRONIZED, Modifier.STATIC)
                        .returns(CLASS_XML_TAG_REGISTRAR)
                        .beginControlFlow("if ($L == null)", S_INSTANCE)
                        .addStatement("$L = new $L()", S_INSTANCE, XML_TAG_REGISTRAR)
                        .addStatement("$L.$L()", S_INSTANCE, INITIALIZE)
                        .endControlFlow()
                        .addStatement("return " + S_INSTANCE)
                        .build())
                .addMethod(initialize.build())
                .addMethod(MethodSpec.methodBuilder(HAS_REGISTERED_CLASS_NAME_FOR)
                        .addAnnotation(AnnotationSpec.builder(Override.class).build())
                        .addParameter(CLASS_STRING, TAG)
                        .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                        .addStatement("return $L.containsKey($L)", M_MAP, TAG)
                        .returns(boolean.class)
                        .build())
                .addMethod(MethodSpec.methodBuilder(GET_REGISTERED_CLASS_NAME_FOR)
                        .addAnnotation(AnnotationSpec.builder(Override.class).build())
                        .addParameter(CLASS_STRING, TAG)
                        .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                        .addStatement("return $L.get($L)", M_MAP, TAG)
                        .returns(CLASS_STRING)
                        .build())
                .addMethod(MethodSpec.methodBuilder(REGISTER)
                        .addAnnotation(AnnotationSpec.builder(Override.class).build())
                        .addParameter(CLASS_STRING, CLASS_NAME)
                        .addParameter(CLASS_STRING, TAG)
                        .beginControlFlow("if ($L($L))", HAS_REGISTERED_CLASS_NAME_FOR, TAG)
                        .addStatement("$L.remove($L)", M_MAP, TAG)
                        .endControlFlow()
                        .addStatement("$L.put($L, $L)", M_MAP, TAG, CLASS_NAME)
                        .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                        .build())
                .addMethod(MethodSpec.constructorBuilder()
                        .addModifiers(Modifier.PRIVATE)
                        .build());
        return builder;
    }
}
package com.bartoszlipinski.xmltag.compiler.code;

import com.bartoszlipinski.xmltag.compiler.utils.AnnotatedClass;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.TypeVariableName;

import javax.lang.model.element.Modifier;

import butterknife.ButterKnife;

/**
 * Created by Bartosz Lipinski
 * 28.12.2015
 */
public class BinderCodeGenerator extends CodeGenerator {
    private static final ClassName VIEW_BINDER = ClassName.get(ButterKnife.ViewBinder.class);
    private static final ClassName FINDER = ClassName.get(ButterKnife.Finder.class);
    private static final ParameterizedTypeName VIEW_BINDER_TYPE_NAME = ParameterizedTypeName.get(VIEW_BINDER, TypeVariableName.get("T"));
    private static final String XML_TAG_BINDER_SUFFIX = "$$XmlTagBinder";
    private static final String BINDER_FIELD = "mBinder";
    private static final String BINDER_PARAMETER = "binder";
    private static final String BIND = "bind";
    private static final String UNBIND = "unbind";
    private static final String CONSTRUCTOR_STATEMENT = "$L = $L";
    private static final String BIND_STATEMENT = "$L.bind($L, $L, $L)";
    private static final String UNBIND_STATEMENT = "$L.unbind($L)";
    private static final String FINDER_ARG = "finder";
    private static final String TARGET_ARG = "target";
    private static final String SOURCE_ARG = "source";

    public static TypeSpec.Builder generate(AnnotatedClass annotated) {
        ClassName taggedClassName = ClassName.get(annotated.mPackageName, annotated.mShortName);

        TypeSpec.Builder builder = TypeSpec.classBuilder(annotated.mShortName + XML_TAG_BINDER_SUFFIX)
                .addModifiers(Modifier.FINAL, Modifier.PUBLIC)
                .addTypeVariable(TypeVariableName.get("T", taggedClassName))
                .addSuperinterface(VIEW_BINDER_TYPE_NAME)
                .addField(VIEW_BINDER_TYPE_NAME, BINDER_FIELD, Modifier.PRIVATE)
                .addMethod(generateConstructor())
                .addMethod(generateBindMethod())
                .addMethod(generateUnbindMethod());
        return builder;
    }

    private static MethodSpec generateConstructor() {
        MethodSpec.Builder result = MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PUBLIC)
                .addParameter(VIEW_BINDER_TYPE_NAME, BINDER_PARAMETER)
                .addStatement(CONSTRUCTOR_STATEMENT, BINDER_FIELD, BINDER_PARAMETER);
        return result.build();
    }

    private static MethodSpec generateBindMethod() {
        MethodSpec.Builder result = MethodSpec.methodBuilder(BIND)
                .addAnnotation(Override.class)
                .addModifiers(Modifier.PUBLIC)
                .addParameter(FINDER, FINDER_ARG, Modifier.FINAL)
                .addParameter(TypeVariableName.get("T"), TARGET_ARG, Modifier.FINAL)
                .addParameter(Object.class, SOURCE_ARG)
                .addStatement(BIND_STATEMENT, BINDER_FIELD, FINDER_ARG, TARGET_ARG, SOURCE_ARG);

        return result.build();
    }

    private static MethodSpec generateUnbindMethod() {
        MethodSpec.Builder result = MethodSpec.methodBuilder(UNBIND)
                .addAnnotation(Override.class)
                .addModifiers(Modifier.PUBLIC)
                .addParameter(TypeVariableName.get("T"), TARGET_ARG)
                .addStatement(UNBIND_STATEMENT, BINDER_FIELD, TARGET_ARG);

        return result.build();
    }
}
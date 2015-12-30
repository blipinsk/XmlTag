package com.bartoszlipinski.xmltag.compiler.code;

import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import javax.lang.model.element.Modifier;

/**
 * Created by Bartosz Lipinski
 * 28.12.2015
 */
public class ValidatorCodeGenerator extends CodeGenerator {
    public static TypeSpec.Builder generate() {
        TypeSpec.Builder builder = TypeSpec.classBuilder("TestValidator")
                .addModifiers(Modifier.FINAL, Modifier.PUBLIC)
                .addMethod(MethodSpec
                        .methodBuilder("test")
                        .returns(boolean.class)
                        .addModifiers(Modifier.STATIC, Modifier.PUBLIC)
                        .addStatement("return com.bartoszlipinski.xmltag.XmlTagValidator.hasButterKnife()")
                        .build());
        return builder;
    }
}

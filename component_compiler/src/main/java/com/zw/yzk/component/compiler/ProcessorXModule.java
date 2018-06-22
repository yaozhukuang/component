package com.zw.yzk.component.compiler;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import com.zw.yzk.component.annotation.XModule;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

/**
 * @author zhanwei
 */
@AutoService(Processor.class)
public class ProcessorXModule extends AbstractProcessor {

    private Filer filer;
    private Messager messager;


    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);

        filer = processingEnvironment.getFiler();
        messager = processingEnvironment.getMessager();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotations = new LinkedHashSet<>();
        annotations.add(XModule.class.getCanonicalName());
        return annotations;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(XModule.class);
        if (elements.isEmpty()) {
            return false;
        }
        for (Element element : elements) {
            if (element.getKind() == ElementKind.CLASS) {
                createClass((TypeElement) element);
            }
        }
        return false;
    }

    private void createClass(TypeElement element) {
        String value = element.getAnnotation(XModule.class).value();
        if ("".equals(value)) {
            throw new RuntimeException("XModule value must be set");
        }
        String className = getClassName(value);
        TypeSpec.Builder builder = TypeSpec
                .classBuilder(className)
                .addModifiers(Modifier.PUBLIC);

        String simpleName = element.getSimpleName().toString();
        ClassName returnType = ClassName.get(element);
        MethodSpec method = MethodSpec
                .methodBuilder(getMethodName(value))
                .returns(returnType)
                .addModifiers(Modifier.PUBLIC)
                .addStatement("return new $L()", simpleName)
                .build();

        messager.printMessage(Diagnostic.Kind.NOTE, "create class: " + className);
        createFile(builder.addMethod(method));
    }

    /**
     * 生成类文件
     */
    private void createFile(TypeSpec.Builder builder) {
        String packageName = Constant.PACKAGE;
        JavaFile javaFile = JavaFile.builder(packageName, builder.build())
                .build();
        try {
            javaFile.writeTo(filer);
            messager.printMessage(Diagnostic.Kind.NOTE, "writeTo class complete");
        } catch (IOException e) {
            System.out.print("IOException");
            e.printStackTrace();
        }
    }

    public static String getClassName(String value) {
        return Constant.SERVICE + value;
    }

    public static String getMethodName(String value) {
        return "get" + value;
    }

}

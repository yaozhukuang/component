package com.zw.yzk.component.compiler;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import com.zw.yzk.component.annotation.XModuleInject;
import com.zw.yzk.component.annotation.XModule;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;

import static com.zw.yzk.component.compiler.Constant.SERVICE_MANAGER;

/**
 * @author zhanwei
 */
@AutoService(Processor.class)
public class ProcessorXModuleInject extends AbstractProcessor {

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
        annotations.add(XModuleInject.class.getCanonicalName());
        return annotations;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(XModuleInject.class);
        if (elements.isEmpty()) {
            return false;
        }
        for (Element element : elements) {
            createClass((TypeElement) element);
        }
        return false;
    }

    private void createClass(TypeElement element) {
    TypeSpec.Builder builder = TypeSpec
            .classBuilder(SERVICE_MANAGER)
            .addModifiers(Modifier.PUBLIC);
    //添加单例方法
    addInstanceMethod(builder, SERVICE_MANAGER);
    //添加成员方法
    List<Element> elements = (List<Element>) element.getEnclosedElements();
    for (Element e : elements) {
        addMethod(builder, e);
    }
    createFile(builder);
}

    /**
     * 添加方法
     */
    private void addMethod(TypeSpec.Builder builder, Element element) {
        XModule xModule = element.getAnnotation(XModule.class);
        if (xModule == null) {
            return;
        }
        String value = xModule.value();
        String simpleClassName = ProcessorXModule.getClassName(value);
        TypeName className = ClassName.get(element.asType());

        String fieldName = "m" + simpleClassName;
        FieldSpec fieldSpec = FieldSpec
                .builder(className, fieldName, Modifier.PRIVATE)
                .build();

        //添加get方法
        MethodSpec.Builder getBuilder = MethodSpec
                .methodBuilder("get" + simpleClassName)
                .returns(className)
                .addModifiers(Modifier.PUBLIC)
                .addStatement("return $L", fieldName);
        //添加set方法
        MethodSpec.Builder setBuilder = MethodSpec
                .methodBuilder("set" + simpleClassName)
                .addModifiers(Modifier.PUBLIC)
                .addParameter(className, "param")
                .addStatement("this.$L = param", fieldName);

        builder.addField(fieldSpec)
                .addMethod(getBuilder.build())
                .addMethod(setBuilder.build());
    }

    /**
     * 添加单例方法
     */
    private void addInstanceMethod(TypeSpec.Builder builder, String name) {
        ClassName className = ClassName.get(Constant.PACKAGE, name);
        FieldSpec field = FieldSpec.builder(className, "instance", Modifier.PRIVATE, Modifier.STATIC).build();

        MethodSpec method = MethodSpec.methodBuilder(Constant.SERVICE_MANAGER_INSTANCE_METHOD)
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(className)
                .addStatement("if(instance == null) {")
                .addStatement("synchronized ($L.class) {", name)
                .addStatement("if(instance == null) {")
                .addStatement("instance = new $L();", name)
                .addStatement("}")
                .addStatement("}")
                .addStatement("}")
                .addStatement("return instance")
                .build();

        MethodSpec constructor = MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PRIVATE)
                .build();

        builder.addField(field)
                .addMethod(method)
                .addMethod(constructor);
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
        } catch (IOException e) {
            System.out.print("IOException");
            e.printStackTrace();
        }
    }

}

package com.zw.yzk.component.compiler;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import com.zw.yzk.component.annotation.XModule;
import com.zw.yzk.component.annotation.XModuleInit;


import java.io.IOException;
import java.lang.reflect.Field;
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
public class ProcessorXModuleInit extends AbstractProcessor {

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
        annotations.add(XModuleInit.class.getCanonicalName());
        annotations.add(XModule.class.getCanonicalName());
        return annotations;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        log("process start");
        Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(XModuleInit.class);
        if (elements.isEmpty()) {
            return false;
        }
        for (Element element : elements) {
            createClass((TypeElement) element, roundEnv.getElementsAnnotatedWith(XModule.class));
        }
        log("process end");
        return false;
    }

    private void createClass(TypeElement element, Set<? extends Element> mElement) {
        String value = element.getAnnotation(XModuleInit.class).value();
        TypeSpec.Builder cBuilder = TypeSpec
                .classBuilder(value)
                .addModifiers(Modifier.PUBLIC);
        //添加方法,通过反射添加App所依赖模块的module
        String name = Constant.PACKAGE + "." + Constant.SERVICE_MANAGER;
        try {
            Class manager = Class.forName(name);

            MethodSpec.Builder mBuilder = MethodSpec
                    .methodBuilder("init")
                    .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                    .addModifiers(Modifier.PUBLIC);

            String className = manager.getSimpleName();
            mBuilder.addStatement("$L instance = $L.$L()",
                    className, className, Constant.SERVICE_MANAGER_INSTANCE_METHOD);

            for (Field field : manager.getDeclaredFields()) {
                if (!field.getName().startsWith("m")) {
                    continue;
                }
                String simpleClassName = field.getName().replace("m", "");
                String methodName = simpleClassName.replace(Constant.SERVICE, "");

                if (classExists(simpleClassName)) {
                    //添加APP依赖的模块暴露的module
                    mBuilder.addStatement("instance.set$L(new $L().get$L())",
                            simpleClassName, simpleClassName, methodName);
                } else {
                    //添加本模块被XModule注解的Module,因为在同一个module中，刚刚由AbstractProcessor生成的类无法反射出来,
                    //所以采用类名匹配
                    for (Element e : mElement) {
                        if (e.getKind().equals(ElementKind.CLASS)) {
                            XModule mModule = e.getAnnotation(XModule.class);
                            if (ProcessorXModule.getClassName(mModule.value()).equals(simpleClassName)) {
                                mBuilder.addStatement("instance.set$L(new $L().get$L())",
                                        simpleClassName, simpleClassName, methodName);
                            }
                        }
                    }
                }

            }

            //添加方法到class
            cBuilder.addMethod(mBuilder.build());
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException("XModuleInject must be added to create class " + Constant.SERVICE_MANAGER);
        }
        createFile(cBuilder);
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
            e.printStackTrace();
        }
    }

    /**
     * 判断代码生成包下某个类是否存在
     */
    private boolean classExists(String simpleClassName) {
        String name = Constant.PACKAGE + "." + simpleClassName;
        try {
            Class.forName(name);
            return true;
        } catch (ClassNotFoundException ex) {
            log(simpleClassName + " not exists");
            return false;
        }
    }

    private void log(String msg) {
        messager.printMessage(Diagnostic.Kind.NOTE, "XModuleInit " + msg);
    }

}

package com.zw.yzk.component.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zhanwei
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
public @interface XModuleInject {
}

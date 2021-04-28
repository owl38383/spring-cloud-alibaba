package com.dc.order.annotation;

import java.lang.annotation.*;


/**
 * @author 25738
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ActionLogs {
    String type() default "";
}

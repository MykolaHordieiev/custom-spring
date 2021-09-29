package com.spring.custom.infra.annotation;

public @interface InjectProperty {
    String value() default "";
}

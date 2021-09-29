package com.spring.custom.infra;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
@Data
@AllArgsConstructor
public class BeanDefinition<T> {
    private String beanName;
    private Class<T> aClass;
}

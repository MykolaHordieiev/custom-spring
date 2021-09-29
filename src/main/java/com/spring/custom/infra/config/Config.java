package com.spring.custom.infra.config;

import com.spring.custom.infra.BeanDefinition;
import org.reflections.Reflections;

import java.util.Collection;

public interface Config {
    <T> Class<? extends T> getImplClass(Class<T> inf);

    Reflections getScanner();

    <T> BeanDefinition<T> configBeanDefinition(Class<T> type);

}

package com.spring.custom.infra;

import com.spring.custom.infra.annotation.Component;
import com.spring.custom.infra.config.Config;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ApplicationContext {
    @Setter
    private ObjectFactory factory;
    private Map<Class, Object> cache = new ConcurrentHashMap<>();
    @Getter
    private Config config;

    public ApplicationContext(Config config) {
        this.config = config;
    }

    public <T> T getObject(Class<T> type) {
        return (T) cache.entrySet()
                .stream()
                .filter(entry -> entry.getKey().equals(type))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElseGet(() -> createObject(type));
//        return (T) cache.get(type);
    }

    private <T> T createObject(Class<T> type) {
        Class<? extends T> implClass = type;
        BeanDefinition<T> tBeanDefinition = config.configBeanDefinition(type);
        Set<Class<?>> typesAnnotatedWith = config.getScanner().getTypesAnnotatedWith(Component.class);

        if (type.isInterface()) {
            Set<Class<? extends T>> subTypesOf = config.getScanner().getSubTypesOf(type);

            for (Class<?> aClass : typesAnnotatedWith) {
                subTypesOf.stream().filter(subType->subType.equals(aClass)).findFirst()
            }
            Class<?> component = typesAnnotatedWith.stream().filter(t -> t.equals(type)).findFirst()
                    .orElseThrow(() -> new RuntimeException("no component: " + type.getSimpleName()));
            implClass = (Class<? extends T>) component;
//            implClass = config.getImplClass(type);
        }
        T t = factory.createObject(implClass);
        if (implClass.isAnnotationPresent(Component.class)) {
            cache.put(implClass, t);
        }
        return t;
    }
}

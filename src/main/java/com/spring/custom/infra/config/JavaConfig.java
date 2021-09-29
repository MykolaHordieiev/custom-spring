package com.spring.custom.infra.config;

import com.spring.custom.infra.BeanDefinition;
import com.spring.custom.infra.annotation.Component;
import com.spring.custom.infra.annotation.InjectByType;
import lombok.Getter;
import org.reflections.Reflections;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class JavaConfig implements Config {
    @Getter
    private Reflections scanner;
    private Map<Class, Class> ifc2ImplClass;
    private Map<String, BeanDefinition> beanDefinitions;

    public JavaConfig(String packageToScan, Map<Class, Class> ifc2ImplClass) {
        this.scanner = new Reflections(packageToScan);
        this.ifc2ImplClass = ifc2ImplClass;
    }

    @Override
    public <T> BeanDefinition<T> configBeanDefinition(Class<T> type) {
        Set<Class<?>> typesAnnotatedWithComponent = scanner.getTypesAnnotatedWith(Component.class);

        typesAnnotatedWithComponent.stream()
                .filter(t -> t.equals(type))
                .map(t -> (Class<T>) t)
                .findFirst()
                .ifPresent(t -> beanDefinitions.put(t.getSimpleName(), new BeanDefinition<T>(type.getSimpleName(), t)));

        typesAnnotatedWithComponent.stream()
                .filter(t -> t.getSuperclass().equals(type))
                .map(t -> (Class<T>) t)
                .forEach(t -> beanDefinitions.put(t.getSimpleName(), new BeanDefinition<T>(type.getSimpleName(), t)));

        Set<Class<?>> typesAnnotatedWithInjectByType = scanner.getTypesAnnotatedWith(InjectByType.class);

    }

    @Override
    public <T> Class<? extends T> getImplClass(Class<T> inf) {
        return ifc2ImplClass.computeIfAbsent(inf, aClass ->
        {
            Set<Class<? extends T>> classes = scanner.getSubTypesOf(inf);
            if (classes.size() != 1) {
                throw new RuntimeException(inf + " has 0 or more than one impl please update your config");
            }
            return classes.iterator().next();
        });
    }
}

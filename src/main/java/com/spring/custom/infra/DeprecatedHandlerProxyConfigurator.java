package com.spring.custom.infra;

import net.sf.cglib.proxy.Enhancer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.stream.Stream;

public class DeprecatedHandlerProxyConfigurator implements ProxyConfigurator {
    @Override
    public Object replaceWithProxyIfNeeded(Object t, Class implClass) {
        return Stream.of(implClass.getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(Deprecated.class))
                .map(method -> replaceWithProxy(t, implClass))
                .findFirst()
                .orElse(t);
    }

    private Object replaceWithProxy(Object t, Class implClass) {
        if (implClass.getInterfaces().length == 0) {
            return Enhancer.create(implClass, new net.sf.cglib.proxy.InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    return getInvocationHandlerLogic(method, args, t);
                }
            });
        }

        return Proxy.newProxyInstance(implClass.getClassLoader(), implClass.getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return getInvocationHandlerLogic(method, args, t);
            }
        });
    }

    private Object getInvocationHandlerLogic(Method method, Object[] args, Object t) throws IllegalAccessException, InvocationTargetException {
        System.out.println("********** что ж ты делаешь урод!! ");
        return method.invoke(t, args);
    }
}

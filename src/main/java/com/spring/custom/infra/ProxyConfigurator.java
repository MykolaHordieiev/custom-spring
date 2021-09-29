package com.spring.custom.infra;

public interface ProxyConfigurator {
    <T> T replaceWithProxyIfNeeded(T t, Class<? extends T> implClass);
}

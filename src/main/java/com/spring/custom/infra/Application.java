package com.spring.custom.infra;

import com.spring.custom.infra.config.JavaConfig;

import java.util.Map;

public class Application {
    public static ApplicationContext run(String packageToScan, Map<Class, Class> ifc2ImplClass){
        JavaConfig javaConfig = new JavaConfig(packageToScan,ifc2ImplClass);
        ApplicationContext context = new ApplicationContext(javaConfig);
        ObjectFactory objectFactory = new ObjectFactory(context);
        context.setFactory(objectFactory);
        return context;
    }
}

package com.spring.custom;

import com.spring.custom.desinfector.CoronaDesinfector;
import com.spring.custom.desinfector.Room;
import com.spring.custom.desinfector.policeman.GoodPoliceman;
import com.spring.custom.desinfector.policeman.Policeman;
import com.spring.custom.infra.Application;
import com.spring.custom.infra.ApplicationContext;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = Application.run("com.spring.custom", new HashMap<>(Map.of(Policeman.class, GoodPoliceman.class)));
        CoronaDesinfector desinfector = context.getObject(CoronaDesinfector.class);
        desinfector.start(new Room("kitchen"));
    }
}

package com.spring.custom.desinfector.policeman;

import com.spring.custom.desinfector.recommender.Recommender;
import com.spring.custom.infra.annotation.Component;
import com.spring.custom.infra.annotation.InjectByType;

import javax.annotation.PostConstruct;

@Component
public class GoodPoliceman implements Policeman {
    @InjectByType
    private Recommender recommender;

    @PostConstruct
    public void init() {
        System.out.println(recommender.getClass());
    }

    @Override
    public void makePeopleLeaveRoom() {
        System.out.println("пиф паф, бах бах, кыш, кыш!");
    }
}

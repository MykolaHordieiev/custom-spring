package com.spring.custom.desinfector.recommender;

import com.spring.custom.infra.annotation.Component;
import com.spring.custom.infra.annotation.InjectProperty;

//@Component
public class RecommenderImpl implements Recommender {
    @InjectProperty
    private String alcohol;

    @Override
    public void recommend() {
        System.out.println("to protect from covid-2019, drink " + alcohol);
    }
}

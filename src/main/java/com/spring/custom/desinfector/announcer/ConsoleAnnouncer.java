package com.spring.custom.desinfector.announcer;

import com.spring.custom.infra.annotation.Component;
import com.spring.custom.infra.annotation.InjectByType;
import com.spring.custom.desinfector.recommender.Recommender;

@Component
public class ConsoleAnnouncer implements Announcer {
    @InjectByType
    private Recommender recommender;

    @Override
    public void announce(String message) {
        recommender.recommend();
        System.out.println(message);
    }
}

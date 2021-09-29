package com.spring.custom.desinfector;

import com.spring.custom.desinfector.announcer.Announcer;
import com.spring.custom.infra.annotation.Component;
import com.spring.custom.infra.annotation.InjectByType;
import com.spring.custom.desinfector.policeman.Policeman;
import lombok.SneakyThrows;

@Component
public class CoronaDesinfector {
    @InjectByType
    private Announcer announcer;
    @InjectByType
    private Policeman policeman;

    public void start(Room room) {
        announcer.announce("Начинаем дезинфекцию, всe вон!");
        policeman.makePeopleLeaveRoom();
        desinfect(room);
        announcer.announce("you can try enter back to " + room);
    }

    @SneakyThrows
    private void desinfect(Room room) {
        System.out.println("start desinfect " + room);
        Thread.sleep(300);
        System.out.println("desinfect was success");
        System.out.println(room + " is clear");
    }
}

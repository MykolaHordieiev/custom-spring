package com.spring.custom.desinfector.policeman;

import com.spring.custom.infra.annotation.Component;

@Component
public class AngryPoliceman implements Policeman {
    @Override
    @Deprecated
    public void makePeopleLeaveRoom() {
        System.out.println("Всех убью! Вон пошли");
    }
}

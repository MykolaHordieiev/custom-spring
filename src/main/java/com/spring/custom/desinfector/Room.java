package com.spring.custom.desinfector;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
public class Room {
    private String name;

    @Override
    public String toString() {
        return this.name;
    }
}

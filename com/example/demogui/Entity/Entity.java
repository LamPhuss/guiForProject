package com.example.demogui.Entity;

public class Entity {
    private String name;

    private String description;

    public Entity(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}

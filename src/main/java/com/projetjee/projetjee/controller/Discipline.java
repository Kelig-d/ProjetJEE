package com.projetjee.projetjee.controller;

public class Discipline {

    private int id;
    private String name;
    private Boolean flags;

    public Discipline(int id, String name, Boolean flags) {
        this.id = id;
        this.name = name;
        this.flags = flags;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getFlags() {
        return flags;
    }

    public void setFlags(Boolean flags) {
        this.flags = flags;
    }

    @Override
    public String toString() {
        return "Discipline [id=" + id + ", name=" + name + ", flags=" + flags + "]";
    }


}

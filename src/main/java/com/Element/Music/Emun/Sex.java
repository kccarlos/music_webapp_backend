package com.Element.Music.Emun;

public enum  Sex {

    MALE(0, "male"),
    FEMALE(1, "female"),
    BAND(2, "band");

    private int code;
    private String name;

    private Sex(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

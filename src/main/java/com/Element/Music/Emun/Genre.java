package com.Element.Music.Emun;

public enum Genre {

    POP(0, "流行"),
    ROCK(1, "摇滚"),
    ELECTRONIC(2, "电子"),
    FOLK(3, "民谣"),
    CLASSICAL(4, "古典"),
    JAZZ(5, "爵士"),
    ABSOLUTE_MUSIC(6, "纯音乐"),
    RAP(7, "说唱"),
    METAL(8, "金属"),
    WORLD_MUSIC(9, "世界音乐"),
    NEW_AGE(10, "新世纪"),
    AMBIENT_MUSIC(11, "氛围音乐"),
    INDIE(12, "独立");


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

    private int code;
    private String name;

    private Genre(int code, String name) {
        this.code = code;
        this.name = name;
    }
    }

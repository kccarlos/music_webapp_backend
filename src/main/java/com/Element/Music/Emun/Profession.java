package com.Element.Music.Emun;

public enum Profession {

    SINGER(0, "singer"),
    DOCTOR(1, "doctor"),
    TEACHER(2, "teacher"),
    PROGRAMMER(3, "programmer");

    private int code;
    private String name;

    private Profession(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getIndex() {
        return code;
    }

    public void setIndex(int index) {
        this.code = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}

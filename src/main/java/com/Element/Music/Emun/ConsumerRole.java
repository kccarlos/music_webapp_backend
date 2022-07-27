package com.Element.Music.Emun;

public enum ConsumerRole {
    NORMAL(1, "normal"),
    VIP(2, "normal"),
    SVIP(3, "normal");

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

    private ConsumerRole(int code, String name) {
        this.code = code;
        this.name = name;
    }
}

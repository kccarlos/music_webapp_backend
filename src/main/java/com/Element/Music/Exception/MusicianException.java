package com.Element.Music.Exception;

public class MusicianException extends ElementException {
    private static final long serialVersionUID = -2652447942917803396L;

    public MusicianException() {
        super("Musician存在异常");
    }

    public MusicianException(String s) {
        super(s);
    }
}
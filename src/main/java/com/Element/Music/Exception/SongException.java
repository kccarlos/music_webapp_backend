package com.Element.Music.Exception;

public class SongException extends ElementException {

    private static final long serialVersionUID = 4372421471396509259L;

    public SongException() {
        super("Song存在异常");
    }

    public SongException(String s) {
        super(s);
    }
}

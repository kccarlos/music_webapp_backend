package com.Element.Music.Exception;

public class PlaylistException extends ElementException {
//    private static final long serialVersionUID = -2652447942917803396L;

    public PlaylistException() {
        super("Playlist存在异常");
    }

    public PlaylistException(String s) {
        super(s);
    }
}
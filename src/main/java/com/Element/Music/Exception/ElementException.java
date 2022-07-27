package com.Element.Music.Exception;

public class ElementException extends Exception {
    private static final long serialVersionUID = -5188968930677610238L;

    public ElementException() {
        super();
    }

    public ElementException(String s) {
        super(s);
    }

    public ElementException(Throwable cause) {
        super(cause);
    }

    public ElementException(String s, Throwable cause) {
        super(s, cause);
    }
}

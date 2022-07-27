package com.Element.Music.Exception;

public class ConsumerException extends ElementException {
    private static final long serialVersionUID = 434077029534858568L;

    public ConsumerException() {
        super("Consumer存在异常");
    }

    public ConsumerException(String s) {
        super(s);
    }
}

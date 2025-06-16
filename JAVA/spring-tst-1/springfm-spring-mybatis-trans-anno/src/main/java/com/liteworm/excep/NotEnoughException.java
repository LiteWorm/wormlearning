package com.liteworm.excep;

/**
 * @ClassName NotEnoughException
 * @Decription @TOTO
 * @AUthor LiteWorm
 * @Date 2020/4/21 15:09
 * @Version 1.0
 **/
public class NotEnoughException extends RuntimeException {
    public NotEnoughException() {
        super();
    }

    public NotEnoughException(String message) {
        super(message);
    }
}
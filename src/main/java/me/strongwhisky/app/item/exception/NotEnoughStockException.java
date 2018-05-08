package me.strongwhisky.app.item.exception;

/**
 * Created by taesu on 2018-05-07.
 */
public class NotEnoughStockException extends RuntimeException {

    public NotEnoughStockException(){

    }

    public NotEnoughStockException(String message){
        super(message);
    }
}

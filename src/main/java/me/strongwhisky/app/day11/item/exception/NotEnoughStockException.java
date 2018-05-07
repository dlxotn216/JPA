package me.strongwhisky.app.day11.item.exception;

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

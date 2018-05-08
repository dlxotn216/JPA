package me.strongwhisky.app.member.exception;

/**
 * Created by taesu on 2018-05-07.
 */
public class DuplicatedMemberIdException extends IllegalArgumentException {
    public DuplicatedMemberIdException() {
    }

    public DuplicatedMemberIdException(String s) {
        super(s);
    }

    public DuplicatedMemberIdException(String message, Throwable cause) {
        super(message, cause);
    }
}

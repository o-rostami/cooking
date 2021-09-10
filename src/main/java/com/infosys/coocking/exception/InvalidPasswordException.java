package com.infosys.coocking.exception;


/**
 * A<i>InvalidPasswordException</i>.This Exception will be thrown whenever password value would be invalid<p>
 *
 * @author Omid Rostami
 */

public class InvalidPasswordException extends BaseException {

    public InvalidPasswordException(String message, String... args) {
        super(message);
        setArguments(args);
    }

    public InvalidPasswordException() {
    }
}

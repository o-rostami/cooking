package com.infosys.coocking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * A<i>BadRequestException</i>.This Exception will be thrown whenever bad request exception expose<p>
 *
 * @author Omid Rostami
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends BaseException {

    public BadRequestException(String message, String... args) {
        super(message);
        setArguments(args);
    }

    public BadRequestException() {
    }
}

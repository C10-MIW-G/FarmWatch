package com.theteapottroopers.farmwatch.exception;

/**
 * @Author: M.S. Pilat <pilat_m@msn.com>
 * <p>
 * this exception is thrown when a field is empty
 */

public class FieldHasNoInputException extends RuntimeException{

    public FieldHasNoInputException(String message) {
        super(message);
    }

}

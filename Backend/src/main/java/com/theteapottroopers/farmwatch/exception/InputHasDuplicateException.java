package com.theteapottroopers.farmwatch.exception;

import javax.management.RuntimeErrorException;

/**
 * @Author: M.S. Pilat <pilat_m@msn.com>
 * <p>
 * dit programma doet iets
 */

public class InputHasDuplicateException extends RuntimeException {

    public InputHasDuplicateException (String message) { super(message); }

}

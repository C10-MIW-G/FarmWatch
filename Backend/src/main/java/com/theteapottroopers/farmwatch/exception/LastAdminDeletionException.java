package com.theteapottroopers.farmwatch.exception;

/**
 * @Author: M.S. Pilat <pilat_m@msn.com>
 * <p>
 * this is an exception for when the last admin is trying to be deleted
 */

public class LastAdminDeletionException extends RuntimeException{

    public LastAdminDeletionException(String message) {
        super(message);
    }

}

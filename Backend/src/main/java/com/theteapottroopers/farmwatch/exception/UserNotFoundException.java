package com.theteapottroopers.farmwatch.exception;

/**
 * @author Berend Boksma <b.boksma@st.hanze.nl>
 * <p>
 * A User not found exception
 */
public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message) {
        super(message);
    }
}

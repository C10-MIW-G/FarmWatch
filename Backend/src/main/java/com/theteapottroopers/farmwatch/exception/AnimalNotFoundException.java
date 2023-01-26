package com.theteapottroopers.farmwatch.exception;

/**
 * @Author is Berend Boksma <b.boksma@st.hanze.nl>
 * <p>
 * An Animal not found exception
 */
public class AnimalNotFoundException extends RuntimeException{
    public AnimalNotFoundException(String message) {
        super(message);
    }
}

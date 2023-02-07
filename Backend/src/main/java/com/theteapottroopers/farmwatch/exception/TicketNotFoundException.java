package com.theteapottroopers.farmwatch.exception;

/**
 * @author Dave Thijs <d.thijs@st.hanze.nl>
 * <p>
 * A Ticket not found Exception
 */
public class TicketNotFoundException extends RuntimeException{
    public TicketNotFoundException(String message) {
        super(message);
    }
}

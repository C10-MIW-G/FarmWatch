package com.theteapottroopers.farmwatch.security.event;


import com.theteapottroopers.farmwatch.security.user.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class OnForgotPasswordEvent extends ApplicationEvent {

    private User user;

    public OnForgotPasswordEvent(User user) {
        super(user);
        this.user = user;
    }
}

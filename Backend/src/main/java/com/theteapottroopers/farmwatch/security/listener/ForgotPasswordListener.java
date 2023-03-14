package com.theteapottroopers.farmwatch.security.listener;

import com.theteapottroopers.farmwatch.security.event.OnForgotPasswordEvent;
import com.theteapottroopers.farmwatch.security.user.User;
import com.theteapottroopers.farmwatch.service.UserService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

@Component
public class ForgotPasswordListener implements ApplicationListener<OnForgotPasswordEvent> {

    @Autowired
    private UserService userService;
    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void onApplicationEvent(OnForgotPasswordEvent event) {
        this.resetPassword(event);
    }

    private void resetPassword(OnForgotPasswordEvent event) {
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        userService.createForgotPasswordToken(user, token);

        String recipientAddress = user.getEmail();
        String subject = "Password Reset FarmWatch";
        String confirmationUrl = "http://localhost:4200/resetPassword?token=" + token;

        try {
            MimeMessage mailMessage = mailSender.createMimeMessage();
            mailMessage.setSubject(subject, "UTF-8");

            MimeMessageHelper helper = new MimeMessageHelper(mailMessage, true, "UTF-8");
            helper.setTo(recipientAddress);
            try {
                helper.setFrom(new InternetAddress("farmwatchmail@gmail.com", "FarmWatch"));
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
            helper.setText("<p>Hello " + user.getFullName() + ",</p>\n" +
                    "\n" +
                    "<p>Did you forget your password?</p>\n" +
                    "\n" +
                    "<p>This link will expire in 24 hours and can be used only once.</p>\n" +
                    "\n" +
                    "<a href=\"" + confirmationUrl + "\">Reset password</a>\n" +
                    "\n" +
                    "If you don't want to change your password or didn't request this, please ignore and delete this message.\n" +
                    "\n" +
                    "<p>Team FarmWatch</p>", true);

            mailSender.send(mailMessage);
        } catch (MessagingException ex) {
            throw new RuntimeException(ex);
        }
    }
}

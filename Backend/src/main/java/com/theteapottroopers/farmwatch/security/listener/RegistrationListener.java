package com.theteapottroopers.farmwatch.security.listener;

import com.theteapottroopers.farmwatch.security.event.OnRegistrationCompleteEvent;
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
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    @Autowired
    private UserService userService;
    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event){
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        userService.createVerificationToken(user, token);

        String recipientAddress = user.getEmail();
        String subject = "Registration Confirmation FarmWatch";
        String confirmationUrl = "http://localhost:4200/registrationConfirmation?token=" + token;

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
                    "<p>Congratulations and welcome to FarmWatch! We are thrilled to inform you that your account has been successfully created.</p>\n" +
                    "\n" +
                    "<p>Activate your account to gain access to information about the animals in your local petting zoo, and help the caretakers by reporting any injured animals.</p>\n" +
                    "\n" +
                    "<p>To activate your account, please click on the link below:</p>\n" +
                    "\n" +
                    "<a href=\"" + confirmationUrl + "\">Activate account</a>\n" +
                    "\n" +
                    "<p>Team FarmWatch</p>", true);

            mailSender.send(mailMessage);
        }catch(MessagingException ex){
            throw new RuntimeException(ex);
        }
    }
}

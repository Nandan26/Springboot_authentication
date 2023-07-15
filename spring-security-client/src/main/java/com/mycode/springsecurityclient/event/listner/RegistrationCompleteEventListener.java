package com.mycode.springsecurityclient.event.listner;

import com.mycode.springsecurityclient.entity.User;
import com.mycode.springsecurityclient.event.RegistrationCompleteEvent;
import com.mycode.springsecurityclient.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {
    @Autowired
    private UserService userService;
    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        // create the verification token for the user with the link
        User user = event.getUser();
        String token = UUID.randomUUID().toString();

        userService.saveVerificationTokenForUSer(token, user);

        // send mail to user

        String url = event.getApplicationUrl() + "/verifyRegistration?token=" + token;

//        consider below logging functionality as same as sending email
        log.info("click into verify your account {}", url);
    }
}

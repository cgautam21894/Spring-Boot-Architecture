package com.Shopping.Myshop.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

//https://howtodoinjava.com/spring-boot2/send-email-with-attachment/
//https://techblogstation.com/spring-boot/spring-boot-send-email-with-attachment/

@Configuration
public class EmailConfig {

    @Autowired
    static
    JavaMailSender javaMailSender;

    @Bean
    public SimpleMailMessage emailTemplate() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(new String[]{"cgautam21894@gmail.com", "cgautam21894test@gmail.com",
                "satishkathiriya99@gmail.com", "sagarhmalaviya44@gmail.com"});
        message.setSubject("Spring Boot Mail Test ");
        message.setText("Kya bolati public???");
        return message;

    }

}

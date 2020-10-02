package com.Shopping.Myshop.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

//https://howtodoinjava.com/spring-boot2/send-email-with-attachment/
//https://techblogstation.com/spring-boot/spring-boot-send-email-with-attachment/

@Configuration
public class EmailWithAttachment {

    @Autowired
    JavaMailSender javaMailSender;

    public void sendMailWithAttachment() {

        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(new String[]{"cgautam21894@gmail.com","cgautam21894test@gmail.com",
                    "satishkathiriya99@gmail.com", "sagarhmalaviya44@gmail.com"});

            helper.setSubject("Mail with Attachment tutorial");
            helper.setText("Kya Bolti Hain Public?? Misal Hojaaye kal");
            FileSystemResource file = new FileSystemResource("C:\\Users\\Gautam\\Desktop\\Gautam_Chauhan.pdf");
            helper.addAttachment(file.getFilename(), file);
        } catch (MailException | MessagingException e) {
            throw new RuntimeException("Error while sending attachment" + e);
        }
        javaMailSender.send(message);
    }


}



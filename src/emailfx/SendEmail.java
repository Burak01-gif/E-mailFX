/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emailfx;

import java.util.Date;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.MimeMessage;
/**
 *
 * @author LENOVO
 */
public class SendEmail {
   public static void main(String[] args) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.from", "advjava0@gmail.com");
        
        Authenticator a = new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication("advjava0@gmail.com", "osmanburak");
            }
        };
        
        Session session = Session.getDefaultInstance(props, a);

        try {
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(); //from is empty
            msg.setRecipients(Message.RecipientType.TO, "advjava0@gmail.com");
            msg.setSubject("JavaMail hello world example");
            msg.setSentDate(new Date());
            msg.setText("Hello, world!\n");
            Transport.send(msg);

        } catch (MessagingException mex) {
            System.out.println("Send failed, exception: " + mex);
        }

    }
}

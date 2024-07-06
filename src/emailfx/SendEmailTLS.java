/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emailfx;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.InternetAddress;

/**
 *
 * @author LENOVO
 */
public class SendEmailTLS {
    public static void main(String[] args){
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.starttis.enable", true);
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");  
        
        Authenticator a = new Authenticator() { 
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){ 
                return new  PasswordAuthentication("advjava0@gmail.com","osmanburak");}
        } ; 
        Session session = Session.getInstance(props, a);
        
        try{
            Address toAddress = new InternetAddress("advjava0@gmail.com");
            MimeMessage msg = new MimeMessage(session);
            msg.setSubject("Email example with tis");
            msg.setContent("<p>Hello!</p>" , "text/html");
            msg.setRecipient(Message.RecipientType.TO, toAddress);
            Transport.send(msg);
        }
        catch(MessagingException mex){
            System.out.println("Send failed, exception: " + mex);
        }
    }
}

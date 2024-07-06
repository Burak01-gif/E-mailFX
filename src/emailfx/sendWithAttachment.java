/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emailfx;

import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.activation.DataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author LENOVO
 */
public class sendWithAttachment {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.from", "advjava51@gmail.com");
        
        Authenticator a = new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication("advjava51@gmail.com", "gduumafmdfqfatwp");
            }
        };
        
        Session session = Session.getDefaultInstance(props, a);

        try {
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(); //from is empty
            msg.setRecipients(Message.RecipientType.TO, "advjava51@gmail.com");
            msg.setSubject("JavaMail attachment example");
            msg.setSentDate(new Date());
            Multipart mp = new MimeMultipart();
            BodyPart bp = new MimeBodyPart();
            bp.setText("Sending an attachment");
            mp.addBodyPart(bp);
            
            // Attachment
            BodyPart partForAtt = new MimeBodyPart();
            String filename ="getFile.jpg";
            DataSource source = new FileDataSource(filename);
            partForAtt.setDataHandler(new DataHandler(source));
            partForAtt.setFileName(filename);
            mp.addBodyPart(partForAtt);
            
            msg.setContent(mp);
            
            Transport.send(msg);
            System.out.println("E-mail sent!");

        } catch (MessagingException mex) {
            System.out.println("Send failed, exception: " + mex);
        }

    }
}

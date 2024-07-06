/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emailproject3;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
public class SendMail {
  public static Email emailItem;
  public static EmailList emailList = new EmailList();
  public static void main(String[] args) {


    try {
      createEmail();
    System.out.println("emailList.toStringSentEmailArrayList() => " + emailList.toStringSentEmailArrayList());
    } catch (MessagingException mex) {
      System.out.println("Send failed, exception: " + mex);
    }
  }

  public static void createEmail() throws MessagingException {
    /*
    Session session = createSession();
    MimeMessage msg = new MimeMessage(session);
    msg.setFrom(senderName);
    msg.setRecipients(Message.RecipientType.TO, receiverName);
    msg.setSubject(subject);
    msg.setSentDate(new Date());
    msg.setContentID("Inbox");
    Multipart mp = new MimeMultipart();
    BodyPart bp = new MimeBodyPart();
    bp.setText("Sending an attachment");
    mp.addBodyPart(bp);

    // Attachment
    BodyPart partForAtt = new MimeBodyPart();
    String filename ="trial.txt";
    DataSource source = new FileDataSource(filename);
    partForAtt.setDataHandler(new DataHandler(source));
    partForAtt.setFileName(filename);
    mp.addBodyPart(partForAtt);

    msg.setContent(mp);
    //boolean hasAttachment = true;
//      if (!filename.equals("") && !filename.equals("null")) {
//        hasAttachment = true;
//      }
    Transport.send(msg);
    System.out.println("E-mail sent!");
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    //Email emailItem = new Email(userName, receiverName, msg.getSubject(), formatter.format(msg.getSentDate()), msg.getContentID(), hasAttachment, filename);
*/
  }
  public static Session createSession() {
    String userName = "comp4102project3@hotmail.com";
    Properties props = new Properties();
    props.put("mail.smtp.host", "smtp.office365.com");
    props.put("mail.smtp.port", "587");
    props.put("mail.smtp.socketFactory.port", "587");
    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    props.put("mail.smtp.auth", "true");
    props.put("mail.from", userName);
    props.put("mail.smtp.starttls.enable", true);
    props.put("mail.smtp.starttls.required", false);

    String passwordString = "comp4102mailappproject3";
    javax.mail.Authenticator authenticator = new javax.mail.Authenticator() {
      @Override
      protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(userName, passwordString);
      }
    };
    Session session = Session.getDefaultInstance(props, authenticator);
    return session;
  }
}


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emailproject3;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Properties;

public class EmailController {
  public Button refreshButton;
  public Button composeNewButton;
  public Button replyButton;
  public ComboBox selectHostDropdown;
  public Button sendMailButton;
  public TextField sendMailToReceiver;
  public TextField sendMailSubject;
  public TextArea sendMailContent;
  public TextField sendMailAttachment;
  public TextField receiverPortAddress;
  public TextField senderPortAddress;
  public TextField hostAddress;
  public TextField userName;
  public TextField userPassword;

  public EmailList emailList = new EmailList();
  public Email emailItem;

  public void sendEmail() throws MessagingException {
    /*for(int i = 0; i < emailList.sentEmailArrayList.size(); i++) {
      emailList.sentEmailArrayList.get(i).getDate();
      emailList.sentEmailArrayList.get(i).getAttachment();
      emailList.sentEmailArrayList.get(i).getFolder();
      emailList.sentEmailArrayList.get(i).getReceiverName();
      emailList.sentEmailArrayList.get(i).getSenderName();
      emailList.sentEmailArrayList.get(i).getSubject();
    }*/
    Session session = createSession();

    Date sendDate = new Date();
    //String sendDate = emailList.sentEmailArrayList.get(emailList.sentEmailArrayList.size() - 1).getDate();
    String fileName = emailList.sentEmailArrayList.get(emailList.sentEmailArrayList.size() - 1).getAttachment().toString();
    String folderName = emailList.sentEmailArrayList.get(emailList.sentEmailArrayList.size() - 1).getFolder().toString();
    String receiverName = emailList.sentEmailArrayList.get(emailList.sentEmailArrayList.size() - 1).getReceiverName();
    String senderName = emailList.sentEmailArrayList.get(emailList.sentEmailArrayList.size() - 1).getSenderName();
    String subject = emailList.sentEmailArrayList.get(emailList.sentEmailArrayList.size() - 1).getSubject();

    MimeMessage msg = new MimeMessage(session);
    msg.setFrom(senderName);
    msg.setRecipients(Message.RecipientType.TO, receiverName);
    msg.setSubject(subject);
    msg.setSentDate(sendDate);
    msg.setContentID(folderName);
    Multipart mp = new MimeMultipart();
    BodyPart bp = new MimeBodyPart();
    bp.setText(sendMailContent.getText());
    mp.addBodyPart(bp);

    // Attachment
    BodyPart partForAtt = new MimeBodyPart();
//    String filename = "trial.txt";
    //String filename = sendMailAttachment.getText();
    DataSource source = new FileDataSource(fileName);
    partForAtt.setDataHandler(new DataHandler(source));
    partForAtt.setFileName(fileName);
    mp.addBodyPart(partForAtt);

    msg.setContent(mp);
    //boolean hasAttachment = true;
//      if (!filename.equals("") && !filename.equals("null")) {
//        hasAttachment = true;
//      }
    Transport.send(msg);
    System.out.println("E-mail sent!");
    //SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    //Email emailItem = new Email(userName, receiverName, msg.getSubject(), formatter.format(msg.getSentDate()), msg.getContentID(), hasAttachment, filename);

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
    return Session.getDefaultInstance(props, authenticator);
  }

  public void onSendMailButtonClicked(ActionEvent actionEvent) {
    try {
      emailItem = new Email("comp4102project3@hotmail.com", sendMailToReceiver.getText(), sendMailSubject.getText(), "20/12/2022 00:20:15", "Inbox", false, sendMailAttachment.getText());
      emailList.sentEmailArrayList.add(emailItem);
      sendEmail();
      Stage stage = (Stage) sendMailButton.getScene().getWindow();
      Parent table2 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Main.fxml")));
      Scene scene = new Scene(table2);
      stage.setScene(scene);
    } catch (Exception e) {
//      e.getStackTrace();
    }

  }

  public void onRefreshButtonClicked(ActionEvent actionEvent) {
  }

  public void onComposeNewButtonClicked(ActionEvent actionEvent) {
    try {
      Stage stage = (Stage) composeNewButton.getScene().getWindow();
      Parent table2 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Compose New.fxml")));
      Scene scene = new Scene(table2);
      //IP = IPtext.getText();
      stage.setScene(scene);
    } catch (Exception e) {
//      e.getStackTrace();
    }
  }

  public void onReplyButtonClicked(ActionEvent actionEvent) {

  }

  public void onSelectHostDropdownClicked(ActionEvent actionEvent) {
  }


}

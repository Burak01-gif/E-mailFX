/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emailproject3;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeBodyPart;

public class ReceiveEmail {

  public static EmailList emailList = new EmailList();

  public static void main(String[] args) {
    // Define Properties
    Properties props = new Properties();
    // Set the protocol
    props.put("mail.store.protocol", "imaps");
    // Create Session
    Session session = Session.getInstance(props, null);
    try {
      Store store = session.getStore("imaps");
      String userName = "comp4102project3@hotmail.com";
      String passwordString = "comp4102mailappproject3";
      store.connect("outlook.office365.com", userName, passwordString);
      //System.out.println(store);

      Folder inbox = store.getFolder("Inbox");
      inbox.open(Folder.READ_ONLY);

      //System.out.println(inbox.getMessageCount());
      for (int i = 1; i <= inbox.getMessageCount(); i++) {
        Message msg = inbox.getMessage(i);
        Address[] in = msg.getFrom();
        Object content = msg.getContent();
        /*if (content instanceof Multipart mp) {
          int partCount = mp.getCount();
          for (int j = 0; j < partCount; j++) {
            MimeBodyPart bp = (MimeBodyPart) mp.getBodyPart(j);
            if (Part.ATTACHMENT.equalsIgnoreCase(bp.getDisposition())) {
              bp.saveFile(bp.getFileName());
              //System.out.println("attachment is saved!");
            }
          }
        }*/
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Email emailItem = new Email(in[0].toString(), userName, msg.getSubject(), formatter.format(msg.getReceivedDate()), msg.getFolder().getFullName(), false, "null");
        emailList.receivedEmailArrayList.add(emailItem);
      }
      System.out.println("emailList.toStringReceivedEmailArrayList() => " + emailList.toStringReceivedEmailArrayList());
    } catch (Exception mex) {
      mex.printStackTrace();
    }

  }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package email;

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

/**
 *
 * @author Tugba
 */
public class ReceiveEmainWithAttachment {
    public static void main(String[] args) {
        // Define Properties
        Properties props = new Properties();
        // Set the protocol
        props.put("mail.store.protocol", "imaps");
        // Create Session
        Session session = Session.getInstance(props, null);
        
        try{
            Store store = session.getStore("imaps");
            store.connect("imap.gmail.com", "advjava51@gmail.com", "gduumafmdfqfatwp");
            System.out.println(store);
            
            Folder inbox = store.getFolder("Inbox");
            inbox.open(Folder.READ_ONLY);
            
            System.out.println(inbox.getMessageCount());
            
            for(int i= 1; i <= inbox.getMessageCount();i++){
                System.out.println("Message " + (i));
                Message msg = inbox.getMessage(i);
                Address[] in = msg.getFrom();
                for(Address address: in)
                    System.out.println("FROM " + address.toString());
                Object content = msg.getContent();
                if(content instanceof Multipart){
                    Multipart mp = (Multipart) content;
                    int partCount = mp.getCount();
                    for(int j = 0; j <partCount; j++){
                        MimeBodyPart bp = (MimeBodyPart)mp.getBodyPart(j);
                        if(Part.ATTACHMENT.equalsIgnoreCase(bp.getDisposition())){
                            bp.saveFile(bp.getFileName());
                            System.out.println("attachment is saved!");
                        }
                    }
                }
                
            }        
        }
        catch(Exception mex){
            mex.printStackTrace();
        }
        
    }
}
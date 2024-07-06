/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emailfx;

import java.util.Properties;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Store;

/**
 *
 * @author LENOVO
 */
public class ReceiveEmail {
    public static void main(String[] args){
        //Define Properties
        Properties props = new Properties();
        //Set this protocol
        props.put("mail.store.protocol", "imaps");
        //Create Session
        Session session = Session.getInstance(props, null);
        
        try{
            Store store = session.getStore("imaps");
            store.connect("imap.gmail.com","advjava51@gmail.com" , "gduumafmdfqiatwp");
            System.out.println(store);
            
            Folder inbox = store.getFolder("Inbox");
            inbox.open(Folder.READ_ONLY);
            
            System.out.println(inbox.getMessageCount());
            
            //e-mails are stored with indices starting from 1
            Message msg = inbox.getMessage(2);
            
            Address[] in = msg.getFrom();
            for(Address address: in)
                System.out.println("FROM" + address.toString());
            
            Object content = msg.getContent();
            if(content instanceof String){
                System.out.println("This is a simple string");
                String body = (String) content;
                System.out.println(body);
            }
            else if(content instanceof Multipart){
                System.out.println("This is a multiple string");
                Multipart mp = (Multipart) content;
                BodyPart bp = mp.getBodyPart(0);
                System.out.println("SENT DATE " + msg.getSentDate());
                System.out.println("SUBJECT " + msg.getSubject());
                System.out.println("CONTENT " + bp.getContent());
            }
            
        }
        catch(Exception mex){
            mex.printStackTrace();
        }
        
    }
}

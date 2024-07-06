/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emailproject3;

import java.util.ArrayList;

public class EmailList {
  public ArrayList<Email> receivedEmailArrayList = new ArrayList<>();
  public ArrayList<Email> sentEmailArrayList = new ArrayList<>();

  public String toStringReceivedEmailArrayList() {
    StringBuilder content = new StringBuilder();
    for (Email email : receivedEmailArrayList) {
      content.append(email.toString());
    }
    return content.toString();
  }
  public String toStringSentEmailArrayList() {
    StringBuilder content = new StringBuilder();
    for (Email email : sentEmailArrayList) {
      content.append(email.toString());
    }
    return content.toString();
  }
}

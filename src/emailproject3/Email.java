
package emailproject3;

public class Email {
  private String senderName;
  private String receiverName;
  private String subject;
  private String folder;
  private String date;
  private Object attachment;
  private static int counter = 0;
  private int emailId = 0;
  public Email(String senderName, String receiverName, String subject, String date, String folder, boolean hasAttachment, String attachmentName) {
    setDate(date);
    setSenderName(senderName);
    setReceiverName(receiverName);
    setSubject(subject);
    setFolder(folder);
    if (hasAttachment && !attachmentName.equals("null")) {
      setAttachment(attachmentName);
    }
    counter++;
    setEmailId(counter);
  }


  @Override
  public String toString() {
    return String.format("\nEmail #%s:\n\tFrom:%s\n\tTo:%s\n\tSubject:%s\n\tFolder:%s\n\tDate:%s\n\tAttachment:%s\n",
        getEmailId(), getSenderName(), getReceiverName(), getSubject(), getFolder(), getDate(), getAttachment());
//    return "Email{" +
//        "senderName='" + senderName + '\'' +
//        ", subject='" + subject + '\'' +
//        ", folder='" + folder + '\'' +
//        ", date=" + date +
//        ", attachment=" + attachment +
//        '}';
  }

  public String getSenderName() {
    return senderName;
  }

  public void setSenderName(String senderName) {
    this.senderName = senderName;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public Object getAttachment() {
    return attachment;
  }

  public void setAttachment(Object attachment) {
    this.attachment = attachment;
  }

  public String getFolder() {
    return folder;
  }

  public void setFolder(String folder) {
    this.folder = folder;
  }

  public int getId() {
    return counter;
  }

  public String getReceiverName() {
    return receiverName;
  }

  public void setReceiverName(String receiverName) {
    this.receiverName = receiverName;
  }

  public int getEmailId() {
    return emailId;
  }

  public void setEmailId(int emailId) {
    this.emailId = emailId;
  }
}

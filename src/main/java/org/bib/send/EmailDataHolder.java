package org.bib.send;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.*;

public class EmailDataHolder {
    private static String to;
    private static String cc;
    private static String bcc;
    private static String subject;
    private static String body;
    private static String filename;

    public EmailDataHolder(String txtTO, String txtCC, String txtBCC, String txtSubject, String txtBody, String txtFilename) {
        this.setTo(txtTO);
        this.setCc(txtCC);
        this.setBcc(txtBCC);
        this.setSubject(txtSubject);
        this.setBody(txtBody);
        this.setFilename(txtFilename);
    }

    public static String getTo() {
        return to;
    }
    public void setTo(String to) {
        this.to = to;
    }
    public static String getCc() {
        return cc;
    }
    public void setCc(String cc) {
        this.cc = cc;
    }
    public static String getBcc() {
        return bcc;
    }
    public void setBcc(String bcc) {
        this.bcc = bcc;
    }
    public static String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public static String getFilename() {
        return filename;
    }
    public void setFilename(String filename) {
        this.filename = filename;
    }
    public static String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }

    public static boolean validateFieldIfEmpty(String... emails) {
        boolean status = false;
        for (String arr: emails) {
            if(!(arr.equals(""))){
                status = true;
                break;
            }
        }
        return status;
    }

    public static MimeMessage addRecipients(MimeMessage message) throws MessagingException {
        //for sending 1 to MANY recipient
        addRecipients(getTo(), Message.RecipientType.TO, message);
        //for CC
        addRecipients(getCc(), Message.RecipientType.CC, message);
        //for BCC
        addRecipients(getBcc(), Message.RecipientType.BCC, message);

        return message;
    }

    private static void addRecipients(String em, Message.RecipientType type, MimeMessage message) throws MessagingException {
        if (!(em.equals(""))) {
            String[] emailAddresses = em.split(",");
            for (String email : emailAddresses) {
                String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
                if (email.matches(EMAIL_PATTERN)) {
                    message.addRecipient(type, new InternetAddress(email));
                }else{
                    JOptionPane.showMessageDialog(null,  "Invalid Email Address [" + email + "] with a Recipient type : " + type, "Email Error",JOptionPane.ERROR_MESSAGE);
                    break;
                }
            }
        }
    }


}

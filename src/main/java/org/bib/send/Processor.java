package org.bib.send;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class Processor extends EmailDataHolder{


    public Processor(String txtTO, String txtCC, String txtBCC, String txtSubject, String txtBody, String txtFilename) {
        super(txtTO, txtCC, txtBCC, txtSubject, txtBody, txtFilename);
    }

    public static void processMessage() {
        try {
            MimeMessage message = new MimeMessage(Processor.server());
            message.setFrom(new InternetAddress("olivaresmarkanthony14@outlook.com"));
            message.setSubject(getSubject());
            message.setText(getBody());
            message = addRecipients(message);
            Processor.addMultipart(message);
            System.out.println("sending...");

            try{
                Transport.send(message);
                System.out.println("Sent message successfully....");
            }catch (Exception e){
                JOptionPane.showMessageDialog(null,  "Please make sure the email is valid!\nShould be like: sample@address.com","Email Error", JOptionPane.WARNING_MESSAGE);
            }

        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

    static Session server() {

        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", "smtp-mail.outlook.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("olivaresmarkanthony14@outlook.com", "09095082474Oliy");
            }
        });
        session.setDebug(true);

        return session;
    }

    public static void addMultipart(MimeMessage message) throws MessagingException {

        //for FILE ATTACHMENT
        if (!(getFilename().equals(""))) {
            Multipart multipart = new MimeMultipart();
            MimeBodyPart attachmentPart = new MimeBodyPart();
            MimeBodyPart textPart = new MimeBodyPart();

            try {
                File file = new File(getFilename());
                attachmentPart.attachFile(file);
                textPart.setText("Attached File Sent! <3");
                multipart.addBodyPart(textPart);
                multipart.addBodyPart(attachmentPart);
            } catch (IOException e) {
                e.printStackTrace();
            }
            message.setContent(multipart);
        }

    }
}

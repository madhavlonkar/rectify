package com.rentapp.utility;

import java.io.File;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailService {

    private Session createEmailSession() {
        Properties connectionProperties = new Properties();
        connectionProperties.put("mail.smtp.host", "smtp-relay.sendinblue.com");
        connectionProperties.put("mail.smtp.auth", "true");
        connectionProperties.put("mail.smtp.socketFactory.port", "587");
        connectionProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        connectionProperties.put("mail.smtp.port", "587");

        return Session.getDefaultInstance(connectionProperties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("vayavi6626@yasiok.com", "ZDgPTAzQnXOcsESY");
            }
        });
    }

    public void sendEmail(String recipientEmail, String subject, String messageBody) {
//    	String filePath=null;
        try {
            Session session = createEmailSession();
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("java88pro@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject(subject);

            MimeBodyPart textBodyPart = new MimeBodyPart();
            textBodyPart.setText(messageBody);

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(textBodyPart);

//            if (filePath != null && !filePath.isEmpty()) {
//                MimeBodyPart fileBodyPart = new MimeBodyPart();
//                fileBodyPart.attachFile(new File(filePath));
//                multipart.addBodyPart(fileBodyPart);
//            }

            message.setContent(multipart);

            Transport.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

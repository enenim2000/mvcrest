package com.etranzact.mvcrest;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class MailTask implements Runnable {

    private MailHelper mailHelper = new MailHelper();
    private Mail mail;

    public MailTask(Mail mail) {
        this.mail = mail;
    }

   @Override
    public void run() {
        System.out.println("About to send mail to: " + mail.getTo());
        sendMail();
        System.out.println("Mail sent to:" + mail.getTo());
    }

    private void sendMail(){
        try {
            mailHelper.sendMail(this.mail);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private class MailHelper {
        void sendMail(Mail mail) throws MessagingException {
            Properties props = new Properties();
            props.put("mail.smtp.host", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");

            Session session = Session.getInstance(props, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("enenim2000@gmail.com", "password");
                }
            });

            try {
                MimeMessage msg = new MimeMessage(session);
                String to = mail.getTo(); //"enenim2000@yahoo.com,basso4real2000@gmail.com";
                InternetAddress[] address = InternetAddress.parse(to, true);
                msg.setRecipients(Message.RecipientType.TO, address);
                String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                msg.setSubject("Sample Mail : " + timeStamp);
                msg.setSentDate(new Date());
                msg.setText("Sample System Generated mail");
                msg.setHeader("XPriority", "1");
                Transport.send(msg);
                System.out.println("Mail has been sent successfully");
            } catch (MessagingException mex) {
                System.out.println("Unable to send an email" + mex);
            }
        }
    }
}
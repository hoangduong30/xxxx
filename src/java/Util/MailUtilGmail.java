/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
public class MailUtilGmail {
    public static void sendMail(String to,String from,String subject, String body,boolean bodyIsHTML)
            throws MessagingException
    {
    Properties pros= new Properties();
    pros.put("mail.transport.protocol", "smtps");
    pros.put("mail.smtps.host", "smtp.gmail.com");
    pros.put("mail.smtps.port", 465);
    pros.put("mail.smtps.auth", "true");
    pros.put("mail.smtps.quitwait", "false");
    Session session =Session.getDefaultInstance(pros);
    session.setDebug(true);
    
    //Create massage
    Message message = new MimeMessage(session);
    message.setSubject(subject);
    if(bodyIsHTML){
        message.setContent(body,"text/html");
    }else{
        message.setText(body);
    }
    //Address the message
    Address fromAddress = new InternetAddress(from);
    Address toAddress = new InternetAddress(to);
    message.setFrom(fromAddress);
    message.setRecipient(Message.RecipientType.TO, toAddress);
    Transport transport = session.getTransport();
    transport.connect("ginmafia.dn@gmail.com","hoangduong30");
    transport.sendMessage(message,message.getAllRecipients());
    transport.close();
    }
}

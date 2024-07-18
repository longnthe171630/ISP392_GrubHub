package model;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {

    public boolean isValidEmail(String email) {
        // Implement your email validation logic here
        return email != null && email.contains("@");
    }

    public String subjectContact(String name) {
        return "Liên hệ từ " + name;
    }

    public String messageContact(String name) {
        return "Xin chào " + name + ",<br/><br/>Cảm ơn bạn đã liên hệ với chúng tôi. Chúng tôi sẽ kết nối với bạn trong thời gian sớm nhất.<br/><br/>Trân trọng,<br/>Nhóm hỗ trợ";
    }

    public void sendEmail(String subject, String message, String to) {
        String from = "manhndhe173383@fpt.edu.vn";
        String host = "smtp.gmail.com";
        final String username = "manhndhe173383@fpt.edu.vn";
        final String password = "boka etdm zdcb skbh"; // hoặc App Password nếu sử dụng 2-step verification

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(from));
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            mimeMessage.setSubject(subject, "UTF-8");
            mimeMessage.setText(message, "UTF-8", "html");

            Transport.send(mimeMessage);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}

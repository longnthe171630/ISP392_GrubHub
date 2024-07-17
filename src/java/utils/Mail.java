/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import dao.AccountDAO;
import dao.CustomerDAO;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import model.Account;
import model.Cart;
import model.Customer;


public class Mail {
    
    public boolean sendEmail(String to, String subject, String text) {
        // URL to which the request will be sent
        String url = "https://mail-sender-service.vercel.app/send-email";

        try {
            // Create a URL object
            URL apiUrl = new URL(url);

            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();

            // Set the request method
            connection.setRequestMethod("POST");

            // Enable input/output streams
            connection.setDoOutput(true);

            // Set the content type
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

            // Prepare the request payload
            String payload = "{\"to\":\"" + to + "\",\"subject\":\"" + subject + "\",\"text\":\"" + text + "\"}";

            // Write the payload to the output stream
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = payload.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            // Get the response code
            int responseCode = connection.getResponseCode();

            // Close the connection
            connection.disconnect();

            return responseCode == 200;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
       public static void sendOrderConfirmationEmail(Customer customer, Cart cart) {
        // Sender's email address
        final String username = "manhndhe173383@fpt.edu.vn"; // Your email address
        final String password = "boka etdm zdcb skbh"; // Your email password

        // SMTP server information
        String host = "smtp.gmail.com";
        int port = 587;
        
        AccountDAO accountDAO = new AccountDAO();
        CustomerDAO customerDAO = new CustomerDAO();
        Account account = customerDAO.getAccountByCustomerId(customer.getId());
        String mailCus = account.getEmail();
        
//        Order order = new Order();
//        OrderDetails od = new OrderDetails();
//        Product p = new Product();
        // Recipient's email address
        String to = mailCus; // Customer's email address

        // Email subject
        String subject = "Order Confirmation";

        // Email content
        String content = "Dear " + customer.getName() + ",\n\n"
                + "Thank you for your order.\n\n"
                + "Order Total: " + cart.getTotalMoney() + "vnđ" +"\n\n"
                + "We will process your order shortly.\n\n"
                + "Thank you for shopping with us!\n\n"
                + "Best regards,\n"
                + "GrubHub";

        // Set properties for the SMTP server
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

        // Create a Session object with authentication
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Create a default MimeMessage object
            Message message = new MimeMessage(session);

            // Set From: header field of the header
            message.setFrom(new InternetAddress(username));

            // Set To: header field of the header
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

            // Set Subject: header field
            message.setSubject(subject);

            // Set Content: text of the email message
            message.setText(content);

            // Send email
            Transport.send(message);

            System.out.println("Email sent successfully to " + to);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    
}

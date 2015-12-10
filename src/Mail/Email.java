package Mail;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.Properties;
import java.io.UnsupportedEncodingException;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;


/**
 * Created by xuch on 2015/12/7.
 */
public class Email {
    public static void main(String[] args) {

        String  d_email = "lyroechan@gmail.com",
                d_uname = "Name",
                d_password = "9887765432lyroechan",//"AIzaSyCeh2KTi99bSAyOaHu-TzZ9t0ChVHwjOUk",//"9887765432lyroechan",
                d_host = "smtp.gmail.com",
                d_port  = "465",
                m_to = "xuch@amazon.com",
                m_subject = "This is from JavaMail Test! ",// + params[0].getName(),
                m_text = "To whom maybe concerned,<br>" +
                        "This is from Amazon Auto-Email Notifying System.<br>" +
                        "Please don't reply to this email.<br><br>" +
                        "Best," +
                        "Regards.";
        Properties props = new Properties();
        props.put("mail.smtp.user", "xuch@amazon.com");
        props.put("mail.smtp.host", d_host);
        props.put("mail.smtp.port", d_port);
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.debug", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.socketFactory.port", d_port);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");

        //SMTPAuthenticator auth = new SMTPAuthenticator();
        //Session session = Session.getInstance(props, auth);
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                //return super.getPasswordAuthentication();
                return new PasswordAuthentication(d_email, d_password);
            }
        });
        session.setDebug(true);

        MimeMessage msg = new MimeMessage(session);
        try {
            msg.setSubject(m_subject);
            msg.setFrom(new InternetAddress("xuch@amazon.com"));
            //msg.addRecipient(Message.RecipientType.TO, new InternetAddress(m_to));
            //msg.addRecipient(Message.RecipientType.TO, InternetAddress.parse(m_to));
            msg.addRecipients(Message.RecipientType.TO, new InternetAddress[]{new InternetAddress("lyroechan@gmail.com"), new InternetAddress(m_to)});//, new InternetAddress("shiwuzhu@amazon.com")});
            //msg.setText(m_text);

            // set the body text
            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent("<strong> <font color = blue>" + m_text + "</strong> </font>", "text/html");
            //mimeBodyPart.setText(m_text);
            //mimeBodyPart.setHeader("Content-Type", "test/html; charset= \"UTF-8\"");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);

            // add the attachments
            String fileDir = new java.io.File(".").getCanonicalPath() + "\\src\\Mail\\", fileName = "ReadMe.txt";
            mimeBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(fileDir + fileName);
            mimeBodyPart.setDataHandler(new DataHandler(source));
            mimeBodyPart.setFileName(fileName);
            String contentType = Files.probeContentType(FileSystems.getDefault().getPath(fileDir, fileName));
            mimeBodyPart.setHeader("Content-Type", contentType + "; name =\"" + fileName + "\"");
            mimeBodyPart.setHeader("Content-Transfer-Encoding", "base64");

            multipart.addBodyPart(mimeBodyPart);

            msg.setContent(multipart);

            Transport transport = session.getTransport("smtps");
            transport.connect(d_host, Integer.valueOf(d_port), d_uname, d_password);
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();

        } catch (AddressException e) {
            e.printStackTrace();
            System.exit(-1);//return false;
        } catch (MessagingException e) {
            e.printStackTrace();
            System.exit(-1);//return false;
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }


        /*final String username = "lyroechan@gmail.com";
        final String password = "9887765432lyroechan";
        final String to = "xuch@amazon.com";

        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "25");//587 465

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            message.setSubject("Testing Subject");
            message.setText("Dear Mail Crawler,"
                    + "\n\n No spam to my email, please!");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }*/
    }
}


/*
    OAuth 用戶端
    這是您的用戶端 ID:
    319694605841-9jh8taudo5kg5ranoc2t2i1m6rec35aj.apps.googleusercontent.com
    您的用戶端密鑰如下:
    _wpBfsQenQmVZmlBJHss_L-x
 */

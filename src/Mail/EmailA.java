package Mail;

/**
 * Created by xuch on 2015/12/7.
 */
import java.util.Date;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class EmailA extends Object{

    public static void main(String [] args)
    {

        try{

            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.amazon.com"); // for gmail use smtp.gmail.com
            props.put("mail.smtp.auth", "true");
            props.put("mail.debug", "true");
            //props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.port", "25"); // 465 587
            //props.put("mail.smtp.socketFactory.port", "25"); // 587
            //props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            //props.put("mail.smtp.socketFactory.fallback", "false");

            Session mailSession = Session.getInstance(props, new javax.mail.Authenticator() {

                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("xuch@amazon.com", "LyroeChan@2345");
                }
            });

            mailSession.setDebug(true); // Enable the debug mode

            Message msg = new MimeMessage( mailSession );

            //--[ Set the FROM, TO, DATE and SUBJECT fields
            msg.setFrom( new InternetAddress( "xuch@amazon.com" ) );
            msg.setRecipients( Message.RecipientType.TO,InternetAddress.parse("shiwuzhu@amazon.com") );
            //msg.setRecipients(Message.RecipientType.TO, new InternetAddress[]{new InternetAddress("xujun@amazon.com"), new InternetAddress("xuch@amazon.com"),});
            msg.setSentDate( new Date());
            msg.setSubject( "Hello from my first e-mail sent with JavaMail!" );

            //--[ Create the body of the mail
            String m_text =
                    "To whom maybe concerned,<br>" +
                    "This is from Amazon Auto-Email Notifying System.<br>" +
                    "Please don't reply to this email.<br><br>" +
                    "Best," +
                    "Regards.";
            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent("<strong> <font color = black>" + m_text + "</strong> </font>", "text/html");
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);
            msg.setContent(multipart);

            //--[ Ask the Transport class to send our mail message
            Transport.send( msg );

        }catch(Exception E){
            System.out.println( "Oops something has gone pearshaped!");
            System.out.println( E );
        }
    }
}


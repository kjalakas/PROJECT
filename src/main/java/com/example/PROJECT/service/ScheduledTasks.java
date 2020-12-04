package com.example.PROJECT.service;

import com.example.PROJECT.repository.EmailData;
import com.example.PROJECT.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;

@Component
public class ScheduledTasks extends EventService {

    @Autowired
    private EventRepository repository;

    // 1000ms=1s e 60 000 = 1 min
    @Scheduled(fixedRate=60000)
    public void sendMessages() throws MessagingException {
        List<EmailData> emailData = repository.getEmailData1();
        List<EmailData> emailData2 = repository.getEmailData2();

        for (int i = 0; i < emailData.size(); i++) {

            String emailSubject = repository.getWelcomeText(emailData.get(i).getParticipantLanguage());
            emailSubject = emailSubject.replace("${event_date}", String.valueOf(emailData.get(i).getEventDate()));
            emailSubject = emailSubject.replace("${location}", emailData.get(i).getLocation());
            String emailText = repository.getPersonalText(emailData.get(i).getParticipantLanguage());
            emailText = emailText.replace("${giftee}", repository.getName(emailData.get(i).getEventId(), emailData.get(i).getGiftToId()));
            emailText = emailText.replace("${participant}", repository.getName(emailData.get(i).getEventId(), emailData.get(i).getParticipantId()));
            emailText = emailText.replace("${uuid}", repository.getUuid(emailData.get(i).getParticipantId(), emailData.get(i).getEventId()));
            sendMessage(emailData.get(i).getEmail(), emailSubject, emailText);
            repository.updateEmailSent(emailData.get(i).getParticipantId(), emailData.get(i).getEventId());
        }

        for (int i = 0; i < emailData2.size(); i++) {

            if (repository.wishEntered(emailData2.get(i).getGiftToId(), emailData2.get(i).getEventId()) != 0) {

                String emailSubject2 = repository.getWishlistWelcomeText(emailData2.get(i).getParticipantLanguage());
                emailSubject2 = emailSubject2.replace("${giftee}", repository.getName(emailData2.get(i).getEventId(), emailData2.get(i).getGiftToId()));
                String emailText2 = repository.getWishlistPersonalText(emailData2.get(i).getParticipantLanguage());
                emailText2 = emailText2.replace("${giftee}", repository.getName(emailData2.get(i).getEventId(), emailData2.get(i).getGiftToId()));
                emailText2 = emailText2.replace("${participant}", repository.getName(emailData2.get(i).getEventId(), emailData2.get(i).getParticipantId()));
                emailText2 = emailText2.replace("${kingisoovid}", repository.getWish(emailData2.get(i).getGiftToId(), emailData2.get(i).getEventId()));
                sendMessage(emailData2.get(i).getEmail(), emailSubject2, emailText2);
                repository.updateWishlistEmailSent(emailData2.get(i).getParticipantId(), emailData2.get(i).getEventId());

            }
        }
    }


    public void sendMessage(String email,
                            String EmailSubject,
                            String EmailText) throws MessagingException {
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("kingiloos2020", "polaroid999");
                    }
                });

        try { Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("kingiloos2020@gmail.com"));
        message.setRecipients(
                Message.RecipientType.TO, InternetAddress.parse(email));
        message.setSubject(EmailSubject);
        message.setContent(EmailText,"text/html;charset=UTF-8");
        Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


}

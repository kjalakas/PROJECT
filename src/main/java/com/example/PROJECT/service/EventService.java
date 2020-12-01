package com.example.PROJECT.service;

import com.example.PROJECT.controller.EventRequest;
import com.example.PROJECT.controller.ParticipantRequest;
import com.example.PROJECT.repository.EventRepository;
import com.example.PROJECT.repository.ParticipantEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.util.*;

@Service
public class EventService {

    @Autowired
    private EventRepository repository;

    //siia proge funtsioonid kogu äriloogikast

    @Transactional
    public int createEvent(EventRequest eventRequest) throws MessagingException {
        int eventId = repository.createEvent(eventRequest.getEventDate(), eventRequest.getEventLocation(), eventRequest.getEventLanguage());

        for (ParticipantRequest participant : eventRequest.getParticipants()) {
            repository.createParticipant(participant.getName(), participant.getEmail(), participant.getParticipantLanguage(),
                    eventId);
        }
        List<ParticipantEntity> eventParticipants = repository.getParticipantsByEventId(eventId);
        randomFunction(eventParticipants);

        for (int i = 0; i < eventParticipants.size(); i++) {
            sendMessage(eventParticipants.get(i).getEmail(),
                    "Sündmus toimub: " + eventRequest.getEventDate() + ", kohas: " + eventRequest.getEventLocation(),
                    "Sina teed kingi osalejale: " + repository.getName(eventId,eventParticipants.get(i).getGiftToId()));
        }
        return eventId;
    }

    public void randomFunction(List<ParticipantEntity> participantEntities) {
        List<Integer> randomArrayList = new ArrayList<>();
        // PEAB VÕTMA tabelist participant ühe konkreetse evendi ja
        // salvestama tulpa gift_to_id igale participant_id unikaalse random-i id,
        // mis ei tohi võrduda sama participant_id-ga e endale kingitusi ei tee :)
        // Samuti ei tohi korduda (unikaalsuse constraint) e üks inimene teeb ühe kingi
        // iga inimene peab tegema ja saama ühe kingi

        // LOE SISSE participandid nende participantId-dega RANDOMI JAOKS
        for (int i = 0; i < participantEntities.size(); i++) {
            randomArrayList.add(participantEntities.get(i).getParticipantId());
        }

        // Randomly sega numbrid randomArray listis
        Collections.shuffle(randomArrayList);

        for (int i = 0; i < participantEntities.size(); i++) {

            // Juhul kui esimene element randomArrayListis ei võrdu, siis teeb
            if (participantEntities.get(i).getParticipantId().equals(randomArrayList.get(0))!=true) {
                // uuendab randomArrayListis kohal 0 oleva elemendiga gift_to_id participandi tabelis
                repository.updateGiftToId(participantEntities.get(i).getParticipantId(), randomArrayList.get(0));
                // uuendab ka participantEntities klassis GiftToId
                participantEntities.get(i).setGiftToId(randomArrayList.get(0));
                // kustutab randomArraylistis esimese elemendi, (kogu array liigub vasakule)
                randomArrayList.remove(0);
                // Muidu lahuta i-st 1 ja sega randomArrayList uuesti ära, et esimene element ei oleks võrdne
            } else if (randomArrayList.size() > 1) {
                i--;
                Collections.shuffle(randomArrayList);
                // Juhul kui jääbki üks element lõppu, mis võrdub iseendaga, siis vaheta see
                // eelmisega (uuendab andmebaasis ära)
            } else {
                repository.updateGiftToId(participantEntities.get(i).getParticipantId(), participantEntities.get(i - 1).getGiftToId());
                repository.updateGiftToId(participantEntities.get(i - 1).getParticipantId(), randomArrayList.get(0));
            }
        }
    }

    public void sendMessage (String email,
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
    Message message = new MimeMessage(session);
    message.setFrom(new InternetAddress("kingiloos2020@gmail.com"));
    message.setRecipients(
    Message.RecipientType.TO, InternetAddress.parse(email));
    message.setSubject(EmailSubject);
    message.setText(EmailText);
    Transport.send(message);
    }
}


package com.example.PROJECT.service;

import com.example.PROJECT.controller.EventRequest;
import com.example.PROJECT.controller.ParticipantRequest;
import com.example.PROJECT.repository.EventRepository;
import com.example.PROJECT.repository.ParticipantEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
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
            int participantId = repository.createParticipant(participant.getName(), participant.getEmail(), participant.getParticipantLanguage(),
                    eventId);
            repository.createEmail(participantId, eventId);
        }
        List<ParticipantEntity> eventParticipants = repository.getParticipantsByEventId(eventId);
        randomFunction(eventParticipants);
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
            if (participantEntities.get(i).getParticipantId().equals(randomArrayList.get(0)) != true) {
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

}


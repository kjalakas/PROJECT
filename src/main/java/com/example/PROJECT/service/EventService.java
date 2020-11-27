package com.example.PROJECT.service;

import com.example.PROJECT.controller.EventRequest;
import com.example.PROJECT.controller.ParticipantRequest;
import com.example.PROJECT.repository.EventRepository;
import com.example.PROJECT.repository.ParticipantEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
public class EventService {

    @Autowired
    private EventRepository repository;

    //siia proge funtsioonid kogu äriloogikast

    public int createEvent(EventRequest eventRequest) {
        int eventId = repository.createEvent(eventRequest.getEventDate(), eventRequest.getEventLocation(), eventRequest.getEventLanguage());
        // TODO iterate over participants
        // TEEB SAMA ASJA for (int i = 1; i<=eventRequest.getParticipantRequests().size() ; i++) {
        // TEEB SAMA ASJA  ParticipantRequest participant = eventRequest.getParticipantRequests().get(i);
        for (ParticipantRequest participant : eventRequest.getParticipants()) {
            repository.createParticipant(participant.getName(), participant.getEmail(), participant.getParticipantLanguage(),
                    eventId);
        }
        List<ParticipantEntity> eventParticipants = repository.getParticipantsByEventId(eventId);
        randomFunction(eventParticipants, eventId);
        return eventId;
    }

    public void randomFunction(List<ParticipantEntity> participantEntities,
                               int eventId) {
        List<Integer> randomArrayList = new ArrayList<>();
        // PEAB VÕTMA tabelist participant ühe konkreetse evendi ja
        // salvestama tulpa gift_to_id igale participant_id unikaalse random-i id,
        // mis ei tohi võrduda sama participant_id-ga e endale kingitusi ei tee :)
        // ei tohi korduda (unikaalsuse constraint) e üks inimene teeb ühe kingi
        // iga inimene peab tegema ja saama ühe kingi

        // LOE SISSE participandid nende participantId-dega RANDOMI JAOKS


        for (int i = 0; i < participantEntities.size(); i++) {
            randomArrayList.add(participantEntities.get(i).getParticipantId());
        }

        // Randomly sega numbrid listis
        Collections.shuffle(randomArrayList);

        for (int i = 0; i <= participantEntities.size() - 1; i++) {
            if (participantEntities.get(i).getParticipantId() != randomArrayList.get(0)) {
                repository.updateGiftToId(participantEntities.get(i).getParticipantId(), randomArrayList.get(0));
                participantEntities.get(i).setGiftToId(randomArrayList.get(0));
                randomArrayList.remove(0);
            } else if (randomArrayList.size() > 1) {
                i--;
                Collections.shuffle(randomArrayList);
            } else {
                repository.updateGiftToId(participantEntities.get(i).getParticipantId(), participantEntities.get(i - 1).getGiftToId());
                repository.updateGiftToId(participantEntities.get(i - 1).getParticipantId(), randomArrayList.get(0));
            }
        }
    }
    /*
        if (a != participantId && a != randomArrayList[i]) {


        }
    }

    public static boolean checkunique(int[] Array) {
        for (int i = 0; i < Array.length - 1; i++) {
            for (int j = 1 + 1; j < Array.length; j++) {
                if (Array[i] == Array[j]) {
                    return true;
                }
            }
        }
        return false;
    }

     */

}


package com.example.PROJECT.service;

import com.example.PROJECT.controller.EventRequest;
import com.example.PROJECT.controller.ParticipantRequest;
import com.example.PROJECT.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return eventId;
    }

}

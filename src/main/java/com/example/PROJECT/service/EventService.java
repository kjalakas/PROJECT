package com.example.PROJECT.service;

import com.example.PROJECT.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    @Autowired
    private EventRepository repository;

    //siia proge funtsioonid kogu äriloogikast

    public int createEvent(Event event) {
        return repository.createEvent(event.getEventDate(), event.getLocation(),
                event.getNrOfParticipants(), event.getEventLanguage());
    }
}

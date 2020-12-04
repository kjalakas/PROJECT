package com.example.PROJECT.controller;


import com.example.PROJECT.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@RestController
public class GiftDrawManager {

    @Autowired
    private EventService eventService;

    //siia controlleri koodid

    @CrossOrigin
    @PostMapping("createEvent")
    public String createEvent(@RequestBody EventRequest eventRequest) throws MessagingException {
        return "Event created! Your Event ID is " + eventService.createEvent(eventRequest);
    }

    @CrossOrigin
    @PostMapping("wishlist")
    public void sendWish(@RequestBody UuidRequest uuidRequest) {
        eventService.sendWish(uuidRequest);
    }


}

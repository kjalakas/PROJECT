package com.example.PROJECT.controller;


import com.example.PROJECT.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

    @CrossOrigin
    @PostMapping("event/upload")
    public List<ParticipantRequest> upload(@RequestParam("texts") MultipartFile file) throws IOException {

        String content = new String(file.getBytes(), StandardCharsets.UTF_8);

        //Declare global arrays
        String[] stringArray = content.split("\n");
        int length= stringArray.length;
        List<ParticipantRequest> participantRequests = new ArrayList<>();

        for (int i = 1; i < length; i++) {
            String[] dual = stringArray[i].trim().split(",");
            ParticipantRequest participantRequest = new ParticipantRequest();
            participantRequest.setName(dual[0]);
            participantRequest.setEmail(dual[1]);
            participantRequest.setParticipantLanguage(dual[2]);
            participantRequests.add(participantRequest);
        }
        return participantRequests;
    }


}

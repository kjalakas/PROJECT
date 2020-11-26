package com.example.PROJECT.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EventRequest {
    // kuupäev
    private LocalDate eventDate;
    // asukoht
    private String eventLocation;
    // keel
    private String eventLanguage;

    private List<ParticipantRequest> participants = new ArrayList<>();

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public String getEventLanguage() {
        return eventLanguage;
    }

    public void setEventLanguage(String eventLanguage) {
        this.eventLanguage = eventLanguage;
    }

    public List<ParticipantRequest> getParticipants() {
        return participants;
    }

    public void setParticipants(List<ParticipantRequest> participants) {
        this.participants = participants;
    }
}

/*
    {
        "date": "",
        "location": "",
        "language": "",
        "participants": [
            {
                "name": "",
                "language": "",
                "email": ""
            },
            {
                "name": "",
                "language": "",
                "email": ""
            }
        ]
    }
 */

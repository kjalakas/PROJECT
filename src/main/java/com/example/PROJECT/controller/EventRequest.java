package com.example.PROJECT.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EventRequest {
    // kuupäev
    private LocalDate eventDate;
    // asukoht
    private String location;
    // keel
    private String language;

    private List<ParticipantRequest> participantRequests = new ArrayList<>();

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<ParticipantRequest> getParticipantRequests() {
        return participantRequests;
    }

    public void setParticipantRequests(List<ParticipantRequest> participantRequests) {
        this.participantRequests = participantRequests;
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

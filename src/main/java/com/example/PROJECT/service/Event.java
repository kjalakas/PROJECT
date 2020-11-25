package com.example.PROJECT.service;

import java.time.LocalDate;

public class Event {
    private Integer eventId;
    private LocalDate eventDate;
    private String location;
    private Integer nrOfParticipants;
    private String eventLanguage;

    public Event() {
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

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

    public Integer getNrOfParticipants() {
        return nrOfParticipants;
    }

    public void setNrOfParticipants(Integer nrOfParticipants) {
        this.nrOfParticipants = nrOfParticipants;
    }

    public String getEventLanguage() {
        return eventLanguage;
    }

    public void setEventLanguage(String eventLanguage) {
        this.eventLanguage = eventLanguage;
    }
}

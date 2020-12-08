package com.example.PROJECT.service;

import java.time.LocalDate;

public class Event {
    private Integer eventId;
    private LocalDate eventDate;
    private String location;
    private String eventLanguage;
    private Integer eventAmount;
    private String personalText;

    public String getPersonalText() {
        return personalText;
    }

    public Event setPersonalText(String personalText) {
        this.personalText = personalText;
        return this;
    }

    public Integer getEventAmount() {
        return eventAmount;
    }

    public Event setEventAmount(Integer eventAmount) {
        this.eventAmount = eventAmount;
        return this;
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

    public String getEventLanguage() {
        return eventLanguage;
    }

    public void setEventLanguage(String eventLanguage) {
        this.eventLanguage = eventLanguage;
    }
}

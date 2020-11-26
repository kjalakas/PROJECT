package com.example.PROJECT;

public class Participant {
    private Integer participantId;
    private Integer eventId;
    private String name;
    private String email;
    private Integer wishlistId;
    private String participantLanguage;

    public Participant() {
    }

    public Integer getPrticipantId() {
        return participantId;
    }

    public void setParticipantId(Integer participantId) {
        this.participantId = participantId;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getWishlistId() {
        return wishlistId;
    }

    public void setWishlistId(Integer wishlistId) {
        this.wishlistId = wishlistId;
    }

    public String getParticipantLanguage() {
        return participantLanguage;
    }

    public void setParticipantLanguage(String participantLanguage) {
        this.participantLanguage = participantLanguage;
    }
}


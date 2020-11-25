package com.example.PROJECT.controller;

public class ParticipantRequest {
    // nimi
    private String name;
    //email
    private String email;
    // keel
    private String participantLanguage;

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

    public String getParticipantLanguage() {
        return participantLanguage;
    }

    public void setParticipantLanguage(String participantLanguage) {
        this.participantLanguage = participantLanguage;
    }
}
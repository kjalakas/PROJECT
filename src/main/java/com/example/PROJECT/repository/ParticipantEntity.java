package com.example.PROJECT.repository;

public class ParticipantEntity {
    private Integer participantId;
    private Integer eventId;
    private String name;
    private String email;
    private Integer wishlistId;
    private String participantLanguage;
    private Integer giftToId;

    public Integer getParticipantId() {
        return participantId;
    }

    public ParticipantEntity setParticipantId(Integer participantId) {
        this.participantId = participantId;
        return this;
    }

    public Integer getEventId() {
        return eventId;
    }

    public ParticipantEntity setEventId(Integer eventId) {
        this.eventId = eventId;
        return this;
    }

    public String getName() {
        return name;
    }

    public ParticipantEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public ParticipantEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public Integer getWishlistId() {
        return wishlistId;
    }

    public ParticipantEntity setWishlistId(Integer wishlistId) {
        this.wishlistId = wishlistId;
        return this;
    }

    public String getParticipantLanguage() {
        return participantLanguage;
    }

    public ParticipantEntity setParticipantLanguage(String participantLanguage) {
        this.participantLanguage = participantLanguage;
        return this;
    }

    public Integer getGiftToId() {
        return giftToId;
    }

    public ParticipantEntity setGiftToId(Integer giftToId) {
        this.giftToId = giftToId;
        return this;
    }
}

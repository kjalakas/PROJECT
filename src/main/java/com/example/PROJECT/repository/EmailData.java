package com.example.PROJECT.repository;

import java.util.Date;

public class EmailData {
    private Integer eventId;
    private Date eventDate;
    private String location;
    private String eventLanguage;
    private Integer participantId;
    private String name;
    private String email;
    private String wishlist;
    private String wishlistSentEmail;
    private String participantLanguage;
    private Integer giftToId;
    private Integer emailId;

    public Integer getEventId() {
        return eventId;
    }

    public EmailData setEventId(Integer eventId) {
        this.eventId = eventId;
        return this;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public EmailData setEventDate(Date eventDate) {
        this.eventDate = eventDate;
        return this;
    }

    public String getLocation() {
        return location;
    }

    public EmailData setLocation(String location) {
        this.location = location;
        return this;
    }

    public String getEventLanguage() {
        return eventLanguage;
    }

    public EmailData setEventLanguage(String eventLanguage) {
        this.eventLanguage = eventLanguage;
        return this;
    }

    public Integer getParticipantId() {
        return participantId;
    }

    public EmailData setParticipantId(Integer participantId) {
        this.participantId = participantId;
        return this;
    }

    public String getName() {
        return name;
    }

    public EmailData setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public EmailData setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getWishlist() {
        return wishlist;
    }

    public EmailData setWishlist(String wishlist) {
        this.wishlist = wishlist;
        return this;
    }

    public String getWishlistSentEmail() {
        return wishlistSentEmail;
    }

    public EmailData setWishlistSentEmail(String wishlistSentEmail) {
        this.wishlistSentEmail = wishlistSentEmail;
        return this;
    }

    public String getParticipantLanguage() {
        return participantLanguage;
    }

    public EmailData setParticipantLanguage(String participantLanguage) {
        this.participantLanguage = participantLanguage;
        return this;
    }

    public Integer getGiftToId() {
        return giftToId;
    }

    public EmailData setGiftToId(Integer giftToId) {
        this.giftToId = giftToId;
        return this;
    }

    public Integer getEmailId() {
        return emailId;
    }

    public EmailData setEmailId(Integer emailId) {
        this.emailId = emailId;
        return this;
    }
}

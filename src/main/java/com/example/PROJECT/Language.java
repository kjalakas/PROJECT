package com.example.PROJECT;

public class Language {
    private Integer languageId;
    private String languageDescription;
    private String welcomeText;
    private String personalMessageText;

    public Language() {
    }

    public Integer getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Integer languageId) {
        this.languageId = languageId;
    }

    public String getLanguageDescription() {
        return languageDescription;
    }

    public void setLanguageDescription(String languageDescription) {
        this.languageDescription = languageDescription;
    }

    public String getWelcomeText() {
        return welcomeText;
    }

    public void setWelcomeText(String welcomeText) {
        this.welcomeText = welcomeText;
    }

    public String getPersonalMessageText() {
        return personalMessageText;
    }

    public void setPersonalMessageText(String personalMessageText) {
        this.personalMessageText = personalMessageText;
    }
}

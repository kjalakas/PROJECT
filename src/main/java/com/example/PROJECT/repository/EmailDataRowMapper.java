package com.example.PROJECT.repository;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class EmailDataRowMapper implements RowMapper<EmailData> {

    @Override
    public EmailData mapRow(ResultSet resultSet, int i) throws SQLException {
        return new EmailData()
                .setEventId(resultSet.getInt("event_id"))
                .setEventDate(resultSet.getDate("event_date"))
                .setLocation(resultSet.getString("location"))
                .setEventLanguage(resultSet.getString("event_language"))
                .setEventAmount(resultSet.getInt("event_amount"))
                .setPersonalText(resultSet.getString("personal_text"))
                .setParticipantId(resultSet.getInt("participant_id"))
                .setName(resultSet.getString("name"))
                .setEmail(resultSet.getString("email"))
                .setParticipantLanguage(resultSet.getString("p_language"))
                .setGiftToId(resultSet.getInt("gift_to_id"))
                .setEmailId(resultSet.getInt("email_id"))
                .setWishlist(resultSet.getString("wishlist"))
                .setWishlistSentEmail(resultSet.getString("wishlist_sent_email"));

    }
}

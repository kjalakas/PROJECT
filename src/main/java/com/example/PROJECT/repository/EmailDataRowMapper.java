package com.example.PROJECT.repository;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmailDataRowMapper implements RowMapper<EmailData> {

    @Override
    public EmailData mapRow(ResultSet resultSet, int i) throws SQLException {
        return new EmailData()
                .setEventId(resultSet.getInt("event_id"))
                .setEventDate(resultSet.getDate("event_date"))
                .setLocation(resultSet.getString("location"))
                .setEventLanguage(resultSet.getString("event_language"))
                .setParticipantId(resultSet.getInt("participant_id"))
                .setName(resultSet.getString("name"))
                .setEmail(resultSet.getString("email"))
                .setWishlistId(resultSet.getInt("wishlist_id"))
                .setParticipantLanguage(resultSet.getString("p_language"))
                .setGiftToId(resultSet.getInt("gift_to_id"))
                .setEmailId(resultSet.getInt("email_id"));
    }
}

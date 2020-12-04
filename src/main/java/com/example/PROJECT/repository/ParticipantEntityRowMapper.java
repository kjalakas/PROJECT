package com.example.PROJECT.repository;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ParticipantEntityRowMapper implements RowMapper<ParticipantEntity> {

    @Override
    public ParticipantEntity mapRow(ResultSet resultSet, int i) throws SQLException {
        return new ParticipantEntity()
                .setParticipantId(resultSet.getInt("participant_id"))
                .setEventId(resultSet.getInt("event_id"))
                .setName(resultSet.getString("name"))
                .setEmail(resultSet.getString("email"))
                .setParticipantLanguage(resultSet.getString("p_language"))
                .setGiftToId(resultSet.getInt("gift_to_id"));
    }
}

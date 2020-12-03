package com.example.PROJECT.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.*;

@Repository
public class EventRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    //siia proge funktsioonid mis suhtlevad DB-ga

    public Integer createEvent(LocalDate eventDate,
                            String eventLocation,
                            String eventLanguage) {
        String sql = "INSERT INTO event (event_date, location, event_language)" +
                "VALUES (:eventDate, :eventLocation, :eventLanguage)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("eventDate", eventDate);
        paramMap.put("eventLocation", eventLocation);
        paramMap.put("eventLanguage", eventLanguage);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, new MapSqlParameterSource(paramMap), keyHolder);
        return (Integer) keyHolder.getKeys().get("event_id");
    }

    public Integer createParticipant(String name,
                                     String email,
                                     String participantLanguage,
                                     Integer eventId) {
        String sql = "INSERT INTO participant (event_id, name, email, p_language, uuid)" +
                "VALUES (:eventId,:name, :email, :participantLanguage, :uuid)";
        Map<String, Object> paramMap = new HashMap<>();
        UUID uuid = UUID.randomUUID();
        paramMap.put("eventId", eventId);
        paramMap.put("name", name);
        paramMap.put("email", email);
        paramMap.put("participantLanguage", participantLanguage);
        paramMap.put("uuid", uuid);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, new MapSqlParameterSource(paramMap), keyHolder);
        return (Integer) keyHolder.getKeys().get("participant_id");
    }

    public void createEmail(      Integer participantId,
                                  Integer eventId
                                  ) {
        String sql = "INSERT INTO email (participant_id, event_id)" +
                "VALUES (:participantId,:eventId)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("participantId", participantId);
        paramMap.put("eventId", eventId);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, new MapSqlParameterSource(paramMap), keyHolder);
        keyHolder.getKeys().get("email_id");
    }



    public void updateGiftToId(Integer participantId,
                               Integer giftToId) {
        String sql2 = "UPDATE participant SET gift_to_id=:giftToId " +
                " WHERE participant_id=:participantId";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("participantId", participantId);
        paramMap.put("giftToId", giftToId);
        jdbcTemplate.update(sql2, paramMap);
    }


    public List<ParticipantEntity> getParticipantsByEventId(int eventId) {
        String sql = "SELECT * FROM participant WHERE event_id = :eventId";
        Map<String, Integer> paramMap = new HashMap<>();
        paramMap.put("eventId", eventId);
        return jdbcTemplate.query(sql, paramMap, new ParticipantEntityRowMapper());
    }

    public List<EmailData> getEmailData() {
        String sql = "SELECT * FROM email\n" +
                "    JOIN event e on email.event_id = e.event_id\n" +
                "    JOIN participant p on email.participant_id = p.participant_id\n" +
                "where email_sent is NULL; ";
        Map<String, Integer> paramMap = new HashMap<>();
        return jdbcTemplate.query(sql, paramMap, new EmailDataRowMapper());
    }

    public void updateEmailSent(Integer participantId,
                               Integer eventId) {
        String sql2 = "UPDATE email SET email_sent=:emailSent " +
                " WHERE participant_id=:participantId and event_id=:eventId";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("participantId", participantId);
        paramMap.put("eventId", eventId);
        paramMap.put("emailSent",  new Timestamp((new Date()).getTime()));
        jdbcTemplate.update(sql2, paramMap);
    }

    public String getWelcomeText(String participantLanguage) {
        String sql = "SELECT welcome_text FROM language WHERE language_description=:participantLanguage";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("participantLanguage", participantLanguage);
        return jdbcTemplate.queryForObject(sql, paramMap, String.class);
    }

    public String getUuid(Integer participantId,
                          Integer eventId) {
        String sql = "SELECT uuid FROM participant WHERE participant_id=:participantId and event_id=:eventId";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("participantId", participantId);
        paramMap.put("eventId", eventId);
        return jdbcTemplate.queryForObject(sql, paramMap, String.class);
    }

    public String getPersonalText(String participantLanguage) {
        String sql = "SELECT personal_message_text FROM language WHERE language_description=:participantLanguage";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("participantLanguage", participantLanguage);
        return jdbcTemplate.queryForObject(sql, paramMap, String.class);
    }


    public String getName(int eventId,
                          Integer participantId) {
        String sql = "SELECT name FROM participant WHERE event_id=:eventId AND participant_id=:participantId";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("participantId", participantId);
        paramMap.put("eventId", eventId);
        return jdbcTemplate.queryForObject(sql, paramMap, String.class);
    }
}

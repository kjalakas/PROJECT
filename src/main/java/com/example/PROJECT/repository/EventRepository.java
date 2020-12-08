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
                            String eventLanguage,
                           Integer eventAmount,
                               String personalText) {
        String sql = "INSERT INTO event (event_date, location, event_language, event_amount, personal_text)" +
                "VALUES (:eventDate, :eventLocation, :eventLanguage, :eventAmount, :personalText)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("eventDate", eventDate);
        paramMap.put("eventLocation", eventLocation);
        paramMap.put("eventLanguage", eventLanguage);
        paramMap.put("eventAmount", eventAmount);
        paramMap.put("personalText", personalText);
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

    public List<EmailData> getEmailData1() {
        String sql = "SELECT * FROM email\n" +
                "    JOIN event e on email.event_id = e.event_id\n" +
                "    JOIN participant p on email.participant_id = p.participant_id\n" +
                "where email_sent is NULL; ";
        Map<String, Integer> paramMap = new HashMap<>();
        return jdbcTemplate.query(sql, paramMap, new EmailDataRowMapper());
    }

    public List<EmailData> getEmailData2() {
        String sql = "SELECT * FROM email\n" +
                "    JOIN event e on email.event_id = e.event_id\n" +
                "    JOIN participant p on email.participant_id = p.participant_id\n" +
                "where wishlist_sent_email is NULL; ";
        Map<String, Integer> paramMap = new HashMap<>();
        return jdbcTemplate.query(sql, paramMap, new EmailDataRowMapper());
    }

    public void updateWishlistEmailSent(Integer participantId,
                                Integer eventId) {
        String sql2 = "UPDATE participant SET wishlist_sent_email=:wishlistSentEmail " +
                " WHERE participant_id=:participantId and event_id=:eventId";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("participantId", participantId);
        paramMap.put("eventId", eventId);
        paramMap.put("wishlistSentEmail",  new Timestamp((new Date()).getTime()));
        jdbcTemplate.update(sql2, paramMap);
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



    public String getUuid(Integer participantId,
                          Integer eventId) {
        String sql = "SELECT uuid FROM participant WHERE participant_id=:participantId and event_id=:eventId";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("participantId", participantId);
        paramMap.put("eventId", eventId);
        return jdbcTemplate.queryForObject(sql, paramMap, String.class);
    }

    public String getWish(Integer participantId,
                          Integer eventId) {
        String sql = "SELECT wishlist FROM participant WHERE participant_id=:participantId and event_id=:eventId";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("participantId", participantId);
        paramMap.put("eventId", eventId);
        return jdbcTemplate.queryForObject(sql, paramMap, String.class);
    }

    public int getAmount(Integer eventId) {
        String sql = "SELECT event_amount FROM event WHERE event_id=:eventId";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("eventId", eventId);
        return jdbcTemplate.queryForObject(sql, paramMap, Integer.class);
    }

    public String getPersonalMessage(Integer eventId) {
        String sql = "SELECT personal_text FROM event WHERE event_id=:eventId";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("eventId", eventId);
        return jdbcTemplate.queryForObject(sql, paramMap, String.class);
    }

    public Integer wishEntered(Integer participantId,
                          Integer eventId) {
        String sql = "SELECT count(wishlist) FROM participant WHERE participant_id=:participantId and event_id=:eventId;";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("participantId", participantId);
        paramMap.put("eventId", eventId);
        return jdbcTemplate.queryForObject(sql, paramMap, Integer.class);
    }

    public void sendWish(String uuid, String wishlist) {
        String sql2 = "UPDATE participant SET wishlist=:wishlist " +
                " WHERE uuid=:uuid";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("uuid", uuid);
        paramMap.put("wishlist", wishlist);
        jdbcTemplate.update(sql2, paramMap);
    }

    public String getWelcomeText(String participantLanguage) {
        String sql = "SELECT welcome_text FROM language WHERE language_description=:participantLanguage";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("participantLanguage", participantLanguage);
        return jdbcTemplate.queryForObject(sql, paramMap, String.class);
    }

    public String getPersonalText(String participantLanguage) {
        String sql = "SELECT personal_message_text FROM language WHERE language_description=:participantLanguage";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("participantLanguage", participantLanguage);
        return jdbcTemplate.queryForObject(sql, paramMap, String.class);
    }

    public String getWishlistWelcomeText(String participantLanguage) {
        String sql = "SELECT wishlist_welcome_text FROM language WHERE language_description=:participantLanguage";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("participantLanguage", participantLanguage);
        return jdbcTemplate.queryForObject(sql, paramMap, String.class);
    }

    public String getWishlistPersonalText(String participantLanguage) {
        String sql = "SELECT wishlist_personal_message_text FROM language WHERE language_description=:participantLanguage";
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

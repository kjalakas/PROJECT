package com.example.PROJECT.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Integer result = (Integer) keyHolder.getKeys().get("event_id");
        return result;
    }

    public void createParticipant(String name,
                                     String email,
                                     String participantLanguage,
                                     Integer eventId) {
        String sql = "INSERT INTO participant (event_id, name, email, p_language)" +
                "VALUES (:eventId,:name, :email, :participantLanguage)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("eventId", eventId);
        paramMap.put("name", name);
        paramMap.put("email", email);
        paramMap.put("participantLanguage", participantLanguage);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, new MapSqlParameterSource(paramMap), keyHolder);
        Integer result = (Integer) keyHolder.getKeys().get("participant_id");
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

    public String getEmail(int eventId,
                           Integer participantId) {
        String sql = "SELECT email FROM participant WHERE event_id=:eventId AND participant_id=:participantId";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("participantId", participantId);
        paramMap.put("eventId", eventId);
        String email = jdbcTemplate.queryForObject(sql, paramMap, String.class);
        return email;
    }

    public String getName(int eventId,
                          Integer participantId) {
        String sql = "SELECT name FROM participant WHERE event_id=:eventId AND participant_id=:participantId";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("participantId", participantId);
        paramMap.put("eventId", eventId);
        String name = jdbcTemplate.queryForObject(sql, paramMap, String.class);
        return name;
    }
}

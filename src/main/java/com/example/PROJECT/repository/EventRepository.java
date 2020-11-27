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

    public Integer getNumber(Integer eventId) {
        String sql = "SELECT count(participant_id) FROM participant WHERE event_id=:eventId";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("eventId", eventId);
        Integer dbNumber = jdbcTemplate.queryForObject(sql, paramMap, Integer.class);
        return dbNumber;
    }

    public Integer getMinId(Integer eventId) {
        String sql = "SELECT Min(participant_id) FROM participant WHERE event_id=:eventId";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("eventId", eventId);
        Integer minNumber = jdbcTemplate.queryForObject(sql, paramMap, Integer.class);
        return minNumber;
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

    public Integer getResult(Integer eventId) {
        String sql = "SELECT CASE WHEN EXISTS(SELECT gift_to_id FROM participant WHERE gift_to_id is NULL and event_id = :eventId) THEN 1 ELSE 0 END";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("eventId", eventId);
        return jdbcTemplate.queryForObject(sql, paramMap, Integer.class);
    }

    public List<ParticipantEntity> getParticipantsByEventId(int eventId) {
        String sql = "SELECT * FROM participant WHERE event_id = :eventId";
        Map<String, Integer> paramMap = new HashMap<>();
        paramMap.put("eventId", eventId);
        return jdbcTemplate.query(sql, paramMap, new ParticipantEntityRowMapper());
    }
}

package com.example.PROJECT.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Repository
public class EventRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    //siia proge funktsioonid mis suhtlevad DB-ga

    public Integer createEvent(LocalDate eventDate,
                            String location,
                            String eventLanguage) {
        String sql = "INSERT INTO event (event_date, location, event_language)" +
                "VALUES (:eventDate, :location, :eventLanguage)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("eventDate", eventDate);
        paramMap.put("location", location);
        paramMap.put("eventLanguage", eventLanguage);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, new MapSqlParameterSource(paramMap), keyHolder);
        Integer result = (Integer) keyHolder.getKeys().get("event_id");
        return result;
    }
}

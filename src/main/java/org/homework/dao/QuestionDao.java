package org.homework.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Roman.
 */
@Repository
public class QuestionDao {
    @Autowired
    private JdbcTemplate template;

    public void saveQuestion(String question, String countryCode) {
        template.update("insert into QUESTION VALUES (qidseq.nextval, ?, ?)", question, countryCode);
    }

    public List<Map<String, Object>> listQuestions() {
        return template.queryForList("select Q_TEXT , Q_COUNTRY_CODE from QUESTION");
    }

    public List<Map<String, Object>> listQuestions(String countryCode) {
        return template.queryForList("select Q_TEXT , Q_COUNTRY_CODE from QUESTION where Q_COUNTRY_CODE = ?", countryCode);
    }

    public List<Map<String,Object>> getBlacklistedWords(String[] values) {
        String inClause = Arrays.asList(values).stream().map(s -> "?").collect(Collectors.joining(", "));
        return template.queryForList(String.format("select * from words_blacklist where word in (%s)", inClause), values);
    }
}

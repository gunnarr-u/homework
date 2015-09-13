package org.homework.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    public List<String> listQuestions() {
        return template.queryForList("select q_text from QUESTION", String.class);
    }
}

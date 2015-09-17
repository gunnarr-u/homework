package org.homework.dao;

import java.util.List;
import java.util.Map;

/**
 * Created by rmashchenko on 9/17/2015.
 */
public interface QuestionDao {
    void saveQuestion(String question, String countryCode);

    List<Map<String, Object>> listQuestions();

    List<Map<String, Object>> listQuestions(String countryCode);

    List<Map<String,Object>> getBlacklistedWords(String[] values);
}

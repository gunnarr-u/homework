package org.homework.service;

import org.homework.dao.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Roman.
 */
@Service
public class BlacklistService {

    @Autowired
    private QuestionDao dao;

    public boolean checkForBlackList(String question) {
        String[] tokens = question.split("[^\\w]");
        List<Map<String, Object>> list = dao.getBlacklistedWords(tokens);
        return list.size() > 0;
    }
}

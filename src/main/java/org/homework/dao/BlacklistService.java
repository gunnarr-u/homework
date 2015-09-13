package org.homework.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Roman.
 */
@Service
public class BlacklistService {

    @Autowired
    private JdbcTemplate template;

    public boolean checkForBlackList(String question) {
        String[] tokens = question.split("[^\\w]");
        String inClause = Arrays.asList(tokens).stream().map(s -> "?").collect(Collectors.joining(", "));
        List<Map<String, Object>> list = template.queryForList(String.format("select * from words_blacklist where word in (%s)", inClause), tokens);
        return list.size() > 0;
    }
}

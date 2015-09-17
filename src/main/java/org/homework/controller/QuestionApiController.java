package org.homework.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by rmashchenko on 9/17/2015.
 */
public interface QuestionApiController {
    @RequestMapping("/ask")
    String askQuestion(HttpServletRequest request, @RequestParam("question") String question);

    @RequestMapping("/listQuestions")
    List<Map<String, Object>> listQuestions();

    @RequestMapping("/listQuestionsByCountryCode")
    List<Map<String, Object>> listQuestions(@RequestParam("countryCode") String countryCode);
}

package org.homework;

import org.homework.dao.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Roman.
 */
@RestController
public class QuestionApiController {


    @Autowired
    private QuestionDao dao;

    @RequestMapping("/ask")
    public String askQuestion(@RequestParam("question") String question) {
        dao.saveQuestion(question, "lv");
        return "Question: " + question + " Accepted";
    }

    @RequestMapping("/listQuestion")
    public List<String> listQuestions() {
        return dao.listQuestions();
    }
}

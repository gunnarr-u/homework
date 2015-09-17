package org.homework;

import org.homework.service.BlacklistService;
import org.homework.dao.QuestionDao;
import org.homework.service.GeolocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author Roman.
 */
@RestController
public class QuestionApiController {

    private static final Logger LOG = LoggerFactory.getLogger(QuestionApiController.class);

    @Autowired
    private QuestionDao dao;
    @Autowired
    private GeolocationService geolocationService;

    @Autowired
    private BlacklistService blacklistService;

    @RequestMapping("/ask")
    public String askQuestion(HttpServletRequest request, @RequestParam("question") String question) {

        if (blacklistService.checkForBlackList(question)) {
            return "Question: " + question + " not accepted";
        }
        String remoteAddr = request.getRemoteAddr();
        LOG.debug("Received new question: {} form IP: {}");
        String countryCode = geolocationService.getCountryCodeByIp(remoteAddr);
        dao.saveQuestion(question, countryCode);
        return "Question: " + question + " Accepted";
    }

    @RequestMapping("/listQuestions")
    public List<Map<String, Object>> listQuestions() {
        return dao.listQuestions();
    }

    @RequestMapping("/listQuestionsByCountryCode")
    public List<Map<String, Object>> listQuestions(@RequestParam("countryCode") String countryCode) {
        return dao.listQuestions(countryCode);
    }
}

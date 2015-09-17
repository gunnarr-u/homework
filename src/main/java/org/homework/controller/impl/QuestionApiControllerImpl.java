package org.homework.controller.impl;

import org.homework.controller.QuestionApiController;
import org.homework.dao.QuestionDao;
import org.homework.service.BlacklistService;
import org.homework.service.GeolocationService;
import org.homework.service.QuestionQuotaService;
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
public class QuestionApiControllerImpl implements QuestionApiController {

    private static final Logger LOG = LoggerFactory.getLogger(QuestionApiControllerImpl.class);

    @Autowired
    private QuestionDao dao;
    @Autowired
    private GeolocationService geolocationService;
    @Autowired
    private QuestionQuotaService questionQuotaService;

    @Autowired
    private BlacklistService blacklistService;

    @Override
    @RequestMapping("/ask")
    public String askQuestion(HttpServletRequest request, @RequestParam("question") String question) {
        String remoteAddr = request.getRemoteAddr();
        String countryCode = geolocationService.getCountryCodeByIp(remoteAddr);



        if (!questionQuotaService.getPermission(countryCode) || blacklistService.checkForBlackList(question)) {
            return "Question: " + question + " rejected";
        }
        LOG.debug("Received new question: {} form IP: {}");
        dao.saveQuestion(question, countryCode);
        return "Question: " + question + " Accepted";
    }

    @Override
    @RequestMapping("/listQuestions")
    public List<Map<String, Object>> listQuestions() {
        return dao.listQuestions();
    }

    @Override
    @RequestMapping("/listQuestionsByCountryCode")
    public List<Map<String, Object>> listQuestions(@RequestParam("countryCode") String countryCode) {
        return dao.listQuestions(countryCode);
    }
}

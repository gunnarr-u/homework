package org.homework.service.impl;

import com.google.common.util.concurrent.RateLimiter;
import org.homework.service.QuestionQuotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by rmashchenko on 9/17/2015.
 */
@Service
public class QuestionQuotaServiceImpl implements QuestionQuotaService {
    ConcurrentMap<String, RateLimiter> limiterConcurrentMap = new ConcurrentHashMap<>();
    private Double permitsPerSecond;

    @Autowired
    public QuestionQuotaServiceImpl(@Value("${question.rate.limit.per.country}")Double rate) {
        permitsPerSecond = rate;
    }

    @Override
    public boolean getPermission(String countryCode) {
        return limiterConcurrentMap.computeIfAbsent(countryCode, (cc) -> RateLimiter.create(permitsPerSecond)).tryAcquire();
    }
}

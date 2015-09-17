package org.homework.service;

import org.homework.service.impl.QuestionQuotaServiceImpl;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by rmashchenko on 9/17/2015.
 */
public class QuestionQuotaServiceTest {

    @Test
    public void testGetPermission() throws Exception {
        QuestionQuotaService quotaService = new QuestionQuotaServiceImpl(1d);
        assertTrue(quotaService.getPermission("LV"));
        assertFalse(quotaService.getPermission("LV"));
    }

    @Test
    public void testGetPermissionDifferentCountry() throws Exception {
        QuestionQuotaService quotaService = new QuestionQuotaServiceImpl(1d);
        assertTrue(quotaService.getPermission("LV"));
        assertTrue(quotaService.getPermission("RU"));
        assertFalse(quotaService.getPermission("LV"));
        assertFalse(quotaService.getPermission("RU"));
    }

    @Test
    public void testGetPermissionDifferentCountryPlusTimeout() throws Exception {
        QuestionQuotaService quotaService = new QuestionQuotaServiceImpl(1d);
        assertTrue(quotaService.getPermission("LV"));
        assertTrue(quotaService.getPermission("RU"));
        Thread.sleep(1001);
        assertTrue(quotaService.getPermission("LV"));
        assertTrue(quotaService.getPermission("RU"));
    }
}
package org.homework.service;

import org.homework.Country;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * @author Roman.
 */
@Service
public class GeolocationService {

    public static final String DEFAULT_CODE = "LV";

    public String getCountryCodeByIp(String ip) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            String countryCode = restTemplate.getForObject(String.format("http://www.telize.com/geoip/%s", ip), Country.class).getCountry_code();
            return countryCode != null ? countryCode : DEFAULT_CODE;
        } catch (RestClientException e) {
            return DEFAULT_CODE;
        }
    }
}

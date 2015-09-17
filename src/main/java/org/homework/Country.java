package org.homework;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Roman.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Country {
    private String country;
    private String country_code;

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }
}

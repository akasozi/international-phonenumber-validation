package com.exercise.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author Abu Bizibu
 * @created
 * @project
 */

@Service
public class PhoneNumberValidator {

    public static final Logger log = LoggerFactory.getLogger(PhoneNumberValidator.class);

    public boolean isPhoneNumberValid(String phoneNumber, String regex) {
         log.info("isPhoneNumberValid() | phoneNumber: {}, regex: {} ", phoneNumber, regex);
         return phoneNumber.matches(regex);
    }

    public boolean isPhoneNumberCountryCodeValid(String phoneNumber, String countryCode) {
        if (phoneNumber.equals("") || countryCode.equals(""))
            return false;
        log.info("isPhoneNumberCountryCodeValid() | phoneNumber: {}, countryCode: {}", phoneNumber, countryCode);
        String phoneNumberPrefix = phoneNumber.substring(0, phoneNumber.indexOf(" ")).replaceAll("[\\(\\)\\[\\]\\{\\}]", "");
        log.info("isPhoneNumberCountryCodeValid() | phoneNumber: {}, phoneNumberPrefix: {}", phoneNumber, phoneNumberPrefix);
        String sanitizedCountryCode = countryCode.replace("+", "");
        log.info("isPhoneNumberCountryCodeValid() | countryCode: {}, sanitizedCountryCode: {}", countryCode, sanitizedCountryCode);
        return phoneNumberPrefix.equalsIgnoreCase(sanitizedCountryCode);
    }
}

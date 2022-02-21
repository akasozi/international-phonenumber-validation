package com.exercise.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Abu Bizibu
 * @created
 * @project
 */
class PhoneNumberValidatorTest {

    PhoneNumberValidator phoneNumberValidator;
    @BeforeEach
    void setUp() {
        phoneNumberValidator = new PhoneNumberValidator();
    }

    @Test
    @DisplayName("Ensure that the method can validate phone numbers correctly when passed correct input")
    void itShouldValidatePhoneNumberCorrectly() {
        // Given
        String regex = "\\(251\\)\\ ?[1-59]\\d{8}$";
        String phoneNumber = "(251) 914701723";
        // When
        boolean isPhoneNumberValid = phoneNumberValidator.isPhoneNumberValid(phoneNumber, regex);
        // Then
        assertThat(isPhoneNumberValid).isTrue();
    }

    @Test
    @DisplayName("Ensure that the method can fails validation when passed incorrect input")
    void itShouldFailPhoneNumberValidationWhenPassedIncorrectPhoneNumbers() {
        // Given
        String regex = "\\(251\\)\\ ?[1-59]\\d{8}$";
        String[] phoneNumbers = {"(251) 914701", "", "+237"};
        boolean[] results = new boolean[3];
        int index = 0; //
        // When
        for (String phoneNumber: phoneNumbers) {
            results[index++] = phoneNumberValidator.isPhoneNumberValid(phoneNumber, regex);
        }
        // Then
        assertThat(results).containsExactly(false, false, false);
    }

    @Test
    @DisplayName("Ensure that the method can validate phone number country codes correctly when passed correct input")
    void itShouldPassPhoneNumberCountryCodeValid() {
        // Given
        String countryCode = "+251";
        String phoneNumber = "(251) 914701723";
        // When
        boolean isPhoneNumberCountryCodeValid = phoneNumberValidator.isPhoneNumberCountryCodeValid(phoneNumber, countryCode);
        // Then
        assertThat(isPhoneNumberCountryCodeValid).isTrue();
    }

    @Test
    @DisplayName("Ensure that the method can fails country code validation when passed incorrect input")
    void itShouldFailPhoneNumberCountryCodeValidationWhenPassedInvalidInput() {
        // Given
        String countryCode = "+251";
        String[] phoneNumbers = {"(212) 914701", "", "+237 2631662", "(256) 1234567"};
        boolean[] results = new boolean[4];
        int index = 0; //
        // When
        for (String phoneNumber: phoneNumbers) {
            results[index++] = phoneNumberValidator.isPhoneNumberCountryCodeValid(phoneNumber, countryCode);
        }
        // Then
        assertThat(results).containsExactly(false, false, false, false);
    }
}
package com.exercise.repositories;

import com.exercise.models.Country;
import com.exercise.models.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Abu Bizibu
 * @created
 * @project
 */
@DataJpaTest(
    properties = {
       "spring.jpa.properties.javax.persistence.validation.mode=none"
    }
)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CountryRepositoryTest {

    @Autowired
    private CountryRepository countryRepository;

    @BeforeEach
    void setUp() {

    }

    @Test
    @DisplayName("It should pass given the country exists")
    void itShouldFindByNameWhenCountryExists() {
        // Given
        String countryName = "Cameroon";
        // (String code, String name, String regex)
        Country cameroon = new Country("+237", "Cameroon", "\\(237\\)\\ ?[2368]\\d{7,8}$");
        // Then
        Optional<Country> optionalCountry = countryRepository.findByName(countryName);
        assertThat(optionalCountry)
                .isPresent()
                .hasValueSatisfying(country -> {
                    assertThat(country).isEqualToComparingFieldByField(cameroon);
                });
    }

    @Test
    @DisplayName("It should fail if the country does not exist")
    void itShouldNotFindByNameWhenCountryDoesNotExist() {
        // Given
        String countryName = "Jamaica";
        // When
        Optional<Country> optionalCountry = countryRepository.findByName(countryName);
        // Then
        assertThat(optionalCountry).isNotPresent();
    }

    @Test
    @DisplayName("It should fail if the country code does not exist")
    void itShouldNotFindByCodeWhenCountryCodeDoesNotExist() {
        // Given
        String countryCode = "+100";
        // When
        Optional<Country> optionalCountry = countryRepository.findByCode(countryCode);
        // Then
        assertThat(optionalCountry).isNotPresent();
    }

    @Test
    @DisplayName("It should pass given the country code exists")
    void itShouldFindByCodeWhenCountryExists() {
        // Given
        String countryCode = "+237";
        // (String code, String name, String regex)
        Country cameroon = new Country("+237", "Cameroon", "\\(237\\)\\ ?[2368]\\d{7,8}$");
        // Then
        Optional<Country> optionalCountry = countryRepository.findByCode(countryCode);
        assertThat(optionalCountry)
                .isPresent()
                .hasValueSatisfying(country -> {
                    assertThat(country).isEqualToComparingFieldByField(cameroon);
                });
    }

}
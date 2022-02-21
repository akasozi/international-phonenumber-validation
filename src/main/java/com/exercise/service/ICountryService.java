package com.exercise.service;

import com.exercise.models.Country;

import java.util.List;
import java.util.Optional;

/**
 * @author Abu Bizibu
 * @created
 * @project
 */
public interface ICountryService {

    Country insertCountry(Country country);

    Country updateCountry(Country country, String code);

    List<Country> getAllCountries();

    Optional<Country> findByName(String name);
}

package com.exercise.service.impl;

import com.exercise.models.Country;
import com.exercise.repositories.CountryRepository;
import com.exercise.service.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Abu Bizibu
 * @created
 * @project
 */
@Service
public class CountryService implements ICountryService {

    private final CountryRepository countryRepository;

    @Autowired
    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public Country insertCountry(Country country) {
         return this.countryRepository.save(country);
    }

    @Override
    public List<Country> getAllCountries() {
        return (List<Country>) this.countryRepository.findAll();
    }

    @Override
    public Optional<Country> findByName(String name) {
        return countryRepository.findByName(name);
    }

    @Override
    public Country updateCountry(Country country, String code) {
         Optional<Country> optionalCountry = countryRepository.findByCode(code);
         if (optionalCountry.isPresent()) {
             Country existingCountry = optionalCountry.get();
             existingCountry.setName(country.getName());
             existingCountry.setRegex(country.getRegex());
             return countryRepository.save(existingCountry);
         }
         throw new IllegalArgumentException("country with code: "  + country.getCode() + " missing");
    }
}

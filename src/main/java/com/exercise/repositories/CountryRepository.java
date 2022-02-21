package com.exercise.repositories;

import com.exercise.models.Country;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * @author Abu Bizibu
 * @created
 * @project
 */
public interface CountryRepository extends CrudRepository<Country, String> {

    Optional<Country> findByName(String name);

    Optional<Country> findByCode(String code);
}

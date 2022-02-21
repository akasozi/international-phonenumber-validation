package com.exercise.api;

import com.exercise.models.Country;
import com.exercise.models.Customer;
import com.exercise.service.ICountryService;
import com.exercise.service.ICustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Abu Bizibu
 * @created
 * @project
 */
@RestController
public class CountryController {

    private static final Logger log = LoggerFactory.getLogger(CountryController.class);

    private final ICountryService countryService;

    @Autowired
    public CountryController(ICountryService countryService) {
        this.countryService = countryService;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/api/v1/countries")
    public List<Country> getAllCountries() {
        return this.countryService.getAllCountries();
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/api/v1/countries")
    public void addCountry(@Valid @RequestBody Country country) {
       log.info("Request Received :: {}", country);
       if (countryService.insertCountry(country) == null)
          throw new RuntimeException(("Country not created!"));
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PutMapping("/api/v1/countries/{code}")
    public void updateCountry(@Valid @RequestBody Country country, @PathVariable String code) {
        log.info("Request Received :: {}", country);
        if (countryService.insertCountry(country) == null)
            throw new RuntimeException(("Country not created!"));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
